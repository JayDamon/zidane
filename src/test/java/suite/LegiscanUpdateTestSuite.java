package suite;

import com.protean.legislativetracker.zidane.service.update.BillUpdateServiceTest;
import com.protean.legislativetracker.zidane.service.update.PersonUpdateServiceImplTest;
import com.protean.legislativetracker.zidane.service.update.RollCallUpdateServiceTest;
import com.protean.legislativetracker.zidane.service.update.SessionUpdateServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.annotation.Profile;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BillUpdateServiceTest.class,
        PersonUpdateServiceImplTest.class,
        SessionUpdateServiceTest.class,
        RollCallUpdateServiceTest.class
})
public class LegiscanUpdateTestSuite {
}
