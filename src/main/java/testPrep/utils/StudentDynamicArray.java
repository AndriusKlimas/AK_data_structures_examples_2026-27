package testPrep.utils;
import testPrep.entities.Student;

public class StudentDynamicArray {
    private Student[] data = new Student[10];
    private static final int EXPANSION_MULT = 2;
    private int size = 0;

    private void ensureCapacity(){
        if(size == data.length){
            Student [] temp = new Student[data.length*EXPANSION_MULT];

            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }

            data = temp;
        }
    }

    private void validateIndex(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is out of bounds of list");
        }
    }

    private void validateElement(Student student){
        if(student == null){
            throw new IllegalArgumentException("Student cannot be null");
        }
    }

    public void add(Student student){
        validateElement(student);
        ensureCapacity();
        data[size] = student;
        size++;
    }

    public Student get(int index){
        validateIndex(index);
        return data[index];
    }

    public Student set(int index, Student student){
        validateElement(student);
        validateIndex(index);
        Student oldStudent = data[index];
        data[index] = student;
        return oldStudent;
    }

    public Student remove(int index){
        validateIndex(index);
        Student oldStudent = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size-1] = null;
        size--;
        return oldStudent;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }

    public boolean contains(Student student){
        validateElement(student);
        for (int i = 0; i < size; i++) {
            if (data[i].equals(student)) {
                return true;
            }
        }
        return false;
    }
}
