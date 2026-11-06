package testPrep.utils;
import b_lists.utils.LinkedList;
import testPrep.entities.Student;


public class StudentLinkedList {
    private Node first;
    private Node last;
    private int size;

    private static class Node{
        private Student data;
        private Node next;

        public Node(Student data){
            this.data = data;
            this.next = null;
        }
    }

    private boolean isEmpty(){
        if(first == null)
            return true;
        else
            return false;
    }

    private int size() {
        return size;
    }

    private void addFirst(Student student){
        Node newNode = new Node(student);
        if(isEmpty()){
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    private void addLast(Student student){
        Node newNode = new Node(student);
        if(isEmpty()){
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    private void removeFirst(){
        if(!isEmpty()){
            first = first.next;
            if(first == null){
                last = null;
            }
            size--;
        }
    }

    private void removeLast(){
        if(!isEmpty()){
            if(first == last){
                first = null;
                last = null;
            } else {
                Node current = first;
                for (int i = 0; i < size - 2; i++) {
                    current = current.next;
                }
                current.next = null;
                last = current;
            }
            size--;
        }
    }

    private Student get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

}
