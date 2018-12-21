package suite;

import com.protean.legislativetracker.zidane.service.retrieval.file.FileBillRetrievalServiceTest;
import com.protean.legislativetracker.zidane.service.retrieval.file.FilePersonRetrievalServiceTest;
import com.protean.legislativetracker.zidane.service.retrieval.file.FileRollCallRetrievalServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FileBillRetrievalServiceTest.class,
        FilePersonRetrievalServiceTest.class,
        FileRollCallRetrievalServiceTest.class
})
public class LegiscanFileRetrievalSuite {
}
