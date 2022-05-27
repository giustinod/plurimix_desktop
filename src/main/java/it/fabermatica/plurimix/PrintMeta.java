package it.fabermatica.plurimix;

import com.google.gson.annotations.SerializedName;

public class PrintMeta {
    Orientation paperOrientation; // P (Portrait), L (Landscape)
    PaperFormat paperFormat; // A4, A5
    int numberCopies;

    String fileName;
    String file;
}

enum PaperFormat {
    A4, A5
}

enum Orientation {
    @SerializedName("P")
    PORTRAIT(0),
    @SerializedName("L")
    LANDSCAPE(1);

    private final int value;

    public int getValue() {
        return value;
    }

    private Orientation(int value) {
        this.value = value;
    }
}