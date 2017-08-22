package cn.itcast.shop.order.adminaction;

import java.util.List;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	
	private Order order = new Order();
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	
	public String findAll(){
		PageBean<Order> pageBean = orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String findByState(){
		PageBean<Order> pageBean = orderService.findByStatePage(order.getState(),page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByState";
	}
	
	public String findOrderItem(){
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	public String updateState(){
		order = orderService.findByOid(order.getOid());
		order.setState(3);
		orderService.update(order);
		return "updateStateSuccess";
	}
	
}
