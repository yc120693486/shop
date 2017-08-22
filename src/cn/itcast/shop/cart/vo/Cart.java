package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	//购物集合 key  PID  Map 删除简单
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	//Cart 中有CartItem的属性
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//总计
	private double total;
	
	public double getTotal() {
		return total;
	}

	//将购物项添加到购物车
	public void addCart(CartItem cartItem){
		/*判断购物车中是否存在该购物项 
		如果存在 数量增加 
		不存在 直接添加*/
		int pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem _cartItem =map.get(pid);//原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//不存在 直接添加
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	//从购物车中移除购物项
	public void removeCart(int pid){
		//购物项移除  总计减去移除项小计
		CartItem cartItem=map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//清空购物车
	public void clearCart(){
		//将所有购物项清空 总计设置为0
		map.clear();
		total=0;
	}
}
