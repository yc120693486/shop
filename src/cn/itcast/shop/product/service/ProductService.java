package cn.itcast.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;
/**
 * 业务层
 * @author M MMMM
 *
 */
@Transactional  //����
public class ProductService {
	
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//��ҳ������Ʒ��ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		return productDao.findNew();
	}
	
	public Product findByPid(int pid){
		
		return productDao.findByPid(pid);
	}

	public PageBean<Product> finByPageCid(int cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);
		//��ҳ��
		int totalCount = 0;
		totalCount = productDao.finCoundCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		//totalPage = (int) Math.ceil(totalCount/limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin= (page-1)*limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//��ݶ������� ��ѯ��Ʒ
	public PageBean<Product> finByPageCsid(int csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);
		//��ҳ��
		int totalCount = 0;
		totalCount = productDao.finCoundCsid(csid);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin= (page-1)*limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPage(int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int limit=10;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int begin= (page-1)*limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
		
	}

	public void delete(Product product) {
		productDao.delete(product);
	}

	public void update(Product product) {
		productDao.update(product);
	}
	
}
