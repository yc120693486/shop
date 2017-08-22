package cn.itcast.shop.order.service;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

@Transactional   //事物管理
public class OrderService {
	//ע��orderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
		
	}

	public PageBean<Order> findByPageUid(int uid, int page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		int limit = 5;
		pageBean.setLimit(limit);
		int totalCount =0;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		int totalPage =0;
		
		if(totalCount % limit ==0){
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page-1)*limit;
		List<Order> list = orderDao.findByPageUid(uid, begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(int oid) {
		
		return orderDao.findByOid(oid);
	}
	//修改订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	public PageBean<Order> findByPage(int page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount ;
		totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page-1)*limit;
		List<Order> list = orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Order> findByStatePage(int state, int page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		
		int limit = 10;
		pageBean.setLimit(limit);
		int totalCount ;
		totalCount = orderDao.findCountByState(state);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		}else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page-1)*limit;
		List<Order> list = orderDao.findByStatePage(state,begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public List<OrderItem> findOrderItem(int oid) {
		
		return orderDao.findOrderItem(oid);
	}
}
