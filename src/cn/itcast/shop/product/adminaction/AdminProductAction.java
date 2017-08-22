package cn.itcast.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	

	private Product  product= new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	int page;
	
	public void setPage(int page) {
		this.page = page;
	}
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	// 文件上传需要的三个属性:
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	//带分页查询执行方法
	public String findAll(){
		PageBean<Product> pageBean=productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
		
	}
	public String  addPage(){
		//获得所有二级分类
		List<CategorySecond> csList =categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	//添加商品
	public String save() throws Exception{
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
		product.setPdate(time);
		// product.setImage(image);
		if(upload != null){
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
			//"F:/MyEclipse 10 workspace/shop/WebRoot/products"
			File saveFile = new File("F:/MyEclipse 10 workspace/shop/WebRoot/products/",uploadFileName);
			FileUtils.copyFile(upload, saveFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	
	public String delete(){
		product = productService.findByPid(product.getPid());
		//删除图片
		String path=product.getImage();
		if (path != null) {
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			File file2 = new File("F:/MyEclipse 10 workspace/shop/WebRoot/products",realPath);
			file2.delete();
			file.delete();
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	
	public String edit(){
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
		
	}
	
	public String update() throws ParseException, IOException{
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
		product.setPdate(time);
		if(upload != null){
			//删除之前的
			String oldPath = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+oldPath));
			file.delete();
			File file2 = new File("F:/MyEclipse 10 workspace/shop/WebRoot/",oldPath);
			file2.delete();
			//修改图片
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
			//"F:/MyEclipse 10 workspace/shop/WebRoot/products"
			File saveFile = new File("F:/MyEclipse 10 workspace/shop/WebRoot/products/",uploadFileName);
			FileUtils.copyFile(upload, saveFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
		
	}
}
