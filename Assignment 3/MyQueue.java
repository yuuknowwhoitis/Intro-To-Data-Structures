
public class MyQueue implements QueueInterface {
	/* 
	* TODO 2: Implement "MyQueue"
	*/
	//list for storing elements
	MyLinkedList list = new MyLinkedList();
	
	//toString method
	public String toString(){
		String qTemp = "(";
		for(int i = 0; i < list.size; i++){
			if(i == list.size - 1){
				qTemp += list.get(i);
				qTemp += ")";
			}
			else{
				qTemp += list.get(i);
				qTemp += ", ";
			}
		}
		return qTemp;
		
		
	}
	//isEmpty - returns true if empty
	public boolean isEmpty(){
		return list.size == 0;
	}
	
	//enqueue - adds to queue
	public void enqueue(Object item){
		list.add(list.size , item);
	}
	
	//dequeue - removes front of queue
	public Object dequeue(){
		if(!isEmpty()){
			Object item = list.get(0);
			list.remove(0);
			return item;
		}
		else{
			throw new QueueException("QueueException on dequeue:" + " queue empty");
		}
		
	}
	
	//dequeueAll -removes all
	public void dequeueAll(){
		list.removeAll();
	}
	
	public Object peek(){
		if(!isEmpty()){
			return list.get(0);
		}
		else{
			throw new QueueException("Queue exception: " + "queue empty");
		}
	}
	
	/* 
	* TODO 2: Implement "MyQueue"
	*/

} 