package com.protean.legislativetracker.zidane.service.retrieval.file;

import java.util.HashMap;
import java.util.Map;

public class SessionFolder {
    public static final Map<Integer, String> SESSION_FOLDER = populateSessionMap();

    private static Map<Integer, String> populateSessionMap() {
        Map<Integer, String> folderLocations = new HashMap<>();
        folderLocations.put(1258, "2017-2018_128th_Legislature");
        folderLocations.put(1132, "2015-2016_127th_Legislature");
        folderLocations.put(1004, "2013-2014_126th_Legislature");
        folderLocations.put(81, "2011-2012_125th_Legislature");
        folderLocations.put(48, "2009-2010_124th_Legislature");
        return folderLocations;
    }

}
