package homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayStringTest {

    @Test
    void removeAllInList() {
        DynamicArrayString myList = new DynamicArrayString();
        myList.add("A");
        myList.add("X");
        myList.add("X");
        myList.add("B");

        int removed = myList.removeAll("X");

        assertEquals(2, removed);
        assertEquals(2, myList.size());
        assertEquals("A", myList.get(0));
        assertEquals("B", myList.get(1));
    }

    @Test
    void removeAllNotInList(){
        DynamicArrayString myList = new DynamicArrayString();
        myList.add("A");
        myList.add("X");
        myList.add("X");
        myList.add("B");

        int removed = myList.removeAll("Y");

        assertEquals(0, removed);
        assertEquals(4, myList.size());
        assertEquals("A", myList.get(0));
        assertEquals("X", myList.get(1));
        assertEquals("X", myList.get(2));
        assertEquals("B", myList.get(3));

    }

}