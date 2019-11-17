package com.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FileFormatRepository {

    private static Map<String, FileFormat> fileFormats;

    public Map<String, FileFormat> getFileFormats() {
        return initialize();
    }

    private Map<String, FileFormat> initialize() {

        if (fileFormats != null) return fileFormats;

        fileFormats = new HashMap<>();
        fileFormats.put("txt", new FileFormat("txt", new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}, 0));
        fileFormats.put("gif", new FileFormat("gif", new byte[]{(byte) 0x47, (byte) 0x49, (byte) 0x46, (byte) 0x38, (byte) 0x37, (byte) 0x61}, 0));
        fileFormats.put("gif", new FileFormat("gif", new byte[]{(byte) 0x47, (byte) 0x49, (byte) 0x46, (byte) 0x38, (byte) 0x39, (byte) 0x61}, 0));
        fileFormats.put("jpg", new FileFormat("jpg", new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xDB}, 0));
        fileFormats.put("txt", new FileFormat("jpg", new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xEE}, 0));

        return fileFormats;
    }

    public int findLongestPossibleNumberOfBytesToBeRead() {  // since file reading is quite expensive, there is no point to read more bytes than we can analyse
        FileFormat fileFormat = fileFormats.entrySet().stream().max(Comparator
                .comparingInt(a -> (a.getValue().getOffset() + a.getValue().getHexSignature().length))).get().getValue();
        return fileFormat.getOffset() + fileFormat.getHexSignature().length;
    }
}
