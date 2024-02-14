package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/testForm")
public class Testform extends HttpServlet {

	private static class ValidationResult {
		private String status;
		private String message;

		public ValidationResult(String status, String message) {
			this.status = status;
			this.message = message;
		}

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		System.out.println("ENTER");
		// Set the content type of the response
		response.setContentType("application/json");

		// Get the email parameter from the request
		String email = request.getParameter("email");

		// Perform server-side validation logic here
		boolean isValid = isValidEmail(email);

		// Create a ValidationResult object
		ValidationResult result = new ValidationResult(isValid ? "OK" : "ERROR",
				isValid ? "" : "Invalid email address from Servlet");

		// Serialize ValidationResult to JSON using Gson
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(result);

		// Send the JSON response back to the client
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
		out.flush();
	}

	private boolean isValidEmail(String email) {
		// Add your email validation logic here
		// For simplicity, let's assume any non-empty email is considered valid
		return email != null && !email.trim().isEmpty() && email.charAt(0)=='A';
	}
}