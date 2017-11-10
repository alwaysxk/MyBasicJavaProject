package com.simple.dao;

import java.util.List;

import com.simplemvc.domain.CriteriaUser;
import com.simplemvc.domain.User;

/**
 * 
 * @author XK
 *	对User操作的具体实现类
 */
public class UserDaoImpl extends Dao<User> implements UserDao{

	public static void main(String[] args) {		
	}

	@Override
	public void add(User user) {
		String sql = "insert into user (username, age, address) values (?, ?, ?);";
		update(sql, user.getUserName(),user.getAge(), user.getAddress());
	}

	@Override
	public List<User> getAll() {
		String sql = "select * from user";
		return getForList(sql);
	}

	@Override
	public User get(Integer id) {
		String sql = "select * from user where id = ?";
		return get(sql, id);
	}

	@Override
	public void update(User user) {
		String sql = "update user set username = ?, age = ?, address = ? where id = ?";
		update(sql, user.getUserName(), user.getAge(), user.getAddress(), user.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from user where id = ?";
		update(sql, id);
		
	}

	@Override
	public long getCountWithName(String username) {
		String sql = "select count(id) from user where username = ?";

		return getForValue(sql, username);
	}

	@Override
	public List<User> getForListWithCriteriaCustomer(CriteriaUser user) {
		String sql;
		if(user.getAge() == null){
		sql = "select * from user where username like ?  and address like ?";
		return getForList(sql, user.getUserName(), user.getAddress());
		}
		else{
		sql = "select * from user where username like ? and age = ? and address like ?";
		return getForList(sql, user.getUserName(), user.getAge(), user.getAddress());
		}
	}

}
