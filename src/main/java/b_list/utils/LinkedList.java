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

    public int indexOf(String target){
        if(target==null){
            throw new IllegalArgumentException("Target cannot be null");
        }
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(target)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void add(String element, int index){

        if(index<0||index>size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is out of bounds of list");
        }

        Node newNode = new Node(element);
        if (index == 0) {
            if(!isEmpty()){
                newNode.next = first;
            }else{
                last = newNode;
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

            if(index == size){
                last=newNode;
            }
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
                String deleted = current.data;
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
        }
        size--;

    }

    public int removeValue(String target){
        if(target==null){
            throw new IllegalArgumentException("Target cannot be null");
        }
        int index = indexOf(target);
        Node current = first;
        Node prev = null;
        if(index != -1){
            for (int i = 0; i <= index; i++) {
                String deleted = current.data;
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
        }

        size--;
        return index;
    }
}
