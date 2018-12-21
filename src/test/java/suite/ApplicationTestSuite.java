package suite;

import com.protean.legislativetracker.zidane.TempBillServiceTest;
import com.protean.legislativetracker.zidane.ZidaneApplicationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TempBillServiceTest.class,
        ZidaneApplicationTests.class
})
public class ApplicationTestSuite {
}
