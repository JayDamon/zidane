package com.protean.legislativetracker.zidane.service.retrieval.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileRetrievalService {

    private static final Logger log = LoggerFactory.getLogger(FileRetrievalService.class);

    private String fileLocation;
    private String specificLocation;

    public FileRetrievalService(String fileLocation, String specificLocation) {
        this.fileLocation = fileLocation;
        this.specificLocation = specificLocation;
    }

    public String getFullFileLocation(String folderName) {
        return fileLocation + "/" + folderName + specificLocation;
    }

    List<File> getFilesInDirectory(String directory) {
        File dir = new File(directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            return Arrays.asList(directoryListing);
        } else {
            String msg = directory + " is not a readable directory";
            log.info(msg);
            throw new IllegalArgumentException(msg);
        }
    }
}