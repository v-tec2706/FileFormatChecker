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
        int firstIndex;
        int lastIndex;
        byte[] currentExtensionSignature;

        if (fileFormats.containsKey(expectedExtension)) {
            FileFormat expectedFileFormat = fileFormats.get(expectedExtension);
            hexSignature = fileReader.readBytesFromFile(fileName, 0, maxBytesToRead);

            firstIndex = expectedFileFormat.getOffset();
            lastIndex = firstIndex + expectedFileFormat.getHexSignature().length;
            currentExtensionSignature = Arrays.copyOfRange(hexSignature, firstIndex, lastIndex);

            if (Arrays.equals(currentExtensionSignature, expectedFileFormat.getHexSignature())) {
                return;
            } else {

                for (Map.Entry<String, FileFormat> fileFormatEntry : fileFormats.entrySet()) {
                    firstIndex = fileFormatEntry.getValue().getOffset();
                    lastIndex = firstIndex + fileFormatEntry.getValue().getHexSignature().length;

                    currentExtensionSignature = Arrays.copyOfRange(hexSignature, firstIndex, lastIndex);
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

