package cn.itcast.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
/*
 * ��ҳ����Action
 */
public class IndexAction extends ActionSupport{
	/**
	 * ִ�з�����ҳ�ķ���
	 */
	//ע��һ�������Service
	private CategoryService categoryService;
	//ע����Ʒ��Service
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/**
	 * ִ�з�����ҳ�ķ���
	 */
	public String execute(){
		//��ѯһ������
		List<Category> cList =categoryService.findAll();
		//����session
		ActionContext.getContext().getSession().put("cList", cList);
		
		//��ѯ������Ʒ
		List<Product> hList = productService.findHot();
		//���浽ֵջ
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		//���浽ֵջ
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}

}
