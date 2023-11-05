package com.spring.view.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.biz.cart.CartService;
import com.spring.biz.category.CategoryService;
import com.spring.biz.category.CategoryVO;
import com.spring.biz.categorydetail.CategorydetailService;
import com.spring.biz.categorydetail.CategorydetailVO;
import com.spring.biz.common.AlertVO;
import com.spring.biz.images.ImagesService;
import com.spring.biz.images.ImagesVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.pay.PayService;
import com.spring.biz.pay.PayVO;
import com.spring.biz.paydetail.PayDetailService;
import com.spring.biz.paydetail.PayDetailVO;
import com.spring.biz.product.ProductService;
import com.spring.biz.product.ProductVO;
import com.spring.biz.review.ReviewService;
import com.spring.biz.review.ReviewVO;
import com.spring.biz.wishlist.WishlistService;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
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
	private PayService payService;
	@Autowired
	private CartService cartService;
	@Autowired
	private PayDetailService payDetailService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private JavaMailSender mailSender;

	// 관리자 메인페이지 이동
	@RequestMapping(value="/adminMain.do")
	public String adminmain(PayVO payVO,PayDetailVO pdVO,ReviewVO rVO,Model model,HttpSession session) {
		//	인가 알고리즘
		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}
		//	최근주문건수 (최근1일)
		payVO.setSearchCondition("ADMIN_RECENTSALE");
		payVO = payService.selectOne(payVO);
		if(payVO == null) {
			payVO = new PayVO();
		}
		//	최근리뷰건수 (최근1일)
		rVO.setSearchCondition("ADMIN_RECENTREVIEW");
		rVO = reviewService.selectOne(rVO);
		if(rVO == null) {
			rVO = new ReviewVO();
		}
		//	매출액 (총 매출액)
		// 	PD 테이블에서 product PK를 통해 가격 받아와서 paycount랑 곱한 값을 selectOne을 통해 구해옴..!
		pdVO.setSearchCondition("ADMIN_MAIN");
		pdVO = payDetailService.selectOne(pdVO);
		//	새로운 주문 (배송 처리하지 않은 애들)
		payVO.setSearchCondition("ADMIN_MAIN");
		List<PayVO> paydatas = payService.selectAll(payVO);
		if(paydatas != null) {
			for (int i = 0; i < paydatas.size(); i++) {
				Date date = paydatas.get(i).getPayTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = sdf.format(date);
				paydatas.get(i).setTime(dateString);
			}
		}
		// 새로운 리뷰 (답글 처리하지 않은 애들)
		rVO.setSearchCondition("ADMIN_MAIN");
		List<ReviewVO> rdatas = reviewService.selectAll(rVO);
		if(rdatas != null) {
			for (int i = 0; i < rdatas.size(); i++) {
				Date date = rdatas.get(i).getReviewTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = sdf.format(date);
				rdatas.get(i).setTime(dateString);
			}
		}
		model.addAttribute("paydata",payVO);
		model.addAttribute("rdata",rVO);
		model.addAttribute("pddata",pdVO);
		model.addAttribute("paydatas",paydatas);
		model.addAttribute("rdatas",rdatas);
		return "admin-main.jsp";
	}

	// 관리자 상품 목록 페이지 이동
	@RequestMapping(value="/productListPage.do")
	public String productListPage(ProductVO pVO,Model model,HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}

		pVO.setSearchCondition("PRODUCTLIST");
		List<ProductVO> pdatas = productService.selectAll(pVO);

		model.addAttribute("pdatas",pdatas);

		return "admin-productlist.jsp";
	}

	// 관리자 상품 수정 페이지 이동
	@RequestMapping(value="/editProductPage.do")
	public String editProductPage(ProductVO pVO,ImagesVO iVO,CategoryVO ctVO,CategorydetailVO cdVO,Model model,HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}

		pVO.setSearchCondition("SELECTONE");
		pVO = productService.selectOne(pVO);

		List<ImagesVO> idatas = imagesService.selectAll(iVO);

		ctVO.setSearchCondition("SELECTALL");
		List<CategoryVO> cdatas = categoryService.selectAll(ctVO);

		cdVO.setSearchCondition("SELECTALL");
		List<CategorydetailVO> cddatas = categoryDetailService.selectAll(cdVO);

		model.addAttribute("pdata",pVO);
		model.addAttribute("idatas",idatas);
		model.addAttribute("cdatas",cdatas);
		model.addAttribute("cddatas",cddatas);

		return "admin-productedit.jsp";
	}

	// 관리자 상품 수정 액션
	@RequestMapping(value="/editProduct.do")
	public String editProduct(ProductVO pVO,CategorydetailVO cdVO,ImagesVO iVO,@RequestParam("file") List<MultipartFile> uploadImages) throws IllegalStateException, IOException  {

		cdVO.setSearchCondition("CTNUM");
		cdVO = categoryDetailService.selectOne(cdVO);
		pVO.setCategoryNum(cdVO.getCategoryNum());

		pVO.setSearchCondition("ALL");

		System.out.println("업로드 이미지 : "+uploadImages.get(0).getOriginalFilename());

		if(productService.update(pVO)) {
			if(uploadImages.get(0).getOriginalFilename() == null || uploadImages.get(0).getOriginalFilename().isEmpty()) {
				return "redirect:productListPage.do";
			}
			else {
				List<ImagesVO> idatas = imagesService.selectAll(iVO);
				for (int i = 0; i < idatas.size(); i++) {
					iVO.setImageNum(idatas.get(i).getImageNum());
					if(imagesService.delete(iVO)) {
						File data = new File(ImagesVO.getPath2()+idatas.get(i).getPath());
						if(data.exists()) {
							data.delete();
						}
					}
				}
				ImagesVO idata = new ImagesVO();
				idata.setProductNum(pVO.getProductNum());
				for (int i = 0; i < uploadImages.size(); i++) {
					String fileName=uploadImages.get(i).getOriginalFilename();
					String ext = fileName.substring(fileName.lastIndexOf("."));
					String randFileName= UUID.randomUUID().toString()+ext;

					String path = ImagesVO.getPath2()+randFileName;

					uploadImages.get(i).transferTo(new File(path));
					idata.setPath(randFileName);

					imagesService.insert(idata);
				}
			}
		}
		return "redirect:productListPage.do";
	}

	// 관리자 상품 등록 페이지 이동
	@RequestMapping(value="/addProductPage.do")
	public String addProductPage(CategoryVO ctVO, CategorydetailVO cdVO, Model model,HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}

		ctVO.setSearchCondition("SELECTALL");
		List<CategoryVO> cdatas = categoryService.selectAll(ctVO);

		cdVO.setSearchCondition("SELECTALL");
		List<CategorydetailVO> cddatas = categoryDetailService.selectAll(cdVO);


		model.addAttribute("cdatas",cdatas);
		model.addAttribute("cddatas",cddatas);

		return "admin-addproduct.jsp";
	}

	// 관리자 상품 등록 액션
	@RequestMapping(value="/addproduct.do")
	public String addproduct(ProductVO pVO, CategorydetailVO cdVO, ImagesVO iVO,
			@RequestParam("file") List<MultipartFile> uploadImages) throws IllegalStateException, IOException {
		System.out.println("상품추가 로직 테스트");
		System.out.println("pVO : "+pVO);
		System.out.println("idatas : "+uploadImages);

		cdVO.setCategoryDetailNum(pVO.getCategoryDetailNum());
		cdVO.setSearchCondition("CTNUM");
		cdVO = categoryDetailService.selectOne(cdVO);
		pVO.setCategoryNum(cdVO.getCategoryNum());

		if(productService.insert(pVO)) {
			pVO.setSearchCondition("PRODUCTNUM");
			pVO = productService.selectOne(pVO);
			iVO.setProductNum(pVO.getProductNum());
			for (int i = 0; i < uploadImages.size(); i++) {
				String fileName=uploadImages.get(i).getOriginalFilename();
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String randFileName= UUID.randomUUID().toString()+ext;
				String path = ImagesVO.getPath2()+randFileName;

				uploadImages.get(i).transferTo(new File(path));
				iVO.setPath(randFileName);

				imagesService.insert(iVO);
			}
		}
		return "redirect:productListPage.do";
	}

	// 관리자 상품 삭제 액션
	@RequestMapping(value="/deleteProduct.do")
	public String deleteProduct(ProductVO pVO,ImagesVO iVO) {

		// System.out.println(" 제품 번호 : "+iVO.getProductNum());
		// 관리자가 상품 삭제 전에... 
		List<ImagesVO> idatas = imagesService.selectAll(iVO);

		//System.out.println("idatas = "+idatas);
		for (int i = 0; i < idatas.size(); i++) {
			File data = new File(ImagesVO.getPath2()+idatas.get(i).getPath());
			if(data.exists()) {
				data.delete();
				iVO.setImageNum(idatas.get(i).getImageNum());
				if(imagesService.delete(iVO)) {
					if(productService.delete(pVO)) {
						return "redirect:productListPage.do";
					}
				}
			}
		}
		return "error.do";
	}

	// 관리자 상품 선택 삭제 액션
	@RequestMapping(value="/deleteProducts.do")
	public String deleteProducts(@RequestParam("number") List<String> selectedProducts,ImagesVO iVO) {
		
		if(selectedProducts == null || selectedProducts.isEmpty()) {
			return "redirect:productListPage.do";
		}
		
		List<ImagesVO> idatas = imagesService.selectAll(iVO);
		
		for(int i = 0; i < selectedProducts.size(); i++) {
			iVO.setProductNum(Integer.parseInt(selectedProducts.get(i)));
			for (int j = 0; j<idatas.size();j++) {
				File data = new File(ImagesVO.getPath2()+idatas.get(j).getPath());
				if(data.exists()) {
					data.delete();
					iVO.setImageNum(idatas.get(j).getImageNum());
					imagesService.delete(iVO);
				}
			}
			ProductVO pVO = new ProductVO();
			pVO.setProductNum(Integer.parseInt(selectedProducts.get(i)));
			productService.delete(pVO);
		}
		return "redirect:productListPage.do";
	}

	// 관리자 카테고리 관리 페이지 이동
	@RequestMapping(value="/categoryListPage.do")
	public String categoryListPage(CategoryVO ctVO,CategorydetailVO cdVO, Model model,HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}

		cdVO.setSearchCondition("ADMIN");
		List<CategorydetailVO> cddatas = categoryDetailService.selectAll(cdVO);

		List<CategoryVO> ctdatas = categoryService.selectAll(ctVO);

		model.addAttribute("cddatas",cddatas);
