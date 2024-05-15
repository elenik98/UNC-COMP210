package assn03;

public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public void removeAtIndex(int i) {

        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }
        // Removing head node
        if (i == 0) {
            head = head.getNext();
            size--;
            return;
        }
        Node current = head;
        for (int j=0; j < i-1; j++){
            current = current.getNext();
        }

        Node nodeToRemove = current.getNext();
        current.setNext(nodeToRemove.getNext());
        size--;
    }

    public boolean isEqual(LinkedList list2){
        if (size != list2.size()) {
            return false;
        }
        Node<T> current = head;
        Node<T> current2 = list2.getHead();

        while (current != null) {
            if (!current.getValue().equals(current2.getValue())){
                return false;
            }
            current = current.getNext();
            current2 = current2.getNext();
        }
        return true;
    }



    public void removeRepeats() {
        if (head == null || head.getNext() == null){
            return;
        }

        Node current = head;

        while (current != null) {
            Node prev = current;
            while (prev.getNext() != null){
                if (prev.getNext().getValue() == current.getValue()){
                    prev.setNext(prev.getNext().getNext());
                } else {
                    prev = prev.getNext();
                }
            }
            current = current.getNext();
        }
    }


    public void reverse() {
        Node<T> current = head;
        Node<T> next = head;
        Node<T> last = null;
        tail = head;

        while (current != null) {
            next = next.getNext();
            current.setNext(last);
            last = current;
            current = next;
        }
        head = last;
    }




    public void merge(LinkedList list2) {
        if (list2.size() == 0) {
            return;
        }
        size = list2.size() + size;
        Node<T> current = head;
        Node<T> mergeNode = list2.getHead();
        Node<T> tempCurrent;
        Node<T> tempMerge = mergeNode.getNext();

        while (tempMerge != null) {
            tempCurrent = current.getNext();
            current.setNext(mergeNode);
            tempMerge = mergeNode.getNext();
            mergeNode.setNext(tempCurrent);
            current = tempCurrent;

            if (tempMerge != null) mergeNode = tempMerge;
        }

        if (size - list2.size() == list2.size()) {
            tail = mergeNode;
        }

        list2.head = null;
        list2.tail = null;
        list2.size = 0;
    }


    /* Implementations below are being given to you. Do not modify below this. */

    public int size() {return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Object element) {
        Node<T> current = head;
        while(current != null) {
            if(current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public T[] toArray() {
        T[] arr =  (T[]) new Object[size()];
        Node<T> current = head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(Object element) {
        Node<T> newNode = new NodeImpl<T>((T) element, null);
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }

    }

    public boolean remove(Object element) {
        Node<T> current = head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            head = head.getNext();
            size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            tail = current;
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    public T get(int index) {
        validIndex(index);
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public T set(int index, Object element) {
        validIndex(index);
        Node<T> current = head;
        T prevValue = null;
        int i = 0;
        if(index == 0) {
            prevValue = head.getValue();
            head.setValue((T) element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue((T) element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, Object element) {
        if(index > size) {
            validIndex(index);
        }
        Node<T> current = head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node<T> newNode = new NodeImpl<T>((T) element, head.getNext());
                head = newNode;
                size++;
                return;
            }

        }  else if(index == size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node<T> temp = current.getNext();
                Node<T> newNode = new NodeImpl<T>((T) element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(Object element) {
        Node<T> current = head;
        int index = 0;
        while(current != null) {
            if(current.getValue().equals((T) element)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(Object element) {
        Node<T> current = head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue().equals ((T) element)) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        String list = "";
        Node<T> current = head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }
}