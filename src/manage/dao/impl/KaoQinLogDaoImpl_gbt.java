package manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import manage.dao.KaoQinLogDao_gbt;
import manage.model.KaoQinLog_gbt;

public class KaoQinLogDaoImpl_gbt extends HibernateDaoSupport implements  KaoQinLogDao_gbt{
	
	@SuppressWarnings("unchecked")
	public List<KaoQinLog_gbt> getAll(String where) {
		return this.getHibernateTemplate().find("from KaoQinLog_gbt where 1=1 "+where+" order by id");
	}
	
	public void insertKaoQinLog(KaoQinLog_gbt kaoqinlog){
		this.getHibernateTemplate().save(kaoqinlog);
	}
	
	public void delKaoQinLog(KaoQinLog_gbt kaoqinlog) {
		this.getHibernateTemplate().delete(kaoqinlog);
	}
	
	public void updateKaoQinLog(KaoQinLog_gbt kaoqinlog) {
		this.getHibernateTemplate().update(kaoqinlog);
		
	}

	@SuppressWarnings("unchecked")
	public List<KaoQinLog_gbt> selectAllKaoQinLog(final int start,final int limit) {
		return (List<KaoQinLog_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<KaoQinLog_gbt> list = session.createQuery("from KaoQinLog_gbt  order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public int selectAllKaoQinLogCount() {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from KaoQinLog_gbt").get(0);
		return (int)count;
	}

	public KaoQinLog_gbt selectKaoQinLog(int id) {
		return this.getHibernateTemplate().get(KaoQinLog_gbt.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<KaoQinLog_gbt> selectAllKaoQinLogBy(final int start,final int limit,final String keyword) {
		return (List<KaoQinLog_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<KaoQinLog_gbt> list = session.createQuery("from KaoQinLog_gbt where 1=1 "+keyword+" order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}
	
}
