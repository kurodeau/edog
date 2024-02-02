package com.remittance.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.remittance.model.RemittanceService;
import com.remittance.model.RemittanceVO;

public class RemittanceServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");  //�]�w�ШD���r���s�X��UTF-8:���F�T�O���T�B�z�Ӧ۫Ȥ�ݪ�Unicode�r�šA�H�����Ψ�L�DASCII�r�Ū��ýX���D
		String action = req.getParameter("action");

		// ��@�d��

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			String str = req.getParameter("remittanceId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J�״ڽs��");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/remittance/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			Integer remittanceId = null;
			try {
				remittanceId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("�״ڽs���榡�����T");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/remittance/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 2.�}�l�d�߸�� *****************************************/
			RemittanceService remittanceSvc = new RemittanceService();
			RemittanceVO remittanceVO = remittanceSvc.getOneRemittance(remittanceId); // �Q��Svc�̹�@����k�d��
			if (remittanceVO == null) {
				errorMsgs.add("�d�L���");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/remittance/select_page.jsp");
				failureView.forward(req, res);
				return;// �{�����_
			}

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
			req.setAttribute("remittanceVO", remittanceVO); // ��Ʈw���X��remittanceVO����,�s�Jreq
			String url = "/remittance/listOneRemittance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllremittance.jsp���ШD ���X�@�Ӹ�Ƭ��F��s��

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ****************************************/
			Integer remittanceId = Integer.valueOf(req.getParameter("remittanceId")); // listAllEmp 81��e�Ӫ����

			/*************************** 2.�}�l�d�߸�� ****************************************/
			RemittanceService remittanceSvc = new RemittanceService();
			RemittanceVO remittanceVO = remittanceSvc.getOneRemittance(remittanceId);

			/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
			req.setAttribute("remittanceVO", remittanceVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/remittance/update_remittance_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}

		// ��s

		if ("update".equals(action)) { // �Ӧ�update_remittance_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			Integer remittanceId = Integer.valueOf(req.getParameter("remittanceId").trim());
			Integer sellerId = Integer.valueOf(req.getParameter("sellerId").trim());

			java.sql.Timestamp remittanceEstimatedTime = null;
			try {
				remittanceEstimatedTime = java.sql.Timestamp
						.valueOf(req.getParameter("remittanceEstimatedTime").trim());
			} catch (IllegalArgumentException e) {
				remittanceEstimatedTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J�w�p�״ڮɶ�!");
			}

			java.sql.Timestamp remittanceTime = null;
			try {
				remittanceTime = java.sql.Timestamp.valueOf(req.getParameter("remittanceTime").trim());
			} catch (IllegalArgumentException e) {
				remittanceTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J�״ڮɶ�!");
			}

			java.sql.Timestamp settlementTime = null;
			try {
				settlementTime = java.sql.Timestamp.valueOf(req.getParameter("settlementTime").trim());
			} catch (IllegalArgumentException e) {
				settlementTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J����ɶ�!");
			}

			// turnover
			Integer turnover = null;
			try {
				turnover = Integer.valueOf(req.getParameter("turnover").trim());
			} catch (NumberFormatException e) {
				turnover = 0;
				errorMsgs.add("��~�B�ж�Ʀr.");
			}

			Integer handlingFee = null;
			try {
				handlingFee = Integer.valueOf(req.getParameter("handlingFee").trim());
			} catch (NumberFormatException e) {
				handlingFee = 0;
				errorMsgs.add("����O�ж�Ʀr.");
			}

			Integer remittanceAmount = null;
			try {
				remittanceAmount = Integer.valueOf(req.getParameter("remittanceAmount").trim());
			} catch (NumberFormatException e) {
				remittanceAmount = 0;
				errorMsgs.add("�״ڪ��B�ж�Ʀr.");
			}

			Integer remittanceStatus = null;
			try {
				remittanceStatus = Integer.valueOf(req.getParameter("remittanceStatus").trim());
			} catch (NumberFormatException e) {
				remittanceStatus = 0;
				errorMsgs.add("�״ڽж�Ʀr.");
			}

			String explanation = req.getParameter("explanation").trim();
			if (explanation == null || explanation.trim().length() == 0) {
				errorMsgs.add("�Բӻ����ФŪť�");
			}

			// remittanceVO
			RemittanceVO remittanceVO = new RemittanceVO();
			remittanceVO.setRemittanceId(remittanceId);
			remittanceVO.setSellerId(sellerId);
			remittanceVO.setRemittanceEstimatedTime(remittanceEstimatedTime);
			remittanceVO.setRemittanceTime(remittanceTime);
			remittanceVO.setSettlementTime(settlementTime);
			remittanceVO.setTurnover(turnover);
			remittanceVO.setHandlingFee(handlingFee);
			remittanceVO.setRemittanceAmount(remittanceAmount);
			remittanceVO.setRemittanceStatus(remittanceStatus);
			remittanceVO.setExplanation(explanation);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("remittanceVO", remittanceVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/remittance/update_remittance_input.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			RemittanceService remittanceSvc = new RemittanceService();
			remittanceVO = remittanceSvc.updateRemittance(remittanceId, sellerId, remittanceEstimatedTime,
					remittanceTime, settlementTime, turnover, handlingFee, remittanceAmount, remittanceStatus,
					explanation); // ��s�W�h�F���u�s��empno��@where�d�ߪ��Ѽ�

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			req.setAttribute("remittanceVO", remittanceVO); // ��Ʈwupdate���\��,���T����remittanceVO����,�s�Jreq
			String url = "/remittance/listOneRemittance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneRemittance.jsp
			successView.forward(req, res);
		}

		// insert

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
//			Integer remittanceId = Integer.valueOf(req.getParameter("remittanceId").trim());

			Integer sellerId = null;
			try {
				sellerId = Integer.valueOf(req.getParameter("sellerId").trim());
//	 Integer.valueOf(req.getParameter("sellerId").trim()); //�S�ᤩ��Ȥ���I�I
			} catch (Exception e) {
				errorMsgs.add("�п�J��a�s��!");
			}

			java.sql.Timestamp remittanceEstimatedTime = null;
			try {
				remittanceEstimatedTime = java.sql.Timestamp
						.valueOf(req.getParameter("remittanceEstimatedTime").trim());
			} catch (IllegalArgumentException e) {
				remittanceEstimatedTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J�w�p�״ڮɶ�!");
			}

			java.sql.Timestamp remittanceTime = null;
			try {
				remittanceTime = java.sql.Timestamp.valueOf(req.getParameter("remittanceTime").trim());
			} catch (IllegalArgumentException e) {
				remittanceTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J�״ڮɶ�!");
			}

			java.sql.Timestamp settlementTime = null;
			try {
				settlementTime = java.sql.Timestamp.valueOf(req.getParameter("settlementTime").trim());
			} catch (IllegalArgumentException e) {
				settlementTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J����ɶ�!");
			}

			// turnover
			Integer turnover = null;
			try {
				turnover = Integer.valueOf(req.getParameter("turnover").trim());
			} catch (NumberFormatException e) {
				turnover = 0;
				errorMsgs.add("��~�B�ж�Ʀr.");
			}

			Integer handlingFee = null;
			try {
				handlingFee = Integer.valueOf(req.getParameter("handlingFee").trim());
			} catch (NumberFormatException e) {
				handlingFee = 0;
				errorMsgs.add("����O�ж�Ʀr.");
			}

			Integer remittanceAmount = null;
			try {
				remittanceAmount = Integer.valueOf(req.getParameter("remittanceAmount").trim());
			} catch (NumberFormatException e) {
				remittanceAmount = 0;
				errorMsgs.add("�״ڪ��B�ж�Ʀr.");
			}

			Integer remittanceStatus = null;
			try {
				remittanceStatus = Integer.valueOf(req.getParameter("remittanceStatus").trim());
			} catch (NumberFormatException e) {
				remittanceStatus = 0;
				errorMsgs.add("�״ڽж�Ʀr.");
			}

			String explanation = req.getParameter("explanation").trim();
			if (explanation == null || explanation.trim().length() == 0) {
				errorMsgs.add("�Բӻ����ФŪť�");
			}

			// remittanceVO
			RemittanceVO remittanceVO = new RemittanceVO();
//			remittanceVO.setRemittanceId(remittanceId);
			remittanceVO.setSellerId(sellerId);
			remittanceVO.setRemittanceEstimatedTime(remittanceEstimatedTime);
			remittanceVO.setRemittanceTime(remittanceTime);
			remittanceVO.setSettlementTime(settlementTime);
			remittanceVO.setTurnover(turnover);
			remittanceVO.setHandlingFee(handlingFee);
			remittanceVO.setRemittanceAmount(remittanceAmount);
			remittanceVO.setRemittanceStatus(remittanceStatus);
			remittanceVO.setExplanation(explanation);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("remittanceVO", remittanceVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/remittance/addRemittance.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/*************************** 2.�}�l�s�W��� *****************************************/
			RemittanceService remittanceSvc = new RemittanceService();
			remittanceVO = remittanceSvc.addRemittance(sellerId, remittanceEstimatedTime, remittanceTime,
					settlementTime, turnover, handlingFee, remittanceAmount, remittanceStatus, explanation); // ��s�W�h�F���u�s��empno��@where�d�ߪ��Ѽ�

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			req.setAttribute("remittanceVO", remittanceVO); // ��Ʈwupdate���\��,���T����remittanceVO����,�s�Jreq
			String url = "/remittance/listAllRemittance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� ***************************************/
			Integer remittanceId = Integer.valueOf(req.getParameter("remittanceId"));

			/*************************** 2.�}�l�R����� ***************************************/

			RemittanceService remittanceSvc = new RemittanceService();
			remittanceSvc.deleteRemittance(remittanceId);

			/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
			String url = "/remittance/listAllRemittance.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
	}

}
