package org.app.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileProvider {

    private FileProvider() {};

    public static List<String> readLinesOfTxtFile(Path path) throws Exception {
        File file = new File(path.toString());

        FileProvider.checkFileValidity(file);

        FileProvider.checkTxtFile(path);
        List<String> data = Files.readAllLines(path);

        return data;
    }

    public static void checkTxtFile(Path path) throws Exception {
        int fromIndex = path.toString().lastIndexOf('.');
        String extension = path.toString().substring(fromIndex);

        if(!extension.equals(FileExtensions.TXT.getValue())) {
            throw new IOException(String.format("Extension provided: %s --- You must provide .txt file", extension));
        }

    }

    public static void checkFileValidity(File file) throws Exception {
        if(!file.exists()) {
            throw new IOException("File does not exist");
        }

        if(!file.canRead()) {
            throw new IOException("File is not readable");
        }
    }
}
