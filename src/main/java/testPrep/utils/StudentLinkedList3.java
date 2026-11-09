package testPrep.utils;

import testPrep.entities.Student;

public class StudentLinkedList3 {
    private Node first;
    private Node last;
    private int size;

    private static class Node{
        private Student data;
        private Node next;

        public Node(Student student) {
            this.data = student;
            this.next = null;
        }
    }

    public boolean isEmpty(){
        return size==0;
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

    public int indexOf(Student s){
        Node current = first;
        for (int i = 0; i < size; i++) {
            if(current.data.equals(s))
                return i;
            current = current.next;
        }
        return -1;
    }

    public void add(Student s, int index){
        Node newNode = new Node(s);
        if(index==0) {
            if (isEmpty()) {
                first = newNode;
            } else {
                last.next = newNode;
            }
            last = newNode;
        }else{
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

    public Student remove(int index){

        Student removed;
        if(index==0){
            removed = first.data;
            first = first.next;

            if(first == null){
                last = null;
            }

        }else{
            Node current = first;
            Node prev = null;

            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            removed = current.data;

            prev.next = current.next;

            if(index == size-1){
                last = prev;
            }
        }
        size--;
        return removed;
    }
}
