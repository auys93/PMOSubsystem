import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.sql2o.Sql2o;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//the controller for PMO API
public class API {
	public static void main(String[] args) {
		//port(123); //specify port as 4567 instead of 123
		Sql2o sql2o = new Sql2o("jdbc:mysql://127.0.0.1:3306/pmodb", "root", "root");
		Model.setSql2o(sql2o);
		enableCORS("*", "*", "*");

		/* services that API provides to controller (all are sub-methods in main) */
		//All Staff
		get("/hello", (req, res) -> { 
			return "hello";
		});
		//All Staff
		get("/staff", (req, res) -> { 
			res.status(200);
			res.type("application/json");
			return dataToJson(Staff.getAllStaff());
		});


		post("/staff/:f_name/:l_name/:username/:password", (req, res) -> {
			Staff staff = new Staff(); //for neat, model
			staff.setFirstName(req.params(":f_name"));
			staff.setLastName(req.params(":l_name"));
			staff.setUsername(req.params(":username"));
			staff.setPassword(req.params(":password"));
			int createdID = Staff.createStaff(staff); //if don't want neat, 4 fields in the 'createStaff function, serializing is done in the entity class as it deals with the DB connections
			return createdID;
		});


		post("/staff/create", (req, res) -> { //can have multiple post as long as unique paths
			System.out.println(req.body ());
			ObjectMapper mapper = new ObjectMapper(); //ObjectMapper = jackson (read guide)
			try {
				Staff staff = mapper.readValue(req.body(),Staff.class); // map JSON to become an obj; in this case the vars' name test to see if need to match
				int createdID = Staff.createStaff(staff);
				return createdID;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 1;
		});

		post("/staff/update", (req, res) -> {
			System.out.println(req.body());
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				Staff s = mapper.readValue(req.body(),Staff.class); 
				int createdID = Staff.updateStaff(s);
				return createdID;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 1;
		});

		//All Report
		post("/report/create", (req, res) -> {
			System.out.println(req.body());
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				Report r = mapper.readValue(req.body(),Report.class); 
				int createdID = Report.createReport(r);
				return createdID;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 1;
		});

		get("/report", (req, res) -> {
			res.status(200);
			res.type("application/json");
			return dataToJson(Report.getAllReport());
		});

		post("/report/update", (req, res) -> {
			System.out.println(req.body());
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				Report r = mapper.readValue(req.body(),Report.class); 
				int createdID = Report.updateReport(r);
				return createdID;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 1;
		});

		//All ReportUpdate
		post("/reportupdate/create", (req, res) -> {
			System.out.println(req.body());
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				ReportUpdate r = mapper.readValue(req.body(),ReportUpdate.class); 
				int createdID = ReportUpdate.createReportUpdate(r);
				return createdID;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 1;
		});

		get("/reportupdate", (req, res) -> {
			res.status(200);
			res.type("application/json");
			return dataToJson(ReportUpdate.getAllReportUpdate());
		});

		//All Strategy
		post("/strategy/create", (req, res) -> {
			System.out.println(req.body());
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				Strategy s = mapper.readValue(req.body(),Strategy.class); 
				int createdID = Strategy.createStrategy(s);
				return createdID;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 1;
		});

		get("/strategy", (req, res) -> {
			res.status(200);
			res.type("application/json");
			return dataToJson(Strategy.getAllStrategy());
		});

		post("/strategy/update", (req, res) -> {
			System.out.println(req.body());
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				Strategy s = mapper.readValue(req.body(),Strategy.class); 
				int createdID = Strategy.updateStrategy(s);
				return createdID;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return 1;
		});




	}

	/* method to convert a data object received into a JSON object */
	private static Object dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data); //map obj to JSON 
			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWriter.");
		}
	}

	// Enables CORS on requests. This method is an initialization method and should be called once.
	private static void enableCORS(final String origin, final String methods, final String headers) {

		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		before((request, response) -> {
			response.header("Access-Control-Allow-Origin", origin);
			response.header("Access-Control-Request-Method", methods);
			response.header("Access-Control-Allow-Headers", headers);
			// Note: this may or may not be necessary in your particular application
			response.type("application/json");
		});
	}

}
