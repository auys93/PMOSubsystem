import java.util.List;

import org.sql2o.Connection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Strategy extends Model {
	//CRU
	// attributes
	private int strategyID;
	private String description1;
	private String description2;
	private String description3;
	private String strategyStatus;
	private int reportID;
	private String username;


	//CRU tested
	final static String insertQuery = "INSERT INTO PMODB.STRATEGY( "
			+ "strategyID, description1, description2, "
			+ "description3, strategyStatus, reportID, username) "
			+ "VALUES "
			+ "(:strategyID,:description1, :description2,"
			+ " :description3, :strategyStatus, :reportID, :username)";
	final static String getAllQ = "SELECT * FROM PMODB.STRATEGY";
	final static String updateQ = "UPDATE PMODB.STRATEGY SET strategyStatus = :strategyStatus WHERE strategyID = :strategyID";

	//constructor, no empty constructor cannot work!
	public Strategy () {
		super();
	}

	public Strategy (int strategyID, String description1, String description2, String description3, String strategyStatus, int reportID, String username) {
		super();
		this.strategyID = strategyID;
		this.description1 = description1;
		this.description2 = description2;
		this.description3 = description3;
		this.strategyStatus = strategyStatus;
		this.reportID = reportID;
		this.username = username;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
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

	public int getStrategyID() {
		return strategyID;
	}

	public void setStrategyID(int strategyID) {
		this.strategyID = strategyID;
	}

	public String getStrategyStatus() {
		return strategyStatus;
	}

	public void setStrategyStatus(String strategyStatus) {
		this.strategyStatus = strategyStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public static int createStrategy(Strategy strategy) { //no need create instance of Message class to use this method, straight Message.createReport because it is static
		int createdId = 0;
		try (Connection connecta = Strategy.getSql2o().beginTransaction()) {
			connecta.createQuery(insertQuery)
			.addParameter("strategyID", strategy.getStrategyID()).addParameter("description1", strategy.getDescription1())
			.addParameter("description2", strategy.getDescription2()).addParameter("description3", strategy.getDescription3())
			.addParameter("strategyStatus", strategy.getStrategyStatus()).addParameter("reportID", strategy.getReportID())
			.addParameter("username", strategy.getUsername())
			.executeUpdate(); // MySQL syntax
			connecta.commit();
			createdId = 1; //if success, return 1, won't go into the last line's 'return 1'
		}catch(Exception e){
			e.printStackTrace();
		}
		return createdId;
	}


	public static List<Strategy> getAllStrategy(){ //returns a list object (in it containing all strategies)
		try (Connection connecta = Strategy.getSql2o().open()) {
			List<Strategy> strategyList= connecta.createQuery(getAllQ).executeAndFetch(Strategy.class);
			return strategyList;
		}
	}

	public static int updateStrategy(Strategy strategy) { 
		try (Connection connecta = Strategy.getSql2o().beginTransaction()) {
			System.out.println(strategy.getStrategyStatus().toString());
			connecta.createQuery(updateQ).addParameter("strategyStatus", strategy.getStrategyStatus())
			.addParameter("strategyID", strategy.getStrategyID())
			.executeUpdate(); 
			connecta.commit();

		}catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}




}
