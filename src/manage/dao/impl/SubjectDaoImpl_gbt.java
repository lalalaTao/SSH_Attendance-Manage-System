package manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import manage.dao.SubjectDao_gbt;
import manage.model.Subject_gbt;

public class SubjectDaoImpl_gbt extends HibernateDaoSupport implements  SubjectDao_gbt{
	
	@SuppressWarnings("unchecked")
	public List<Subject_gbt> getAll(String where) {
		return this.getHibernateTemplate().find("from Subject_gbt where 1=1 "+where+" order by id");
	}
	
	public void insertSubject(Subject_gbt banji){
		this.getHibernateTemplate().save(banji);
	}
	
	public void delSubject(Subject_gbt banji) {
		this.getHibernateTemplate().delete(banji);
	}
	
	public void updateSubject(Subject_gbt banji) {
		this.getHibernateTemplate().update(banji);
		
	}

	@SuppressWarnings("unchecked")
	public List<Subject_gbt> selectAllSubject(final int start,final int limit) {
		return (List<Subject_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Subject_gbt> list = session.createQuery("from Subject_gbt  order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public int selectAllSubjectCount() {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Subject_gbt").get(0);
		return (int)count;
	}

	public Subject_gbt selectSubject(int id) {
		return this.getHibernateTemplate().get(Subject_gbt.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Subject_gbt> selectAllSubjectBy(final int start,final int limit,final String keyword) {
		return (List<Subject_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Subject_gbt> list = session.createQuery("from Subject_gbt where 1=1 "+keyword+" order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}
	
}
