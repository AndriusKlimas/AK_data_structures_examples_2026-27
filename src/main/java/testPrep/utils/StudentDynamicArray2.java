package testPrep.utils;

import testPrep.entities.Student;

public class StudentDynamicArray2 {
    //set up variables
    //ultiplier, size, inital array
    private static final int ARRAY_MULTIPLIER = 2;
    private static int size =0;
    private Student[] data = new Student[10];

    //ENCAPSULATION
    //
    private void ensureCapacity(){
        if(size == data.length){
            Student[] temp = new Student[data.length * ARRAY_MULTIPLIER];

            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];

            }
            data = temp;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public Student get(int index){
        return data[index];
    }

    public Student indexOf(Student student){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(student)) {
                return data[i];
            }
        }
        return null;
    }

    //adds the student to the last location
    public void add(Student student){
        ensureCapacity();
        data[size] = student;
        size++;
    }

    //add the object to a specififc location
    public void add(int index, Student student){
        if(isEmpty()){
            add(student);
        }else{
            //go to end of array, move the students over by one until you reach index, when you do add the new student their
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = student;
            size++;
        }
    }

    public void remove(int index){
        Student removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size] = null;
        size--;
    }
}
