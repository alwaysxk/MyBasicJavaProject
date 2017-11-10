package com.simple.dao;

import java.util.List;

import com.simplemvc.domain.CriteriaUser;
import com.simplemvc.domain.User;

/**
 * 
 * @author XK
 *	��װ�˶�User�ľ������
 *	����ɾ���ġ���
 */
public interface UserDao {

	public void update(User user);
	
	public void add(User user);
	
	public List<User> getAll();
	
	public User get(Integer id);
	
	public void delete(Integer id);
	
	public long getCountWithName(String username);
	public List<User> getForListWithCriteriaCustomer(CriteriaUser user);
}
