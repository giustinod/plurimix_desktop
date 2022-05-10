package it.fabermatica.plurimix;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

public class FileDecoder {
    File file;

    FileDecoder(String name, String fileString) {
        file = new File("assets/file", name);
        try {
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);
            byte[] decodedByte = Base64.getDecoder().decode(fileString);
            stream.write(decodedByte);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }
}
