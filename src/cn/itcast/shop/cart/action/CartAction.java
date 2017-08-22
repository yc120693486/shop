package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	
	private int pid;
	
	private int count;
	
	private ProductService productService;
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//加入购物车ﳵ
	public String addCart(){
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findByPid(pid));
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	//创建购物车session   **********
	private Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//��չ��ﳵ
	public String clearCart(){
		Cart cart= getCart();
		cart.clearCart();
		return "clearCart";
	}
	//ɾ������
	public String removeCart(){
		Cart cart= getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	//�ҵĹ��ﳵ
	public String myCart(){
		return "myCart";
	}
}
