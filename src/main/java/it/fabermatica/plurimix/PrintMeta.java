package it.fabermatica.plurimix;

public class PrintMeta {
    Orientation paperOrientation; // O, V
    PaperFormat paperFormat; // A4, A5
    int numberCopies;

    String fileName;
    String file;
}

enum Orientation {
    HORIZONTAL, VERTICAL
}

enum PaperFormat {
    A4, A5
}
