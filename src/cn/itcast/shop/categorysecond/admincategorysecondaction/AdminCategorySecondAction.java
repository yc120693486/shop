package cn.itcast.shop.categorysecond.admincategorysecondaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	
	private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	
	
	//注入一级分类Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
		
	}
	
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//查找所有二级分类
	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String delete(){
		//级联删除
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	public String edit(){
		//根据csid查询二级分类对象
		categorySecond =categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有一级分类
		List<Category> cList =categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
		
	}
	
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
