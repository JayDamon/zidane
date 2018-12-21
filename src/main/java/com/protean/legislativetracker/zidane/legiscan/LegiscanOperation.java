package com.protean.legislativetracker.zidane.legiscan;

public enum LegiscanOperation {
    GET_STATE_LIST("getStateList"), GET_SESSION_LIST("getSessionList"), GET_MASTER_LIST("getMasterList"),
    GET_BILL("getBill"), GET_BILL_TEXT("getBillText"), GET_AMENDMENT("getAmendment"), GET_SUPPLEMENT("getSupplement"),
    GET_ROLL_CALL("getRollCall"), GET_PERSON("getPerson"), SEARCH("search"), SEARCH_RAW("searchRaw");

    private final String value;

    LegiscanOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
