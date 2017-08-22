package cn.itcast.shop.product.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��ƷAction
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	
	private Product product = new Product();
	
	private ProductService productService;
	//����cid
	private int cid;
	//���ն�������ID
	private int csid;
	
	//ע��һ������Service
	private CategoryService categoryService;
	//���յ�ǰҳ��
	private int page;


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public Product getModel() {
		return product;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCid() {
		return cid;
	}
	public int getCsid() {
		return csid;
	}
	public void setCsid(int csid) {
		this.csid = csid;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
	public void setPage(int page) {
		this.page = page;
	}

	

	public String findByCid(){

		//List<Category> list=categoryService.findAll();

		PageBean<Product> pageBean=productService.finByPageCid(cid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	
		public String findByCsid(){
			
		PageBean<Product> pageBean=productService.finByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
		}
	
	//根据商品pid查询商品
		
	public String findByPid(){
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	
}
