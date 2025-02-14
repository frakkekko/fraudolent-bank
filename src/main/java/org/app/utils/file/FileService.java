package org.app.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {

    private FileService() {};

    public static List<String> readLinesOfTxtFile(Path path) throws Exception {
        File file = new File(path.toString());

        FileService.checkFileValidity(file);
        FileService.checkTxtFile(path);

        List<String> data = Files.readAllLines(path)
                .stream().filter(line -> !line.isEmpty())
                .collect(Collectors.toList());

        return data;
    }

    public static void writeTxtOutputFile(Path path, Iterable<String> data) throws IOException {
        Files.write(path, data);
    }

    private static void checkTxtFile(Path path) throws Exception {
        int fromIndex = path.toString().lastIndexOf('.');
        String extension = path.toString().substring(fromIndex);

        if(!extension.equals(FileExtensions.TXT.getValue())) {
            throw new IOException(String.format("Extension provided: %s --- You must provide .txt file", extension));
        }

    }

    private static void checkFileValidity(File file) throws Exception {
        if(!file.exists()) {
            throw new IOException("File does not exist");
        }

        if(!file.canRead()) {
            throw new IOException("File is not readable");
        }
    }
}
