
public class Stack {

	private final int size;
	private int top, items;
	private Room[] stList;
	
	public Stack() {
		size = 50;
		top = 0;
		items = 0;
		stList = new Room[size];
	}
	
	public Stack(int size) {
		this.size = size;
		top = 0;
		items = 0;
		stList = new Room[size];
	}
	
	public void push(Room room) {
		if (top == size - 1) {
			System.out.println("Stack is full");
		}
		stList[top] = room;
		top++;
		items++;
	}
	
	public Room pop() {
		if (top == 0) {
			System.out.println("Stack is empty, nothing to pop!");
		}
		Room room = stList[top - 1];
		top--;
		items--;
		return room;
	}
	
	public boolean isEmpty() {
		return (top == 0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
