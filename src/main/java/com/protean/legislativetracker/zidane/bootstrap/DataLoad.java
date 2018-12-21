package com.protean.legislativetracker.zidane.bootstrap;

import com.protean.legislativetracker.yuna.bootstrap.LoadTestData;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Component
@Profile("test")
public class DataLoad implements ApplicationListener<ContextRefreshedEvent> {

    private DataSource dataSource;

    public DataLoad(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LoadTestData loadTestData = new LoadTestData(dataSource);
        loadTestData.loadInitialStartData();
        loadTestData.loadInitialSessions();
//        loadTestData.loadInitialBills();
    }
}
