package com.spring.biz.wishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WishlistService")
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistDAO2 WishlistDAO;
	@Override
	public WishlistVO selectOne(WishlistVO wVO) {
		return WishlistDAO.selectOne(wVO);
	}

	@Override
	public List<WishlistVO> selectAll(WishlistVO wVO) {
		return WishlistDAO.selectAll(wVO);
	}

	@Override
	public boolean insert(WishlistVO wVO) {
		return WishlistDAO.insert(wVO);
	}

	@Override
	public boolean delete(WishlistVO wVO) {
		return WishlistDAO.delete(wVO);
	}

	@Override
	public boolean update(WishlistVO wVO) {
		return WishlistDAO.update(wVO);
	}

}
