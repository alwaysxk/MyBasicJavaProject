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
 * @param <T> 当前 DAO 处理的实体类的类型是什么
 * 	封装了对基本数据库增删改查的操作，以便子类继承
 */
public class Dao<T> {
	
	private Connection connection = null;
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T>  clazz;
	
	public Dao(){
		/**
		 * 当子类继承Dao<T>时，如  Class B extends Dao<A>
		 * clazz获得的就是A这个运行时类
		 */
		clazz = ReflectionUtils.getSuperGenericType(getClass());
		
	}
	
	/**
	 * 获得一组对象
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
	 *  获得一个对象
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
	 * insert、 delete、update
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
	 * 返回某一个字段的值， 比如返回一条记录的address
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

