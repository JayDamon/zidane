package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.protean.legislativetracker.zidane.legiscan.LegiscanHttpUri;
import com.protean.legislativetracker.zidane.legiscan.LegiscanOperation;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class LegiscanRetrievalService<T> {

    private Logger log = LoggerFactory.getLogger(LegiscanRetrievalService.class);

    private Class<T> mappedClass;
    HttpRequestService httpRequestService;

    public LegiscanRetrievalService(Class<T> mappedClass, HttpRequestService httpRequestService) {
        this.mappedClass = mappedClass;
        this.httpRequestService = httpRequestService;
    }

    public T getItem(LegiscanOperation operation, String nodeName, String parameterName,
                     String parameter) {
        LegiscanHttpUri.LegiscanHttpUriBuilder legiscanHttpUriBuilder =
                new LegiscanHttpUri.LegiscanHttpUriBuilder(operation, parameterName, parameter);
        String json = httpRequestService.readResponse(legiscanHttpUriBuilder.build().getConnection());
        return httpRequestService.getPojoFromJson(mappedClass, json, nodeName);
    }

    public List<T> getList(LegiscanOperation operation, String nodeName,
                           Map<String, String> parameters) {
        LegiscanHttpUri.LegiscanHttpUriBuilder legiscanHttpUri =
                new LegiscanHttpUri.LegiscanHttpUriBuilder(operation);
        parameters.forEach(legiscanHttpUri::addParameter);
        String json = httpRequestService.readResponse(legiscanHttpUri.build().getConnection());
        return httpRequestService.getPojoListFromJson(mappedClass, json, nodeName);
    }

    public List<T> getList(LegiscanOperation operation, String nodeName, String parameterName,
                           String parameter) {
        LegiscanHttpUri.LegiscanHttpUriBuilder legiscanHttpUri =
                new LegiscanHttpUri.LegiscanHttpUriBuilder(operation, parameterName, parameter);
        String json = httpRequestService.readResponse(legiscanHttpUri.build().getConnection());
        return httpRequestService.getPojoListFromJson(mappedClass, json, nodeName);
    }

    public List<T> getNestedList(LegiscanOperation operation, String nodeName, String parameterName,
                                 String parameter, List<String> nodes) {
        LegiscanHttpUri.LegiscanHttpUriBuilder legiscanHttpUri =
                new LegiscanHttpUri.LegiscanHttpUriBuilder(operation, parameterName, parameter);
        String json = httpRequestService.readResponse(legiscanHttpUri.build().getConnection());
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode nestedJson = mapper.readTree(json);
            for (String s : nodes) {
                nestedJson = nestedJson.get(s);
            }
            return httpRequestService.getPojoListFromJson(mappedClass, nestedJson.toString(), nodeName);
        } catch (IOException e) {
            String msg = "Unable to find node from json: " + json;
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }

    public List<T> getList(LegiscanOperation operation, String nodeName) {
        LegiscanHttpUri.LegiscanHttpUriBuilder legiscanHttpUri =
                new LegiscanHttpUri.LegiscanHttpUriBuilder(operation);
        String json = httpRequestService.readResponse(legiscanHttpUri.build().getConnection());
        return httpRequestService.getPojoListFromJson(mappedClass, json, nodeName);
    }

}
