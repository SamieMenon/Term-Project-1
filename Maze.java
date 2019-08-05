import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Maze {
	public int size;
	public Room[][]rooms;
	public String[] graphicPath;
	
	public Random rand = new Random();
	
	public Maze() {
		int size = 0;
	}
	
	public Maze(int size) {
		this.size = size;
		rooms = new Room[size][size];
		int seq = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				rooms[i][j] = new Room();
				rooms[i][j].roomNum = seq;
				seq++;
			}
		} 
		rooms[0][0].setWall(0, new Room());
		rooms[size - 1][size - 1].setWall(1, new Room());
	}
	
	public Maze(String infile) {
		try {
			Scanner in = new Scanner(new File(infile));
			
			String input;
			size = in.nextInt();
			System.out.println(size);
			rooms = new Room[size][size];
			int seq = 0;
			for (int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) {
					rooms[x][y] = new Room();
					rooms[x][y].roomNum = seq;
					seq++;
				}
			}
			rooms[0][0].setWall(0, new Room());
			rooms[size - 1][size - 1].setWall(1, new Room());
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					for (int a = 0; a < 4; a++) {
						input = in.next();
						int adj = chooseAdjacentRoom(rooms[i][j].roomNum, a);
						int adjWall = getAdjacentWall(a);
						if (input.equals("1") && isBorder(rooms[i][j].roomNum, a) == false) {
							rooms[i][j].sides[a].wall = true;
						} else if (input.equals("0") && isBorder(rooms[i][j].roomNum, a) == false) {
							breakWall(rooms[i][j], a, rooms[adj / size][(adj + size) % size], adjWall);
						}
					}
				}
			}
			
		in.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print(printMaze());
		
	}
	
	public void createMaze() {
		DisjointSet set = new DisjointSet(size * size);
		
		while(set.find(0) != set.find((size * size) - 1)) {
			int randomRoom = rand.nextInt(size * size);
			int randomWall = chooseRandomWall(randomRoom);
			
			int adjRoom = chooseAdjacentRoom(randomRoom, randomWall);
			int adjWall = getAdjacentWall(randomWall);
			
			if (set.find(randomRoom) != set.find(adjRoom)) {
				breakWall(rooms[randomRoom / size][randomRoom + size % size], randomWall, rooms[adjRoom / size][adjRoom + size % size], adjWall);
				set.union(set.find(randomRoom), set.find(adjRoom));
			}
		}
		System.out.print(printMaze());
	}
	
	public void bfsSolution() {
		String pathTaken = "";
		String solPat = "";
		int distance = 0;
		Queue q = new Queue(size * size);
		Room ptr;
		q.enqueue(rooms[0][0]);
		rooms[0][0].visited = true;
		rooms[0][0].distance = distance;
		
		while (!q.isEmpty()) {
			ptr = q.dequeue();
			pathTaken += ptr.roomNum + " ";
			if (ptr == rooms[size - 1][size - 1]) {
				System.out.println("Rooms visited by BFS: " + pathTaken);
				System.out.println(getShortestPath(rooms[size - 1][size - 1]));
				printShortestPath(graphicPath);
			}
			for (int i = 0; i < 4; i++) {
				Room r = ptr.getRoom(i);
				if (r.wall == false && r.visited == false) {
					q.enqueue(r);
					r.visited = true;
					r.distance = distance + 1;
				}
			}
			distance++;
		}
		if (rooms[size - 1][size - 1].visited == false) {
			System.out.println("No BFS solution was found for this maze.");
		}
	}

	public void dfsSolution() {
		String pathTaken = "";
		String solPath = "";
		int distance = 0;
		Stack s = new Stack(size * size);
		Room ptr;
		s.push(rooms[0][0]);
		rooms[0][0].visited = true;
		rooms[0][0].distance = distance;
		
		while (!s.isEmpty()) {
			ptr = s.pop();
			pathTaken += ptr.roomNum + " ";
			if (ptr == rooms[size - 1][size - 1]) {
				System.out.println("Rooms visited by DFS: " + pathTaken);
				System.out.println(getShortestPath(rooms[size - 1][size - 1]));
				printShortestPath(graphicPath);
			}
			for (int i = 0; i < 4; i++) {
				Room r = ptr.getRoom(i);
				if (r.wall == false && r.visited == false) {
					s.push(r);
					r.visited = true;
					r.distance = distance + 1;
				}
			}
			distance++;
		}
		if (rooms[size - 1][size - 1].visited == false) {
			System.out.println("No DFS solution was found for this maze.");
		}
	}
	
	public String getShortestPath(Room room) {
		graphicPath = new String[size * size];
		graphicPath[size * size - 1] = "X ";
		int step = 0;
		int temp = room.distance;
		String sol = "This is the path(in reverse): " + room.roomNum + " ";
		while (room.distance != 0) {
			for (int i = 0; i < 4; i++) {
				Room r = room.getRoom(i);
				if(r.distance < temp && r.wall == false) {
					temp = r.distance;
					step = i;
				}
			}
			room = room.getRoom(step);
			graphicPath[room.roomNum] = "X ";
			sol += room.roomNum + " ";
		}
		return sol;
	}
	
	/*HELPER*/
	
	public int chooseAdjacentRoom(int room, int side) {
		int a = room;
		if (side == 0) {
			a = a - size;
		}
		else if (side == 1) {
			a = a + size;
		}
		else if (side == 2) {
			a = a + 1;
		} 
		else if (side == 3) {
			a = a - 1;
		}
		return a;
	}
	
	private int chooseRandomWall(int room) {
		boolean[] isBorder = new boolean[4];
		if (room < size) {
			isBorder[0] = true;
		}
		if (room + size >= (size * size)) {
			isBorder[1] = true;
		}
		if ((room + 1) % size == 0) {
			isBorder[2] = true;
		}
		if (room % size == 0) {
			isBorder[3] = true;
		}
		int wall;
		do {
			wall = rand.nextInt(4);
		} 
		while (isBorder[wall] == true);
		return wall;
	}
	
	private int getAdjacentWall(int wall) {
		wall++;
		int adjWall;
		if (wall % 2 == 0) {
			adjWall = wall - 1;
		} 
		else {
			adjWall = wall + 1;
		}
		return adjWall - 1;
	}
	
	public void breakWall(Room rm1, int wall1, Room rm2, int wall2) {
		rm1.setWall(wall1, rm2);
		rm2.setWall(wall2, rm1);
	}
	
	public void resetMaze() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				rooms[i][j].visited = false;
			}
		}
	}
	
	public boolean isBorder(int room, int side) {
		boolean border = false;
		
		if (room < size && side == 0) {
			border = true;
		}
		if ((room + size) >= (size * size) && side == 1) {
			border = true;
		}
		if ((room + 1) % size == 0 && side == 2) {
			border = true;
		}
		if (room % size == 0 && side == 3) {
			border = true;
		}
		return border;
	}
	
	public void printShortestPath(String[] path) {
		for (int x = 0; x < size * size; x++) {
			if (x % size == 0 && x != 0) {
				System.out.print("\n");
			}
			if (path[x] != null) {
				System.out.print(path[x]);
			}
			else {
				System.out.print(" ");
			}
		}
		System.out.println("\n");
	}
	
	public String printMaze() {
		String point = " ";
		for (int x = 1; x < size; x++) {
			point += "__";
		}
		point += "\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j == 0) {
					point += "|";
				}
				point += rooms[i][j].printRoom();
				if ((j + 1) % size == 0) {
					point += "\n";
				}
			}
		}
		return point;
	}
	
	public void createMazeFile() {
		try {
			File infile = new File("maze.txt");
			FileWriter input = new FileWriter(infile);
			BufferedWriter in = new BufferedWriter(input);
			
			System.out.println("File" + infile + " was created!");
			in.write(String.valueOf(size));
			in.newLine();
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					for (int k = 0; k < 4; k++) {
						if (rooms[i][j].sides[k].wall == true) {
							in.write("1 ");
						}
						else {
							in.write("0 ");
						}
					}
					in.newLine();
				}
			}
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[]args) {
		Scanner input = new Scanner(System.in);
		
		Maze maze = new Maze();
		
		if (args.length != 0) {
			maze = new Maze(args[0]);
		}
		else {
			int size = 0;
			while (size <= 1) {
				System.out.print("Enter size of maze: ");
				size = input.nextInt();
				if (size <= 1) {
					System.out.println("Error: size of maze must be bigger than 1\n");
				}
			}
			maze = new Maze(size);
			maze.createMaze();
		}
		
		maze.bfsSolution();
		maze.resetMaze();
		maze.dfsSolution();
		
		System.out.print("Would you like to create a .txt file for this maze? (y/n): ");
		String user = input.next();
		if (user.equalsIgnoreCase("Y")) {
			maze.createMazeFile();
		}
	}
		
}
