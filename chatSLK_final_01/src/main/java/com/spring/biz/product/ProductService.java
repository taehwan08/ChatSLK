package com.spring.biz.product;

import java.util.List;

public interface ProductService {
	
	public ProductVO selectOne(ProductVO pVO);
	public List<ProductVO> selectAll(ProductVO pVO);
	
	
	public boolean insert(ProductVO pVO);
	public boolean update(ProductVO pVO);
	public boolean delete(ProductVO pVO);

}
