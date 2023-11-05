package com.spring.view.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.biz.address.AddressService;
import com.spring.biz.address.AddressVO;
import com.spring.biz.cart.CartService;
import com.spring.biz.cart.CartVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.pay.PayService;
import com.spring.biz.pay.PayVO;
import com.spring.biz.paydetail.PayDetailService;
import com.spring.biz.paydetail.PayDetailVO;

@Controller
public class PayController {

	@Autowired
	private PayService payService;

	@Autowired
	private CartService cartService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private PayDetailService paydetailService;

	@Autowired
	private AddressService addressService;

	@RequestMapping(value="/payPage.do")
	public String payPage(@RequestParam("number") List<String> selectedProducts,CartVO cVO, MemberVO mVO, AddressVO aVO, HttpSession session,Model model) {
		System.out.println("payPageController로그");

		List<CartVO> cdatas = new ArrayList<CartVO>();

		cVO.setMemberId((String)session.getAttribute("memberId"));
		if(selectedProducts != null) {
			for(int i =0;i<selectedProducts.size(); i++) {
				int num = Integer.parseInt(selectedProducts.get(i));
				cVO.setProductNum(num);
				cVO.setSearchCondition("SELECTONE");
				CartVO cdata = cartService.selectOne(cVO);
				if(cdata != null) {
					cdatas.add(cdata);
				}
			}
		}

		mVO.setMemberId((String)session.getAttribute("memberId"));
		mVO.setSearchCondition("NOPW");
		MemberVO mdata = memberService.selectOne(mVO);
		List<AddressVO> adatas = new ArrayList<AddressVO>();

		if(mdata != null) {
			aVO.setMemberId(mdata.getMemberId());
			adatas = addressService.selectAll(aVO);
		}

		model.addAttribute("cdatas", cdatas);
		model.addAttribute("mdata", mdata);
		model.addAttribute("adatas", adatas);

		return "pay.jsp";
	}
	
	@RequestMapping(value="/directPayPage.do")
	public String payPage(CartVO cVO, MemberVO mVO, AddressVO aVO, HttpSession session,Model model) {
		System.out.println("payPageController로그");

		List<CartVO> cdatas = new ArrayList<CartVO>();

		cVO.setMemberId((String)session.getAttribute("memberId"));
		CartVO cdata = (CartVO)session.getAttribute("cdata");
		if(cdata != null) {
			cdatas.add(cdata);
		}

		mVO.setMemberId((String)session.getAttribute("memberId"));
		mVO.setSearchCondition("NOPW");
		MemberVO mdata = memberService.selectOne(mVO);
		List<AddressVO> adatas = new ArrayList<AddressVO>();

		if(mdata != null) {
			aVO.setMemberId(mdata.getMemberId());
			adatas = addressService.selectAll(aVO);
		}

		model.addAttribute("cdatas", cdatas);
		model.addAttribute("mdata", mdata);
		model.addAttribute("adatas", adatas);

		return "pay.jsp";
	}

	@RequestMapping(value="/try.do")
	public String tryPay(MemberVO mVO,@RequestParam("cnum") List<String> cnums,@RequestParam("total") String total,
			AddressVO aVO,HttpSession session) {

		System.out.println("cdatas : "+cnums);

		List<CartVO> cdatas = new ArrayList<CartVO>();
		for (int i = 0; i < cnums.size(); i++) {
			CartVO cdata = new CartVO();
			cdata.setSearchCondition("CARTNUM");
			cdata.setCartNum(Integer.parseInt(cnums.get(i)));
			cdata = cartService.selectOne(cdata);
			cdatas.add(cdata);
		}

		System.out.println("real cdatas = "+cdatas);

		session.setAttribute("cdatas", cdatas);
		session.setAttribute("total", total);
		session.setAttribute("orderName", mVO.getMemberName());
		session.setAttribute("orderPhonenumber", mVO.getPhonenumber());
		session.setAttribute("orderZipcode", aVO.getZipcode());
		session.setAttribute("orderAddress", aVO.getAddress());
		session.setAttribute("orderAddressDetail", aVO.getAddressDetail());

		return "pay2.jsp";
	}

