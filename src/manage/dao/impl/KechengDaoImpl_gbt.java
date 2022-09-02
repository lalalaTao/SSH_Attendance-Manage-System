package manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import manage.dao.KechengDao_gbt;
import manage.model.Kecheng_gbt;

public class KechengDaoImpl_gbt extends HibernateDaoSupport implements  KechengDao_gbt{
	
	@SuppressWarnings("unchecked")
	public List<Kecheng_gbt> getAll(String where) {
		return this.getHibernateTemplate().find("from Kecheng_gbt where 1=1 "+where+" order by id");
	}
	
	public void insertKecheng(Kecheng_gbt kecheng_gbt){
		this.getHibernateTemplate().save(kecheng_gbt);
	}
	
	public void delKecheng(Kecheng_gbt kecheng_gbt) {
		this.getHibernateTemplate().delete(kecheng_gbt);
	}
	
	public void updateKecheng(Kecheng_gbt kecheng_gbt) {
		this.getHibernateTemplate().update(kecheng_gbt);
		
	}

	@SuppressWarnings("unchecked")
	public List<Kecheng_gbt> selectAllKecheng(final int start,final int limit) {
		return (List<Kecheng_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Kecheng_gbt> list = session.createQuery("from Kecheng_gbt  order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public int selectAllKechengCount() {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Kecheng_gbt").get(0);
		return (int)count;
	}

	public Kecheng_gbt selectKecheng(int id) {
		return this.getHibernateTemplate().get(Kecheng_gbt.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Kecheng_gbt> selectAllKechengBy(final int start,final int limit,final String keyword) {
		return (List<Kecheng_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Kecheng_gbt> list = session.createQuery("from Kecheng_gbt where 1=1 "+keyword+" order by kechenglock")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}
	
}
