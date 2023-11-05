package com.spring.biz.categorydetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CategorydetailService")
public class CategorydetailServiceImpl implements CategorydetailService{
	
	
	@Autowired
	private CategorydetailDAO2 CategorydetailDAO;
	@Override
	public CategorydetailVO selectOne(CategorydetailVO cdVO) {
		return CategorydetailDAO.selectOne(cdVO);
	}

	@Override
	public List<CategorydetailVO> selectAll(CategorydetailVO cdVO) {
		return CategorydetailDAO.selectAll(cdVO);
	}

	@Override
	public boolean insert(CategorydetailVO cdVO) {
		return CategorydetailDAO.insert(cdVO);
	}

	@Override
	public boolean update(CategorydetailVO cdVO) {
		return CategorydetailDAO.update(cdVO);
	}
	@Override
	public boolean delete(CategorydetailVO cdVO) {
		return CategorydetailDAO.delete(cdVO);
	}

}
