package com.spring.biz.images;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ImagesService")
public class ImagesServiceImpl implements ImagesService {

	@Autowired
	private ImagesDAO2 ImagesDAO;
	// 의존관계 -> DI
	
	@Override
	public List<ImagesVO> selectAll(ImagesVO iVO) {
		return ImagesDAO.selectAll(iVO);
	}

	@Override
	public ImagesVO selectOne(ImagesVO iVO) {
		return ImagesDAO.selectOne(iVO);
	}

	@Override
	public boolean insert(ImagesVO iVO) {
		return ImagesDAO.insert(iVO);
	}

	@Override
	public boolean update(ImagesVO iVO) {
		return ImagesDAO.update(iVO);
	}

	@Override
	public boolean delete(ImagesVO iVO) {
		return ImagesDAO.delete(iVO);
	}

}
