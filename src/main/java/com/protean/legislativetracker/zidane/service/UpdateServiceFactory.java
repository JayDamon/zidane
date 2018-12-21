package com.protean.legislativetracker.zidane.service;

import com.protean.legislativetracker.zidane.service.retrieval.RetrievalType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

@Component
public class UpdateServiceFactory {

    private Logger log = LoggerFactory.getLogger(UpdateServiceFactory.class);

    private UpdateService legiscanUpdateService;
    private UpdateService fileUpdateService;

    public UpdateServiceFactory(LegiscanUpdateService legiscanUpdateService,
                                FileUpdateService fileUpdateService) {
        this.legiscanUpdateService = legiscanUpdateService;
        this.fileUpdateService = fileUpdateService;
    }

    public UpdateService get(RetrievalType type) {
        switch (type) {
            case LEGISCAN:
                return legiscanUpdateService;
            case FILE:
                return fileUpdateService;
            default:
                String msg = "Update Service Bean of type <"
                        + type + "> does not exist or has not been setup in this factor";
                log.error(msg);
                throw new NoSuchBeanDefinitionException(msg);
        }
    }
}
