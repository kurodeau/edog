package com.ad.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ad.model.AdService;
import com.ad.model.AdVO;
import com.google.gson.Gson;
import com.seller.entity.SellerVO;

@WebServlet("/ad/ad.do")

@MultipartConfig(fileSizeThreshold = 1024*1024 , maxFileSize = 10 * 1024 * 1024 , maxRequestSize = 10*10*1024*1024)
public class AdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 
		    req.setCharacterEncoding("UTF-8");
		    res.setContentType("application/json");
		    res.setHeader("Access-Control-Allow-Origin", "*");
		    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		    res.setHeader("Access-Control-Allow-Headers", "Content-Type");

		    String action = req.getParameter("action");
		    System.out.println(action+"jjjjjj");

		    if ("getAdvertisements".equals(action)) {
		    	
		    	System.out.println( "44444444444");
		        try {
		            AdService advertis = new AdService();
		            System.out.println( "22222222");
		            List<AdVO> advertiseList = advertis.getAll();
		            System.out.println(advertiseList + "11111111");
		            // 將廣告資料轉換為 JSON 格式並寫入 HttpServletResponse 中
		            String json = new Gson().toJson(advertiseList);
		            System.out.println(json);
		            res.getWriter().write(json);
		         
		        } catch (Exception e) {
		            e.printStackTrace();
		            // 根据实际情况处理异常，例如返回错误响应
		            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		            res.getWriter().write("An error occurred while fetching advertisements.");
		        }
		    }
	    
	       

