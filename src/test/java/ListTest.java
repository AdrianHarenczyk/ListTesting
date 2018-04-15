import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListTest {

    private static List list;
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final List<String> otherList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        System.out.println(Color.ANSI_BLUE + "----====STARTING TESTS====----\n");
        Collections.addAll(otherList,"Roman","Adam","Borys");


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
    //========================================
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
    public static void testAddingAtSpecificIndexThrowAnException() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        boolean result = false;
        Integer seven = 7;
        int nonExistingIndex = 1;
        // When
        try {
            list.add(nonExistingIndex,seven);
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert result :"Adding at non existing index does not throw an exception.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    // addAll
    public static void testAddingAnotherCollectionToEmptyCollectionReturnsTrue() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Set<String> anotherCollection = new HashSet<>();
        Collections.addAll(anotherCollection,"Roman","Adam","Borys");
        // When
        boolean result = list.addAll(anotherCollection);
        // Then
        assert result:"Adding another collection resulted in false.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfAddAllSustainsOrderOfTheList() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        list.addAll(otherList);
        boolean result = list.get(0).equals(otherList.get(0));
        // Then
        assert result:"Lists objects are not in the same places.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSizeOfNewListIsEqualToTheOriginal() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        list.addAll(otherList);
        boolean result = list.size() == otherList.size();
        // Then
        assert  result :"List sizes are not equal.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // addAll with index
    public static void testIfOtherIndexesShift() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        String secondElementBefore = (String) list.get(1);
        // When
        list.addAll(1,otherList);
        String secondElementAfter = (String) list.get(list.size()-2);
        boolean result = secondElementBefore.equals(secondElementAfter);
        // Then
        assert  result :"Objects doesn't shift.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testName2() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        boolean result = true;
        // Then
        assert  result :"Text";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testName3() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        boolean result = true;
        // Then
        assert  result :"Text";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================






}
