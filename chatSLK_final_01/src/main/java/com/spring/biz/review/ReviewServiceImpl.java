package com.spring.biz.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {

	
	@Autowired
	private ReviewDAO2 ReviewDAO;
	// 의존관계 -> DI
	
	@Override
	public ReviewVO selectOne(ReviewVO rVO) {
		return ReviewDAO.selectOne(rVO);
	}

	@Override
	public List<ReviewVO> selectAll(ReviewVO rVO) {
		return ReviewDAO.selectAll(rVO);
	}

	@Override
	public boolean insert(ReviewVO rVO) {
		return ReviewDAO.insert(rVO);
	}

	@Override
	public boolean update(ReviewVO rVO) {
		return ReviewDAO.update(rVO);
	}

	@Override
	public boolean delete(ReviewVO rVO) {
		return ReviewDAO.delete(rVO);
	}
}