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

    // Add
    public static void testAddingWhenObjectTypeIsPassed() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Integer one = 1;
        // When
        boolean result = list.add(one);
        // Then
        assert result :"Adding to the list was not successful";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testAddingNullWhenIntegerGenericType() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Integer integer = null;
        // When
        boolean result = list.add(integer);
        // Then
        assert result: Color.ANSI_RED + "Adding to the list was not successful";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfFirstElementInListIsTheSameAsOnlyOneAddedToIt() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Integer five = 5;
        // When
        list.add(five);
        Integer number = (Integer) list.get(0);
        // Then
        assert five.equals(number) :"Passed number doesn't equals requested number.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // Add with index
    public static void testAddingAtSpecificIndexAndCheckIfEquals() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Integer five = 5;
        Integer one = 1;
        // When
        list.add(one);
        list.add(0,five);
        Integer number = (Integer)list.get(0);
        // Then
        assert number.equals(five):"Number doesn't equals inserted number.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfAddShiftNextElementToNextPosition() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Integer six = 6;
        Integer ten = 10;
        list.add(six);
        // When
        list.add(0,ten);
        Integer number = (Integer) list.get(1);

        // Then
        assert number.equals(six):"Number does not shift to the right.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }





}
