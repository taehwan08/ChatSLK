package com.spring.view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.category.CategoryService;
import com.spring.biz.categorydetail.CategorydetailService;
import com.spring.biz.categorydetail.CategorydetailVO;
import com.spring.biz.images.ImagesService;
import com.spring.biz.member.MemberService;
import com.spring.biz.pay.PayService;
import com.spring.biz.pay.PayVO;
import com.spring.biz.product.ProductService;
import com.spring.biz.product.ProductVO;
import com.spring.biz.review.ReviewService;
import com.spring.biz.review.ReviewVO;
import com.spring.biz.wishlist.WishlistService;
import com.spring.biz.wishlist.WishlistVO;

@Controller
public class MainController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private WishlistService wishlistService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private CategorydetailService categoryDetailService;
	@Autowired
	private MemberService MemberService;
	@Autowired
	private PayService payService;
	@Autowired
	private ReviewService reviewService;
	
	
	@RequestMapping(value="/main.do")
	public String main(WishlistVO wVO,CategorydetailVO cdVO,ReviewVO rVO, Model model) {
		Random rand =new Random();
		
		cdVO.setSearchCondition("SELECTALL");
		List<CategorydetailVO> tmpcddatas = categoryDetailService.selectAll(cdVO);
		List<CategorydetailVO> cddatas = new ArrayList<CategorydetailVO>();
		int categorycount = 3;
		int randcategory[]= new int[categorycount];
		
		for(int i=0;i<randcategory.length;i++) {
			randcategory[i] = rand.nextInt(tmpcddatas.size())+1;
			for(int j=0;j<i;j++) {
				if(randcategory[i]==randcategory[j]) {
					i--;
				}
			}
		}
		for (int i=0;i<randcategory.length;i++) {
			System.out.println("randcategory : "+ randcategory[i]);
		}
		
		cdVO.setSearchCondition("SELECTONE");
		for(int i=0;i<randcategory.length;i++) {
			cdVO.setCategoryDetailNum(randcategory[i]);
			CategorydetailVO cddata=categoryDetailService.selectOne(cdVO);
			System.out.println("cddata : "+cddata);
			cddatas.add(i, cddata);
		}
		
		System.out.println("cddatas : "+cddatas);
		
		wVO.setSearchCondition("WISHCNT");
		List<WishlistVO> wdatas = wishlistService.selectAll(wVO);
		
		List<ProductVO> pdatas = new ArrayList<ProductVO>();
		for (int i = 0 ; i < wdatas.size() ; i++) {
			ProductVO pVO = new ProductVO();
			pVO.setProductNum(wdatas.get(i).getProductNum());
			pVO.setSearchCondition("SELECTONE");
			pVO = productService.selectOne(pVO);
			pdatas.add(pVO);
		}
		
		rVO.setSearchCondition("AVGSTAR");
		for(int i = 0 ; i<pdatas.size() ; i++) {
			rVO.setProductNum(pdatas.get(i).getProductNum());
			ReviewVO rdata = reviewService.selectOne(rVO);
			if(rdata == null) {
				pdatas.get(i).setStar(0);
			}else {
				pdatas.get(i).setStar(rdata.getStar());
			}
		}
		
		
		model.addAttribute("cddatas",cddatas);
		model.addAttribute("pdatas",pdatas);
		
		return "main.jsp";
	}
	
	
}
