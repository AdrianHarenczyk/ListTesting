import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {
        // method add
        testAddingToTheListWhenObject();

    }

// method add
    public static void testAddingToTheListWhenObject() {
        System.out.println("Testing adding to the list.");
        // Given
        List list = new ArrayList();
        List anotherList = new LinkedList();
        Integer one = 1;
        // When
        boolean result = list.add(one);
        // Then
        assert !result :"Adding to the list was not successful";
        System.out.println("Test result is positive.");
    }
    public static void testName() {
        System.out.println("Testing ");
        // Given

        // When

        // Then
        assert :"";
        System.out.println("Test result is positive.");
    }

}
