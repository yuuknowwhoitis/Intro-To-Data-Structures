public class MySortedLinkedList extends MyLinkedList {

	/* TODO 
	   define the method
	   public void add(Comparable c)
	   that, given a Comparable (an interface type for all Object subclasses that define a compareTo() method), adds it to the 
	   list in sorted order.
	*/
	public void add(Comparable c){
		Node node = new Node(c);
		if(size == 0){
			head = node;
			size++;
		}
		else{
			Node curr = head;
			for(int i = 0; i < size; i++){
				if(((Comparable) node.x).compareTo(curr.x) > 0){
					node.next = curr.next;
					curr.next = node;
					size++;
					break;
				}
				else if(((Comparable) node.x).compareTo(curr.x) == 0){
					node.next = curr.next;
					curr.next = node;
					size++;
					break;
				}
				else{
					curr = curr.next;
				}
			}
		}
    }
	/* TODO
	   override the method
	   void add(int index, Object o)
	   so that it throws an UnsupportedOperationException with the message "Do not call add(int, Object) on MySortedLinkedList".
	   Directly adding objects at an index would mess up the sorted order.
	*/

    @Override
    public void add(int index, Object value) throws ListIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Do not call add(int, Object) on MySortedLinkedList");
    }
}