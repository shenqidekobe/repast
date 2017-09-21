package com.yiyou.repast.order.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.StringUtils;

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
	public CartItem saveCartItem(CartItem obj) {
		return cartItemRepository.save(obj);
	}

	@Override
	public CartItem updateCartItem(CartItem obj) {
		return cartItemRepository.save(obj);
	}

	@Override
	public void removeCartItem(Long id) {
		cartItemRepository.delete(id);
	}

	@Override
	public void clearCart(Long id) {
		Cart cart=findCartById(id);
		//Set<CartItem> set=cart.getItems();
		//if(!set.isEmpty())cartItemRepository.delete(set);
		cartRepository.delete(cart);//级联同步删除购物车里的项
	}

	@Override
	public Cart findCart(Long userId) {
		Cart cart=new Cart();
		cart.setUserId(userId);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Cart> example = Example.of(cart, matcher); 
		return cartRepository.findOne(example);
	}

	@Override
	public Cart findCartById(Long id) {
		return cartRepository.findOne(id);
	}

	@Override
	public CartItem findCartItemById(Long id) {
		return cartItemRepository.findOne(id);
	}

	@Override
	public Cart findCartByDeskNum(String deskNum) {
		if(StringUtils.isEmpty(deskNum))return null;
		Cart cart=new Cart();
		cart.setDeskNum(deskNum);
	    ExampleMatcher matcher = ExampleMatcher.matching();
	    Example<Cart> example = Example.of(cart, matcher); 
		return cartRepository.findOne(example);
	}

}
