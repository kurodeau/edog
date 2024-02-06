package com.articlePic.model;

import java.util.*;

public interface ArticlePicDAO_interface {
	public void insert(ArticlePicVO articlePicVO);

	public void update(ArticlePicVO articlePicVO);

//	public void delete(Integer articlePicId);

	public ArticlePicVO findByPrimaryKey(Integer articlePicId);

	public List<ArticlePicVO> getAll();
}
