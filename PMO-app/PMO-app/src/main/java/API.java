import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;
import java.security.Security;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.sql2o.Sql2o;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Utils;

//the controller for PMO API
public class API {
	public static void main(String[] args) {
		addSecurityProvider();
		//port(123); //specify port as 123 instead of default 4567
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

		post("/notify", (req, res) -> {

			//Wei Siang's Notification
			//****************************************************************
			String endpoint = "https://fcm.googleapis.com/fcm/send/eb38pyJM3Us:APA91bGqgwjE73w7sNJJdmhH9M7FD9c2Fb4AMGyWpgonmFwGA1W8X3rvgvhbiuqwO9BHcOhdAw6UynDSpCGKPdxYAy_WtoSwo7c6liIqpsbFzBOTFPI2O9YFHAIHziFFohWQCTbIIrjw";
			String userPublicKey = "BDJ_xzdA6Xtw-T8XG1NhF4IhrlN4wE8HdV18c3MEmIxhfaTfGvyXsPui4zb3ESRi0Ny5yKiFuFJJVcMG-M5UxdQ=";
			String userAuth = "uE_h1x_OMKnhdRQMR6B4fw==";
			//****************************************************************


			
			//Unique for server
			//****************************************************************
			String serverPublic = "BHJ1c9VcMDjGxY9r0hpzVedE_5dSjHKCNyquNWoaCiE3hmhBjPufSQuM9zmx2NPZBPlJPaG1_TR6z8vlq37G-A4";
			String serverPrivate = "E1Fck7sUAgUHfNBPubBfB2iqQA3Es5B3DcZWvUCRWrk";
			//****************************************************************

			try {
				// Construct notification
				Notification notification_update;
				notification_update = new Notification(endpoint, userPublicKey, userAuth, getPayload("[EF] Bombing in Jurong","2 more explosions heard in Jurong MRT.", "Update"));
				Notification notification_crisis = new Notification(endpoint, userPublicKey, userAuth, getPayload("[911] Explosives in Changi","Explosives discovered in Changi.", "Crisis"));
				Notification notification_approved = new Notification(endpoint, userPublicKey, userAuth, getPayload("[PMO] Bombing in Jurong","Operation Fireworks good to go", "PMOApproval"));


				PushService pushService = new PushService();
				pushService.setSubject("mailto:admin@martijndwars.nl");
				pushService.setPublicKey(Utils.loadPublicKey(serverPublic));
				pushService.setPrivateKey(Utils.loadPrivateKey(serverPrivate));
				pushService.send(notification_update);
				pushService.send(notification_crisis);
				pushService.send(notification_approved);

			} catch (Exception e) {
				// TODO Auto-generated catch block
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
	
	private static byte[] getPayload(String title, String message, String notificationType) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("title", title);
		jsonObject.addProperty("message", message);
		jsonObject.addProperty("notificationType", notificationType);

		return jsonObject.toString().getBytes();
	}
	
	//bouncy castle
	public static void addSecurityProvider() {
		Security.addProvider(new BouncyCastleProvider());
	}

}
