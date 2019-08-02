
public class Room {
	boolean visited, wall;
	Room sides[];
	int roomNum;
	int distance = Integer.MAX_VALUE;
	
	Room() {
		sides = new Room[4];
		for (int i = 0; i < 4; i++) {
			sides[i] = new Room(true);
		} 
		visited = false;	
	}
	
	Room(boolean wall) {
		this.wall = wall;
		visited = false;
	}
	
	Room(int wall) {
		if (wall == 1) {
			this.wall = true;
		} else if (wall == 0) {
			this.wall = false;
		}
	}
	
	public void setWall(int wall, Room room) {
		sides[wall] = room;
	}
	
	public Room getRoom(int side) {
		return sides[side];
	}
	
	public boolean isWall() {
		return wall;
	}
	
	public String printRoom() {
		String s = "";
		if (sides[1].wall != false) {
			s += "__";
		} else if (sides[2].wall != false) {
			s += "|";
		} else {
			s += " ";
		} 
		return s;
	}
	
}
