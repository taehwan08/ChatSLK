package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.wishlist.WishlistService;
import com.spring.biz.wishlist.WishlistVO;

@Controller
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	@RequestMapping(value="/wishlistPage.do")
	public String wishlistPage(WishlistVO wVO, HttpSession session, Model model) {
		System.out.println("wishlistPageController 로그");

		wVO.setMemberId((String)session.getAttribute("memberId"));
		wVO.setSearchCondition("SELECTALL");
		
		model.addAttribute("wdatas",wishlistService.selectAll(wVO));
		return "wishList.jsp";
	}

	@RequestMapping(value="/addWishlist.do")
	@ResponseBody
	public String addWishlist(WishlistVO wVO, HttpSession session) {
		System.out.println("addWishlistController 로그");
		
		System.out.println("wVO : "+wVO);
		
		wVO.setMemberId((String)session.getAttribute("memberId"));

		if(wishlistService.insert(wVO)) {
			System.out.println("위시 추가 성공");
			return "insert";
		}else {
			if(wishlistService.delete(wVO)) {
				System.out.println("위시 삭제 성공");
				return "delete";
			}
		}
		return "error";
	} 

	@RequestMapping(value="/deleteWishlist.do")
	@ResponseBody
	public String deleteWishlist(WishlistVO wVO, HttpSession session) {
		System.out.println("deleteWishlistController 로그");
		
		wVO.setMemberId((String)session.getAttribute("memberId"));
		
		if(wishlistService.delete(wVO)) {
			System.out.println("위시리스트 삭제 성공");
			return "delete";
		}
		return "error";
	}
}
