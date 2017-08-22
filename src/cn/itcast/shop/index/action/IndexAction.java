package cn.itcast.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
/*
 * 首页访问Action
 */
public class IndexAction extends ActionSupport{
	/**
	 * 执行访问首页的方法
	 */
	//注入一级分类的Service
	private CategoryService categoryService;
	//注入商品的Service
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/**
	 * 执行访问首页的方法
	 */
	public String execute(){
		//查询一级分类
		List<Category> cList =categoryService.findAll();
		//存入session
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品
		List<Product> hList = productService.findHot();
		//保存到值栈
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询热门商品
		List<Product> nList = productService.findNew();
		//保存到值栈
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}

}
