package homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayStringTest {

    @Test
    void removeAll() {
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

}