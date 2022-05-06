package it.fabermatica.plurimix;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

public class FileDecoder {
    FileDecoder(String name, String fileString) {
        File pdfFile = new File("assets/file", name);
        try {
            pdfFile.createNewFile();
            FileOutputStream stream = new FileOutputStream(pdfFile);
            byte[] decodedByte = Base64.getDecoder().decode(fileString);
            stream.write(decodedByte);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
