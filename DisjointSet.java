
/*
 * Creates new disjoint set
 */
public class DisjointSet {
	
		int set[];
		int length;
		
		/**
		 * Constructor class for Disjoint Set
		 * creates new set of size length
		 * @param length;
		 */
		public DisjointSet(int length) {
			this.length = length;
			set = new int[length];
			for (int i = 0; i < length; i++) {
				set[i] = -1;
			}
		}
		
		/**
		 * Creates union of two roots
		 * @param firstR, first root
		 * @param secondR, second root
		 */
		public void vertex(int firstR, int secondR) {
			if (set[secondR] < set[firstR]) {
				set[secondR] += set[firstR];
				set[firstR] = secondR;
			} else {
				set[firstR] += set[secondR];
				set[secondR] = firstR;
			}
		}
		
		/**
		 * Returns index of object to find
		 * @param toFind, index of object to find
		 */
		public int find(int toFind) {
			if (set[toFind] < 0) {
				return toFind;
			} else {
				set[toFind] = find(set[toFind]);
				return set[toFind];
			}	
		}
		
		
	


}
