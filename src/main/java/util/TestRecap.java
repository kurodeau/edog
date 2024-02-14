package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@WebServlet("/testrecap")
public class TestRecap extends HttpServlet {

	private static final String API_KEY = "AIzaSyDNcNLMDs5PGs_JPrZpSMpUFA6WZkR5xmM";
	private static final String PROJECT_ID = "edogproject-1707391807859";
	private static final String SITE_KEY = "6LdmTWspAAAAAHYjkR2LmP56JSjUlirs0sBb8hqx";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		Enumeration<String> parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			// Do something with paramName
			System.out.println(paramName);
		}

		try {
			String recaptchaResponse = req.getParameter("g-recaptcha-response");
			String userAction = "login"; // Replace with the actual user action (optional)

			String requestBody = buildJsonRequestBody(recaptchaResponse, userAction);
			sendPostRequest(requestBody, resp);
		} catch (IOException e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}

	private String buildJsonRequestBody(String token, String userAction) {
		// Build the JSON request body
		Event event = new Event(token, userAction, SITE_KEY);
		String jsonRequestBody = new Gson().toJson(event);
		System.out.println("jsonRequestBody====" + jsonRequestBody);
		return jsonRequestBody;
	}

	private void sendPostRequest(String requestBody, HttpServletResponse resp) throws IOException {
		// Create OkHttpClient
		OkHttpClient client = new OkHttpClient();

		// Construct the URL with API_KEY
		String url = "https://recaptchaenterprise.googleapis.com/v1/projects/" + PROJECT_ID + "/assessments?key="
				+ API_KEY;

		// Parse the existing JSON string into a JsonObject
		JsonObject originalJson = JsonParser.parseString(requestBody).getAsJsonObject();

		// Create a new JsonObject with "event" as the outer object
		JsonObject newJson = new JsonObject();
		newJson.add("event", originalJson);

		// Build the request
		Request request = new Request.Builder().url(url)
				.post(RequestBody.create(MediaType.get("application/json"), newJson.toString())).build();

		// Execute the request and write the response to the servlet response
		try (Response response = client.newCall(request).execute()) {
			resp.getWriter().write(response.body().string());
		} catch (IOException e) {
			// Handle IO exception
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}

	private static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Handle encoding exception
			e.printStackTrace();
			return value;
		}
	}

	// Helper class representing the structure of the "event" in the JSON request
	// body
	static class Event {
		String token;
		String expectedAction;
		String siteKey;

		public Event(String token, String expectedAction, String siteKey) {
			this.token = token;
			this.expectedAction = expectedAction;
			this.siteKey = siteKey;
		}
	}
}
