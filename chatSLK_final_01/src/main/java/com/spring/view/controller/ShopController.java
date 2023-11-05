package com.spring.view.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.category.CategoryService;
import com.spring.biz.category.CategoryVO;
import com.spring.biz.categorydetail.CategorydetailService;
import com.spring.biz.categorydetail.CategorydetailVO;
import com.spring.biz.common.ConvertEntoKo;
import com.spring.biz.images.ImagesService;
import com.spring.biz.images.ImagesVO;
import com.spring.biz.member.MemberVO;
import com.spring.biz.product.ProductService;
import com.spring.biz.product.ProductVO;
import com.spring.biz.review.ReviewService;
import com.spring.biz.review.ReviewVO;
import com.spring.biz.wishlist.WishlistService;
import com.spring.biz.wishlist.WishlistVO;

@Controller
public class ShopController {

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
	private ReviewService reviewService;


	@RequestMapping(value="/shopPage.do")
	public String shopPage(Model model, MemberVO mVO, ProductVO pVO, CategoryVO ctVO, CategorydetailVO cdVO, WishlistVO wVO, ReviewVO rVO, HttpSession session) {

		System.out.println("shopPage pVO : "+pVO);

		List<ProductVO> pdatas = null;
		List<WishlistVO> wdatas = null;
		List<CategorydetailVO> cddatas = null;
		List<CategoryVO> ctdatas = null;

		//1. 그냥 shop 버튼 눌렀을때 (keyword , cdNum) == null
		if((pVO.getSearchKeyword()==null || pVO.getSearchKeyword().isEmpty() || pVO.getSearchKeyword().isBlank() || pVO.getSearchKeyword().equals("")) && (pVO.getCategoryDetailNum()==0)) {
			System.out.println("1번 케이스");
			pVO.setSearchCondition("SELECTALL");
			pdatas = productService.selectAll(pVO);
		}
		//2. 추천 카테고리 버튼 눌러서 들어왔을때 (keyword) == null
		//3. shop에서 상세 카테고리 버튼 눌렀을때 (keyword) == null
		else if(pVO.getSearchKeyword()==null || pVO.getSearchKeyword().isEmpty() || pVO.getSearchKeyword().isBlank() || pVO.getSearchKeyword().equals("")) {
			System.out.println("2,3번 케이스");
			pVO.setSearchCondition("CATEGORY");
			pdatas = productService.selectAll(pVO);
			
			boolean flag = false;
			for(int i=0;i<pdatas.size();i++) {
				if(pdatas.get(i).getCategoryDetailNum() == pVO.getCategoryDetailNum()) {
					flag = true;
				}
			}
			if(!flag) {
				return "redirect:shopPage.do";
			}
		}
		//4. 키워드 검색할때, (cdNum) == null
		else if(pVO.getCategoryDetailNum()==0) {
			System.out.println("4번 케이스");
			pVO.setSearchCondition("SEARCH");
			pdatas = productService.selectAll(pVO);

			// 검색목록 없을때
			if(pdatas==null || pdatas.isEmpty()) {
				
				Pattern p = Pattern.compile("[a-zA-Z0-9]");
				Matcher m = p.matcher(pVO.getSearchKeyword());
				System.out.println("Found = " + (m.find() ? "True" : "Flase"));
				if (m.find()) {
					String str = pVO.getSearchKeyword().substring(m.start(), m.end());
					System.out.println("Result = " + str);
				}
				boolean isEnglish = m.find();
				if(isEnglish) {			
					String fixedText = ConvertEntoKo.engToKor(pVO.getSearchKeyword());
					pVO.setSearchKeyword(fixedText);
					pdatas = productService.selectAll(pVO);
					if(pdatas == null) {
						return "redirect:shopPage.do";
					}
				} else {
					return "redirect:shopPage.do";
				}
				
				
			}
		}

		// 상품 별점 평균 구해오기..!
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

		// 카테고리 이름 전부 불러오기
		ctdatas = categoryService.selectAll(ctVO);
		// 상세 카테고리 이름 전부 불러오기
		cddatas = categoryDetailService.selectAll(cdVO);

		// 로그인 했으면 위시리스트 체크
		wVO.setMemberId((String)session.getAttribute("memberId"));
		if(!(wVO.getMemberId()==null || wVO.getMemberId().isEmpty() || wVO.getMemberId().isBlank() || wVO.getMemberId().equals(""))) {
			wVO.setSearchCondition("NOPIC");
			wdatas = wishlistService.selectAll(wVO);
			for (int i = 0; i < pdatas.size(); i++) {
				pdatas.get(i).setCheck(false); // 모든 pdatas 요소를 false로 초기화
				for (int j = 0; j < wdatas.size(); j++) {
					if (pdatas.get(i).getProductNum() == wdatas.get(j).getProductNum()) {
						pdatas.get(i).setCheck(true); // 일치하는 경우 true로 설정
						break; // 이미 true로 설정되었으므로 더 이상 비교할 필요 없음
					}
				}
			}
		}
		// 제품이 비어있지 않아야 정상 작동 가능
		if(pdatas != null) {
			if(wdatas == null) {
				wdatas = new ArrayList<WishlistVO>();
			}
			model.addAttribute("pdatas", pdatas);
			model.addAttribute("ctdatas", ctdatas);
			model.addAttribute("cddatas", cddatas);
			model.addAttribute("wdatas", wdatas);
		}
		return "shop.jsp";


	}

