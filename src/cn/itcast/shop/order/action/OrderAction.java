package cn.itcast.shop.order.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order = new Order();

	private OrderService orderService;
	
	private int page;
	//接收支付通道编码：
	private String pd_FrpId;
	
	// 接收付款成功后的参数:
		private String r3_Amt;
		private String r6_Order;
		
		public void setR3_Amt(String r3_Amt) {
			this.r3_Amt = r3_Amt;
		}

		public void setR6_Order(String r6_Order) {
			this.r6_Order = r6_Order;
		}
	
	public Order getModel() {
		return order;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public String saveOrder() throws ParseException{
		//从session 中获取购物车中的信息
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionError("亲，您还没有购物请先去购物！");
			return "msg";
		}
		//设置订单所属用户
		User existUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionError("亲，您还没有登录！请先去登录！");
			return "login";
		}
		order.setUser(existUser);
		//保存到数据库
				//设置订单生成时间
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
		order.setOrdertime(time);
		//1  未付款   设置订单是否付款
		order.setState(1);
		order.setAddr(order.getUser().getAddr());
		order.setPhone(order.getUser().getPhone());
		order.setName(order.getUser().getName());
		//设置订单总金额
		order.setTotal(cart.getTotal());
		//遍历购物车中的所有订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			//将订单项中的可用信息存储在 订单中
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		orderService.saveOrder(order);
		// 清空购物车:
		cart.clearCart();
		//显示在页面上
		//保存在值栈  模型驱动默认在栈顶
		return "saveOrder";
	}
	
	//我的订单查询
	public String findByUid(){
		//根据用户id查询
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(),page);
		//存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByUid";
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	//未付款订单 付款
	public String payOrder() throws IOException{
		Order currOrder= orderService.findByOid(order.getOid());
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = String.valueOf(order.getOid());// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	

	// 付款成功后跳转回来的路径:
	public String callBack(){
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	public String updateState(){
		order = orderService.findByOid(order.getOid());
		order.setState(4);
		orderService.update(order);
		return "updateStateSuccess";
	}

}
