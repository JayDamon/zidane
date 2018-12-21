package com.protean.legislativetracker.zidane.config;

import com.protean.legislativetracker.yuna.service.BillService;
import com.protean.legislativetracker.yuna.service.PersonService;
import com.protean.legislativetracker.yuna.service.RollCallService;
import com.protean.legislativetracker.zidane.model.Bill;
import com.protean.legislativetracker.zidane.service.FileUpdateService;
import com.protean.legislativetracker.zidane.service.LegiscanUpdateService;
import com.protean.legislativetracker.zidane.service.UpdateService;
import com.protean.legislativetracker.zidane.service.retrieval.BillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.PersonRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.RollCallRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FileBillRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FilePersonRetrievalService;
import com.protean.legislativetracker.zidane.service.retrieval.file.FileRollCallRetrievalService;
import com.protean.legislativetracker.zidane.service.update.AsyncService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.protean.security.auron",
        "com.protean.legislativetracker.yuna",
        "com.protean.legislativetracker.zidane"})
public class ApplicationConfig {

    @Value("${legiscan.data.directory}")
    private String legiscanDataDirectory;

    @Bean(name = "zidaneBillAsync")
    public AsyncService<Bill> zidaneBillAsync() {
        return new AsyncService<>();
    }

    @Bean(name = "yunaBillAsync")
    public AsyncService<com.protean.legislativetracker.yuna.model.Bill> yunaBillAsync() {
        return new AsyncService<>();
    }

    @Bean(name = "fileBillRetrievalService")
    public BillRetrievalService fileBillRetrievalService() {
        return new FileBillRetrievalService(zidaneBillAsync(), legiscanDataDirectory);
    }

    @Bean(name = "filePersonRetrievalService")
    public PersonRetrievalService filePersonRetrievalService() {
        return new FilePersonRetrievalService(legiscanDataDirectory);
    }

    @Bean(name = "fileRollCallRetrievalService")
    public RollCallRetrievalService fileRollCallRetrievalService() {
        return new FileRollCallRetrievalService(legiscanDataDirectory);
    }
}
