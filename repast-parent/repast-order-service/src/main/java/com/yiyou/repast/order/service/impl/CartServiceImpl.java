package com.yiyou.repast.order.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.alibaba.dubbo.config.annotation.Service;
import com.yiyou.repast.order.dao.CartItemRepository;
import com.yiyou.repast.order.dao.CartRepository;
import com.yiyou.repast.order.model.Cart;
import com.yiyou.repast.order.model.CartItem;
import com.yiyou.repast.order.service.ICartService;

@Service
public class CartServiceImpl implements ICartService{

	@Resource
	private CartItemRepository cartItemRepository;
	@Resource
	private CartRepository cartRepository;
	
	@Override
	public Cart save(Cart obj) {
		obj.setCreateTime(new Date());
		return cartRepository.save(obj);
	}

	@Override
	public Cart update(Cart obj) {
		return cartRepository.save(obj);
	}

	@Override
	public CartItem save(CartItem obj) {
		return cartItemRepository.save(obj);
	}

	@Override
	public CartItem update(CartItem obj) {
		return cartItemRepository.save(obj);
	}

	@Override
	public void removeCartItem(Long id) {
		cartItemRepository.delete(id);
	}

	@Override
	public void clearCart(Long userId) {
		cartRepository.delete(findCart(userId));
	}

	@Override
	public Cart findCart(Long userId) {
		Cart cart=new Cart();
		cart.setUserId(userId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Cart> example = Example.of(cart, matcher); 
		return cartRepository.findOne(example);
	}


}
