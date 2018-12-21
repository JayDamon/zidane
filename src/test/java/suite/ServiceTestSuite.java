package suite;

import com.protean.legislativetracker.zidane.service.FileUpdateServiceTest;
import com.protean.legislativetracker.zidane.service.LegiscanHttpRequestServiceTest;
import com.protean.legislativetracker.zidane.service.LegiscanServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LegiscanHttpRequestServiceTest.class,
        LegiscanServiceTest.class
})
public class ServiceTestSuite {
}
