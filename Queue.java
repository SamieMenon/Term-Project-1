
public class Queue {
	
	private Room[] qList;
	private int size, first, last;
	private final int MAX_SIZE;
	
	public Queue() {
		size = 0;
		first = 0;
		last = 0;
		MAX_SIZE = 50;
		qList = new Room[MAX_SIZE];
	}
	
	public Queue(int maxSize) {
		size = 0;
		first = 0;
		last = 0;
		MAX_SIZE = maxSize;
		qList = new Room[MAX_SIZE];
	}
	
	public void enqueue(Room x) {
		qList[last] = x;
		last++;
		if (last == MAX_SIZE) {
			last = 0;
		}
		size++;
	}
	
	public Room dequeue() {
		Room item = qList[first];
		first++;
		if (first == MAX_SIZE) {
			first = 0;
		}
		size--;
		return item;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public void printQueue() {
		for (int i = first; i < last + 1; i++) {
			System.out.print(qList[i] + " ");
		}
		System.out.println();
	}
	
	
	
}
