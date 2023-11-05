package com.spring.biz.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProductService") 
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO2 ProductDAO;
	
	@Override
	public ProductVO selectOne(ProductVO pVO) {
		return ProductDAO.selectOne(pVO);
	}

	@Override
	public List<ProductVO> selectAll(ProductVO pVO) {
		return ProductDAO.selectAll(pVO);
	}

	@Override
	public boolean insert(ProductVO pVO) {
		return ProductDAO.insert(pVO);
	}

	@Override
	public boolean update(ProductVO pVO) {
		return ProductDAO.update(pVO);
	}

	@Override
	public boolean delete(ProductVO pVO) {
		return ProductDAO.delete(pVO);
	}

}
