package it.fabermatica.plurimix;

import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

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

    public void print() {
        try {
            PDDocument document = PDDocument.load(new File("./assets/file/test.pdf"));
            PrintService printer = getPrinter("HP075EF5");
            if (printer == null)
                return;

            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPageable(new PDFPageable(document));
            printJob.setPrintService(printer);
            printJob.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print();
    }
}
