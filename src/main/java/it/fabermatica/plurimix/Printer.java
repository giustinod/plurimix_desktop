package it.fabermatica.plurimix;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

public class Printer {
    private PrintService[] printers;

    Printer() {
        printers = PrintServiceLookup.lookupPrintServices(null, null);
    }

    public String[] getPrinterNames() {
        String[] printerNames = new String[this.printers.length];
        for (int i = 0; i < this.printers.length; i++)
            printerNames[i] = this.printers[i].getName();
        return printerNames;
    }

    private PrintService getPrinter(String name) {
        for (PrintService printer : this.printers)
            if (printer.getName().trim().equals(name))
                return printer;
        return null;
    }

    public void print(File inputFile) {
        try {
            PDDocument document = PDDocument.load(inputFile);
            PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
            printerAttributes.add(Chromaticity.COLOR);
            printerAttributes.add(new Copies(1));

            PageFormat pageFormat = new PageFormat();
            pageFormat.setOrientation(PageFormat.PORTRAIT);

            PDFPrintable printable = new PDFPrintable(document, Scaling.SCALE_TO_FIT);
            PrintService printer = getPrinter("HP075EF5");
            if (printer == null)
                return;

            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPrintable(printable, pageFormat);
            printJob.setPrintService(printer);
            printJob.print(printerAttributes); // questo fa partire la stampa
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
