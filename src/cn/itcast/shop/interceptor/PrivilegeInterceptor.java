package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 后台拦截器
 * @author M MMMM
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	//执行拦截的方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (existAdminUser == null) {
			//没登陆
			ActionSupport actionSupport =(ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("亲！ 您未登陆，没有权限访问！");
			return "loginFail";
		}else {
			return actionInvocation.invoke();
		}
		
	}

}
