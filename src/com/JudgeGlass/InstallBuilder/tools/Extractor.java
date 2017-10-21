package com.JudgeGlass.InstallBuilder.tools;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

public class Extractor {
    public void unZipIt(String zipFileName, String outputFolder) {
        try {
            ZipFile zipFile = new ZipFile(zipFileName);

            zipFile.extractAll(outputFolder);
        } catch (ZipException e) {
            e.printStackTrace();
        }

    }
}
