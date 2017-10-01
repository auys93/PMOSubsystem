import java.util.List;

import org.sql2o.Connection;

import com.fasterxml.jackson.annotation.JsonProperty;

/* model(entity) of report, attributes correlate with DB's report fields */

public class Report extends Model{
	// attributes
	private int reportID;
	private String reportHandler;
	private String reportLiaison;
	private String liaisonPass; //
	private String crisisType;
	private String reportStatus;
	private String locationLat;
	private String locationLong;
	private String description1;
	private String description2;
	private String description3;
	private int teamID;
	private String timestamp;
	private int deleted;

	//CRU
	final static String insertQ = "INSERT INTO PMODB.REPORT (reportID, reportHandler, reportLiaison, liaisonPass, crisisType, reportStatus, locationLat, locationLong, description1, description2, description3, teamID, timestamp, deleted) VALUES "
			+ "(:reportID, :reportHandler, :reportLiaison, :liaisonPass,"
			+ " :crisisType, :reportStatus, :locationLat, :locationLong,"
			+ " :description1, :description2, :description3, :teamID,"
			+ " NOW(), :deleted)";
	final static String getAllQ = "SELECT * FROM PMODB.REPORT";
	final static String updateQ = "UPDATE PMODB.REPORT SET reportStatus = :reportStatus, timestamp = NOW() WHERE reportID = :reportID";

	//constructor, no empty constructor cannot work!
	public Report () {

	}

	public Report (int reportID, String reportHandler, String reportLiaison, String liaisonPass, String crisisType, String reportStatus, String locationLat, String locationLong, String description1, String description2, String description3, int teamID, String timestamp, int deleted) {
		super();
		this.reportID = reportID;
		this.reportHandler = reportHandler;
		this.reportLiaison = reportLiaison;
		this.liaisonPass = liaisonPass;
		this.crisisType = crisisType;
		this.reportStatus = reportStatus;
		this.locationLat = locationLat;
		this.locationLong = locationLong;
		this.description1 = description1;
		this.description2 = description2;
		this.description3 = description3;
		this.teamID = teamID;
		this.timestamp = timestamp;
		this.deleted = deleted;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
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

	public String getCrisisType() {
		return crisisType;
	}

	public void setCrisisType(String crisisType) {
		this.crisisType = crisisType;
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

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}


	public static int createReport(Report report) { //no need create instance of Message class to use this method, straight Message.createReport because it is static
		int createdId = 0;
		try (Connection connecta = Report.getSql2o().beginTransaction()) {
			connecta.createQuery(insertQ)
			.addParameter("reportID", report.getReportID()).addParameter("reportHandler", report.getReportHandler())
			.addParameter("reportLiaison", report.getReportLiaison()).addParameter("liaisonPass", report.getLiaisonPass())
			.addParameter("crisisType", report.getCrisisType()).addParameter("reportStatus", report.getReportStatus())
			.addParameter("locationLat", report.getLocationLat()).addParameter("locationLong", report.getLocationLong())
			.addParameter("description1", report.getDescription1()).addParameter("description2", report.getDescription2())
			.addParameter("description3", report.getDescription3()).addParameter("teamID", report.getTeamID())
			.addParameter("deleted", report.getDeleted())
			.executeUpdate(); // MySQL syntax
			connecta.commit();
			createdId = 1;

		}catch(Exception e){
			e.printStackTrace();
		}
		return createdId; 
	}


	public static List<Report> getAllReport(){ //returns a list object (in it containing all report)
		try (Connection connecta = Report.getSql2o().open()) {
			List<Report> reportList= connecta.createQuery(getAllQ).executeAndFetch(Report.class);
			return reportList;
		}
	}


	public static int updateReport(Report report) { 
		try (Connection connecta = Report.getSql2o().beginTransaction()) {
			System.out.println(report.getReportStatus().toString());
			connecta.createQuery(updateQ).addParameter("reportStatus", report.getReportStatus())
			.addParameter("reportID", report.getReportID())
			.executeUpdate(); 
			connecta.commit();

		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}










}
