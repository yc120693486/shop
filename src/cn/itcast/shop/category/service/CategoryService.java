package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;
/**
 * һ������ҵ������
 * @author M MMMM
 *
 */
@Transactional  //����
public class CategoryService {
	//ע��
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	// 保存一级分类
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}
	//查询一级分类
	public Category findByCid(int cid) {
		return categoryDao.findByCid(cid);
		
	}

	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
}
