package com.protean.legislativetracker.zidane.service.retrieval.file;

import com.protean.legislativetracker.zidane.model.Person;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.HttpRequestServiceImpl;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import com.protean.legislativetracker.zidane.utilities.JsonFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePersonRetrievalService implements PersonRetrievalService {

    private static final Logger log = LoggerFactory.getLogger(FilePersonRetrievalService.class);

    private HttpRequestService httpRequestService;
    private FileRetrievalService fileRetrievalService;

    public FilePersonRetrievalService(String fileLocation) {
        fileRetrievalService = new FileRetrievalService(fileLocation, "/people");
        httpRequestService = new HttpRequestServiceImpl();
    }

    @Override
    public List<Person> getAllById(List<Long> ids) {
        log.info("Starting people retrieval for directory " +
                fileRetrievalService.getFullFileLocation("log"));
        List<Person> people = new ArrayList<>();
        for (Long l : ids) {
            String fileLocation =
                    fileRetrievalService.getFullFileLocation(
                            SessionFolder.SESSION_FOLDER.get(l.intValue()));
            for (File child : fileRetrievalService.getFilesInDirectory(fileLocation)) {
                String json = JsonFileLoader.readJsonFileAsString(child.getPath());
                people.add(
                        httpRequestService.getPojoFromJson(Person.class, json, "person")
                );
            }
        }
        return people;
    }
}
