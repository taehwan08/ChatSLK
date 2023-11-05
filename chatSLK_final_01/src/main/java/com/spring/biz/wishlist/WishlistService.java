package com.spring.biz.wishlist;

import java.util.List;

public interface WishlistService {
	
	public WishlistVO selectOne(WishlistVO wVO);
	public List<WishlistVO> selectAll(WishlistVO wVO);

	
	public boolean insert(WishlistVO wVO);
	public boolean delete(WishlistVO wVO);
	public boolean update(WishlistVO wVO);
}
