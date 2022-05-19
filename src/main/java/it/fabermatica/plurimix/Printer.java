package it.fabermatica.plurimix;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

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

    public static void main(String[] args) throws IOException, PrinterException {
        File newFile = new File("assets/file", "Cartellini.pdf");
        Printer printer = new Printer();
        printer.print(newFile);
    }

    public void print(File inputFile) throws IOException, PrinterException {
        // PrintService printer = this.getPrinter("Microsoft Print to PDF");
        PrintService printer = this.getPrinter("PDF24");
        PDDocument document = PDDocument.load(inputFile);

        // proprietà di stampa
        PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
        printerAttributes.add(Chromaticity.COLOR);
        printerAttributes.add(new Copies(3));

        // foglio senza margini aggiunti
        Paper paper = PrinterJob.getPrinterJob().defaultPage().getPaper();
        paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());

        // formato di stampa
        PageFormat pageFormat = new PageFormat();
        pageFormat.setPaper(paper);
        pageFormat.setOrientation(PageFormat.PORTRAIT);

        // applico il formato al pdf
        Book book = new Book();
        book.append(new PDFPrintable(document, Scaling.SCALE_TO_FIT), pageFormat,
                document.getNumberOfPages());

        // creo la coda di stampa
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintService(printer);
        job.setPageable(book);
        job.print(printerAttributes);
    }
}
