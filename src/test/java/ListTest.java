import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ListTest {

    private static List list;
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final List<String> otherList = new ArrayList<>();
    private static ReentrantLock lock = new ReentrantLock();
    private static final String STARTING_MESSAGE = "\n"+Color.ANSI_CYAN + "STARTING." + Color.ANSI_RESET;
    private static final String PASS_MESSAGE = Color.ANSI_GREEN + "PASSED" + Color.ANSI_RESET + "\n";

    public static void main(String[] args) throws Exception {
        System.out.println(Color.ANSI_BLUE + "----====STARTING TESTS====----\n");
        Collections.addAll(otherList,"Roman","Adam","Borys");
        ExecutorService es = Executors.newFixedThreadPool(8);

        for (Method name : ListTest.class.getDeclaredMethods()) {
            if (name.getName().startsWith("test")) {
                lock.lock();
                setListToArrayList();
                es.submit(() -> name.invoke(null));
                setListToLinkedList();
                es.submit(() -> name.invoke(null));
                lock.unlock();
            }
        }
        es.shutdown();


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
        logger.info(STARTING_MESSAGE);
        // Given
        Integer one = 1;
        // When
        boolean result = list.add(one);
        //list.add(one);
        // Then
        assert result : "Adding to the list was not successful";
        logger.info(PASS_MESSAGE);
    }
    public static void testAddingNullWhenIntegerGenericType() {
        logger.info(STARTING_MESSAGE);
        // Given
        Integer integer = null;
        // When
        boolean result = list.add(integer);
        // Then
        assert result: Color.ANSI_RED + "Adding to the list was not successful";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfFirstElementInListIsTheSameAsOnlyOneAddedToIt() {
        logger.info(STARTING_MESSAGE);
        // Given
        Integer five = 5;
        // When
        list.add(five);
        Integer number = (Integer) list.get(0);
        // Then
        assert five.equals(number) :"Passed number doesn't equals requested number.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // Add with index
    public static void testAddingAtSpecificIndexAndCheckIfEquals() {
        logger.info(STARTING_MESSAGE);
        // Given
        Integer five = 5;
        Integer one = 1;
        // When
        list.add(one);
        list.add(0,five);
        Integer number = (Integer)list.get(0);
        // Then
        assert number.equals(five):"Number doesn't equals inserted number.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfAddShiftNextElementToNextPosition() {
        logger.info(STARTING_MESSAGE);
        // Given
        Integer six = 6;
        Integer ten = 10;
        list.add(six);
        // When
        list.add(0,ten);
        Integer number = (Integer) list.get(1);

        // Then
        assert number.equals(six):"Number does not shift to the right.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testAddingAtSpecificIndexThrowAnException() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    // addAll
    public static void testAddingAnotherCollectionToEmptyCollectionReturnsTrue() {
        logger.info(STARTING_MESSAGE);
        // Given
        Set<String> anotherCollection = new HashSet<>();
        Collections.addAll(anotherCollection, "Roman", "Adam", "Borys");
        // When
        boolean result = list.addAll(anotherCollection);
        // Then
        assert result : "Adding another collection resulted in false.";
        logger.info(PASS_MESSAGE);
    }
    public static void testIfAddAllSustainsOrderOfTheList() {
        logger.info(STARTING_MESSAGE);
        // Given

        // When
        list.addAll(otherList);
        boolean result = list.get(0).equals(otherList.get(0));
        // Then
        assert result:"Lists objects are not in the same places.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSizeOfNewListIsEqualToTheOriginal() {
        logger.info(STARTING_MESSAGE);
        // Given

        // When
        list.addAll(otherList);
        boolean result = list.size() == otherList.size();
        // Then
        assert  result :"List sizes are not equal.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // addAll with index
    public static void testIfOtherIndexesShift() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        String secondElementBefore = (String) list.get(1);
        // When
        list.addAll(1,otherList);
        String secondElementAfter = (String) list.get(list.size()-2);
        boolean result = secondElementBefore.equals(secondElementAfter);
        // Then
        assert  result :"Objects doesn't shift.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testAddingWithIndexToEmptyListThrowsIndexOutOfBoundException() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSecondIndexIsReplacedByFirstIndexOfAddedList() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        String secondElementBefore = (String) list.get(1);
        // When
        list.addAll(1,otherList);
        boolean result = !secondElementBefore.equals(list.get(1));
        // Then
        assert  result :"Second element is as it was.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // clear
    public static void testIfSizeAfterClearIsZero() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        list.clear();
        boolean result = list.size() == 0;
        // Then
        assert  result :"Size is not 0";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfGetRaiseAnError() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // contains
    public static void testIfContainsReturnesTrueIfTheSameStringWhichIsInListIsPassed() {
        logger.info(STARTING_MESSAGE);
        // Given
        String sameStringWhichIsInList = "Roman";
        list.addAll(otherList);
        // When
        boolean result = list.contains(sameStringWhichIsInList);
        // Then
        assert  result :"List doesn't contain specified object.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsResultsInFalseWhenThereIsNoSuchObjectInList() {
        logger.info(STARTING_MESSAGE);
        // Given
        String otherObject = "Nowak";
        list.addAll(otherList);
        // When
        boolean result = !list.contains(otherObject);
        // Then
        assert  result :"Contains returns true, when it should not contain otherObject";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsReturnFalseWhenListIsEmpty() {
        logger.info(STARTING_MESSAGE);
        // Given
        Object object = new Object();
        // When
        boolean result = !list.contains(object);
        // Then
        assert  result :"Contains returns true when the list is empty.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // containsAll
    public static void testIfContainsAllReturnFalseWhenPassedFullListToEmptyList() {
        logger.info(STARTING_MESSAGE);
        // Given

        // When
        boolean result = !list.contains(otherList);
        // Then
        assert  result :"Contains All returns true when there are no objects in the list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsAllReturnTrueIfThereAreAllElementsInThisListWhichAreInTheOther() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.containsAll(otherList);
        // Then
        assert  result :"ContainsAll returns false when all elements in lists are the same.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfContainsAllReturnsTrueWhenGivenEmptyListAsArgument() {
        logger.info(STARTING_MESSAGE);
        // Given
        List emptyList = new ArrayList();
        list.addAll(otherList);
        // When
        boolean result = list.containsAll(emptyList);
        // Then
        assert  result :"Contains all returned false when provided with empty list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // equals
    public static void testIfEqualsReturnsTrueWhenTwoExactlySameListsAreCompared() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.equals(list);
        // Then
        assert  result :"List is not equal to itself.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfTwoListsWithTheSameElementsAreEqual() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.equals(otherList);
        // Then
        assert  result :"Two lists are not equal";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // get
    public static void testIfGetWhenNoSuchIndexThrowsIndexOutOfBoundsException() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfGetToIndexWithTheSpecificObjectReturnesSameObjectAsSpecified() {
        logger.info(STARTING_MESSAGE);
        // Given
        String specificObject = "Adam";
        list.addAll(otherList);
        // When
        boolean result = list.get(1).equals(specificObject);
        // Then
        assert  result :"Get did not return the right object.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfWhenGetIndexZeroFromSizeOneListGetsThisElement() {
        logger.info(STARTING_MESSAGE);
        // Given
        String name = "Tomasz";
        list.add(name);
        // When
        boolean result = list.get(0).equals(name);
        // Then
        assert  result :"Get did not returned exactly the same object.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // hashCode
    public static void testIfExpectedHashCodeMatchesActual() {
        logger.info(STARTING_MESSAGE);
        // Given
        String adam = "Adam";
        int hashOfAdam = adam.hashCode();
        int suposedHashOfList = 31 + hashOfAdam;
        list.add(adam);
        // When
        boolean result = list.hashCode() == suposedHashOfList;
        // Then
        assert  result :"HashCode expected doesn't match actual.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfEmptyListHasHashCodeEqualOne() {
        logger.info(STARTING_MESSAGE);
        // Given
        int expectedHash = 1;
        // When
        int actualHashCode = list.hashCode();
        boolean result = actualHashCode == expectedHash;
        // Then
        assert  result :"The expected hashcode doesn't match actual.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // indexOf
    public static void testIfIndexOfReturnsTwoWhenPassingBorys() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.indexOf("Borys") == 2;
        // Then
        assert  result :"Index is different then it should be.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfIndexOfReturnsMinusOneWhenPassingObjectWhichItDoesntHave() {
        logger.info(STARTING_MESSAGE);
        // Given
        String name = "Tomasz";
        // When
        boolean result = -1 == list.indexOf(name);
        // Then
        assert  result :"Value is different then minus one.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfListReturnsIndexOfFirstObjectOccurenceNotSecondOne() {
        logger.info(STARTING_MESSAGE);
        // Given
        String adam = "Adam";
        list.addAll(otherList);
        list.add(adam);
        // When
        boolean result = 1 == list.indexOf(adam);
        // Then
        assert  result :"IndexOf returned second or other index of object.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // isEmpty
    public static void testIfIsEmptyReturnsTrueWhileEmptyListPassed() {
        logger.info(STARTING_MESSAGE);
        // Given

        // When
        boolean result = list.isEmpty();
        // Then
        assert  result :"Is empty returned false while used on empty list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfIsEmptyReturnsFalseWhenUsedOnListWithObjects() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.add(otherList);
        // When
        boolean result = !list.isEmpty();
        // Then
        assert  result :"isEmpty returned true while there are Objects in list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIsEmptyShouldReturnFalseWhenNullIsInTheList() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.add(null);
        // When
        boolean result = !list.isEmpty();
        // Then
        assert  result :"When null in the list, is empty returned true.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // iterator
    public static void testIfIteratorReturnedReturnsProperObject() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        Iterator iterator = list.iterator();
        // When
        boolean result = iterator.next() == list.get(0);
        // Then
        assert  result :"The objects returned by iterator.next() is different than the first object in list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIteratorOfEmptyListShouldThrowNoSuchElementException() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // lastIndexOf
    public static void testLastIndexOfShouldReturnFiveWhenSearchingForBorys() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        list.addAll(otherList);
        String borys = "Borys";
        // When
        boolean result = 5 == list.lastIndexOf(borys);
        // Then
        assert  result :"Last index is different then expected.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testLastIndexOfShouldReturnMinusOneWhenNoSuchObject() {
        logger.info(STARTING_MESSAGE);
        // Given
        String patryk = "Patryk";
        list.addAll(otherList);
        // When
        boolean result = -1 == list.lastIndexOf(patryk);
        // Then
        assert  result :"Result of last index of was different than -1";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testLastIndexOfShouldReturnMinusOneWhenListIsEmpty() {
        logger.info(STARTING_MESSAGE);
        // Given
        String prometeusz = "Prometeusz";
        // When
        boolean result = -1 == list.lastIndexOf(prometeusz);
        // Then
        assert  result :"Last Index of prometeusz should return -1 on empty list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // listIterator
    public static void testIfListIteratorReturnedReturnsProperObject() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        Iterator iterator = list.listIterator();
        // When
        boolean result = iterator.next() == list.get(0);
        // Then
        assert  result :"The objects returned by iterator.next() is different than the first object in list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testListIteratorOfEmptyListShouldThrowNoSuchElementException() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // listIterator with index
    public static void testIfListIteratorInserdedInTheMiddleHasPrevious() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        ListIterator iterator = list.listIterator(1);
        // When
        boolean result = iterator.hasPrevious();
        // Then
        assert result:"Iterator created with method listIterator with index has no previous.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfListIteratorInsertedInTheMiddleHasNext() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        ListIterator iterator = list.listIterator(1);
        // When
        boolean result = iterator.hasNext();
        // Then
        assert result:"List iterator has no next element.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfWhenEmptyListMethodThrowsIndexOutOfBoundsException() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // remove
    public static void testIfRemoveReturnsIndexOutOfBoundExceptionForEmptyList() {
        logger.info(STARTING_MESSAGE);
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
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfObjectWhichWasAddedEqualsObjectRemoved() {
        logger.info(STARTING_MESSAGE);
        // Given
        String borys = "Borys";
        list.add(borys);
        String otherBorys = (String) list.remove(0);
        // When
        boolean result = borys.equals(otherBorys);
        // Then
        assert result:"Object added does not equal object removed.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfOtherObjectIndexesShiftToLeftIfRemoveIsUsed() {
        logger.info(STARTING_MESSAGE);
        // Given
        Collections.addAll(list,"Roman","Borys","Adam","Morty","Rick");
        String morty = (String)list.get(3);
        list.remove(2);
        // When
        boolean result = morty.equals(list.remove(2));

        // Then
        assert result:"Third index is not Morty after removing previous third index so it doesn't shift left.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // remove Object
    public static void testIfRemoveObjectReturnsTrueIfListContainedThatObject() {
        logger.info(STARTING_MESSAGE);
        // Given
        String morty = "Morty";
        list.add(morty);
        // When
        boolean result = list.remove(morty);

        // Then
        assert result:"Remove object returned false";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfRemoveObjectRemovesOnlyOneOccurrenceOfThatObject() {
        logger.info(STARTING_MESSAGE);
        // Given
        String morty = "Morty";
        list.add(morty);
        list.add(morty);
        // When
        list.remove(morty);
        boolean result = list.size() == 1;
        // Then
        assert result:"List size is other than one.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfRemovingObjectWhichIsNotInListReturnFalse() {
        logger.info(STARTING_MESSAGE);
        // Given
        String morty = "Morty";
        // When
        boolean result = !list.remove(morty);
        // Then
        assert result:"List remove returned true.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // removeAll
    public static void testIfListSizeIsSixMinusThreeWhenRemoveAllWithThreeElementCollection() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        Collections.addAll(list,"Morty","Rick","Rumcajs");
        // When
        list.removeAll(otherList);
        boolean result = list.size() == 3;
        // Then
        assert result:"List size is other than three.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testRemoveAllReturnsFalseWhenThereAreNoElementsLikeThisInThisList() {
        logger.info(STARTING_MESSAGE);
        // Given
        // When
        boolean result = !list.removeAll(otherList);

        // Then
        assert result:"RemoveAll returned true, which points that it changed something.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testRemoveAllReturnsTrueWhenRemovedElementsIsSucessfull() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        boolean result = list.removeAll(otherList);

        // Then
        assert result:"Remove all did return false.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // replaceAll
    public static void testIfReplaceAllElementEqualsElementWithTheSameOperator() {
        logger.info(STARTING_MESSAGE);
        // Given
        String adamKowalski = "Adam" + " Kowalski";
        list.addAll(otherList);
        // When
        list.replaceAll(n -> n + " Kowalski");
        boolean result = list.get(1).equals(adamKowalski);
        // Then
        assert result:"This element isn't changed in a way that replace all should work.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfReplaceAllOnEmptyListDoesntThrowExceptions() {
        logger.info(STARTING_MESSAGE);
        // Given
        boolean result = false;
        // When
        try {
            list.replaceAll(n -> n + " something.");
            result = true;
        } catch (Exception e) {}
        // Then
        assert result:"En exception was thrown.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // retainAll
    public static void testIfRetainAllEmptiesListWhenNoObjectsOfThatSortAreInList() {
        logger.info(STARTING_MESSAGE);
        // Given
        Collections.addAll(list,"Tomasz","Rumcajs","Norbert");
        // When
        list.retainAll(otherList);
        boolean result = 0 == list.size();
        // Then
        assert result:"RetainAll did not empty the list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfRetainAllDoesNotTakeAnEffectWhenUsedWithSameListOfObjects() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        list.addAll(otherList);
        int before = list.size();
        // When
        list.retainAll(otherList);
        int after = list.size();
        boolean result = before == after;
        // Then
        assert result:"Size before using retainAll on list does not match after using it.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfRetainAllOnEmptyListDoesNotTakeAnyEffect() {
        logger.info(STARTING_MESSAGE);
        // Given
        // When
        list.retainAll(otherList);
        boolean result = 0 == list.size();

        // Then
        assert result:"Retain all changed list.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // set
    public static void testIfSetOnEmptyListThrowIndexOutOfBoundException() {
        logger.info(STARTING_MESSAGE);
        // Given
        boolean result = true;
        // When
        try {
            list.set(1,"Roman");
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert result:"Text";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSetChangesObjectInSpecifiedIndex() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        String adam = (String)list.get(1);
        // When
        list.set(1,"Roman");
        boolean result = !list.get(1).equals(adam);

        // Then
        assert result:"Element which index is 1 is not changed.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSetReturnsRemovedElement() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        String adam = (String)list.get(1);
        // When
        String supposedAdam = (String)list.set(1,"Roman");
        boolean result = supposedAdam.equals(adam);
        // Then
        assert result:"Element returned by set is not the same.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // size
    public static void testIfSizeOnEmptyListReturnsZero() {
        logger.info(STARTING_MESSAGE);
        // Given
        // When
        boolean result = 0 == list.size();
        // Then
        assert result:"Size returned other number than zero.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSizeReturnsThreeOnListWithThreeElementsInIt() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        boolean result = 3 == list.size();

        // Then
        assert result:"List size was not as expected.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // sort
    public static void testIfSortChangesIndexOfAdamToZero() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        String adam = "Adam";
        // When
        list.sort((obj1,obj2) -> {
            String s1 = (String) obj1;
            String s2 = (String) obj2;
            return s1.compareTo(s2);
        });
        boolean result = list.get(0).equals(adam);
        // Then
        assert result:"First index is not as it expected.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfMethodDoesNotThrowIllegalArgumentExceptionWhenPassedNull() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        boolean result = false;
        // When
        try {
            list.sort(null);
            result = true;
        } catch (Exception e) {}
        // Then
        assert result:"Method throw en Exception.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // spliterator
    public static void testIfSpliteratorSizeIsZeroIfSourceSizeIsKnownAndIsZero() {
        logger.info(STARTING_MESSAGE);
        // Given
        // When
        boolean result = list.size() == list.spliterator().getExactSizeIfKnown();
        // Then
        assert result:"Spliterator size is not zero.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSpliteratorFromEmptyListMethodTryAdvanceReturnsFalse() {
        logger.info(STARTING_MESSAGE);
        // Given
        Spliterator spliterator = list.spliterator();
        // When
        boolean result = !spliterator.tryAdvance(System.out::println);
        // Then
        assert result:"Spliterator returned true.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSpliteratorEstimateSizeReturnsAccualSizeOfList() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        long actualSize = list.size();
        Spliterator spliterator = list.spliterator();
        long estimatedSize = spliterator.estimateSize();
        // When
        boolean result = actualSize == estimatedSize;
        // Then
        assert result:"The actual size is not equal to estimated.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // subList
    public static void testIfSubListOfListIsEqualToListAddedToOriginalOne() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        list.addAll(otherList);
        // When
        boolean result = list.subList(3,list.size()).get(0) == otherList.get(0);
        // Then
        assert result:"The first object in those lists are different.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfSubListOnEmptyListThrowIndexOutOfBoundException() {
        logger.info(STARTING_MESSAGE);
        // Given
        boolean result = false;
        // When
        try {
            list.subList(0,1);
        } catch (IndexOutOfBoundsException e) {
            result = true;
        }
        // Then
        assert result:"Method did not throw any exceptions.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfWhenFromIndexAndTwoIndexAreEqualReturnedListIsEmpty() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        List subList = list.subList(0,0);
        boolean result = subList.isEmpty();
        // Then
        assert result:"Returned list was not empty.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
    // toArray
    public static void testIfToArrayOfNotEmptyListReturnsArrayWhichLengthIsEqualToListLength() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        // When
        Object[] array = list.toArray();
        boolean result = list.size() == array.length;

        // Then
        assert result:"Length of array returned is not equal to list size.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfObjectInArrayOnIndexZeroIsTheSameAsListIndexZero() {
        logger.info(STARTING_MESSAGE);
        // Given
        list.addAll(otherList);
        Object[] array = list.toArray();
        // When
        boolean result = array[0].equals(list.get(0));
        // Then
        assert result:"Objects are not equal to each other after toArray method called";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    public static void testIfWhenNullListToArrayThrowsNullPointerException() {
        logger.info(STARTING_MESSAGE);
        // Given
        list = null;
        boolean result = true;
        // When
        try {
            list.toArray();
        } catch (NullPointerException e) {
            result = true;
        }
        // Then
        assert result:"No exceptions were thrown.";
        logger.info(Color.ANSI_GREEN  + "PASSED.\n"+ Color.ANSI_RESET);
    }
    //========================================
}
