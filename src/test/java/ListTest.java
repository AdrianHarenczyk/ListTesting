import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListTest {

    private static List list;
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws Exception{
        System.out.println(Color.ANSI_BLUE + "----====STARTING TESTS====----\n");

        for (Method name : ListTest.class.getDeclaredMethods()) {
            if (name.getName().startsWith("test")) {
                setListToArrayList();
                name.invoke(null);
                setListToLinkedList();
                name.invoke(null);
            }
        }

    }
    private static void setListToArrayList() {
        list = new ArrayList<>();
    }
    private static void setListToLinkedList() {
        list = new LinkedList<>();
    }
    private static void startingMessage() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
    }
    private static void closingMessage() {
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }

    // Method add
    public static void testAddingWhenObjectTypeIsPassed() {
        startingMessage();
        // Given
        Integer one = 1;
        // When
        boolean result = list.add(one);
        // Then
        assert result :"Adding to the list was not successful";
        closingMessage();
    }
    public static void testAddingNullWhenIntegerGenericType() {
        startingMessage();
        // Given
        Integer integer = null;
        // When
        boolean result = list.add(integer);
        // Then
        assert result: Color.ANSI_RED + "Adding to the list was not successful";
        closingMessage();
    }
    public static void testIfFirstElementInListIsTheSameAsOnlyOneAddedToIt() {
        startingMessage();
        // Given
        Integer five = 5;
        // When
        list.add(five);
        Integer number = (Integer) list.get(0);
        // Then
        assert five.equals(number) :"Passed number doesn't equals requested number.";
        closingMessage();
    }
    //========================================


    public static void testToArrayThrowsExceptionWhenNullArgumentIsPassed() {
        startingMessage();
        boolean result = false;
        try {
            Object[] array = list.toArray(null);
        } catch (NullPointerException e) {
            result = true;
        }
        assert result: Color.ANSI_RED + "Adding to the list was not successful";
        closingMessage();
    }





}
