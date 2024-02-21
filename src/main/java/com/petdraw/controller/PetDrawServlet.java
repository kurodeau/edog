package com.petdraw.controller;



import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petdraw.entity.PetDrawVO;
import com.petdraw.service.PetDrawService;

public class PetDrawServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - �|��ID **********************/
			String str = req.getParameter("memberid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J�|���s��");
			}
			// Send the use back to the form, if there were errors(���~�B�z)
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/PetDraw/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}
			// �ഫ�s�������
			Integer memberid = null;
			try {
				memberid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("�|���s���榡�����T");
			}
			// Send the use back to the form, if there were errors (�A�����~�B�z)
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/PetDraw/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			PetDrawService petDrawSvc = new PetDrawService();
			PetDrawVO petDrawVO = petDrawSvc.getOnePetDraw(memberid);
			// �ˬd�O�_��Ѥw�g��L�d
		    if (petDrawVO != null) {
		        java.util.Date drawDate = petDrawVO.getPetDrawTime();
		        java.util.Date today = new java.sql.Date(System.currentTimeMillis());

		        if (drawDate.equals(today)) {
		            errorMsgs.add("���Ѥw�g��L�d���d�F�A�Щ��ѦA�ӡI");
		        }
		    }
			// �d�L��ƳB�z
			if (petDrawVO == null) {
				errorMsgs.add("�d�L���");
			}
			// Send the use back to the form, if there were errors(�A�����~�B�z)
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/petDraw/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("petDrawVO", petDrawVO); // ��Ʈw���X��PetDrawVO����,�s�Jreq
			String url = "/petDraw/listOnePetDraw.jsp";
			// ��s��d���
		    petDrawSvc.updateDrawDate(memberid);
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnepetDraw.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllpetDraw.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ****************************************/
			Integer memberid = Integer.valueOf(req.getParameter("memberid"));

			/*************************** 2.�}�l�d�߸�� ****************************************/
			PetDrawService petDrawSvc = new PetDrawService();
			PetDrawVO petDrawVO = petDrawSvc.getOnePetDraw(memberid);

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
			req.setAttribute("petDrawVO", petDrawVO); // ��Ʈw���X��petDrawVO����,�s�Jreq
			String url = "/PetDraw/update_PetDraw_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_petDraw_input.jsp
			successView.forward(req, res);
		}


	}
}
