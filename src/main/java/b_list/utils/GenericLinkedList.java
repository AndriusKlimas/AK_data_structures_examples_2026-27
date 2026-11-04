package b_list.utils;

public class GenericLinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;




    Node<String> myNode = new Node<>(new String());
    private static class Node<T>{
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(T element){
        Node<T> newNode = new Node<>(element);


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

    public T get(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is out of bounds of list");
        }
        Node<T> current = first;

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

    public int indexOf(T target){
        if(target==null){
            throw new IllegalArgumentException("Target cannot be null");
        }
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(target)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void add(T element, int index){

        if(index<0||index>size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is out of bounds of list");
        }

        Node<T> newNode = new Node<>(element);
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

            Node<T> prev = null;
            Node<T> current = first;

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
        Node<T> current = first;

        if(index==0){
            if(first != null){
                first = current.next;
            }

        }else{
            Node<T> prev = null;

            for (int i = 0; i < index; i++) {
                T deleted = current.data;
                prev = current;
                current = current.next;
            }
            prev.next = current.next;
        }
        size--;

    }

}
