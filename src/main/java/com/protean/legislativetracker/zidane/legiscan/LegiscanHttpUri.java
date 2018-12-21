package com.protean.legislativetracker.zidane.legiscan;

import com.protean.legislativetracker.zidane.utilities.PropertiesFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// TODO this needs to change so that it doesn't use the PropertiesFile, it must let spring autowire properties
public class LegiscanHttpUri {

    private static final Logger log = LoggerFactory.getLogger(LegiscanHttpUri.class);

    private HttpURLConnection connection;

    public HttpURLConnection getConnection() {
        return connection;
    }

    public static class LegiscanHttpUriBuilder {

        private String url;

        private String key;

        private Map<String, String> parameters;
        private LegiscanOperation operation;
        private String firstOpKey;
        private String firstOpVal;

        public LegiscanHttpUriBuilder(LegiscanOperation operation) {
            if (operation.equals(LegiscanOperation.GET_STATE_LIST)
                    || operation.equals(LegiscanOperation.GET_SESSION_LIST)) {
                setUpParams(operation);
            } else {
                throw new IllegalArgumentException(operation +
                        ": must be accompanied by an initial set of parameters.");
            }
        }

        public LegiscanHttpUriBuilder(LegiscanOperation operation, String firstOpKey, String firstOpVal) {
            if (operation.equals(LegiscanOperation.GET_STATE_LIST)) {
                setUpParams(operation);
            } else {
                setUpParams(operation, firstOpKey, firstOpVal);
            }
        }

        private void setUpParams(LegiscanOperation operation, String firstOpKey, String firstOpVal) {
            setBaseValues(operation);
            this.firstOpKey = firstOpKey;
            this.firstOpVal = firstOpVal;
        }

        private void setUpParams(LegiscanOperation operation) {
            setBaseValues(operation);
        }

        private void setBaseValues(LegiscanOperation operation) {
            PropertiesFile propertiesFile = new PropertiesFile();
            this.url = propertiesFile.getUrl();
            this.key = propertiesFile.getKey();
            this.operation = operation;
            this.parameters = new HashMap<>();
        }

        public LegiscanHttpUriBuilder addParameter(String key, String value) {
            parameters.put(key, value);
            return this;
        }

        public LegiscanHttpUri build() {
            try {
                StringBuilder stringBuilder = appendInitialValues(
                        new StringBuilder(url)
                );
                if (parameters.size() > 0) {
                    stringBuilder.append(ParameterStringBuilder.getParamsString(parameters));
                }
                return new LegiscanHttpUri(createHttpRequest(stringBuilder.toString()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }

        private StringBuilder appendInitialValues(StringBuilder stringBuilder) throws UnsupportedEncodingException {
            stringBuilder.append("/?")
                    .append(ParameterStringBuilder.addItem("key", key))
                    .append("&")
                    .append(ParameterStringBuilder.addItem("op", operation.getValue()));

            if (firstOpVal != null && firstOpKey != null) {
                stringBuilder.append("&")
                        .append(firstOpKey)
                        .append("=")
                        .append(firstOpVal);
            }

            return stringBuilder;

        }

        private HttpURLConnection createHttpRequest(String urlString) {
            try {
                URL url = new URL(urlString);
                log.debug("Opening connection to URL: " + urlString);
                return (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private LegiscanHttpUri(HttpURLConnection connection) {
        this.connection = connection;
    }

}
