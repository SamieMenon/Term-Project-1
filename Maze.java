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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
