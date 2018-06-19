
public class MyLinkedList implements ListInterface {

	/* TODO: Write a LinkedList implementation for all the methods specified in ListInterface */
    Node head;
    int size = 0;

    protected class Node{
        Object x;
        Node next;
        Node(Object i){
            x = i;
            next = null;
        }
        Node(Object i, Node nextNode){
            x = i;
            next = nextNode;
        }
    }
    
    public String toString(){
    	Node curr = head;
    	String word = "(";
    	for(int i = 0; i < size; i++){
    	 word += curr.x;
    	 if(i == (size - 1)){
    		 break;
    	 }
    	 word += ", ";
    	 curr = curr.next;
    	}
    	word += ")";
    	return word;
    }
    
    @Override
    public boolean isEmpty() {
        if(head == null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int size() {
    	if(size == 0){
    		return 0;
    	}
    	else{
    		
    	
    		return size;
    	}
    }


    @Override
    public void add(int index, Object value) throws ListIndexOutOfBoundsException {
        if(size == 0){
            Node node = new Node(value);
            head = node;
            size++;
        }
        else if(index == 0){
            Node node = new Node(value);
            node.next = head;
            head = node;
      
            size++;
        }
        else{
            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            Node node = new Node(value, curr.next);
            node.next = curr.next;
            curr.next = node;


            size++;
        }
    }


    @Override
    public void remove(int index) throws ListIndexOutOfBoundsException {

        if(index >= 0 && index < size) {
            Node curr = head;
            Node rem = curr.next;
            if (index == 0) {
                curr.next = null;
                head = rem;
            }
            else {
                for (int i = 0; i < index - 1; i++) {
                    curr = curr.next;
                    rem = rem.next;
                }
                curr.next = rem.next;
            }
            size--;
        }
    }

    @Override
    public void removeAll() {
        head = null;
        size = 0;
    }

    @Override
    public Object get(int index) throws ListIndexOutOfBoundsException {
        if(size > 0 && index < size) {
            Node curr = head;
            for (int i = 0; i < index ; i++) {
                curr = curr.next;
            }
            return curr.x;
        }

        else{
            throw new ListIndexOutOfBoundsException("Out of bounds");
        }


    }

    @Override
    public int find(Object o) {
        Boolean found = false;
        Node curr = head;
        int index = 0;
        while(found != true){
            if(curr == null){
                return -1;
            }
            else if(o == curr.x){
                found = true;
                break;
            }

            curr = curr.next;
            index++;

        }
        return index;
    }
}