	@RequestMapping(value="/shopSinglePage.do") 
	public String shopSinglePage(ProductVO pVO, ImagesVO iVO,WishlistVO wVO, ReviewVO rVO, HttpSession session, Model model) {
		System.out.println("shopSinglePageController로그");

		pVO.setSearchCondition("SELECTONE");
		pVO = productService.selectOne(pVO);
		
		if(pVO == null) {
			return "redirect:shopPage.do";
		}
		// 상품 별점 평균 구해오기..!
		rVO.setSearchCondition("AVGSTAR");
		rVO.setProductNum(pVO.getProductNum());
		ReviewVO rdata = reviewService.selectOne(rVO);
		if(rdata == null) {
			pVO.setStar(0);
		}else {
			pVO.setStar(rdata.getStar());
		}

		iVO.setProductNum(pVO.getProductNum());
		List<ImagesVO> idatas = imagesService.selectAll(iVO);


		wVO.setMemberId((String)session.getAttribute("memberId"));

		if(!(wVO.getMemberId()==null || wVO.getMemberId().isEmpty() || wVO.getMemberId().isBlank() || wVO.getMemberId().equals(""))) {
			wVO.setSearchCondition("SELECTONE");
			wVO.setProductNum(pVO.getProductNum());
			wVO = wishlistService.selectOne(wVO);
			pVO.setCheck(false);
			if(wVO!=null) {
				pVO.setCheck(true);
			}
		}

		ProductVO tmppVO=new ProductVO();
		tmppVO.setSearchCondition("SELECTALL");
		List<ProductVO> tmppdatas = productService.selectAll(tmppVO);
		List<ProductVO> pdatas = new ArrayList<ProductVO>();

		Random rand =new Random();
		int count=4;
		int randproduct[]= new int[count];

		for(int i=0;i<count;i++) {
			randproduct[i] = rand.nextInt(tmppdatas.size())+1;
			for(int j=0;j<i;j++) {
				if(randproduct[i]==randproduct[j]) {
					i--;
				}
			}
		}
		for(int i=0;i<count;i++) {
			tmppVO=new ProductVO();
			tmppVO.setProductNum(randproduct[i]);
			tmppVO=productService.selectOne(tmppVO);
			pdatas.add(tmppVO);
		}

		List<ProductVO> recentProductList = null;
		if(session.getAttribute("recentProductList")!=null) {
			recentProductList = (List<ProductVO>)session.getAttribute("recentProductList");
			for(int i = 0 ; i < recentProductList.size(); i++) {
				if(recentProductList.get(i).getProductNum()==pVO.getProductNum()) {
					recentProductList.remove(i);
				}
			}
			//3 번 이후 인덱스는 제거
			recentProductList.add(0, pVO);
			while(recentProductList.size()>3) {
				recentProductList.remove(recentProductList.size()-1);
			}
		}else {
			recentProductList = new ArrayList<ProductVO>();
			recentProductList.add(pVO);
		}

		List<ReviewVO> rdatas = null;
		rVO.setSearchCondition("SHOPSINGLE");
		rVO.setProductNum(pVO.getProductNum());
		rdatas = reviewService.selectAll(rVO);
		if(rdatas == null) {
			rdatas = new ArrayList<ReviewVO>();
		}else {
			for (int i = 0; i < rdatas.size(); i++) {
				Date date = rdatas.get(i).getReviewTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String dateString = sdf.format(date);
				rdatas.get(i).setTime(dateString);
			}
		}

		if(pVO!=null && idatas!=null) {
			session.setAttribute("recentProductList", recentProductList);
			model.addAttribute("pdata", pVO);
			model.addAttribute("pdatas", pdatas);
			model.addAttribute("idatas", idatas);
			model.addAttribute("rdatas", rdatas);
		}

		return "shop-single.jsp";
	}

}
