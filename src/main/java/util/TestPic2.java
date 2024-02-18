package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/test/TestPic2")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 限制上傳檔案的最大大小 (此例為 5 MB)
public class TestPic2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
    	request.setCharacterEncoding("utf-8");
        try {
        	
        	String a = (String) request.getParameter("procductName");
        	System.out.println("procductName : "+a);
        	String b = (String) request.getParameter("mainImage");
        	System.out.println("procductName : "+b);
        	
        	
        	
            int imageCount = 0;
            Collection<Part> fileParts = request.getParts();

            for (Part filePart : fileParts) {
                String partName = filePart.getName();
                String contentType = filePart.getContentType();

                if (contentType != null && contentType.startsWith("image")) {
                    // Process image part
                    imageCount++;
                    InputStream fileContent = filePart.getInputStream();
                    byte[] bytes = fileContent.readAllBytes();
                    String base64Image = Base64.getEncoder().encodeToString(bytes);

                    // Handle the image data as needed

                    // Rest of your image processing code...
                    System.out.println("part name>>" + partName);
                    System.out.println("contentType>>"+contentType);
                    System.out.println("part submitted filename>>" + filePart.getSubmittedFileName());
                    System.out.println("Base64 Image: data:image/png;base64," + base64Image.substring(0, 20));
                    System.out.println("===============");
                } else {
                    // Process non-image part
                    InputStream textContent = filePart.getInputStream();
                    byte[] textBytes = textContent.readAllBytes();
                    
                    
                    // Handle the non-image data as needed
                    // For example, if partName is "name" or "mobile"
                    String textData = new String(textBytes, StandardCharsets.UTF_8);
                    System.out.println("part name>>" + partName);
                    System.out.println("contentType>>"+contentType);
                    System.out.println("part submitted filename>>" + filePart.getSubmittedFileName());
                    System.out.println("Text Data: " + textData);
                    System.out.println("===============");

                }
            }

            

        } catch (Exception e) {
            response.getWriter().println("Error reading image: " + e.getMessage());
        }
    }
}