import java.util.List;

import org.sql2o.Connection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportUpdate extends Model {


	// attributes
	private int updateID;
	private String reportHandler;
	private String reportLiaison;
	private String liaisonPass; //
	private String locationLat;
	private String locationLong;
	private String description1;
	private String description2;
	private String description3;
	private String timestamp;
	private int reportID;

	//CR
	final static String insertQ = "INSERT INTO PMODB.REPORT_UPDATE ("
			+ "updateID, reportHandler, reportLiaison, liaisonPass, "
			+ "locationLat, locationLong, description1, description2, "
			+ "description3, timestamp, reportID) VALUES "
			+ "(:updateID, :reportHandler, :reportLiaison, :liaisonPass,"
			+ " :locationLat, :locationLong, :description1, :description2,"
			+ " :description3, NOW(), :reportID)";
	final static String getAllQ = "SELECT * FROM PMODB.REPORT_UPDATE";

	//constructor, no empty constructor cannot work!
	public ReportUpdate () {

	}

	public ReportUpdate (int updateID, String reportHandler, String reportLiaison, String liaisonPass, String locationLat, String locationLong, String description1, String description2, String description3, String timestamp, int reportID) {
		super();
		this.setUpdateID(updateID);
		this.reportHandler = reportHandler;
		this.reportLiaison = reportLiaison;
		this.liaisonPass = liaisonPass;
		this.locationLat = locationLat;
		this.locationLong = locationLong;
		this.description1 = description1;
		this.description2 = description2;
		this.description3 = description3;
		this.timestamp = timestamp;
		this.reportID = reportID;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}


	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getReportHandler() {
		return reportHandler;
	}

	public void setReportHandler(String reportHandler) {
		this.reportHandler = reportHandler;
	}

	public String getReportLiaison() {
		return reportLiaison;
	}

	public void setReportLiaison(String reportLiaison) {
		this.reportLiaison = reportLiaison;
	}

	public String getLiaisonPass() {
		return liaisonPass;
	}

	public void setLiaisonPass(String liaisonPass) {
		this.liaisonPass = liaisonPass;
	}

	public String getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(String locationLat) {
		this.locationLat = locationLat;
	}

	public String getLocationLong() {
		return locationLong;
	}

	public void setLocationLong(String locationLong) {
		this.locationLong = locationLong;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}

	public int getUpdateID() {
		return updateID;
	}

	public void setUpdateID(int updateID) {
		this.updateID = updateID;
	}

	public static int createReportUpdate(ReportUpdate reportUpdate) { //no need create instance of Message class to use this method, straight Message.createReport because it is static
		int createdId = 0;
		try (Connection connecta = ReportUpdate.getSql2o().beginTransaction()) {
			connecta.createQuery(insertQ)
			.addParameter("updateID", reportUpdate.getUpdateID()).addParameter("reportHandler", reportUpdate.getReportHandler())
			.addParameter("reportLiaison", reportUpdate.getReportLiaison()).addParameter("liaisonPass", reportUpdate.getLiaisonPass())
			.addParameter("locationLat", reportUpdate.getLocationLat()).addParameter("locationLong", reportUpdate.getLocationLong())
			.addParameter("description1", reportUpdate.getDescription1()).addParameter("description2", reportUpdate.getDescription2())
			.addParameter("description3", reportUpdate.getDescription3()).addParameter("reportID", reportUpdate.getReportID())
			.executeUpdate(); // MySQL syntax
			connecta.commit();
			createdId = 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return createdId; 
	}


	public static List<ReportUpdate> getAllReportUpdate(){ //returns a list object (in it containing all report)
		try (Connection connecta = ReportUpdate.getSql2o().open()) {
			List<ReportUpdate> reportList= connecta.createQuery(getAllQ). executeAndFetch(ReportUpdate.class);
			return reportList;
		}
	}



}
