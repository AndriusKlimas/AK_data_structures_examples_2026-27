package homework;

public class DynamicArrayString {
    private static final int EXPANSION_MULTIPLIER=2;
    private int size = 0;
    private String[] data = new String[10];


    /* increases the capacity of the array by 2

     */
    private void ensureCapacity(){
        if (size == data.length){
            String[] temp = new String[data.length*EXPANSION_MULTIPLIER];

            for (int i=0;i<data.length;i++){
                temp[i] = data[i];
            }
            data = temp;
        }
    }

    /* returns the size of array only amount of slots used

     */
    public int size(){
        return size;
    }


    /* finds the iteam at the index spot stated

    args: index - the slot to find and to return
    return: the item found in the array slot stated
     */
    public String get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }
        return data[index];
    }

    /* find the index of which the target is at

    args: target - the String the person is looking for
    return: returns the index or -1 if not found
     */
    public int indexOf(String target){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(target)){
                return i;
            }
        }

        return -1;
    }


    /* adds the value (string) at the end of array with a capacity check

    args: value - the string that should be added to end of the array
     */
    public void add(String value){
        ensureCapacity();

        data[size] = value;
        size++;
    }

//    public void add(int index, String toAdd){
//
//        System.arraycopy(data,index,data,index+1,(size-index));
//
//        size++;
//        data[index] = toAdd;
//
//        }

    /* puts value (string) into specified array location (index)

    args:
        index - location to place the value
        toAdd - the String provided
     */
    public String set(int index, String toAdd){

        String removed = "";
        removed = data[index];
        data[index] = toAdd;

        return removed;
    }

    public String remove(int index){
        String removed = data[index];
        data[index] = null;
        return removed;

    }
    public int removeAll(String target){
        int count = 0;
        for (int i = 0; i < size; i++) {
            if(data[i].equals(target)){
                count++;
                data[i] = null;
            }

        }
        return count;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public DynamicArrayString deduplicate(){
        DynamicArrayString newArray = new DynamicArrayString();
        for (int i = 0; i < size; i++) {
            String current = data[i];
            for (int j = 0; j < size; j++) {
                if(data[j].equals(current)){
                    newArray.add(current);
                }
            }
        }
        // i need to add a filtering process, currnetly all in order, just need to remove duplicates
        return newArray;
    }

}
