package testPrep.utils;

import testPrep.entities.Student;

public class StudentDynamicArray3 {
    private static final int EXP_MULT = 2;
    private static int size;
    private Student[] students = new Student[10];

    private void ensureEncapsulation() {
        if(size==students.length){
            Student[] temp = new Student[students.length*EXP_MULT];
            for (int i = 0; i < students.length; i++) {
                temp[i] = students[i];

            }
            students=temp;
        }
    }

    public boolean isEmpty(){
        return size ==0;
    }

    public int size(){
        return size;
    }

    public Student get(int index){
        return students[index];
    }

    public int  indexOf(Student s){
        for (int i = 0; i < size; i++) {
            if (students[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }



    public void add(Student s) {
        ensureEncapsulation();
        students[size] = s;
        size++;
    }

    public void add(Student s, int index){
        //go to index
        //move everything over
        for (int i = size; i > index; i--) {
            students[i+1] = students[i];
        }
        students[index] = s;
        size++;
    }

    public Student remove(int index){
        Student removed = students[index];
        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
        }
        students[size] = null;
        size--;
        return removed;
    }
}
