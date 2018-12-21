package suite;

import com.protean.legislativetracker.zidane.service.retrieval.legiscan.LegiscanBillRetrievalServiceTest;
import com.protean.legislativetracker.zidane.service.retrieval.legiscan.LegiscanPersonRetrievalServiceTest;
import com.protean.legislativetracker.zidane.service.retrieval.legiscan.LegiscanRollCallRetrievalServiceTest;
import com.protean.legislativetracker.zidane.service.retrieval.legiscan.LegiscanSessionRetrievalServiceTest;
import com.protean.legislativetracker.zidane.service.retrieval.legiscan.LegiscanStateRetrievalServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.annotation.Profile;

@Profile("test")
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LegiscanBillRetrievalServiceTest.class,
        LegiscanPersonRetrievalServiceTest.class,
        LegiscanSessionRetrievalServiceTest.class,
        LegiscanStateRetrievalServiceTest.class,
        LegiscanRollCallRetrievalServiceTest.class
})
public class LegiscanRetrievalSuite {
}
