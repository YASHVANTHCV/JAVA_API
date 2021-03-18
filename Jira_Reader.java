import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//import org.json.simple.parser.*;
//import org.json.simple.*;

public class Jira_Reader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String loginResponse = "";
//		String sessionId = "";
//		String JsonData = "";
		String JiraURL = "https://jira.sonymobile.net/rest/api/latest/issue/PSTQA-80/watchers";
		String UserName = "";
		String Password = "";
		boolean errorsOcurred = false;
		
		if(!errorsOcurred) {
			loginResponse = loginToJira(JiraURL, UserName, Password);
			if(loginResponse == "ERROR")
				{
				errorsOcurred = true;
				}
		}
//		
//		if(!errorsOcurred) {
//			sessionId = SessionId(loginResponse);
//			if(sessionId == "ERROR")
//			{
//				errorsOcurred = true;
//			}
//		}
//		
//		if(!errorsOcurred) {
//			JsonData = JsonData();
//			if(JsonData == "ERROR")
//			{
//				errorsOcurred = true;
//			}
//		}
		
		if(!errorsOcurred) {
			System.out.println("success");
		}
		else {
			System.out.println("failure");
		}

	}
		public static String loginToJira(String JiraURL, String UserName, String Password) {
			String loginResponse = "";
			URL url = null;
			HttpURLConnection connect = null;
			String input = "";
			OutputStream os = null;
			BufferedReader br = null;
			String output = null;
			try {
				url = new URL(JiraURL);
				//url = new URL(baseURL + loginURL);
				connect = (HttpURLConnection) url.openConnection();
				connect.setDoOutput(true);
				connect.setRequestMethod("POST");
				connect.setRequestProperty("content-Type", "application/json");
				
				input = "{\"username\":\"" + UserName + "\",\"password\":\"" + Password + "\"}"; 
				
				os = connect.getOutputStream();
				os.write(input.getBytes());
				os.flush();
				
				if(connect.getResponseCode() == 200) {
					br = new BufferedReader(new InputStreamReader((connect.getInputStream())));
					while((output = br.readLine()) != null) {
						loginResponse += output;
					}
					connect.disconnect();
					System.out.println(loginResponse);
				}
						
			}catch(Exception e) {
				System.out.println("error in loginToJira" + e);
				loginResponse = "ERROR";
			}
			System.out.println("LoginResponse:");
			System.out.println(loginResponse);
			return loginResponse;
		}
		
		
//		public static String SessionId(String input) {
//			String sessionId = "";
//			try {
//				JSONParser parser = new JSONParser();
//				Object obj = parser.parse(input);
//				JSONObject jobject = (JSONObject) obj;
//				JSONObject sessionJobj = (JSONObject) jobject.get("session");
//				sessionId = (String) sessionJobj.get("value");
//			}catch(Exception e) {
//				System.out.println("error in SessionID " + e);
//				sessionId = "ERROR";
//			}
//			System.out.println("sessionId:");
//			System.out.println(sessionId);
//			return sessionId;
//		}
//		
//		
//		public static String JsonData() {
//			String JsonData = "";
//			return JsonData;
//		}
}
