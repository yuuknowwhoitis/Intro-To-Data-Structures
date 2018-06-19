
public class MyStack implements StackInterface {
	/* 
	* TODO 1: Implement "MyStack"
	*/
	//list for storing elements
	MyLinkedList list = new MyLinkedList();
	
	//to String method
	public String toString(){
		String sTemp = "(";
		for(int i = list.size - 1; i >= 0; i--){
			sTemp += list.get(i);
			sTemp += ", ";
			if(i == 0){
				sTemp += ")";
			}
		}
		return sTemp;
		
		
	}
	
	//returns true if empty
	public boolean isEmpty(){
		return (list.size() == 0);
	}
	//push - add to stack
	public void push(Object o){
		list.add(list.size , o);
	}
	//pop - removes top of stack
	public Object pop() throws StackException{
		if(!isEmpty()){
			Object temp = list.get(list.size - 1);
			list.remove(list.size - 1);
			return temp;
		}
		else{
			throw new StackException("StackException on " + "pop: stack empty");
		}
	}
	//peek - returns value of top of stack but does not remove
	public Object peek() throws StackException{
		if(!isEmpty()){
			return list.get(list.size - 1);
		}
		else{
			throw new StackException("Stackexception on " + "peek: stack empty");
		}
	}
	
	//pop all - removes all
	public void popAll(){
		list.removeAll();
	}
	
	

	
	/* 
	* TODO 1: Implement "MyStack"
	*/
}