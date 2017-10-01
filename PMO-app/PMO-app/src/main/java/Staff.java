import java.util.List;

import org.sql2o.Connection;

import com.fasterxml.jackson.annotation.JsonProperty;

/* model(entity) of staff, attributes correlate with DB's staff fields */

public class Staff extends Model{
	// attributes
	private int staffID;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	//CRU
	final static String insertQ = "INSERT INTO PMODB.STAFF (firstName, lastName, username, password) "
			+ "VALUES (:firstName, :lastName, :username, :password)";
	final static String getAllQ = "SELECT * FROM PMODB.STAFF";
	final static String updateQ = "UPDATE PMODB.STAFF SET firstName = :firstName, lastName = :lastName, username = :username, password = :password WHERE staffID = :staffID";

	//constructor
	public Staff (int staffID, String firstName, String lastName, String username, String password) {
		super();
		this.staffID = staffID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username; 
		this.password = password;
	}

	//overloaded constructor no.1 for Staff class for flexibility!
	public Staff (String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//overloaded constructor no.2 for Staff class for flexibility!
	public Staff() {
		super();
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String la_name) {
		this.lastName = la_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public static int createStaff(Staff staff) { // no need create instance of Staff class to use a method, straight Staff.createStaff because it is static
		int createdId = 0;
		try (Connection connecta = Staff.getSql2o().beginTransaction()) {
			connecta.createQuery(insertQ)
			.addParameter("firstName", staff.getFirstName()).addParameter("lastName", staff.getLastName())
			.addParameter("username", staff.getUsername()).addParameter("password", staff.getPassword()) 
			.executeUpdate().getKey(Integer.class); // MySQL syntax
			connecta.commit();
			createdId = 1; 
		}catch(Exception e){
			e.printStackTrace();
		}
		return createdId;
	}

	public static List<Staff> getAllStaff(){ //returns a list object (in it containing all staff)
		try (Connection connecta = Staff.getSql2o().open()) {
			List<Staff> staffList= connecta.createQuery(getAllQ).executeAndFetch(Staff.class);
			return staffList;
		}
	}

	public static int updateStaff(Staff staff) { 
		try (Connection connecta = Staff.getSql2o().beginTransaction()) {
			connecta.createQuery(updateQ)
			.addParameter("firstName", staff.getFirstName()).addParameter("lastName", staff.getLastName())
			.addParameter("username", staff.getUsername()).addParameter("password", staff.getPassword())
			.addParameter("staffID", staff.getStaffID())
			.executeUpdate(); 
			connecta.commit();

		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}


}
