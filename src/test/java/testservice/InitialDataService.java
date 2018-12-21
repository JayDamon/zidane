package testservice;

import com.protean.legislativetracker.yuna.bootstrap.LoadTestData;

import javax.sql.DataSource;

public class InitialDataService {
    private static InitialDataService instance = new InitialDataService();
    private boolean initialized = false;

    private InitialDataService() {
    }

    public static InitialDataService getInstance() {
        return instance;
    }

    public void initializeData(DataSource dataSource) {
        if (!initialized) {
            LoadTestData loadTestData = new LoadTestData(dataSource);
            loadTestData.loadInitialSessions();
            loadTestData.loadInitialBills();
            initialized = true;
        }
    }
}
