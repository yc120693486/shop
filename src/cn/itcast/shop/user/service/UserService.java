package cn.itcast.shop.user.service;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUitls;
import cn.itcast.shop.utils.UUIDUtils;

public class UserService {
	//注入UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	// 按用户名查询用户的方法:
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void save(User user){
		user.setState(0); //0代表未激活 1代表已激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUitls.sendMail(user.getEmail(), code);
	}
	// 根据激活码查询
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}

	public void update(User existUser) {
		userDao.update(existUser);
	}

	public User login(User user) {
		
		return userDao.login(user);
	}
}
