package com.protean.legislativetracker.zidane.service;

import java.net.HttpURLConnection;
import java.util.List;

public interface HttpRequestService {

    boolean statusOk(String json);

    <T> T getPojoFromJson(Class<T> mappedClass, String json, String nodeName);

    <T> List<T> getPojoListFromJson(Class<T> mappedClass, String json, String nodeName);

    String readResponse(HttpURLConnection con);

}
