package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.address.AddressService;
import com.spring.biz.address.AddressVO;
import com.spring.biz.common.AlertVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.pay.PayService;
import com.spring.biz.pay.PayVO;
import com.spring.biz.review.ReviewService;
import com.spring.biz.review.ReviewVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired 
	private PayService payService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ReviewService reviewService;

	//로그인페이지로이동
	@RequestMapping(value="/loginPage.do")
	public String loginPage() {
		System.out.println("loginPageController 로그");
		return "redirect:login.jsp";
	}

	//회원 로그인
	@RequestMapping(value="/login.do")
	public String login(MemberVO mVO, AlertVO aVO, HttpSession session) {
		System.out.println("loginController 로그");

		MemberVO googleMember= (MemberVO)session.getAttribute("mVO");
		if(googleMember == null) {
			mVO.setSearchCondition("SELECTONE");
			mVO = memberService.selectOne(mVO);
		}else {
			mVO = googleMember;
		}
		
		if(mVO!=null) {
			session.setAttribute("memberId", mVO.getMemberId());
			
			// 혹시 구글 로그인 하고나면 세션에서 mVO 날려주기
			session.removeAttribute("mVO");
			
			if(mVO.getRole().equals("ADMIN")) {
				session.setAttribute("memberName", mVO.getMemberName());
				session.setAttribute("role", mVO.getRole());
				return "redirect:adminMain.do";
			}else {
				return "redirect:main.do";
			}
		}else {
			aVO.setSearchCondition("login");
			session.setAttribute("aVO", aVO);
			return "error.do";
		}
	}

	//회원로그아웃
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		System.out.println("loginoutController 로그");

