package com.company;

import com.company.exceptions.FileExtensionDifferentFromExpected;
import com.company.exceptions.FileExtensionNotKnown;

import java.util.Arrays;
import java.util.Map;

public class FormatChecker {

    public void validateFileFormat(String fileName) {

        FileReader fileReader = new FileReader();
        String expectedExtension = fileReader.extractFileExtension(fileName);
        FileFormatRepository fileFormatRepository = new FileFormatRepository();
        Map<String, FileFormat> fileFormats = fileFormatRepository.getFileFormats();
        int maxBytesToRead = fileFormatRepository.findLongestPossibleNumberOfBytesToBeRead();
        byte[] hexSignature;

        if (fileFormats.containsKey(expectedExtension)) {
            FileFormat expectedFileFormat = fileFormats.get(expectedExtension);
            hexSignature = fileReader.readBytesFromFile(fileName, expectedFileFormat.getOffset(), expectedFileFormat.getHexSignature().length);
            if (Arrays.equals(hexSignature, expectedFileFormat.getHexSignature())) {
                return;
            } else {
                hexSignature = fileReader.readBytesFromFile(fileName, 0, maxBytesToRead);
                int firstIndex;
                int lastIndex;
                for (Map.Entry<String, FileFormat> fileFormatEntry : fileFormats.entrySet()) {
                    firstIndex = fileFormatEntry.getValue().getOffset();
                    lastIndex = firstIndex + fileFormatEntry.getValue().getHexSignature().length;

                    byte[] currentExtensionSignature = Arrays.copyOfRange(hexSignature, firstIndex, lastIndex);
                    if (Arrays.equals(currentExtensionSignature, expectedFileFormat.getHexSignature())) {
                        throw new FileExtensionDifferentFromExpected(expectedExtension, fileFormatEntry.getKey());
                    }
                }
                throw new FileExtensionNotKnown(expectedExtension);
            }
        }
        throw new FileExtensionNotKnown(expectedExtension);
    }
}

