import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is part of the "Haunted Restaurant" application.
 * "Haunted Restaurant" is a very simple, text based adventure game. Users must
 * enter the restaurant either through the bar or the lobby, and make thier way
 * through the restaurant while dodging ghosts. There is a secret exit through
 * the restroom in the back... if the player makes it that far.
 * 
 * Connects and reads data from the Zuul database
 * 
 * @author Michael Le-Reiver
 * @version 2015.11.30
 *
 */
public class ZuulDB {
	private static final String DB_NAME = "z233j_LeReiver";
	private static final String DB_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/" + DB_NAME;
	private static final String USERNAME = "z233j_LeReiver";
	private static final String PASSWORD = "nr4Pe2@rd";
	private static final String GET_ROOM_SQL = "SELECT * FROM ROOM";
	private static final String GET_ROOM_EXIT_SQL = "SELECT * FROM ROOM_EXIT WHERE SourceRoomId = ?";
	private static final String GET_SCENARIO_SQL = "SELECT StartRoomID FROM SCENARIO";

	private Scenario scenario;
	private ArrayList<Room> roomObject;
	private ArrayList<Room> rooms;
	
	/**
	 * Create a Zuul Object Reads from the Zuul database and populates rooms
	 * exits, and items.
	 */
	public ZuulDB() {
		scenario = readScenario();
		roomObject = new ArrayList<>();
	}

	/**
	 * Create and return a connection to the database
	 * 
	 * @return connection to the Zuul database
	 */

	private Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return connection;
	}

	/**
	 * Read Zuul info from the Scenario table If an error occurs, a stack trace
	 * is printed to standard error and an empty list is returned.
	 * 
	 * @return scenario The game scenario
	 */
	public Scenario readScenario() {
		readStartRoom();
		readRooms();
		return scenario;
	}

	/**
	 * Read Start Room from database Set the Start Room for the Scenario
	 */
	private void readStartRoom() {
		Scenario scenarios = new Scenario(false);
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_SCENARIO_SQL);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				scenarios.setStartRoom(rooms.get(rs.getInt("StartRoom")));
			}
		} catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Read room info from the ROOM table. If an error occurs, a stack trace is
	 * printed to standard error and an empty list is returned.
	 * 
	 * @return the room and it's description
	 */
	private List<Room> readRooms() {
		List<Room> roomObject = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_ROOM_SQL);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				roomObject.add(new Room(rs.getString("Description"), rs.getInt("RoomID")));
			}
		} catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		return roomObject;
	}

	/**
	 * Read room exit info from the ROOM_EXIT table. If an error occurs, a stack
	 * trace is printed to standard error and an empty list is returned.
	 * 
	 * @param sourceRoom
	 * @return
	 * 
	 * @return the room exit and it's description
	 */
	private void readExits(Room sourceRoom, Room roomObject) {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(GET_ROOM_EXIT_SQL);) {
			for (Room room : rooms) {
				stmt.setInt(1, room.getRoomID());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String direction = rs.getString("Direction");
					Room destRoomId = roomObject;
					sourceRoom.setExit(direction, destRoomId);
				}
				rs.close();
			}
		} catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Finds room with matching id
	 * 
	 * @param sourceRoomId
	 */
	public Room findRoom(int roomId) {
		for (Room room : rooms) {
			if (roomId == room.getRoomID()) {
				return room;
			}
			Room sourceRoom = room;
			return sourceRoom;
		}
		return null;
	}

	/**
	 * @return list of rooms read from the zuul database
	 */
	public ArrayList<Room> getroomObject() {
		return roomObject;
	}
}
