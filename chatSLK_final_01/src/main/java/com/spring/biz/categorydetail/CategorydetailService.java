package com.spring.biz.categorydetail;

import java.util.List;

public interface CategorydetailService {

	public CategorydetailVO selectOne(CategorydetailVO cdVO);
	public List<CategorydetailVO> selectAll(CategorydetailVO cdVO);
	
	public boolean insert(CategorydetailVO cdVO);
	public boolean update(CategorydetailVO cdVO);
	public boolean delete(CategorydetailVO cdVO);

}
