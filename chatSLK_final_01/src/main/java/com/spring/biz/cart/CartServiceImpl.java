package com.spring.biz.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CartService")
public class CartServiceImpl implements CartService {
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	// Service 레이어에서는 DAO를 사용함
	//  == C 파트
	//  : DAO를 사용할것이기때문에
	//    DAO와 메서드 시그니처를 맞추면 유리함
	// 아~~ 메서드 시그니처를 강제하고싶다!
	//  => 인터페이스
	
	@Autowired
	private CartDAO2 CartDAO;
	// 의존관계 -> DI
	
	@Override
	public CartVO selectOne(CartVO cVO) {
		return CartDAO.selectOne(cVO);
	}

	@Override
	public List<CartVO> selectAll(CartVO cVO) {
		return CartDAO.selectAll(cVO);
	}

	@Override
	public boolean insert(CartVO cVO) {
		return CartDAO.insert(cVO);
	}

	@Override
	public boolean update(CartVO cVO) {
		return CartDAO.update(cVO);
	}

	@Override
	public boolean delete(CartVO cVO) {
		return CartDAO.delete(cVO);
	}
}