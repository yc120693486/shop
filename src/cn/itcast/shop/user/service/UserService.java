package cn.itcast.shop.user.service;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUitls;
import cn.itcast.shop.utils.UUIDUtils;

public class UserService {
	//ע��UserDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	// ���û�����ѯ�û��ķ���:
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	public UserDao getUserDao() {
		return userDao;
	}
	
	public void save(User user){
		user.setState(0); //0����δ���� 1�����Ѽ���
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//���ͼ����ʼ�
		MailUitls.sendMail(user.getEmail(), code);
	}
	// ���ݼ������ѯ
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
