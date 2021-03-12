package com.j2kb.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.j2kb.cart.controller.CartControllerImpl;
import com.j2kb.cart.service.CartService;
import com.j2kb.cart.vo.CartVO;

@RestController
@RequestMapping("/api/cart")
public class CartControllerImpl implements CartController{
	
	@Autowired
	private CartService cartService;
	
	private static final Logger logger = LoggerFactory.getLogger(CartControllerImpl.class);
	
	///ResponseEntity : data + http status code
	@RequestMapping(method = RequestMethod.POST, path = "/addToCart")
	public ResponseEntity<String> addToCart(@RequestBody CartVO cartVO) {
		ResponseEntity<String> entity = null;
		try {
			cartService.addToCart(cartVO);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(method=RequestMethod.GET, path = "/cartlist")
	public List<CartVO> getCartList(@RequestParam("memberId") String memberId) {
		List<CartVO> list= cartService.getCartList(memberId);
		return list;
	}
	
	
}
