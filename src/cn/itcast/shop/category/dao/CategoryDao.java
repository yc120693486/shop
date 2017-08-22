package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{

	public List<Category> findAll() {
		String hql="from Category";
		List<Category> cList =this.getHibernateTemplate().find(hql);
		return cList;
	}

	public void save(Category category) {
		this.getHibernateTemplate().save(category);
		
	}
	//删除一级分类方法
	public void delete(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
	}
	//查询一级分类方法
	public Category findByCid(int cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class,cid);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

	
}
