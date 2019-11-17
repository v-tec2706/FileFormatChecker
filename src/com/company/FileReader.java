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
            return fileName.split(".")[1];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
