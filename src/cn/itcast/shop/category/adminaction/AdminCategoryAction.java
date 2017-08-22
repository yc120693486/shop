package cn.itcast.shop.category.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	
	private Category category = new Category();
	
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	//后台 查询一级分类
	public String findAll(){
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//后台 添加一级分类
	
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
		
	}
	//删除一级分类
	public String delete(){
		//查询一级分类
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//后台编辑一级分类
	public String edit(){
		category = categoryService.findByCid(category.getCid());
		//ActionContext.getContext().getValueStack().set("category", category);
		return "editSuccess";
	}
	
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	
}
