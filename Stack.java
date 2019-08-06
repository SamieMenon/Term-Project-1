public class Stack {
	
	public int items, top;
	private final int length;
	private Room[] stck;
	
	// pushes to the top of the stack
	public void push(Room rm) {
		//if stack is full, cannot push
		if (top == length - 1) {
			System.out.println("Unable to push due to full stack");
		}else {
			stck[top] = rm;
			items++;
			top++;
		}
	}
	//pops on top of stack
	public Room pop() {
		if (top == 0) {
			System.out.println("Unable to pop due to empty stack");
		}
		Room rm = stck[top - 1];
		items--;
		top--;
		return rm;
	}
	//returns true is top is empty
	public boolean isEmpty() {
		return (top == 0);
	}
	//initial stack
	public Stack() {
		items = 0;
		top = 0;
		length = 100;
		stck = new Room[length];
	}
	//stack with length arguments
	public Stack(int length) {
		items = 0;
		top = 0;
		this.length = length;
		stck = new Room[length];
	}

}
