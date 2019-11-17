package com.company.exceptions;

public class FileExtensionNotKnown extends FileExtensionException {
    private String expectedFileExtension;

    public FileExtensionNotKnown(String expectedFileExtension) {
        this.expectedFileExtension = expectedFileExtension;
    }

    public String getExpectedFileExtension() {
        return expectedFileExtension;
    }
}
