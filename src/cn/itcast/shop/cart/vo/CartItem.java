package cn.itcast.shop.cart.vo;

import cn.itcast.shop.product.vo.Product;


//


//  测试  代码 
public class CartItem {
	private Product product;  //购买商品信息
	private int count;   //数量
	private double subtotal; //总计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//计算小计
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
	
}
