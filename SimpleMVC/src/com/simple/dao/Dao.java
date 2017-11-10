package com.simple.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.simplemvc.db.JDBCUtils;
import com.simplemvc.reflect.ReflectionUtils;

/**
 * 
 * @author XK
 *	
 * @param <T> ��ǰ DAO �����ʵ�����������ʲô
 * 	��װ�˶Ի������ݿ���ɾ�Ĳ�Ĳ������Ա�����̳�
 */
public class Dao<T> {
	
	private Connection connection = null;
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T>  clazz;
	
	public Dao(){
		/**
		 * ������̳�Dao<T>ʱ����  Class B extends Dao<A>
		 * clazz��õľ���A�������ʱ��
		 */
		clazz = ReflectionUtils.getSuperGenericType(getClass());
		
	}
	
	/**
	 * ���һ�����
	 */
	public List<T> getForList(String sql, Object ...args){
		try {
			connection = JDBCUtils.getConnection();
			return queryRunner.query(connection,sql, new BeanListHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.realseConnection(connection);
		}
		
		return null;
	}
	/**
	 *  ���һ������
	 */
	public T get(String sql ,  Object ... args){
		try {
			connection = JDBCUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.realseConnection(connection);
		}
		return null;
	}
	
	/**
	 * insert�� delete��update
	 */
	
	public void update(String sql, Object ... args){
		try {
			connection = JDBCUtils.getConnection();
			queryRunner.update(connection, sql,  args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.realseConnection(connection);
			
		}
	}
	
	/**
	 * 
	 * ����ĳһ���ֶε�ֵ�� ���緵��һ����¼��address
	 * 
	 */
	
	public <E> E getForValue(String sql, Object ... args){
		try {
			connection = JDBCUtils.getConnection();
			return (E)queryRunner.query(connection, sql, new ScalarHandler(),args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.realseConnection(connection);
		}
		return null;
	}

}

