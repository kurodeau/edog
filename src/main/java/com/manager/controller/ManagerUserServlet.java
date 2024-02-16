package com.manager.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.manager.entity.*;
import com.manager.service.*;
import com.manager.model.*;

public class ManagerUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 如果useBodyEncoding=true，GET跟著POST編碼走
		// 如果useBodyEncoding=false，則跟請求轉發
		doPost(req, res);
		System.out.println("meowmeow");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			    /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("managerId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入後台管理者編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return; //提前中斷程式
				}
				
				Integer managerId = null;
				try {
					managerId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("後臺管理者編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return; //提前中斷程式
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				ManagerUserService managerUserSvc = new ManagerUserService();
				ManagerUserVO managerUserVO = managerUserSvc.getOneManagerUser(managerId);
				if (managerUserVO == null) {
					errorMsgs.add("不存在這個後臺管理者編號及對應資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return; //提前中斷程式
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("managerUserVO", managerUserVO); // ��Ʈw���X��empVO����,�s�Jreq
//			    String url_manager_listOneManagerUser_jsp = "/manager/listOneManagerUser.jsp";
//			    String url = url_manager_listOneManagerUser_jsp;
				String url = "/manager/listOneManagerUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneManagerUser.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			    /*************************** 1.接收請求參數 ****************************************/
				Integer managerId = Integer.valueOf(req.getParameter("managerId"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				ManagerUserService managerUserSvc = new ManagerUserService();
				ManagerUserVO managerUserVO = managerUserSvc.getOneManagerUser(managerId);
								
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("managerUserVO", managerUserVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/manager/update_ManagerUser_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { //來自update_emp_input
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//Integer managerId = Integer.valueOf(req.getParameter("managerId").trim());
				
String managername = req.getParameter("managername");
String managernameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (managername == null || managername.trim().length() == 0) {
					errorMsgs.add("後臺管理者稱呼不可以空白");
				} else if(!managername.trim().matches(managernameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("後臺管理者稱呼格式錯誤, 2~8個字元, 中文大小寫英文或數字");
	            }				
				
String managerPassword = req.getParameter("managerPassword");
String managerPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
				if (managerPassword == null || managerPassword.trim().length() == 0) {
					errorMsgs.add("後臺管理者密碼不可以空白");
				} else if(!managerPassword.trim().matches(managerPasswordReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("後臺管理者密碼格式錯誤, 6~8個字元, 大小寫英文或數字");
				}				
				
				Integer managerPer = null;
				try {
managerPer = Integer.valueOf(req.getParameter("managerPer").trim());
				} catch (NumberFormatException e) {
					managerPer = 10;
					errorMsgs.add("後臺管理者權限錯誤");
				}
				
				java.sql.Date createtime = null;
				try {
createtime = java.sql.Date.valueOf(req.getParameter("createtime").trim());
				} catch (IllegalArgumentException e) {
					createtime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("後臺管理者創建日期格式錯誤");
				}

Integer managerId = Integer.valueOf(req.getParameter("managerId").trim());

                ManagerUserVO managerUserVO = new ManagerUserVO();
                managerUserVO.setManagerId(managerId); 
                managerUserVO.setManagername(managername);
                managerUserVO.setManagerPassword(managerPassword);
                managerUserVO.setManagerPer(managerPer);
                managerUserVO.setCreatetime(createtime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("managerUserVO", managerUserVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manager/update_ManagerUser_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				ManagerUserService managerUserSvc = new ManagerUserService();
				managerUserVO = managerUserSvc.updateManagerUser(managername, managerPassword, managerPer, createtime, managerId);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("managerUserVO", managerUserVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/manager/listOneManagerUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
String managername = req.getParameter("managername");
String managernameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (managername == null || managername.trim().length() == 0) {
					errorMsgs.add("後臺管理者稱呼不可以空白");
				} else if(!managername.trim().matches(managernameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("後臺管理者稱呼格式錯誤, 2~8個字元, 中文大小寫英文或數字");
				}				
							
String managerPassword = req.getParameter("managerPassword");
String managerPasswordReg = "^[(a-zA-Z0-9_)]{6,10}$";
				if (managerPassword == null || managerPassword.trim().length() == 0) {
					errorMsgs.add("後臺管理者密碼不可以空白");
				} else if(!managerPassword.trim().matches(managerPasswordReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("後臺管理者密碼格式錯誤, 6~8個字元, 大小寫英文或數字");
				}				
							
				Integer managerPer = null;
				try {
managerPer = Integer.valueOf(req.getParameter("managerPer").trim());
				} catch (NumberFormatException e) {
					managerPer = 10;
					errorMsgs.add("後臺管理者權限錯誤");
				}

//Integer managerId = Integer.valueOf(req.getParameter("managerId").trim());


                ManagerUserVO managerUserVO = new ManagerUserVO();
//              managerUserVO.setManagerId(managerId); //流水號不能新增
                managerUserVO.setManagername(managername);
                managerUserVO.setManagerPassword(managerPassword);
                managerUserVO.setManagerPer(managerPer);
//              managerUserVO.setCreatetime(createtime); //自動日期不用新增

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("managerUserVO", managerUserVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manager/addManagerUser.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				ManagerUserService managerUserSvc = new ManagerUserService();
				managerUserVO = managerUserSvc.addManagerUser(managername, managerPassword, managerPer);
				
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/manager/listAllManagerUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // listAllManagerUser.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			/*************************** 1.接收請求參數 ***************************************/
				Integer managerId = Integer.valueOf(req.getParameter("managerId"));
				
				/*************************** 2.開始刪除資料 ***************************************/
				ManagerUserService managerUserSvc = new ManagerUserService();
				managerUserSvc.deleteManagerUser(managerId);
				
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/manager/listAllManagerUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
