package com.viasolutions.directprint.controller;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;

import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class PrinterController {

    @GetMapping("api/impressoras")
    public HashSet<String> getImpressoras(){
        HashSet<String> printers = new HashSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] service = PrintServiceLookup.lookupPrintServices(flavor, aset);
        for (PrintService printService : service) {
            printers.add(printService.getName());
        }
        return printers;
    }

    @PostMapping("api/imprimir")
    public String imprimir(@RequestBody byte[] bytes) throws IOException, PrinterException {
        ByteArrayInputStream pdf = new ByteArrayInputStream(bytes);
        PDDocument pdDocument = Loader.loadPDF(bytes);
        PDFPageable pageable = new PDFPageable(pdDocument);
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(pageable);
        job.print();
        return "";
    }
}