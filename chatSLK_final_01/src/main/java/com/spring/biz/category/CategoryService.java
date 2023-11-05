package com.spring.biz.category;

import java.util.List;

public interface CategoryService {
	
	public CategoryVO selectOne(CategoryVO ctVO);
	public List<CategoryVO> selectAll(CategoryVO ctVO);
	
	public boolean insert(CategoryVO ctVO);
	public boolean update(CategoryVO ctVO);
	public boolean delete(CategoryVO ctVO);


}
