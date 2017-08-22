package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台用户管理
 * @author M MMMM
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	
	private AdminUser adminUser = new AdminUser();
	
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	
	public String login(){
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if (existAdminUser == null) {
			this.addActionError("您的用户名或者密码错误！");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser",existAdminUser );
			return "loginSuccess";
		}
		
	}

}
