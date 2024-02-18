package com.product.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.product.dao.ProductDAO;
import com.product.dao.ProductHBDAO;
import com.product.entity.ProductVO;

import util.HibernateUtil;

public class ProductService {

	private ProductDAO dao;

	public ProductService() {
		dao = new ProductHBDAO();
	}
	public boolean isProductCodeUnique(String productCode,Integer productMyId) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Boolean isUnique = false;
		try {
			
		session.beginTransaction();
		 isUnique =  dao.isProductCodeUnique(productCode,productMyId);
		session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return isUnique;

	}

	public ProductVO addProduct(String productName, String productCode, Date startTime, Date endTime,
			Integer minSpendingAmount, Integer productQuantity, Integer memberAllowQuantity, Integer productDiscount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		ProductVO productVO = null;
		try {
			session.beginTransaction();

			productVO = new ProductVO();

			productVO.setProductName(productName);
			productVO.setProductCode(productCode);
			productVO.setStartTime(startTime);
			productVO.setEndTime(endTime);
			productVO.setMinSpendingAmount(minSpendingAmount);
			productVO.setProductQuantity(productQuantity);
			productVO.setMemberAllowQuantity(memberAllowQuantity);
			productVO.setProductDiscount(productDiscount);
			
			dao.insert(productVO);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return productVO;
	}

	public ProductVO updateProduct(Integer productId, String productName, String productCode, Date startTime, Date endTime,
			Integer minSpendingAmount, Integer productQuantity, Integer memberAllowQuantity, Integer productDiscount, Date productCreateTime) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		ProductVO productVO = null;

		try {
			session.beginTransaction();
			productVO = new ProductVO();

			productVO.setProductId(productId);
			productVO.setProductName(productName);
			productVO.setProductCode(productCode);
			productVO.setStartTime(startTime);
			productVO.setEndTime(endTime);
			productVO.setMinSpendingAmount(minSpendingAmount);
			productVO.setProductQuantity(productQuantity);
			productVO.setMemberAllowQuantity(memberAllowQuantity);
			productVO.setProductDiscount(productDiscount);
			productVO.setProductCreateTime(productCreateTime);

			dao.update(productVO);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return productVO;
	}

	public void deleteProduct(Integer productId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			dao.delete(productId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public ProductVO getOneProduct(Integer productId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ProductVO productVO = null;

		try {
			session.beginTransaction();
			 productVO=dao.findByPrimaryKey(productId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return productVO;
	}

	public List<ProductVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<ProductVO> list = Collections.emptyList() ;
		
		try {
			session.beginTransaction();
			 list =dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return list;
	}

	public int getTotal() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			return dao.getTotal();
		} catch (Exception e) {
			e.printStackTrace();
			return 0; // or handle as needed
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public List<ProductVO> getProductsByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();

		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		   List<ProductVO> result = Collections.emptyList();
		try {
			session.beginTransaction();
			result = dao.getByCompositeQuery(query);
	        session.getTransaction().commit();
			return (result);
		}
		catch (Exception e) {
			e.printStackTrace();
			 
		} finally {
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}
	
	
//	public static void main(String[] args) {
//        // 添加Product
//        addProductTest();
//
//        // 更新Product
//        updateProductTest();
//
//        // 获取所有Products
//        getAllProductsTest();
//
//        // 根据条件查询Products
//        getProductsByCompositeQueryTest();
//
//        // 删除Product
//        deleteProductTest();
//    }
//
//    private static void addProductTest() {
//        ProductService productService = new ProductService();
//
//        Date startTime = new Date();
//        Date endTime = new Date();
//
//        ProductVO product = productService.addProduct("TestProduct", "TEST12SSS3", startTime, endTime, 100, 10, 5, 20);
//
//        if (product != null) {
//            System.out.println("Product added successfully: " + product);
//        } else {
//            System.out.println("Failed to add Product.");
//        }
//    }
//
//    private static void updateProductTest() {
//        ProductService productService = new ProductService();
//
//        Date startTime = new Date();
//        Date endTime = new Date();
//
//        // Assuming you have a productId available for updating
//        Integer productIdToUpdate = 6;
//
//        ProductVO updatedProduct = productService.updateProduct(productIdToUpdate, "UpdatedProduct", "UPDATED123",
//                startTime, endTime, 150, 15, 8, 25);
//
//        if (updatedProduct != null) {
//            System.out.println("Product updated successfully: " + updatedProduct);
//        } else {
//            System.out.println("Failed to update Product.");
//        }
//    }
//
//    private static void getAllProductsTest() {
//        ProductService productService = new ProductService();
//
//        List<ProductVO> allProducts = productService.getAll();
//
//        System.out.println("All Products:");
//        for (ProductVO product : allProducts) {
//            System.out.println(product);
//        }
//    }
//
//    private static void getProductsByCompositeQueryTest() {
//        ProductService productService = new ProductService();
//
//        // Assuming you have a map with query parameters
//        Map<String, String[]> queryParams = new HashMap<>();
//        queryParams.put("productName", new String[]{"TestProduct"});
//
//        List<ProductVO> filteredProducts = productService.getProductsByCompositeQuery(queryParams);
//
//        System.out.println("Filtered Products:");
//        for (ProductVO product : filteredProducts) {
//            System.out.println(product);
//        }
//    }
//
//    private static void deleteProductTest() {
//        ProductService productService = new ProductService();
//
//        // Assuming you have a productId available for deletion
//        Integer productIdToDelete = 8;
//
//        productService.deleteProduct(productIdToDelete);
//
//        System.out.println("Product deleted successfully.");
//    }

}
