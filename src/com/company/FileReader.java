package com.company;

import com.company.exceptions.FileReadingError;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public byte[] readBytesFromFile(String filePath, int offset, int n) {

        byte[] readBytes = new byte[n];

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            if (fileInputStream.read(readBytes, offset, n) < 1) {
                throw new FileReadingError();
            }
        } catch (IOException ignored) {
            throw new FileReadingError();
        }

        return readBytes;
    }

    public String extractFileExtension(String fileName) {
        try {
            String[] path = fileName.split("\\."); // in case if file name is like follow './file.txt' or '/path/file.txt'
            if (path.length == 2) {
                return path[1];
            }
            if (path.length == 3) {
                return path[2];
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
