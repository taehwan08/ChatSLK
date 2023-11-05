package com.spring.view.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.cart.CartService;
import com.spring.biz.cart.CartVO;
import com.spring.biz.member.MemberVO;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping(value="/cartPage.do")
	public String cartPage(MemberVO mVO, CartVO cVO, HttpSession session, Model model) {
		System.out.println("cartPageController로그");
		cVO.setMemberId((String)session.getAttribute("memberId"));
		model.addAttribute("cdatas", cartService.selectAll(cVO));
		return "cart.jsp";
	}

	@RequestMapping(value="/addCartlist.do")
	@ResponseBody
	public String addCartlist(CartVO cVO, HttpSession session) {
		System.out.println("addCartController로그");

		cVO.setMemberId((String)session.getAttribute("memberId"));

		CartVO cdata = cartService.selectOne(cVO);
		if(cdata == null) {
			if(cartService.insert(cVO)) {
				System.out.println("새로운 상품 장바구니 추가 성공");
				return "insert";
			}
		}else {
			cdata.setCartCount(cVO.getCartCount());
			if(cartService.update(cdata)) {
				System.out.println("기존 상품 장바구니 변경 성공");
				return "update";
			}
		}
		return "error";
	}

	@RequestMapping(value="/deleteCart.do")
	@ResponseBody
	public String deleteCart(CartVO cVO, HttpSession session) {
		System.out.println("deleteCartController로그");

		cVO.setMemberId((String)session.getAttribute("memberId"));
		if(cartService.delete(cVO)) {
			System.out.println("장바구니 삭제 성공");
			return "delete";
			//out.print("delete");
		}else {
			return "error";
		}

	}

	@RequestMapping(value="/wishToCart.do")
	public String wishToCart(@RequestParam("number") List<String> selectProducts, CartVO cVO, HttpSession session) {
		System.out.println("wishToCart로그");

		cVO.setMemberId((String) session.getAttribute("memberId"));
		for(int i =0;i<selectProducts.size(); i++) {
			cVO.setProductNum(Integer.parseInt(selectProducts.get(i)));
			CartVO cdata = cartService.selectOne(cVO);
			if(cdata == null) {
				cVO.setCartCount(1);
				if(cartService.insert(cVO)) {
					System.out.println("새로운 상품 장바구니 추가 성공");
				}
			}else {
				cdata.setCartCount(1);
				if(cartService.update(cdata)) {
					System.out.println("기존 상품 장바구니 변경 성공");
				}
			}
		}
		return "cartPage.do";
	}
	
	@RequestMapping(value="/deleteCartlist.do")
	public String deleteCartlist(@RequestParam("number") List<String> selectProducts, CartVO cVO, HttpSession session) {
		System.out.println("wishToCart로그");
		
		if(selectProducts==null) {
			return "redirect:cartPage.do";
		}
		
		cVO.setMemberId((String)session.getAttribute("memberId"));
		for(int i =0;i<selectProducts.size(); i++) {
			cVO.setProductNum(Integer.parseInt(selectProducts.get(i).toString()));
			CartVO cdata = cartService.selectOne(cVO);
			if(cdata != null) {
				cartService.delete(cdata);
			}
		}
		return "cartPage.do";
	}
	
	
	@RequestMapping(value="/addCart.do")
	public String addCart(CartVO cVO, Model model, HttpSession session) {
		System.out.println("addCartController로그");

		cVO.setMemberId((String)session.getAttribute("memberId"));
		
		CartVO cdata = cartService.selectOne(cVO);
		if(cdata == null) {
			if(cartService.insert(cVO)) {
				System.out.println("새로운 상품 장바구니 추가 성공");
			}
		}else {
			cdata.setCartCount(cVO.getCartCount());
			if(cartService.update(cdata)) {
				System.out.println("기존 상품 장바구니 변경 성공");
			}
		}
		cdata = cartService.selectOne(cVO);
		
		session.setAttribute("cdata", cdata);
		
		return "redirect:directPayPage.do";
	}

}