	@RequestMapping(value="/pay.do")
	public String pay(MemberVO mVO, AddressVO aVO, PayVO payVO, CartVO cVO, PayDetailVO pdVO, Model model, HttpSession session, HttpServletRequest request) throws IOException {
		System.out.println("payController로그");	

		String orderId = request.getParameter("orderId");
		String paymentKey = request.getParameter("paymentKey");
		String amount = request.getParameter("amount");

		String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R:";

		Encoder encoder = Base64.getEncoder(); 
		byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
		String authorizations = "Basic "+ new String(encodedBytes, 0, encodedBytes.length);

		paymentKey = URLEncoder.encode((String)paymentKey, StandardCharsets.UTF_8);

		URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", authorizations);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		JSONObject obj = new JSONObject();
		obj.put("paymentKey", paymentKey);
		obj.put("orderId", orderId);
		obj.put("amount", amount);

		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(obj.toString().getBytes("UTF-8"));

		int code = connection.getResponseCode();
		boolean isSuccess = code == 200 ? true : false;

		InputStream responseStream = isSuccess? connection.getInputStream(): connection.getErrorStream();

		Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
		JSONParser parser = new JSONParser();
		String method = null;

		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) parser.parse(reader);
			method = (String)jsonObject.get("method");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		responseStream.close();

		String memberId = (String)session.getAttribute("memberId");
		String zipcode = (String)session.getAttribute("orderZipcode");
		String address = (String)session.getAttribute("orderAddress");
		String addressdetail = (String)session.getAttribute("orderAddressDetail");

		//		session.setAttribute("cdatas", cdatas);
		//		session.setAttribute("total", total);

		payVO.setPayMethod(method);
		payVO.setMemberId(memberId);
		payVO.setZipcode(zipcode);
		payVO.setAddress(address);
		payVO.setAddressDetail(addressdetail);

		int payNum=0;

		if(payService.insert(payVO)) {// pay테이블 한개 생성
			payVO.setSearchCondition("SELECTONE");
			payVO = payService.selectOne(payVO); // 가장 최신거 번호만 가져옴.!
			payNum = payVO.getPayNum();

			if(payVO!=null) {
				cVO.setMemberId(memberId);
				// !!!! 수정 필요 !!!!!
				// 아까 페이 페이지에서 불러온 데이터 들만 선택!
				//MemberVO mVO, AddressVO aVO, PayVO payVO, CartVO cVO, PayDetailVO pdVO,
				List<CartVO> cdatas = (ArrayList<CartVO>)session.getAttribute("cdatas");

				if(cdatas!=null) {
					pdVO.setPayNum(payNum);
					for (int i=0;i<cdatas.size();i++) {

						pdVO.setProductNum(cdatas.get(i).getProductNum());
						pdVO.setPayCount(cdatas.get(i).getCartCount());

						if(paydetailService.insert(pdVO)){

							cVO.setCartNum(cdatas.get(i).getCartNum());
							cVO.setProductNum(cdatas.get(i).getProductNum());
							cartService.delete(cVO);
						}
					}
				}
			}
		}
		PayVO paydata = new PayVO();
		paydata.setMemberId(memberId);
		paydata.setPayNum(payNum);
		paydata.setSearchCondition("PAYONE");
		List<PayVO> paydatas = payService.selectAll(paydata);

		model.addAttribute("paydatas", paydatas);

		session.removeAttribute("cdatas");
		session.removeAttribute("total");
		session.removeAttribute("orderName");
		session.removeAttribute("orderPhonenumber");
		session.removeAttribute("orderZipcode");
		session.removeAttribute("orderAddress");
		session.removeAttribute("orderAddressDetail");

		return "payDone.jsp";
	}
}
