package org.teach.dao;

import java.util.List;

import org.teach.vo.Menu;
import org.teach.vo.User;

public interface LoginDao {

	public User findUser(String username,String password);

	public List<Menu> getManage(User user);
}
