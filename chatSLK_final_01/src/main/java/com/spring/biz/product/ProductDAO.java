//package com.spring.biz.product;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.spring.biz.common.JDBCUtil;
//@Repository("ProductDAO")
//public class ProductDAO {
//
//   private Connection conn;
//   private PreparedStatement pstmt;
//   private ResultSet rs;
//
//   static final private String SQL_SELECTALL="SELECT PRODUCT.PRODUCTNUM,(SELECT SUBSTRING_INDEX(PATH, ',', 1) FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM LIMIT 1) AS PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.CATEGORYNUM = CATEGORY.CATEGORYNUM INNER JOIN CATEGORYDETAIL ON PRODUCT.CATEGORYDETAILNUM = CATEGORYDETAIL.CATEGORYDETAILNUM";
//   static final private String SQL_SELECTALL_SEARCH="SELECT PRODUCT.PRODUCTNUM,(SELECT SUBSTRING_INDEX(PATH, ',', 1) FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM LIMIT 1) AS PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.CATEGORYNUM = CATEGORY.CATEGORYNUM INNER JOIN CATEGORYDETAIL ON PRODUCT.CATEGORYDETAILNUM = CATEGORYDETAIL.CATEGORYDETAILNUM WHERE PRODUCT.PRODUCTNAME LIKE '%'|| ? |'%';";
//   static final private String SQL_SELECTALL_CATEGORY="SELECT PRODUCT.PRODUCTNUM,(SELECT SUBSTRING_INDEX(PATH, ',', 1) FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM LIMIT 1) AS PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.CATEGORYNUM = CATEGORY.CATEGORYNUM INNER JOIN CATEGORYDETAIL ON PRODUCT.CATEGORYDETAILNUM = CATEGORYDETAIL.CATEGORYDETAILNUM WHERE PRODUCT.CATEGORYDETAILNUM=?";
//   static final private String SQL_SELECTALL_RECPRODUCT="SELECT PATH,PRODUCT.PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME FROM PRODUCT INNER JOIN IMAGES ON PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM INNER JOIN CATEGORY ON PRODUCT.CATEGORYNUM = CATEGORY.CATEGORYNUM INNER JOIN CATEGORYDETAIL ON PRODUCT.CATEGORYDETAILNUM = CATEGORYDETAIL.CATEGORYDETAILNUM LIMIT 0,?";
//
//   static final private String SQL_SELECTONE="SELECT PRODUCT.PRODUCTNUM,(SELECT SUBSTRING_INDEX(PATH, ',', 1) FROM IMAGES WHERE PRODUCT.PRODUCTNUM = IMAGES.PRODUCTNUM LIMIT 1) AS PATH,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN,PRODUCT.CATEGORYNUM,CATEGORYNAME,PRODUCT.CATEGORYDETAILNUM,CATEGORYDETAILNAME FROM PRODUCT INNER JOIN CATEGORY ON PRODUCT.CATEGORYNUM = CATEGORY.CATEGORYNUM INNER JOIN CATEGORYDETAIL ON PRODUCT.CATEGORYDETAILNUM = CATEGORYDETAIL.CATEGORYDETAILNUM WHERE PRODUCTNUM=?";
//
//   //   static final private String SQL_INSERT="INSERT INTO PRODUCT (PRODUCTNUM,PRODUCTNAME,COMPANY,PRODUCTPRICE,PRODUCTEXPLAIN) VALUES(?,?,?,?,?)";
//   //   static final private String SQL_UPDATE_NAME="UPDATE PRODUCT SET PRODUCTNAME=? WHERE PRODUCTNUM=?";
//   //   static final private String SQL_UPDATE_COMPANY="UPDATE PRODUCT SET COMPANY=? WHERE PRODUCTNUM=?";
//   //   static final private String SQL_UPDATE_PRICE="UPDATE PRODUCT SET PRODUCTPRICE=? WHERE PRODUCTNUM=?";
//   //   static final private String SQL_UPDATE_EXPLAIN="UPDATE PRODUCT SET PRODUCTEXPLAIN=? WHERE PRODUCTNUM=?";
//   //   static final private String SQL_DELETE="DELETE FROM PRODUCT WHERE PRODUCTNUM=?";
//
//   public List<ProductVO> selectAll(ProductVO pVO){
//      conn=JDBCUtil.connect();
//
//      List<ProductVO> datas=new ArrayList<ProductVO>();
//
//      try {
//         conn=JDBCUtil.connect();         
//         try {
//            if(pVO.getSearchCondition()=="상품목록전체출력") {
//               pstmt=conn.prepareStatement(SQL_SELECTALL);                  
//               rs=pstmt.executeQuery();
//            }            
//            else if(pVO.getSearchCondition()=="상품카테고리전체출력") {                     
//               pstmt=conn.prepareStatement(SQL_SELECTALL_CATEGORY);
//               pstmt.setInt(1,pVO.getCategoryDetailNum());               
//               rs=pstmt.executeQuery();
//            }
//            else if(pVO.getSearchCondition()=="추천상품출력") {
//            	pstmt=conn.prepareStatement(SQL_SELECTALL_RECPRODUCT);
//                pstmt.setInt(1,pVO.getListcnt());               
//                rs=pstmt.executeQuery();
//            }
//            else if(pVO.getSearchCondition()=="검색") {
//            	pstmt=conn.prepareStatement(SQL_SELECTALL_SEARCH);
//            	pstmt.setString(1,pVO.getSearchKeyword());               
//            	rs=pstmt.executeQuery();
//            }
//            
//            while(rs.next()) {
//               
//               ProductVO data=new ProductVO();
//               data.setPath(rs.getString("PATH")); //이미지경로 
//               data.setProductNum(rs.getInt("PRODUCTNUM")); //상품번호 
//               data.setProductName(rs.getString("PRODUCTNAME")); //상품이름 
//               data.setCompany(rs.getString("COMPANY")); //제조사 
//               data.setProductPrice(rs.getInt("PRODUCTPRICE")); //상품가격 
//               data.setProductCnt(rs.getInt("CNT")); //상품가격 
//               data.setProductExplain(rs.getString("PRODUCTEXPLAIN")); //상품설명 
//               data.setCategoryNum(rs.getInt("CATEGORYNUM")); //카테고리번호 
//               data.setCategoryName(rs.getString("CATEGORYNAME")); //카테고리이름
//               data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM")); //카테고리상세번호 
//               data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME")); //카테고리상세이름
//               datas.add(data);      
//            }                  
//         }
//         // SQL문 오류시
//         catch(SQLException e) {
//            e.printStackTrace();
//            return null;
//         }
//      }
//      catch(Exception e) {
//         e.printStackTrace();
//         return null;
//      }
//      finally {      
//         JDBCUtil.disconnect(rs,pstmt, conn);
//      }
//      return datas;
//   }
//
//   public ProductVO selectOne(ProductVO pVO){
//      conn=JDBCUtil.connect();
//
//      ProductVO data=null;
//
//      try {
//
//         pstmt=conn.prepareStatement(SQL_SELECTONE);
//         pstmt.setInt(1, pVO.getProductNum());
//         rs=pstmt.executeQuery();
//         if(rs.next()) {
//            data = new ProductVO();
//            data.setPath(rs.getString("PATH")); //이미지경로 
//            data.setProductNum(rs.getInt("PRODUCTNUM")); //상품번호
//            data.setProductName(rs.getString("PRODUCTNAME"));//상품이름
//            data.setCategoryNum(rs.getInt("CATEGORYNUM"));//카테고리번호
//            data.setCategoryName(rs.getString("CATEGORYNAME"));//카테고리이름
//            data.setCategoryDetailNum(rs.getInt("CATEGORYDETAILNUM"));//카테고리상세번호
//            data.setCategoryDetailName(rs.getString("CATEGORYDETAILNAME"));//카테고리상세이름
//            data.setCompany(rs.getString("COMPANY"));//제조사
//            data.setProductPrice(rs.getInt("PRODUCTPRICE"));//상품가격
//            data.setProductExplain(rs.getString("PRODUCTEXPLAIN"));//상품설명
//         }
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//      finally {
//         JDBCUtil.disconnect(rs, pstmt, conn);
//      }
//      return data;
//   }
//   /*
//   public boolean insert(ProductVO pVO) {
//      conn=JDBCUtil.connect();
//
//      try {
//         pstmt=conn.prepareStatement(SQL_INSERT);
//         pstmt.setInt(1, pVO.getProductNum());
//         pstmt.setString(2, pVO.getProductName());
//         pstmt.setString(3, pVO.getCompany());
//         pstmt.setInt(4, pVO.getProductPrice());
//         pstmt.setString(5, pVO.getProductExplain());
//
//         int rs=pstmt.executeUpdate();
//         if(rs<=0) {
//            return false;
//         }
//      } catch (SQLException e) {
//         e.printStackTrace();
//         return false;
//      }
//
//      JDBCUtil.disconnect(pstmt, conn);
//
//      return true;
//   }
//
//   public boolean update(ProductVO pVO) {
//      conn=JDBCUtil.connect();   
//      try {
//         if(pVO.getSerchCondition().equals("상품이름변경")) {            
//            try {
//               pstmt=conn.prepareStatement(SQL_UPDATE_NAME);
//               pstmt.setString(1,pVO.getProductName());
//               pstmt.setInt(2,pVO.getProductNum());
//               int res = pstmt.executeUpdate();
//               if(res <= 0) {
//                  return false;
//               }
//               return true;
//            }
//            // SQL문 오류시 
//            catch(SQLException e) {
//               e.printStackTrace();
//               return false;
//            }
//         }
//         else if (pVO.getSerchCondition().equals("제조사변경")) {
//            try {
//               pstmt=conn.prepareStatement(SQL_UPDATE_COMPANY);
//               pstmt.setString(1,pVO.getProductName());
//               pstmt.setInt(2,pVO.getProductNum());
//               int res = pstmt.executeUpdate();
//               if(res <= 0) {
//                  return false;
//               }
//               return true;
//            }
//            // SQL문 오류시 
//            catch(SQLException e) {
//               e.printStackTrace();
//               return false;
//            }
//         }
//         else if (pVO.getSerchCondition().equals("상품가격변경")) {
//            try {
//               pstmt=conn.prepareStatement(SQL_UPDATE_PRICE);
//               pstmt.setInt(1,pVO.getProductPrice());
//               pstmt.setInt(2,pVO.getProductNum());
//               int res = pstmt.executeUpdate();
//               if(res <= 0) {
//                  return false;
//               }
//               return true;
//            }
//            // SQL문 오류시 
//            catch(SQLException e) {
//               e.printStackTrace();
//               return false;
//            }
//         }
//         else if(pVO.getSerchCondition().equals("상품설명변경")) {
//            try {
//               pstmt=conn.prepareStatement(SQL_UPDATE_EXPLAIN);
//               pstmt.setString(1,pVO.getProductExplain());
//               pstmt.setInt(2,pVO.getProductNum());
//               int res = pstmt.executeUpdate();
//               if(res <= 0) {
//                  return false;
//               }
//               return true;
//            }
//            // SQL문 오류시 
//            catch(SQLException e) {
//               e.printStackTrace();
//               return false;
//            }
//         }
//
//      }
//      // conn 연결 실패
//      catch(Exception e) {
//         e.printStackTrace();
//         return false;
//      }
//      finally {      
//         JDBCUtil.disconnect(pstmt, conn);
//      }   
//      return false;
//   }
//
//   public boolean delete(ProductVO pVO) {
//      try {
//         conn=JDBCUtil.connect();               
//         try {
//            pstmt=conn.prepareStatement(SQL_DELETE);
//            pstmt.setInt(1,pVO.getProductNum());
//            int res = pstmt.executeUpdate();
//            if(res <= 0) {
//               return false;
//            }
//            return true;
//         }
//         // SQL문 오류시
//         catch(SQLException e) {
//            e.printStackTrace();
//            return false;
//         }         
//      }
//      // conn 연결 실패
//      catch(Exception e) {
//         e.printStackTrace();
//         return false;
//      }
//      finally {      
//         JDBCUtil.disconnect(pstmt, conn);
//      }            
//   }
//    */
//}