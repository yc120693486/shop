package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	public void saveOrder(Order order) {
		this.getHibernateTemplate().save(order);
	}
	
	//我的订单个数统计
	public int findByCountUid(int uid) {
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list =this.getHibernateTemplate().find(hql,uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
		
	}
	//我的订单查询
	public List<Order> findByPageUid(int uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
		
		
	}
	//根据Oid 查询订单
	public Order findByOid(int oid) {
		
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	public int findCount() {
		String hql="select count(*) from Order";
		List<Long> list =this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql,null,
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public List<Order> findByStatePage(int state, int begin, int limit) {
		String hql = "from Order where state = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql,new Object[] { state },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public int findCountByState(int state) {
		String hql="select count(*) from Order where state = ?";
		List<Long> list =this.getHibernateTemplate().find(hql,state);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<OrderItem> findOrderItem(int oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	
}
