import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class TestSystem<T> {

    private List list;
    private Logger logger;
    private final List<String> otherList;
    private ReentrantLock lock;
    private final String STARTING_MESSAGE = "\n"+Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET;
    private final String PASS_MESSAGE = Color.ANSI_GREEN + "PASSED" + Color.ANSI_RESET + "\n";

    public TestSystem () {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        otherList = new ArrayList<>();
        Collections.addAll(otherList,"Roman","Adam","Borys");
        lock = new ReentrantLock();
    }

    public void test(boolean result,Collection otherCollection, T... variables) {
        logger.info(STARTING_MESSAGE);
        // Given
        Integer one = 1;
        // When
        //list.add(one);
        // Then
        assert result : "Adding to the list was not successful";
        logger.info(PASS_MESSAGE);
    }
}
