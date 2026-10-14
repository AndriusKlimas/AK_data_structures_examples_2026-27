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

    /* gets the amount of elements in the array

    return:
        size - the amount of elements in the array
     */
    public int size(){
        return size;
    }


    /* finds the iteam at the index spot stated

    args:
        index - get the element in the index provided
    return:
        data - the item found in the array slot stated
     */
    public String get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }
        return data[index];
    }

    /* find the index of which the target is at

    args:
        target - the String the person is looking for
    return:
        returns the index or -1 if not found
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

    args:
        value - the string that should be added to end of the array
     */
    public void add(String value){
        ensureCapacity();

        data[size] = value;
        size++;
    }

    /* adds the element (toAdd) to the specified location (index)

    args:
        index - the location to add it to
        toAdd - the element to add

     */
    public void add(int index, String toAdd){

        System.arraycopy(data,index,data,index+1,(size-index));

        size++;
        data[index] = toAdd;

        }

    /* puts value (string) into specified array location (index)

    args:
        index - location to place the value
        toAdd - the String provided

     return:
        removed: the element that was removed
     */
    public String set(int index, String toAdd){

        String removed = data[index];
        data[index] = toAdd;

        return removed;
    }

    /* removes the element at the specific index

    args:
        index - the location of element to remove
    return:
        removed - the element that was removed
     */
    public String remove(int index){
        String removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }

    /* removed all elements that are hte same as target

    args:
        target - the element to be removed

    return:
        count - the number of elements removed
     */
    public int removeAll(String target){
        int count = 0;
        for (int i = 0; i < size;) {
            if(data[i].equals(target)){
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[size - 1] = null;
                size--;
                count++;
            } else {
                i++;
            }

        }
        return count;
    }

    /* creats a new array with default settings and makes current one the same as new one, clearing the infomration


     */
    public void clear(){
        DynamicArrayString clear = new DynamicArrayString();
        data = clear.data;
        size = 0;
    }

    /* takes a copy of the current array, takes out the duplicates

    return:
        newArray - the new array without duplicates
     */
    public DynamicArrayString deduplicate(){
        DynamicArrayString newArray = new DynamicArrayString();
        for (int i = 0; i < size; i++) {
            String current = data[i];
            if(newArray.indexOf(current) == -1){
                newArray.add(current);
            }
        }

        return newArray;
        // i need to add a filtering process, currnetly all in order, just need to remove duplicates
    }


    /* adds all the values of provided array into current one

    args:
        otherArray - the array of elements ot be added to current one
     */
    public void join(String[] otherArray){
        for (int i = 0; i < otherArray.length; i++) {
            String current = otherArray[i];
            if (current != null) {
                add(current);
            }
        }
    }


}
