package suite;

import com.protean.legislativetracker.zidane.legiscan.BillModelMapperTest;
import com.protean.legislativetracker.zidane.legiscan.LegiscanHttpUriTest;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapperListTest;
import com.protean.legislativetracker.zidane.legiscan.LegiscanModelMapperSingleItemTest;
import com.protean.legislativetracker.zidane.legiscan.PersonModelMapperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BillModelMapperTest.class,
        LegiscanHttpUriTest.class,
        LegiscanModelMapperListTest.class,
        LegiscanModelMapperSingleItemTest.class,
        PersonModelMapperTest.class
})
public class LegiscanSuite {
}
