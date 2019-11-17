package com.company.exceptions;

public class FileExtensionDifferentFromExpected extends FileExtensionException {
    private String expectedFileExtension;
    private String actualFileExtension;

    public FileExtensionDifferentFromExpected(String expectedFileExtension, String actualFileExtension) {
        this.expectedFileExtension = expectedFileExtension;
        this.actualFileExtension = actualFileExtension;
    }

    public String getExpectedFileExtension() {
        return expectedFileExtension;
    }

    public String getActualFileExtension() {
        return actualFileExtension;
    }
}
