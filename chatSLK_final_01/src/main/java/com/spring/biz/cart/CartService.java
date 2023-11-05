package com.spring.biz.cart;

import java.util.List;
public interface CartService {

	public CartVO selectOne(CartVO cVO);
	public List<CartVO> selectAll(CartVO cVO);
	
	public boolean insert(CartVO cVO);
	public boolean update(CartVO cVO);
	public boolean delete(CartVO cVO);

}




