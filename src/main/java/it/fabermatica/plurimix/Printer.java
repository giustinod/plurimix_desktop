package it.fabermatica.plurimix;

import javax.print.*;

public class Printer {
    public static String[] getPrinters() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        String[] printers = new String[printServices.length];
        for (int i = 0; i < printServices.length; i++)
            printers[i] = printServices[i].getName();
        return printers;
    }
}
