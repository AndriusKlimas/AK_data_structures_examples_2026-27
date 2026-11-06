package testPrep.utils;

import b_lists.utils.LinkedList;
import testPrep.entities.Student;

public class StudentLinkedList2 {
    private Node first;
    private Node last;
    private int size;

    private static class Node{
        private Student data;
        private Node next;

        public Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty(){
        return size ==0;
    }

    public int size(){
        return size;
    }

    public Student get(int index){
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int indexOf(Student student) {
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(student)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void add(int index, Student student){

        Node newNode = new Node(student);

        if(index ==0){
            if(!isEmpty()) {
                newNode.next = first;
            }else{
                last = newNode;
            }
            first = newNode;
        }
        else{
            Node prev = null;
            Node current = first;

            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            prev.next = newNode;
            newNode.next = current;

            if(index == size){
                last = newNode;
            }
        }
        size++;
    }

    public void remove(int index) {
        if(index==0){
            first = first.next;

            if(first==null){
                last = null;
            }
        }else{
            Node current = first;
            Node prev = null;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            prev.next = current.next;

            if(index == size-1){
                last = prev;
                }
            }
        size--;
    }

}


