package com.protean.legislativetracker.zidane.legiscan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.StringJoiner;

class ParameterStringBuilder {
    static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException { //TODO add test
        StringJoiner result = new StringJoiner("&","&", "&");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.add(addItem(entry.getKey(), entry.getValue()));
        }
        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, result.length() - 1) : resultString;
    }

    static String addItem(String parameter, String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(parameter, "UTF-8") +
                "=" +
                URLEncoder.encode(value, "UTF-8");
    }
}
