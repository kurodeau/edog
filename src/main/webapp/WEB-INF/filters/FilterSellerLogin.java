package filters;

import java.io.*;
import javax.servlet.*;

public class FilterSellerLogin implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object sellerAccount = session.getAttribute("sellerAccount");
		if (sellerAccount == null) {
			session.setAttribute("location", req.getRequestURI());
			// 依照login頁面名稱來改這裡字串的內容
			res.sendRedirect(req.getContextPath() + "/login.html");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
		config = null;
	}
}