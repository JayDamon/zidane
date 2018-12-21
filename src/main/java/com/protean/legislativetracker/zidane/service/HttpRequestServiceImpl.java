package com.protean.legislativetracker.zidane.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.protean.legislativetracker.zidane.utilities.ArgumentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;

//TODO refactor, this does too much.  Json mapping and runs HttpRequests
@Service
public class HttpRequestServiceImpl implements HttpRequestService {

    private static Logger log = LoggerFactory.getLogger(HttpRequestServiceImpl.class);

    @Override
    public boolean statusOk(String json) {
        ArgumentValidator.validateArgument(json == null || json.isEmpty(), json + " is not a valid JSON string value", log);
        return getNodeFromJson("status", json).asText().equals("OK");
    }

    @Override
    public <T> T getPojoFromJson(Class<T> mappedClass, String json, String nodeName) {

        ArgumentValidator.validateArgument(mappedClass == null, "The mapped class must not be null", log);
        ArgumentValidator.validateArgument(json == null || json.isEmpty(), json + " is not a valid JSON string value", log);
        ArgumentValidator.validateArgument(nodeName == null || nodeName.isEmpty(), nodeName + "is not a valid node name value", log);

//        json.replaceAll("\"0000-00-00\"", "null");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        try {
            return mapper.treeToValue(
                    getNodeFromJson(nodeName, json), mappedClass);
        } catch (IOException e) {
            log.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public <T> List<T> getPojoListFromJson(Class<T> mappedClass, String json, String nodeName) {

        ArgumentValidator.validateArgument(mappedClass == null, "The mapped class must not be null", log);

        ArgumentValidator.validateArgument(json == null || json.isEmpty(), json + " is not a valid JSON string value", log);
        ArgumentValidator.validateArgument(nodeName == null || nodeName.isEmpty(),nodeName + " is not a valid nodeName value", log);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    getNodeFromJson(nodeName, json).toString(),
                    mapper.getTypeFactory().constructCollectionType(
                            List.class, mappedClass
                    ));
        } catch (IOException e) {
            log.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public String readResponse(HttpURLConnection con) {
        ArgumentValidator.validateArgument(con == null, "HttpURLConnection must not be null", log);
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } catch (IOException e) {
            log.debug(e.getMessage());
            return null;
        } finally {
            con.disconnect();
        }
    }

    private JsonNode getNodeFromJson(String node, String jsonString) {
        JsonNode jsonNode = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode fullNode = mapper.readTree(jsonString);
            jsonNode = fullNode.get(node);
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
        return jsonNode;
    }

}
