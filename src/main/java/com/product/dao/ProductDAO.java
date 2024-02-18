package com.product.dao;


import java.util.List;
import java.util.Map;

import com.product.entity.ProductVO;


public interface ProductDAO {
          public Integer insert(ProductVO product);
          public Integer update(ProductVO product);
          public Integer delete(Integer productId);
          public ProductVO findByPrimaryKey(Integer productId);
          public List<ProductVO> getAll();
          public Integer getTotal();
          public List<ProductVO> getByCompositeQuery(Map<String,String> map); 
          
          //FOR UK
          boolean isProductCodeUnique(String productCode,Integer productMyId) ;

}