//		if ("getOne_For_Display".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs); // �]���n��errorMsg forward�X�h , �ҥH��req�]�w�Ѽ�
//
//			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
//			String str = req.getParameter("adId");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("�п�J�s�i�s��");
//			}
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/ad/select_page.jsp");
//				failureView.forward(req, res); // RequestDispatcher �U���N�n��forward
//				return;
//			}
//
//			Integer adId = null;
//			try {
//				adId = Integer.valueOf(str); // Integer�૬��J�r��|��Exception
//			} catch (Exception e) {
//				errorMsgs.add("�s�i�s���榡�����T");
//			}
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/ad/select_page.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.�}�l�d�߸�� *****************************************/
//			AdService adSvc = new AdService();
//			AdVO adVO = adSvc.getOneAd(adId);
//			if (adVO == null) {
//				errorMsgs.add("�d�L���");
//			}
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/ad/select_page.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/**************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *****************************************/
//			req.setAttribute("adVO", adVO);
//			String url = "/ad/listOneAd.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}
//		

		if ("insert".equals(action)) {
			System.out.println("INSERT");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			
			
String adName = req.getParameter("adName");
			String adNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (adName == null || adName.trim().length() == 0) {
				errorMsgs.add("�s�i�W��:�ФŪť�");
			} else if (!adName.trim().matches(adNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�s�i�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}

Integer sellerId = 1 ;
			
//            Integer sellerId = null;
//			try {
//sellerId = Integer.valueOf(req.getParameter("sellerId").trim());
//			} catch(NumberFormatException e) {
//				sellerId = 0;
//				errorMsgs.add("��aID�ж�Ʀr");
//			}
			
String adUrl = req.getParameter("adUrl");
			String adUrlReg = "^(https?|ftp):\\/\\/[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(\\/[^\\s]*)?$";			
			if(adUrl == null || adUrl.trim().length() ==0 ) {
				errorMsgs.add("�s�i���}:�ФŪť�");
			}else if( !adUrl.trim().matches(adUrlReg)) {
				errorMsgs.add("�s�i���}:�u��O���}�榡");
			}
			
			
			java.util.Date adStartTime = null;
			try {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 設定日期格式
			    String adStartTimeString = req.getParameter("adStartTime").trim(); // 從請求中獲取參數
			    adStartTime = dateFormat.parse(adStartTimeString); // 解析日期字串成 Date 物件
			} catch (Exception e) {			   
			    errorMsgs.add("日期格式錯誤");
			}
			
//			java.sql.Timestamp adStartTime = null;
//			try {
//adStartTime = java.sql.Timestamp.valueOf(req.getParameter("adStartTime").trim());
//			} catch (IllegalArgumentException e) {
//				adStartTime = new java.sql.Timestamp(System.currentTimeMillis());
//				errorMsgs.add("�п�J�_�l���");
//			}
			
			java.sql.Timestamp adEndTime = null;
			try {
adEndTime = java.sql.Timestamp.valueOf(req.getParameter("adEndTime").trim());
			} catch (IllegalArgumentException e) {
				adStartTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J�������");
			}

            Integer adLv = null;
            try {
adLv = Integer.valueOf(req.getParameter("adLv").trim());
            } catch(NumberFormatException e) {
            	adLv = 0 ;
            	errorMsgs.add("�s�i���Žж�Ʀr");
            }
	

			byte[] adImg =null;
Part filePart = req.getPart("adImg");
				InputStream in = filePart.getInputStream();
				if(in.available()==0) {
					errorMsgs.add("�ФW�ǹϤ�");
				}else {
					adImg =in.readAllBytes();
				}
				in.close();
				
				java.sql.Timestamp adImgUploadTime = null;
				try {
adImgUploadTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
				} catch ( Exception e) {
					errorMsgs.add("�п�ܮɶ�");
				}

String adMemo = req.getParameter("adMemo");
				String adMemoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (adMemo == null || adName.trim().length() == 0) {
					errorMsgs.add("�s�i���e:�ФŪť�");
				} else if (!adMemo.trim().matches(adNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s�i���e: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��100����");
				}
				
				Boolean isAdConfirm = false ;
				
//				Boolean isAdConfirm = null ;
//				try {
//isAdConfirm = Boolean.valueOf(req.getParameter("isAdConfirm").trim());
//				      }catch (Exception e) {
//				    	  isAdConfirm = false ;
//				    	  errorMsgs.add("�п�J�f�֪��A");
//				      }
				
				
				
				java.sql.Timestamp adCreateTime = null ;
				try {
adCreateTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
				} catch(Exception e ) {
					errorMsgs.add("�п�J�ɶ�");
				}

				Boolean isEnabled = false ;
				
//				Boolean isEnabled = null ;
//				try {
//isEnabled = Boolean.valueOf(req.getParameter("isEnabled").trim());
//				} catch (Exception e) {
//					isEnabled = false ;
//					errorMsgs.add("�п�J���A");
//				}



//
				AdVO adVO = new AdVO();
				
				adVO.setAdImg(adImg);
				adVO.setAdImgUploadTime(adImgUploadTime);
				adVO.setAdName(adName);
				adVO.setAdUrl(adUrl);
				adVO.setAdStartTime(adStartTime);
				adVO.setAdEndTime(adEndTime);
				adVO.setAdLv(adLv);
				adVO.setAdMemo(adMemo);
				adVO.setIsAdConfirm(isAdConfirm);
				adVO.setAdCreateTime(adCreateTime);
				adVO.setIsEnabled(isEnabled);	
				
				SellerVO sellerVO = new SellerVO();		
				sellerVO.setSellerId(sellerId);
				adVO.setSellerVO(sellerVO);
			
//				
//			
//				
//
//
				/***************************2.�}�l�s�W���***************************************/
				
				AdVO adVOHB = new AdVO(sellerVO,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled);
				
				AdService adSvc = new AdService();
				adVO = adSvc.addAd(adVOHB);
				
	//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
	//				String url = "/ad/listAllAd.jsp" ;
	//				RequestDispatcher successView = req.getRequestDispatcher(url);
	//				successView.forward(req, res);		
		}	
		
//		if("update".equals(action)) {
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			
//			req.setAttribute("errorMsgs", errorMsgs);
//					
//		 
//Integer adId = Integer.valueOf(req.getParameter("adId").trim());
//
//Integer sellerId = null;
//			try {
//			sellerId = Integer.valueOf(req.getParameter("sellerId").trim());
//			} catch(NumberFormatException e) {
//				sellerId = 0;
//				errorMsgs.add("��aID�ж�Ʀr");							
//			}			
//			
//String adName = req.getParameter("adName");
//			String adNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (adName == null || adName.trim().length() == 0) {
//				errorMsgs.add("�s�i�W��:�ФŪť�");
//			} else if (!adName.trim().matches(adNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//				errorMsgs.add("�s�i�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//			}
//String adUrl = req.getParameter("adUrl");
//			String adUrlReg = "^(https?|ftp):\\/\\/[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(\\/[^\\s]*)?$";			
//			if(adUrl == null || adUrl.trim().length() ==0 ) {
//				errorMsgs.add("�s�i���}:�ФŪť�");
//			}else if( !adUrl.trim().matches(adUrlReg)) {
//				errorMsgs.add("�s�i���}:�u��O���}�榡");
//			}
//			
//			java.sql.Timestamp adStartTime = null;
//			try {
//adStartTime = java.sql.Timestamp.valueOf(req.getParameter("adStartTime").trim());
//			} catch (IllegalArgumentException e) {
//				adStartTime = new java.sql.Timestamp(System.currentTimeMillis());
//				errorMsgs.add("�п�J�_�l���");
//			}
//			
//			java.sql.Timestamp adEndTime = null;
//			try {
//adEndTime = java.sql.Timestamp.valueOf(req.getParameter("adEndTime").trim());
//			} catch (IllegalArgumentException e) {
//				adStartTime = new java.sql.Timestamp(System.currentTimeMillis());
//				errorMsgs.add("�п�J�������");
//			}
//			
//		    Integer adLv = null;
//            try {
//adLv = Integer.valueOf(req.getParameter("adLv").trim());
//            } catch(NumberFormatException e) {
//            	adLv = 0 ;
//            	errorMsgs.add("�s�i���Žж�Ʀr");
//            }
//	
//
//			byte[] adImg =null;
//Part filePart = req.getPart("adImg");
//				InputStream in = filePart.getInputStream();
//				if(in.available()==0) {
//					AdService adSvc2 = new AdService();
//					adImg = adSvc2.getOneAd(adId).getAdImg();
//				}else {
//					adImg =in.readAllBytes();
//				}
//				in.close();
//				
//				java.sql.Timestamp adImgUploadTime = null;
//				try {
//adImgUploadTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
//				} catch ( Exception e) {
//					errorMsgs.add("�п�ܮɶ�");
//				}
//
//String adMemo = req.getParameter("adMemo");
//				String adMemoReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
//				if (adMemo == null || adName.trim().length() == 0) {
//					errorMsgs.add("�s�i���e:�ФŪť�");
//				} else if (!adMemo.trim().matches(adNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�s�i���e: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��100����");
//				}
//				
//				Boolean isAdConfirm = null ;
//				try {
//isAdConfirm = Boolean.valueOf(req.getParameter("isAdConfirm").trim());
//				      }catch (Exception e) {
//				    	  isAdConfirm = false ;
//				    	  errorMsgs.add("�п�J�f�֪��A");
//				      }
//				
//				java.sql.Timestamp adCreateTime = null ;
//				try {
//adCreateTime = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
//				} catch(Exception e ) {
//					errorMsgs.add("�п�J�ɶ�");
//				}
//
//				
//				Boolean isEnabled = null ;
//				try {
//isEnabled = Boolean.valueOf(req.getParameter("isEnabled").trim());
//				} catch (Exception e) {
//					isEnabled = false ;
//					errorMsgs.add("�п�J���A");
//				}
//
//				AdVO adVO = new AdVO();
//				adVO.setAdId(adId);	
//				adVO.setSellerId(sellerId);
//				adVO.setAdImg(adImg);
//				adVO.setAdImgUploadTime(adImgUploadTime);
//				adVO.setAdName(adName);
//				adVO.setAdUrl(adUrl);
//				adVO.setAdStartTime(adStartTime);
//				adVO.setAdEndTime(adEndTime);
//				adVO.setAdLv(adLv);
//				adVO.setAdMemo(adMemo);
//				adVO.setIsAdConfirm(isAdConfirm);
//				adVO.setAdCreateTime(adCreateTime);
//				adVO.setIsEnabled(isEnabled);	
//				System.out.println("adVO" +adVO);
//
//				if(!errorMsgs.isEmpty()) {
//				req.setAttribute("adVO", adVO);
//				RequestDispatcher failureView = req.getRequestDispatcher("/ad/update_ad_input.jsp");
//				failureView.forward(req, res);
//				return;
//				}
//
//				/***************************2.�}�l�ק���***************************************/
//				AdService adSvc = new AdService();
//				adVO = adSvc.updateAd(adId,sellerId,adImg,adImgUploadTime,adName,adUrl,adStartTime,adEndTime,adLv,adMemo,isAdConfirm,adCreateTime,isEnabled);
//				
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)***********/
//				String url = "/ad/listAllAd.jsp" ;
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);					
//			
//		}
//		
//		if("getOne_For_Update".equals(action)) {
//			List<String>errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/***************************1.�����ШD�Ѽ�****************************************/
//			Integer adId = Integer.valueOf(req.getParameter("adId"));
//			
//			/***************************2.�}�l�d�߸��****************************************/
//			AdService adSvc = new AdService();
//			AdVO adVO = adSvc.getOneAd(adId);
//			
//			/***************************2.�}�l�d�߸��****************************************/
//			req.setAttribute("adVO", adVO);
//			String url =  "/ad/update_ad_input.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);			
//			
//		}
//		
//		if("delete".equals(action)) {
//			List<String>errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			/***************************1.�����ШD�Ѽ�***************************************/
//			Integer adId = Integer.valueOf(req.getParameter("adId"));
//			
//			/***************************2.�}�l�R�����***************************************/
//			AdService adSvc = new AdService();
//			adSvc.deleteAd(adId);
//			
//			/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//			String url = "/ad/listAllAd.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//			successView.forward(req, res);			
//		}

	}

}
