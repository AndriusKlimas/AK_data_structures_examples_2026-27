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


}
