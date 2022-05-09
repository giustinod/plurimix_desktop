package it.fabermatica.plurimix;

import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

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
        // DocFlavor flavor = DocFlavor.BYTE_ARRAY.PDF;
        PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();
        printAttributes.add(MediaSizeName.ISO_A4);
        PrintService printer = this.getPrinter("HP075EF5");

        if (printer == null)
            return;

        DocPrintJob printJob = printer.createPrintJob();

        try {
            FileInputStream inputStream = new FileInputStream("./assets/file/test.pdf");
            Doc pdfFile = new SimpleDoc(inputStream, DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
            printJob.print(pdfFile, printAttributes);
            // System.out.println("print");
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print();
    }
}
