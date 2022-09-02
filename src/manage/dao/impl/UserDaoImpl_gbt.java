package manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import manage.dao.UserDao_gbt;
import manage.model.User_gbt;


public class UserDaoImpl_gbt extends HibernateDaoSupport  implements UserDao_gbt{
	
	@SuppressWarnings("unchecked")
	public List<User_gbt> getAll(String where) {
		return this.getHibernateTemplate().find("from User_gbt where 1=1 "+where+" order by id");
	}
	
	public void insertUser(User_gbt user_gbt){
		this.getHibernateTemplate().save(user_gbt);
	}
	
	public void delUser(User_gbt user_gbt) {
		this.getHibernateTemplate().delete(user_gbt);
	}
	
	@SuppressWarnings("unchecked")
	public User_gbt selectUserByusername(String username) {
		List<User_gbt> list = this.getHibernateTemplate().find("from User_gbt where username=? and userlock=0",username);
		if(list.size()==0){
			return null;
		}
		return list.get(0);

	}

	@SuppressWarnings("unchecked")
	public User_gbt selectUserbByusernameByPassword(String username, String password) {
		List<User_gbt> list = this.getHibernateTemplate().find("from User_gbt where codenum=? and password = ? and userlock=0",username,password);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	public void updateUser(User_gbt user_gbt) {
		this.getHibernateTemplate().update(user_gbt);
		
	}

	@SuppressWarnings("unchecked")
	public List<User_gbt> selectAllUser(final int start,final int limit) {
		return (List<User_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<User_gbt> list = session.createQuery("from User_gbt order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public int selectAllUserCount() {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from User_gbt").get(0);
		return (int)count;
	}

	public User_gbt selectUser(int id) {
		return this.getHibernateTemplate().get(User_gbt.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User_gbt> selectAllUserByusername(final int start,final int limit,final String keyword) {
		return (List<User_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<User_gbt> list = session.createQuery("from User_gbt where  username like ? order by id desc")
				.setParameter(0, "%"+keyword+"%")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<User_gbt> selectAllUserBy(final int start,final int limit,final String keyword) {
		return (List<User_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<User_gbt> list = session.createQuery("from User_gbt where  1=1 "+keyword+" order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}
	
}
