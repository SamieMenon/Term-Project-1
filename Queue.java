/**
 * Creates new Queue
 * @author 
 *
 */
public class Queue {
	
		private final int MAX_LENGTH;
		private Room[] queueList;
		private int length, head, tail;
		
		
		/**
		 * Constructor function for class Queue
		 * Creates empty Queue
		 */
		public Queue() {
			length = 0;
			head = 0;
			tail = 0;
			MAX_LENGTH = 50;
			queueList = new Room[MAX_LENGTH];
		}
		
		/**
		 * Constructor function for class Queue; Creates Queue of length size
		 * @param size, size of Queue
		 */
		public Queue(int size) {
			length = 0;
			head = 0;
			tail = 0;
			MAX_LENGTH = size;
			queueList = new Room[MAX_LENGTH];
		}
		
		/**
		 * 
		 * @param x
		 */
		public void enqueue(Room newRoom) {
			queueList[tail] = newRoom;
			tail++;
			if (tail == MAX_LENGTH) {
				tail = 0;
			}
			length++;
		}
		
		/**
		 * Removes first room from Queue
		 * @return new queue list
		 */
		public Room dequeue() {
			Room temp = queueList[first];
			head++;
			if (head == MAX_LENGTH) {
				head = 0;
			}
			length--;
			return temp;
		}
		
		/**
		 * Checks if queue is empty
		 * @return true if list is empty; else returns false
		 */
		public boolean isEmpty() {
			if (length==0) {
				return true;
			}
			else return false;
		}
		
		/**
		 * Prints objects in Queue
		 */
		public void printQueue() {
			for (int i = head; i < tail + 1; i++) {
				System.out.print(queueList[i] + " ");
			}
			System.out.println();
		}
		
		
		
	

}
