package cn.itcast.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class UserAction extends ActionSupport implements ModelDriven<User> {
	

	private User user = new User();
	
	public User getModel() {
		return user;
	}

	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String registPage() {
		return "registPage";
	}
	
	public String regist(){
		
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode1.equalsIgnoreCase(checkcode)){
			this.addActionError("您的验证码输入错误！");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功！请去邮箱激活！");
		return "msg";
	}
	
	public String findByName() throws IOException {
		
		User existUser = userService.findByUsername(user.getUsername());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if (existUser != null) {
			
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	
	
	public String active(){
		User existUser=userService.findByCode(user.getCode());
		if (existUser == null) {
			this.addActionMessage("激活失败！激活码错误！");
		}else {
			
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功！");
		}
		return "msg";
	}
	/**
	 * �û���¼
	 * @return
	 */
	public String loginPage(){
		
		return "loginPage";
		
	}
	/**
	 * ��¼����
	 */
	public String login(){
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionMessage("登录失败！用户名或密码错误！");
			return LOGIN;
		}else {
			this.addActionMessage("登录成功！");
			//���û���Ϣ����session
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
		}
		return "loginSuccess";
		
	}
	/**
	 * �û��˳�
	 */
	public String qiut(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "qiut";
	}
}
