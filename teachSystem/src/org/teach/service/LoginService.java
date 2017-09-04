package org.teach.service;

import java.util.List;

import org.teach.dao.LoginDao;
import org.teach.dao.impl.LoginDaoImpl;
import org.teach.vo.Menu;
import org.teach.vo.User;

public class LoginService {

	private LoginDao loginDao = new LoginDaoImpl();
	
	public User findUser(String username,String password){
		return loginDao.findUser(username, password);
	}

	public List<Menu> getManage(User user) {
		List<Menu> listUser = loginDao.getManage(user);
		return listUser;
	}
}
