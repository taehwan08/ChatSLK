package com.spring.biz.images;

import java.util.List;

public interface ImagesService {
	public List<ImagesVO> selectAll(ImagesVO iVO);
	public ImagesVO selectOne(ImagesVO iVO);
	
	public boolean insert(ImagesVO iVO);
	public boolean update(ImagesVO iVO);
	public boolean delete(ImagesVO iVO);

}
