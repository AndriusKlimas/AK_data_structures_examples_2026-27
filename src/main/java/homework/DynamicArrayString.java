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

    public void add(int index, String toAdd){

        System.arraycopy(data,index,data,index+1,(size-index));

        size++;
        data[index] = toAdd;

        }

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
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }


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

    public void clear(){
        DynamicArrayString clear = new DynamicArrayString();
        data = clear.data;
        size = 0;
    }

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


    public void join(String[] otherArray){
        for (int i = 0; i < otherArray.length; i++) {
            String current = otherArray[i];
            if (current != null) {
                add(current);
            }

        }

    }


}
