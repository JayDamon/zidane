package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.annotation.Profile;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationTestSuite.class,
        ServiceTestSuite.class,
        LegiscanSuite.class,
        LegiscanFileRetrievalSuite.class,
        LegiscanRetrievalSuite.class,
        LegiscanUpdateTestSuite.class
})
public class UnitTestSuite {
}
