package com.j2kb.cart.dao;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.j2kb.cart.vo.CartVO;
import com.j2kb.member.vo.MemberVO;
import com.j2kb.product.vo.ProductVO;



@Component
public class CartDAOImpl implements CartDAO{
	
	private static SqlSessionFactory sqlMapper=null;
	private static final Logger logger = LoggerFactory.getLogger(CartDAOImpl.class);
	
	public static SqlSessionFactory getInstance() {
		if(sqlMapper==null) {
			try {
				String resource = "SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	public void insertNewCart(CartVO cartVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		session.insert("mapper.cart.insertNewCart",cartVO);
		session.commit();
		session.close();
	}
	
	public int selectCurrentCartNum() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int cartNum = session.selectOne("mapper.cart.selectCurrentCartNum");
		return cartNum;
	}
	
	public List<CartVO> selectCartList(String memberId){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<CartVO> cartList = session.selectList("mapper.cart.selectCartList", memberId);
		return cartList;
	}

	
	public List<ProductVO> selectProductList(String memberId){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<ProductVO> productList = session.selectList("mapper.cart.selectProductList", memberId);
		return productList;
	}
}
