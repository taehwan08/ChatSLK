package com.spring.biz.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	// Service 레이어에서는 DAO를 사용함
	//  == C 파트
	//  : DAO를 사용할것이기때문에
	//    DAO와 메서드 시그니처를 맞추면 유리함
	// 아~~ 메서드 시그니처를 강제하고싶다!
	//  => 인터페이스
	
	@Autowired
	private CategoryDAO2 CategoryDAO;
	// 의존관계 -> DI

	@Override
	public CategoryVO selectOne(CategoryVO ctVO) {
		return CategoryDAO.selectOne(ctVO);
	}

	@Override
	public List<CategoryVO> selectAll(CategoryVO ctVO) {
		return CategoryDAO.selectAll(ctVO);
	}

	@Override
	public boolean insert(CategoryVO ctVO) {
		return CategoryDAO.insert(ctVO);
	}

	@Override
	public boolean update(CategoryVO ctVO) {
		return CategoryDAO.update(ctVO);
	}

	@Override
	public boolean delete(CategoryVO ctVO) {
		return CategoryDAO.delete(ctVO);
	}
}