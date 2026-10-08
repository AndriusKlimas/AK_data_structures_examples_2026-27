package b_list.utils;

public class LinkedList {

    private Node first;
    private Node last;
    private int size;

    private static class Node{
        private String data;
        private Node next;


        public Node(String data){
            this.data = data;
            this.data = null;
        }
    }

    public void add(String element){
        Node newNode = new Node(element);


        if(isEmpty()){
            first = newNode;
            last = newNode;
        }
        else{
            last.next= newNode;
            last=newNode;
//            Node current = first;
//
//            while(current.next !=null){
//                current = current.next;
//            }
//
//            current.next = newNode;
//
//            size++;

        }
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else {
            return false;
        }
    }

    public String get(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is out of bounds of list");
        }
        Node current = first;

//        for(int i =0;i<size;i++){
//            if(i == index){
//                return current.data;
//            }
//            current = current.next;
//        }
//        return null;

        int i = 0;
        while(i!=index){
            current = current.next;
            i++;
        }
        return current.data;
    }

    public void add(String element, int index){

        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is out of bounds of list");
        }

        Node newNode = new Node(element);
        if (index == 0) {
            if(first !=null){
                newNode.next = first;
            }
            first = newNode;
        }else {
//            last.next = newNode;
//            last = newNode;

            Node prev = null;
            Node current = first;

            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;

            }

            newNode.next = current;
            prev.next = newNode;
        }
        size++;
    }

    public void remove(int index){

        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is out of bounds of list");
        }
        Node current = first;

        if(index==0){
            if(first != null){
                first = current.next;
            }

        }else{
            Node prev = null;

            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
        }
        size--;

    }
}
