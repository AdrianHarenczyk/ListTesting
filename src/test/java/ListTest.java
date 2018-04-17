import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
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
                //list.add(one);
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
    public static void testAddingWithIndexToEmptyListThrowsIndexOutOfBoundException() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        boolean result = false;
        // When
        try {
            list.addAll(1,otherList);
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert  result :"Index out of bound did not occur.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSecondIndexIsReplacedByFirstIndexOfAddedList() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        String secondElementBefore = (String) list.get(1);
        // When
        list.addAll(1,otherList);
        boolean result = !secondElementBefore.equals(list.get(1));
        // Then
        assert  result :"Second element is as it was.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // clear
    public static void testIfSizeAfterClearIsZero() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        // When
        list.clear();
        boolean result = list.size() == 0;
        // Then
        assert  result :"Size is not 0";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfGetRaiseAnError() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        // When
        boolean result = false;
        list.clear();
        try {
            list.get(1);
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert  result :"Get on list which was cleared doesn't raise exception.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // contains
    public static void testIfContainsReturnesTrueIfTheSameStringWhichIsInListIsPassed() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String sameStringWhichIsInList = "Roman";
        list.addAll(otherList);
        // When
        boolean result = list.contains(sameStringWhichIsInList);
        // Then
        assert  result :"List doesn't contain specified object.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsResultsInFalseWhenThereIsNoSuchObjectInList() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String otherObject = "Nowak";
        list.addAll(otherList);
        // When
        boolean result = !list.contains(otherObject);
        // Then
        assert  result :"Contains returns true, when it should not contain otherObject";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsReturnFalseWhenListIsEmpty() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Object object = new Object();
        // When
        boolean result = !list.contains(object);
        // Then
        assert  result :"Contains returns true when the list is empty.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // containsAll
    public static void testIfContainsAllReturnFalseWhenPassedFullListToEmptyList() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        boolean result = !list.contains(otherList);
        // Then
        assert  result :"Contains All returns true when there are no objects in the list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsAllReturnTrueIfThereAreAllElementsInThisListWhichAreInTheOther() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.containsAll(otherList);
        // Then
        assert  result :"ContainsAll returns false when all elements in lists are the same.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsAllReturnsTrueWhenGivenEmptyListAsArgument() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        List emptyList = new ArrayList();
        list.addAll(otherList);
        // When
        boolean result = list.containsAll(emptyList);
        // Then
        assert  result :"Contains all returned false when provided with empty list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // equals
    public static void testIfEqualsReturnsTrueWhenTwoExactlySameListsAreCompared() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.equals(list);
        // Then
        assert  result :"List is not equal to itself.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfTwoListsWithTheSameElementsAreEqual() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.equals(otherList);
        // Then
        assert  result :"Two lists are not equal";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // get
    public static void testIfGetWhenNoSuchIndexThrowsIndexOutOfBoundsException() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        boolean result = false;
        // When
        try {
            list.get(5);
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert  result :"Get did not throw any exception when given non existing index in the specified list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfGetToIndexWithTheSpecificObjectReturnesSameObjectAsSpecified() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String specificObject = "Adam";
        list.addAll(otherList);
        // When
        boolean result = list.get(1).equals(specificObject);
        // Then
        assert  result :"Get did not return the right object.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfWhenGetIndexZeroFromSizeOneListGetsThisElement() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String name = "Tomasz";
        list.add(name);
        // When
        boolean result = list.get(0).equals(name);
        // Then
        assert  result :"Get did not returned exactly the same object.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // hashCode
    public static void testIfExpectedHashCodeMatchesActual() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String adam = "Adam";
        int hashOfAdam = adam.hashCode();
        int suposedHashOfList = 31 + hashOfAdam;
        list.add(adam);
        // When
        boolean result = list.hashCode() == suposedHashOfList;
        // Then
        assert  result :"HashCode expected doesn't match actual.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfEmptyListHasHashCodeEqualOne() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        int expectedHash = 1;
        // When
        int actualHashCode = list.hashCode();
        boolean result = actualHashCode == expectedHash;
        // Then
        assert  result :"The expected hashcode doesn't match actual.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // indexOf
    public static void testIfIndexOfReturnsTwoWhenPassingBorys() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.indexOf("Borys") == 2;
        // Then
        assert  result :"Index is different then it should be.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfIndexOfReturnsMinusOneWhenPassingObjectWhichItDoesntHave() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String name = "Tomasz";
        // When
        boolean result = -1 == list.indexOf(name);
        // Then
        assert  result :"Value is different then minus one.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfListReturnsIndexOfFirstObjectOccurenceNotSecondOne() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String adam = "Adam";
        list.addAll(otherList);
        list.add(adam);
        // When
        boolean result = 1 == list.indexOf(adam);
        // Then
        assert  result :"IndexOf returned second or other index of object.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // isEmpty
    public static void testIfIsEmptyReturnsTrueWhileEmptyListPassed() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        boolean result = list.isEmpty();
        // Then
        assert  result :"Is empty returned false while used on empty list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfIsEmptyReturnsFalseWhenUsedOnListWithObjects() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.add(otherList);
        // When
        boolean result = !list.isEmpty();
        // Then
        assert  result :"isEmpty returned true while there are Objects in list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIsEmptyShouldReturnFalseWhenNullIsInTheList() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.add(null);
        // When
        boolean result = !list.isEmpty();
        // Then
        assert  result :"When null in the list, is empty returned true.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // iterator
    public static void testIfIteratorReturnedReturnsProperObject() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        Iterator iterator = list.iterator();
        // When
        boolean result = iterator.next() == list.get(0);
        // Then
        assert  result :"The objects returned by iterator.next() is different than the first object in list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIteratorOfEmptyListShouldThrowNoSuchElementException() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Iterator iterator = list.iterator();
        boolean result = false;
        // When
        try {
            Object object = iterator.next();
        } catch (NoSuchElementException e) {
            result = true;
        }
        // Then
        assert  result :"Next on empty list didn't throw en exception.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // lastIndexOf
    public static void testLastIndexOfShouldReturnFiveWhenSearchingForBorys() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        list.addAll(otherList);
        String borys = "Borys";
        // When
        boolean result = 5 == list.lastIndexOf(borys);
        // Then
        assert  result :"Last index is different then expected.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testLastIndexOfShouldReturnMinusOneWhenNoSuchObject() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String patryk = "Patryk";
        list.addAll(otherList);
        // When
        boolean result = -1 == list.lastIndexOf(patryk);
        // Then
        assert  result :"Result of last index of was different than -1";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testLastIndexOfShouldReturnMinusOneWhenListIsEmpty() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String prometeusz = "Prometeusz";
        // When
        boolean result = -1 == list.lastIndexOf(prometeusz);
        // Then
        assert  result :"Last Index of prometeusz should return -1 on empty list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // listIterator
    public static void testIfListIteratorReturnedReturnsProperObject() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        Iterator iterator = list.listIterator();
        // When
        boolean result = iterator.next() == list.get(0);
        // Then
        assert  result :"The objects returned by iterator.next() is different than the first object in list.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testListIteratorOfEmptyListShouldThrowNoSuchElementException() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Iterator iterator = list.listIterator();
        boolean result = false;
        // When
        try {
            Object object = iterator.next();
        } catch (NoSuchElementException e) {
            result = true;
        }
        // Then
        assert  result :"Next on empty list didn't throw en exception.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // listIterator with index
    public static void testIfListIteratorInserdedInTheMiddleHasPrevious() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        ListIterator iterator = list.listIterator(1);
        // When
        boolean result = iterator.hasPrevious();
        // Then
        assert result:"Iterator created with method listIterator with index has no previous.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfListIteratorInsertedInTheMiddleHasNext() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        ListIterator iterator = list.listIterator(1);
        // When
        boolean result = iterator.hasNext();
        // Then
        assert result:"List iterator has no next element.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfWhenEmptyListMethodThrowsIndexOutOfBoundsException() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        boolean result = false;
        // When
        try {
            ListIterator iterator = list.listIterator(1);
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert result:"Iterator did not throw any exceptions.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // remove
    public static void testIfRemoveReturnsIndexOutOfBoundExceptionForEmptyList() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        boolean result = false;
        // When
        try {
            list.remove(1);
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert result:"Method did not throw any exceptions.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfObjectWhichWasAddedEqualsObjectRemoved() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String borys = "Borys";
        list.add(borys);
        String otherBorys = (String) list.remove(0);
        // When
        boolean result = borys.equals(otherBorys);
        // Then
        assert result:"Object added does not equal object removed.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfOtherObjectIndexesShiftToLeftIfRemoveIsUsed() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        Collections.addAll(list,"Roman","Borys","Adam","Morty","Rick");
        String morty = (String)list.get(3);
        list.remove(2);
        // When
        boolean result = morty.equals(list.remove(2));

        // Then
        assert result:"Third index is not Morty after removing previous third index so it doesn't shift left.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // remove Object
    public static void testIfRemoveObjectReturnsTrueIfListContainedThatObject() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String morty = "Morty";
        list.add(morty);
        // When
        boolean result = list.remove(morty);

        // Then
        assert result:"Remove object returned false";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfRemoveObjectRemovesOnlyOneOccurrenceOfThatObject() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String morty = "Morty";
        list.add(morty);
        list.add(morty);
        // When
        list.remove(morty);
        boolean result = list.size() == 1;
        // Then
        assert result:"List size is other than one.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfRemovingObjectWhichIsNotInListReturnFalse() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        String morty = "Morty";
        // When
        boolean result = !list.remove(morty);
        // Then
        assert result:"List remove returned true.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // removeAll
    public static void testIfListSizeIsSixMinusThreeWhenRemoveAllWithThreeElementCollection() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        Collections.addAll(list,"Morty","Rick","Rumcajs");
        // When
        list.removeAll(otherList);
        boolean result = list.size() == 3;
        // Then
        assert result:"List size is other than three.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testRemoveAllReturnsFalseWhenThereAreNoElementsLikeThisInThisList() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        // When
        boolean result = !list.removeAll(otherList);

        // Then
        assert result:"RemoveAll returned true, which points that it changed something.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testRemoveAllReturnsTrueWhenRemovedElementsIsSucessfull() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.removeAll(otherList);

        // Then
        assert result:"Remove all did return false.";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // MethodName
    public static void testName1() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        boolean result = true;

        // Then
        assert result:"Text";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testName2() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        boolean result = true;

        // Then
        assert result:"Text";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testName3() {
        logger.log(Level.INFO,Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET);
        // Given

        // When
        boolean result = true;

        // Then
        assert result:"Text";
        logger.log(Level.INFO,Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================







}
