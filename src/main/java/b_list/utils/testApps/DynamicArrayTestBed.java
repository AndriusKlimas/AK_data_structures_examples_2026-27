package b_list.utils.testApps;

import b_list.utils.DynamicArray;

import java.util.Random;

public class DynamicArrayTestBed {
    static void main(String[] args){
        DynamicArray myList = new DynamicArray();
        Random rg = new Random();

        for(int i=0;i<25;i++){
            myList.add(rg.nextInt(100));
        }
        for(int i=0;i<myList.size();i++){
            System.out.println(myList.get(i));
        }
    }
}
