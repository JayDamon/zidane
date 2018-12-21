package com.protean.legislativetracker.zidane.service.retrieval.file;

import com.protean.legislativetracker.zidane.model.RollCall;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileRollCallRetrievalService implements RollCallRetrievalService {

    private static final Logger log = LoggerFactory.getLogger(FileRollCallRetrievalService.class);

    private HttpRequestService httpRequestService;
    private FileRetrievalService fileRetrievalService;

    public FileRollCallRetrievalService(String fileLocation) {
        fileRetrievalService = new FileRetrievalService(fileLocation, "/vote");
        httpRequestService = new HttpRequestServiceImpl();
    }

    @Override
    public List<RollCall> getAllById(List<Long> ids) {
        log.info("Starting roll call retrieval for directory " +
                fileRetrievalService.getFullFileLocation("log"));
        List<RollCall> rollCalls = new ArrayList<>();
        try {
            for (Long i : ids) {
                String fileLocation =
                        fileRetrievalService.getFullFileLocation(
                                SessionFolder.SESSION_FOLDER.get(i.intValue())
                        );
                for (File child : fileRetrievalService.getFilesInDirectory(fileLocation)) {
                    String json = JsonFileLoader.readJsonFileAsString(child.getPath());
                    rollCalls.add(
                            httpRequestService.getPojoFromJson(RollCall.class, json, "roll_call")
                    );
                }
            }
        } catch (IllegalArgumentException e) {
            log.info("Could not read folder, continuing operation... Error: " + e);
        }
        return rollCalls;
    }
}
