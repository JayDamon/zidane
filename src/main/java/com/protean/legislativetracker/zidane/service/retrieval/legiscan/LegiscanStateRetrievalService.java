package com.protean.legislativetracker.zidane.service.retrieval.legiscan;

import com.protean.legislativetracker.zidane.legiscan.LegiscanHttpUri;
import com.protean.legislativetracker.zidane.legiscan.LegiscanOperation;
import com.protean.legislativetracker.zidane.service.HttpRequestService;
import com.protean.legislativetracker.zidane.service.LegiscanUpdateService;
import com.protean.legislativetracker.zidane.service.retrieval.StateRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegiscanStateRetrievalService implements StateRetrievalService {

    private Logger log = LoggerFactory.getLogger(LegiscanUpdateService.class);

    private HttpRequestService httpRequestService;

    public LegiscanStateRetrievalService(HttpRequestService httpRequestService) {
        this.httpRequestService = httpRequestService;
    }

    @Override
    public List<String> getAll() {

        LegiscanHttpUri legiscanHttpUri = new LegiscanHttpUri.LegiscanHttpUriBuilder(
                LegiscanOperation.GET_STATE_LIST).build();

        log.info("Starting available state retrieval from legiscan");

        return httpRequestService.getPojoListFromJson(
                String.class,
                httpRequestService.readResponse(legiscanHttpUri.getConnection()), "states");
    }
}
