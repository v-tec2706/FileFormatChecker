package com.company;

import com.company.exceptions.FileExtensionDifferentFromExpected;
import com.company.exceptions.FileExtensionNotKnown;
import com.company.exceptions.FileReadingError;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("File name should be passed as argument!");
            return;
        }

        FormatChecker formatChecker = new FormatChecker();
        try {
            formatChecker.validateFileFormat(args[0]);
        } catch (FileExtensionDifferentFromExpected e) {
            System.out.println("File name differs from expected. Should be: " + e.getExpectedFileExtension() +
                    ", but found: " + e.getActualFileExtension());
            return;
        } catch (FileExtensionNotKnown e) {
            System.out.println("File name differs from expected. Should be: " + e.getExpectedFileExtension() +
                    ", but actual extension was not recognized");
            return;
        } catch (FileReadingError ignore) {
            System.out.println("Error occurred while reading from file");
        }

        System.out.println("File format is as expected.");
    }
}
