package manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import manage.dao.QingJiaDanDao_gbt;
import manage.model.QingJiaDan_gbt;

public class QingJiaDanDaoImpl_gbt extends HibernateDaoSupport implements  QingJiaDanDao_gbt{
	
	@SuppressWarnings("unchecked")
	public List<QingJiaDan_gbt> getAll(String where) {
		return this.getHibernateTemplate().find("from QingJiaDan_gbt where 1=1 "+where+" order by id");
	}
	
	public void insertQingJiaDan(QingJiaDan_gbt qingjiadan){
		this.getHibernateTemplate().save(qingjiadan);
	}
	
	public void delQingJiaDan(QingJiaDan_gbt qingjiadan) {
		this.getHibernateTemplate().delete(qingjiadan);
	}
	
	public void updateQingJiaDan(QingJiaDan_gbt qingjiadan) {
		this.getHibernateTemplate().update(qingjiadan);
		
	}

	@SuppressWarnings("unchecked")
	public List<QingJiaDan_gbt> selectAllQingJiaDan(final int start,final int limit) {
		return (List<QingJiaDan_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<QingJiaDan_gbt> list = session.createQuery("from QingJiaDan_gbt  order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public int selectAllQingJiaDanCount() {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from QingJiaDan_gbt").get(0);
		return (int)count;
	}

	public QingJiaDan_gbt selectQingJiaDan(int id) {
		return this.getHibernateTemplate().get(QingJiaDan_gbt.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<QingJiaDan_gbt> selectAllQingJiaDanBy(final int start,final int limit,final String keyword) {
		return (List<QingJiaDan_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<QingJiaDan_gbt> list = session.createQuery("from QingJiaDan_gbt where 1=1 "+keyword+" order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}
	
}