//		session.invalidate();
		session.removeAttribute("memberId");
		session.removeAttribute("memberName");
		session.removeAttribute("role");

		return "redirect:main.do";
	}

	//회원가입페이지로이동
	@RequestMapping(value="/signupPage.do")
	public String signupPage(MemberVO mVO, HttpSession session, Model model) {
		System.out.println("signupPageController 로그");

		String memberId=(String)session.getAttribute("memberId");
		if(!(memberId==null || memberId.isEmpty() || memberId.isBlank() || memberId.equals(""))) {
			mVO.setMemberId(memberId);
			mVO.setSearchCondition("NOPW");
			mVO = memberService.selectOne(mVO);

			model.addAttribute("mdata",mVO);

			return "join.jsp";
		}
		return "redirect:join.jsp";
	}

	//회원가입
	@RequestMapping(value="/signup.do", method=RequestMethod.POST)
	public String signup(MemberVO mVO, HttpSession session) {
		System.out.println("signupController 로그");

		if(mVO.getMemberId().contains("G@")) {
			mVO.setSearchCondition("SELECTONE");
			MemberVO mdata = memberService.selectOne(mVO);
			if(mdata == null) {
				if(!(memberService.insert(mVO))){
					return "signupPage.do";
				}
				session.setAttribute("mVO", mVO);
			}else {
				session.setAttribute("mVO", mdata);
			}
			return "redirect:login.do";
		}

		if(memberService.insert(mVO)) {
			return "redirect:loginPage.do";
		}
		return "signupPage.do";
	}
	
	@RequestMapping(value="/checkId.do")
	@ResponseBody
	public String checkId (MemberVO mVO) {
		
		System.out.println("mVO : "+mVO);
		
		
		mVO.setSearchCondition("NOPW");
		mVO = memberService.selectOne(mVO);
		if(mVO == null) {
			return "true";
		}
		return "false";
	}
	
	
	

	//마이페이지로이동
	@RequestMapping(value="/mypagePage.do")
	public String mypagePage(PayVO payVO,ReviewVO rVO, HttpSession session,Model model) {
		System.out.println("mypagePageController로그");

		payVO.setMemberId((String)session.getAttribute("memberId"));
		
		List<PayVO> paydatas = payService.selectAll(payVO);
		
		rVO.setMemberId((String)session.getAttribute("memberId"));
		rVO.setSearchCondition("USER");
		for(int i = 0; i < paydatas.size(); i++) {
			paydatas.get(i).setCheck(false);
			rVO.setProductNum(paydatas.get(i).getProductNum());
			ReviewVO rdata = reviewService.selectOne(rVO);
			if(rdata != null) {
				paydatas.get(i).setCheck(true);
			}
		}
		if(paydatas!=null) {
			model.addAttribute("paydatas", paydatas);
		}
		return "mypage.jsp";
	}

	//멤버업데이트
	@RequestMapping(value="/updateMember.do")
	public String updateMember(MemberVO mVO) {
		System.out.println("updateMemberController로그");
		
		if(mVO.getTmpPw() == null || mVO.getTmpPw().isBlank() || mVO.getTmpPw().isEmpty() || mVO.getTmpPw().equals("")) {
			//비번 변경안한거임..!
			mVO.setSearchCondition("NOPW");
			memberService.update(mVO);
			return "mypagePage.do";
		}
		mVO.setSearchCondition("MEMBERPW");
		memberService.update(mVO);
		return "logout.do";
	}

	//회원탈퇴
	@RequestMapping(value="/deleteMember.do")
	public String deleteAccount(MemberVO mVO) {
		System.out.println("deleteMemberController로그");

		if(memberService.delete(mVO)) {
			return "redirect:logout.do";
		}
		return "mypagePage.do";
	}
	
	//배송지 리스트 페이지 이동
	@RequestMapping(value="/deliveryListPage.do")
	public String deliveryListPage(MemberVO mVO,AddressVO aVO, Model model,HttpSession session) {
		System.out.println("deliveryListPageController로그");
		
		mVO.setMemberId((String)session.getAttribute("memberId"));
		mVO.setSearchCondition("NOPW");
		mVO = memberService.selectOne(mVO);
		if(mVO!=null) {
			aVO.setMemberId((String)session.getAttribute("memberId"));
			List<AddressVO> adatas = addressService.selectAll(aVO);
			
			model.addAttribute("adatas", adatas);
		}
		return "deliveryList.jsp";
	}
	
	//배송지 수정,추가 페이지 이동
	@RequestMapping(value="/addDeliveryListPage.do")
	public String addDeliveryListPage(AddressVO aVO, Model model,HttpSession session) {
		System.out.println("AddDeliveryListPageController로그");
		
		aVO.setMemberId((String)session.getAttribute("memberId"));
		aVO.setSearchCondition("ADDRESSNUM");
		
		aVO = addressService.selectOne(aVO);
		
		model.addAttribute("adata", aVO);
		
		return "addDeliverylist.jsp";
	}
	
	//배송지 추가 액션
	@RequestMapping(value="/addDeliveryList.do")
	public String addDeliveryList(AddressVO aVO,HttpSession session) {
		System.out.println("AddDeliveryListPageController로그");
		
		if(aVO.getStatus()!=null && aVO.getStatus().equals("MAIN")) {
			AddressVO adata = new AddressVO();
			adata.setMemberId((String)session.getAttribute("memberId"));
			adata.setSearchCondition("SELECTONE");
			adata = addressService.selectOne(adata);
			if(adata != null) {
				adata.setSearchCondition("NULL");
				addressService.update(adata);
			}
			aVO.setSearchCondition("MAIN");
		}
		aVO.setMemberId((String)session.getAttribute("memberId"));
		if(addressService.insert(aVO)) {
			return "deliveryListPage.do";
		}else {
			return "error.do";
		}
	}
	
	//배송지 수정 액션
	@RequestMapping(value="/updateDeliveryList.do")
	public String updateDeliveryList(AddressVO aVO, HttpSession session) {
		System.out.println("updateDeliveryListPageController로그");
		
		if(aVO.getStatus()!=null && aVO.getStatus().equals("MAIN")) {
			AddressVO adata = new AddressVO();
			adata.setMemberId((String)session.getAttribute("memberId"));
			adata.setSearchCondition("SELECTONE");
			adata = addressService.selectOne(adata);
			if(adata != null) {
				adata.setSearchCondition("NULL");
				addressService.update(adata);
			}
			aVO.setSearchCondition("MAIN");
		}
		if(addressService.update(aVO)) {
			return "deliveryListPage.do";
		}else {
			return "error.do";
		}
	}
	
	//배송지 삭제 액션
	@RequestMapping(value="/deleteDeliveryList.do")
	public String deleteDeliveryList(AddressVO aVO,HttpSession session) {
		System.out.println("deleteDeliveryListPageController로그");
		
		aVO.setMemberId((String)session.getAttribute("memberId"));
		addressService.delete(aVO);
		return "deliveryListPage.do";
	}
}
