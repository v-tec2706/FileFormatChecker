package com.company;

public class FileFormat {
    private String fileNameExtension;
    private byte[] hexSignature;
    private int offset;

    public FileFormat(String fileNameExtension, byte[] hexSignature, int offset) {
        this.fileNameExtension = fileNameExtension;
        this.hexSignature = hexSignature;
        this.offset = offset;
    }

    public String getFileNameExtension() {
        return fileNameExtension;
    }

    public byte[] getHexSignature() {
        return hexSignature;
    }

    public int getOffset() {
        return offset;
    }
}
