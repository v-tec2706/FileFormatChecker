package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

    public byte[] readBytesFromFile(String filePath, int offset, int n) {

        byte[] readBytes = new byte[n];

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            fileInputStream.read(readBytes, offset, n);
            // TODO check number of read bytes and raise exception if 0
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readBytes;
    }
}
