package cn.itcast.shop.order.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.user.vo.User;

/**
 * ����ʵ����
 * @author M MMMM
 *
 */
public class Order {
	
	private int oid;
	private double total;
	private Date ordertime;
	private int state;
	private String name;
	private String addr;
	private String phone;
	//���������û�
	private User user;
	
	//�����������Ķ��������
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	
	
	
}
