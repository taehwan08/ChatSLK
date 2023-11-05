package com.spring.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO2 MemberDAO;
	// 의존관계 -> DI
	
	@Override
	public MemberVO selectOne(MemberVO mVO) {
		return MemberDAO.selectOne(mVO);
	}

	@Override
	public List<MemberVO> selectAll(MemberVO mVO) {
		return MemberDAO.selectAll(mVO);
	}

	@Override
	public boolean insert(MemberVO mVO) {
		return MemberDAO.insert(mVO);
	}

	@Override
	public boolean update(MemberVO mVO) {
		return MemberDAO.update(mVO);
	}

	@Override
	public boolean delete(MemberVO mVO) {
		return MemberDAO.delete(mVO);
	}
}
