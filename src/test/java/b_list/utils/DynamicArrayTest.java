package b_list.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void get() {
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        int expectedResult = 5;
        int result = myList.get(0);

        assertEquals(expectedResult, result);
    }

    @Test
    void get_AccessBeforeList() {
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(-1);
                }, "Incorrect (or no) exception thrown"
        );
    }

    @Test
    void get_AccessAfterList(){
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        int size2 = myList.size();
        int size3 = size2 +1;
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(size3);
                }, "Incorrect (or no) exception thrown"
        );
    }



    @Test
    void indexOffWithinList() {
        DynamicArray myList = new DynamicArray();
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(20);

        assertEquals(1, myList.indexOff(20));
    }
}