//		model.addAttribute("ctdatas",ctdatas);

		return "admin-categorylist.jsp";
	}

	// 관리자 카테고리 추가 액션
	@RequestMapping(value="/addCategory.do")
	public String addCategory(CategoryVO ctVO, CategorydetailVO cdVO, Model model) {
		System.out.println("addCategory: 로그");

		System.out.println("ctVO 로그 : " + ctVO);
		if(categoryService.insert(ctVO)) {
			ctVO.setSearchCondition("ADMIN");
			ctVO=categoryService.selectOne(ctVO);
			System.out.println("ctVO CategoryNum 로그 : " + ctVO.getCategoryNum());

			CategorydetailVO cddata = new CategorydetailVO();
			cddata.setCategoryNum(ctVO.getCategoryNum());
			cddata.setCategoryDetailName("-");
			System.out.println("cdVO : "+cddata);
			if(categoryDetailService.insert(cddata)) {
				return "categoryListPage.do";
			}
		}
		return "redirect:adminMain.do";
	}

	// 관리자 카테고리 수정 액션
	@RequestMapping(value="/updateCategory.do")
	public String updateCategory(CategoryVO ctVO) {
		System.out.println("updateCategory: 로그");

		categoryService.update(ctVO);

		return "categoryListPage.do";

	}

	// 관리자 카테고리 삭제 액션
	@RequestMapping(value="/deleteCategory.do")
	public String deleteCategory(CategoryVO ctVO, CategorydetailVO cdVO) {
		System.out.println("deleteCategory: 로그");


		/* 카테고리 를 삭제하면 세부 카테고리도 같이 삭제 */
		if(categoryService.delete(ctVO)) {
			cdVO.setSearchCondition("CTNUM");
			if(categoryDetailService.delete(cdVO)) {
				return "categoryListPage.do";
			}
		}
		return "adminMain.do";
	}

	// 관리자 상세 카테고리 추가 액션
	@RequestMapping(value="/addCategoryDetail.do")
	public String addCategoryDetail(CategoryVO ctVO, CategorydetailVO cdVO) {

		cdVO.setCategoryNum(ctVO.getCategoryNum());
		cdVO.setCategoryDetailName(cdVO.getCategoryDetailName());
		categoryDetailService.insert(cdVO);

		return "categoryListPage.do";
	}

	// 관리자 상세 카테고리 수정 액션
	@RequestMapping(value="/updateCategoryDetail.do")
	public String updateCategoryDetail(CategorydetailVO cdVO) {
		categoryDetailService.update(cdVO);

		return "categoryListPage.do";
	}

	// 관리자 상세 카테고리 삭제 액션
	@RequestMapping(value="/deleteCategoryDetail.do")
	public String deleteCategoryDetail(CategorydetailVO cdVO,CategoryVO cVO) {

		int categorynum = cdVO.getCategoryNum();

		cdVO.setSearchCondition("CDNUM");
		if(categoryDetailService.delete(cdVO)) {

			cdVO.setSearchCondition("ADMIN");
			if(categoryDetailService.selectOne(cdVO) == null) {
				cdVO.setCategoryNum(categorynum);
				cdVO.setCategoryDetailName("-");
				System.out.println("cdVO : "+cdVO);
				categoryDetailService.insert(cdVO);
			}
			return "categoryListPage.do";
		}
		return "adminMain.do";
	}

	// 관리자 주문내역 페이지 이동
	@RequestMapping(value="/orderListPage.do")
	public String orderListPage(PayVO payVO,Model model,HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}

		payVO.setSearchCondition("ADMIN");
		List<PayVO> paydatas= payService.selectAll(payVO);

		for (int i = 0; i < paydatas.size(); i++) {
			Date date = paydatas.get(i).getPayTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = sdf.format(date);
			paydatas.get(i).setTime(dateString);
		}

		model.addAttribute("paydatas",paydatas);

		return "admin-orderlist.jsp";
	}

	// 관리자 배송 정보 업데이트 액션
	@RequestMapping(value="/updatepay.do")
	public String updatepay(PayVO payVO) {

		System.out.println("payVO = "+payVO);

		if(payService.update(payVO)) {
			System.out.println("업데이트 성공!");
		}
		return "orderListPage.do";
	}

	@RequestMapping(value="/excelDown.do")
	public String excelDown(PayVO payVO,HttpServletResponse response) throws IOException {
		//sheet 생성
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("배송정보");
		int rowNo = 0;
		// 셀 스타일 설정
		CellStyle headStyle = workbook.createCellStyle();
		headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_BLUE.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		font.setFontHeightInPoints((short) 13);
		headStyle.setFont(font);
		// 헤더 설정
		Row headerRow = sheet.createRow(rowNo++);
		headerRow.createCell(0).setCellValue("결제번호");
		headerRow.createCell(1).setCellValue("상품정보");
		headerRow.createCell(2).setCellValue("주문자ID");
		headerRow.createCell(3).setCellValue("주문일시");
		headerRow.createCell(4).setCellValue("우편번호");
		headerRow.createCell(5).setCellValue("주소");
		headerRow.createCell(6).setCellValue("상세주소");
		// 각 셀 스타일 적용
		for(int i=0; i<=6; i++){
			headerRow.getCell(i).setCellStyle(headStyle);
		}
		// PAY 데이터 SELECTALL
		payVO.setSearchCondition("ADMIN");
		List<PayVO> paydatas= payService.selectAll(payVO);
		for (int i = 0 ; i < paydatas.size(); i++) {
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(paydatas.get(i).getPayNum());
			row.createCell(1).setCellValue(paydatas.get(i).getProductName());
			row.createCell(2).setCellValue(paydatas.get(i).getMemberId());
			row.createCell(3).setCellValue(paydatas.get(i).getPayTime());
			row.createCell(4).setCellValue(paydatas.get(i).getZipcode());
			row.createCell(5).setCellValue(paydatas.get(i).getAddress());
			row.createCell(6).setCellValue(paydatas.get(i).getAddressDetail());
		}
		// 너비 설정
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 8000);
		sheet.setColumnWidth(6, 3000);
		
		// 파일 설정 및 다운
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=Paylist.xlsx");
		workbook.write(response.getOutputStream());
		workbook.close();

		return null;
	}


	// 관리자 리뷰 페이지 이동
	@RequestMapping(value="/reviewPage.do")
	public String reviewPage(ReviewVO rVO,Model model,HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}
		List<ReviewVO> rdatas = reviewService.selectAll(rVO);

		for (int i = 0; i < rdatas.size(); i++) {
			Date date = rdatas.get(i).getReviewTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = sdf.format(date);
			rdatas.get(i).setTime(dateString);
		}
		model.addAttribute("rdatas",rdatas);

		return "admin-reviews.jsp";
	}

	// 관리자 리뷰등록 페이지 이동
	@RequestMapping(value="/addreviewPage.do")
	public String addreviewPage(ProductVO pVO, Model model,HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}

		pVO.setSearchCondition("");
		List<ProductVO> pdatas = productService.selectAll(pVO);

		model.addAttribute("pdatas",pdatas);

		return "admin-addreviews.jsp";
	}

	// 관리자 리뷰 등록 액션
	@RequestMapping(value="/addReview.do")
	public String addReview(ReviewVO rVO, HttpSession session) {
		System.out.println("addReviewController로그");

		rVO.setMemberId((String)session.getAttribute("memberId"));

		try {
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Date parsedDate = originalFormat.parse(rVO.getTime());
			String formattedDateString = targetFormat.format(parsedDate);
			Date formattedDate = targetFormat.parse(formattedDateString);
			rVO.setReviewTime(formattedDate);
		} catch (ParseException e) {
			rVO.setReviewTime(new Date());
		}

		String role=(String)session.getAttribute("role");

		if(role == "ADMIN") {
			rVO.setSearchCondition("ADMIN");
			reviewService.insert(rVO);
			return "redirect:reviewPage.do";
		}
		else {
			rVO.setSearchCondition("USER");
			reviewService.insert(rVO);   
			return "redirect:mypagePage.do";
		}
	}

	// 관리자 리뷰 수정 액션
	@RequestMapping(value="/updateReview.do")
	public String updateReview(ReviewVO rVO, HttpSession session) {
		System.out.println("updateReviewController로그");

		rVO.setSearchCondition("REPLY");
		reviewService.update(rVO);
		return "reviewPage.do";
	}

	// 관리자 답글 달기 액션
	@RequestMapping(value="/updateReply.do")
	public String updateReply(ReviewVO rVO) {
		rVO.setSearchCondition("REPLY");
		reviewService.update(rVO);
		return "redirect:reviewPage.do";
	}

	// 관리자 회원 목록 페이지 이동
	@RequestMapping(value="/memberListPage.do")
	public String memberListPage(MemberVO mVO, Model model, HttpSession session) {

		String role = (String)session.getAttribute("role");
		if(role == null) {
			return "redirect:main.do";
		}

		//mVO.setSearchCondition("");
		List<MemberVO> mdatas = memberService.selectAll(mVO);

		model.addAttribute("mdatas",mdatas);

		return "admin-memberlist.jsp";
	}

	// 관리자 회원 상세 페이지
	@RequestMapping(value="/memberdetail.do")
	@ResponseBody
	public String memberdetail(MemberVO mVO) throws JSONException {
		mVO.setSearchCondition("NOPW");
		mVO = memberService.selectOne(mVO);

		JSONObject obj = new JSONObject();
		obj.put("memberId", mVO.getMemberId());
		obj.put("memberName", mVO.getMemberName());
		obj.put("email", mVO.getEmail()+"@"+mVO.getDomain());
		obj.put("phonenumber", mVO.getPhonenumber());
		obj.put("role", mVO.getRole());

		return obj.toString();
	}

	// 관리자 회원 업데이트 액션
	@RequestMapping(value="/updateRole.do")
	public String updateRole(MemberVO mVO) {

		mVO.setSearchCondition("ROLE");
		memberService.update(mVO);

		return "memberListPage.do";
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/addImageDataPage.do")
	public String addImageDataPage(ProductVO pVO,Model model) {

		pVO.setSearchCondition("PRODUCTLIST");
		List<ProductVO> pdatas = productService.selectAll(pVO);

		model.addAttribute("pdatas", pdatas);

		return "admin-addimagedata.jsp";
	}

	@RequestMapping(value="/addImageData.do")
	public String addImageData(ProductVO pVO,ImagesVO iVO,@RequestParam("file") List<MultipartFile> idatas) throws IllegalStateException, IOException {

		iVO.setProductNum(pVO.getProductNum());
		for (int i = 0; i < idatas.size(); i++) {
			String fileName=idatas.get(i).getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String randFileName= UUID.randomUUID().toString()+ext;

			String path = ImagesVO.getPath2()+randFileName;

			idatas.get(i).transferTo(new File(path));
			iVO.setPath(randFileName);

			imagesService.insert(iVO);
		}

		return "addImageDataPage.do";
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------

	// 아이디 찾기 페이지 이동
	@RequestMapping(value="/idSearchPage.do")
	public String idSearchPage() {

		return "redirect:idsearch.jsp";
	}

	// 아이디 찾기 액션 (MAIL API)
	@RequestMapping(value="/idSearch.do")
	public String idSearch(MemberVO mVO,AlertVO aVO,HttpSession session) {

		mVO.setSearchCondition("MEMBERID");
		mVO = memberService.selectOne(mVO);
		System.out.println("mVO : "+mVO);

		if(mVO.getMemberId().contains("G@")) {
			aVO.setSearchCondition("google");
			session.setAttribute("aVO", aVO);
			return "redirect:error.do";
		}

		if(mVO!=null) {
			// 이메일 제목과 내용 설정
			try {
				String link = "http://localhost:8080/app/loginPage.do";
				String from = "rkdtmdcks012@gmail.com";
				String memberEmail = mVO.getEmail()+"@"+mVO.getDomain();
				String subject = "SoundPlay | 아이디 찾기 정보";
				String content = "<h1>SoundPlay</h1><br>"
						+"<h3>회원님의 아이디 정보입니다.</h3><br>"
						+"<h3> 아이디 : "+mVO.getMemberId()+"</h3><br>"
						+"<h3><a href="+link+">로그인 하기</a></h3>";

				MimeMessage mail = mailSender.createMimeMessage();
				MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");

				mailHelper.setFrom(from);
				mailHelper.setTo(memberEmail);
				mailHelper.setSubject(subject);
				mailHelper.setText(content, true);

				mailSender.send(mail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "idsearchDone.jsp";
		}
		return "error.do";
	}

	// 비번 찾기 페이지 이동
	@RequestMapping(value="/pwSearchPage.do")
	public String pwSearchPage() {

		return "redirect:pwsearch.jsp";
	}

	// 비번 찾기 액션 (SMS API)
	@RequestMapping(value="/pwSearch.do")
	public String pwSearch(MemberVO mVO,Model model, AlertVO aVO, HttpSession session) {

		mVO.setSearchCondition("MEMBERPW");
		mVO = memberService.selectOne(mVO);

		if(mVO.getMemberId().contains("G@")) {
			aVO.setSearchCondition("google");
			session.setAttribute("aVO", aVO);
			return "redirect:error.do";
		}

		if(mVO!=null) {

			Random rand = new Random();

			int min = 1000;
			int max = 9999;
			int randcode = rand.nextInt(max - min + 1)+min;
			String code = Integer.toString(randcode);

			//MWKKLMCFJWKNPVNYA7OYGW2ZVCVPUZUL
			DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSBBLUTAADYDBDV", "MWKKLMCFJWKNPVNYA7OYGW2ZVCVPUZUL", "https://api.coolsms.co.kr");

			Message message = new Message();
			// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
			message.setFrom("01031794058");
			message.setTo(mVO.getPhonenumber());
			message.setText("인증번호는 "+code+"입니다. 정확히 입력해주세요.");

			SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));

			model.addAttribute("mdata",mVO);
			model.addAttribute("code",code);
			return "pwCode.jsp";
		}
		return "redirect:pwsearchFail.jsp";
	}

	// 비밀번호 변경 페이지 이동 (코드 입력 완료된 사람만)
	@RequestMapping(value="/pwsearchDonePage.do")
	public String pwcode(MemberVO mVO,Model model) {

		model.addAttribute("mdata",mVO);
		return "pwsearchDone.jsp";

	}

	// 비밀번호 변경 액션
	@RequestMapping(value="/updatepw.do")
	public String updatepw(MemberVO mVO,Model model) {

		mVO.setSearchCondition("MEMBERPW");
		if(memberService.update(mVO)) {
			return "loginPage.do";
		}
		return "error.do";

	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------

	// 사용자 리뷰 작성 페이지 이동
	@RequestMapping(value="/userReviewPage.do")
	public String userReviewPage(ProductVO pVO,Model model) {
		pVO = productService.selectOne(pVO);
		model.addAttribute("pdata",pVO);
		return "review.jsp";
	}

	// 사용자 리뷰 작성 액션
	@RequestMapping(value="/addUserReview.do")
	public String addUserReview(ReviewVO rVO,Model model) {
		rVO.setSearchCondition("USER");
		if(reviewService.insert(rVO)) {
			System.out.println("성공");
		}
		return "userReviewPage.do";
	}
	//------------------------------------------------------------------------

	@RequestMapping(value="/error.do")
	public String error(AlertVO aVO, Model model,HttpSession session) {

		aVO = (AlertVO)session.getAttribute("aVO");
		// 로그인 안한 경우
		if(aVO == null) {
			aVO = new AlertVO();
			// warning, success, info, error, question
			aVO.setIcon("error");
			aVO.setTitle("이용할 수 없는 기능");
			aVO.setMsg("로그인 후 이용해주시길 바랍니다.");
			aVO.setConfirmtext("로그인 하기");
			aVO.setCanceltext("닫기");
			aVO.setPath("loginPage.do");
		// 구글 회원 경우
		}else if(aVO.getSearchCondition().equals("google")){
			aVO.setIcon("warning");
			aVO.setTitle("찾기 불가");
			aVO.setMsg("구글 계정은 아이디 또는 비밀번호를 찾을 수 없습니다.");
			aVO.setConfirmtext("닫기");
		// 로그인 실패 경우
		}else if(aVO.getSearchCondition().equals("login")){
			aVO.setIcon("warning");
			aVO.setTitle("로그인 실패");
			aVO.setMsg("아이디 또는 비밀번호가 일치하지 않습니다.");
			aVO.setConfirmtext("닫기");
		}
		model.addAttribute("alert",aVO);
		session.removeAttribute("aVO");
		return "sweet.jsp";
	}




}
