package org.teach.dao.impl;


import java.util.List;

import org.teach.dao.LoginDao;
import org.teach.utils.BaseDao;
import org.teach.vo.Menu;
import org.teach.vo.User;

public class LoginDaoImpl implements LoginDao {

	private BaseDao bd =new BaseDao();
	@Override
	public User findUser(String username, String password) {
		String sql = "select * from t_user t where t.username = ? and t.password = ?";
		return bd.get(User.class, sql, username, password);
	}
	@Override
	public List<Menu> getManage(User user) {
		String sql = "select distinct e.* from t_user a, t_u_r b, t_role c, t_r_m d, t_menu e "+
				"where a.uid=b.uid and b.rid=c.rid and c.rid=d.rid and d.mid=e.mid and a.uid = "+user.getUid();
		return bd.findAll(Menu.class, sql);
	}

}
