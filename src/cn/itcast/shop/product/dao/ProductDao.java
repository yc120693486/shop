package cn.itcast.shop.product.dao;

import java.sql.SQLException;
import java.util.List;

import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{
	//��ҳ������Ʒ��ѯ
	public List<Product> findHot() {
		//���߲�ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//��ѯ����  1 �����ŵ�
		criteria.add(Restrictions.eq("is_hot", 1));
		criteria.addOrder(Order.desc("pdate"));
		//ִ��
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
	//��ҳ�£�new����Ʒ��ѯ
	public List<Product> findNew() {
		//���߲�ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//�����ڵ���
		criteria.addOrder(Order.desc("pdate"));
		//ִ��
		List<Product> nlist = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return nlist;
	}
	public Product findByPid(int pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	//�����ƷID��ѯ��Ʒ����
	public int finCoundCid(int cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list !=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//���һ������ID��ѯ��Ʒ����
	public List<Product> findByPageCid(int cid, int begin, int limit) {
		// TODO Auto-generated method stub
		//select p.* from category c, categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		
		//��ҳ��һ��д��
		List<Product> list =this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}
	//���������ѯ��Ʒ����
	public int finCoundCsid(int csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,csid);
		if(list !=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	public List<Product> findByPageCsid(int csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list =this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}
	//统计商品个数
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() !=0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	//分页查询商品
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";  //倒序
		List<Product> list =this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null , begin, limit));
		if (list != null && list.size() !=0) {
			return list;
		}
		return null;
	}
	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
}
