package manage.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import manage.dao.BanJiDao_gbt;
import manage.model.BanJi_gbt;

public class BanJiDaoImpl_gbt extends HibernateDaoSupport implements  BanJiDao_gbt{
	
	@SuppressWarnings("unchecked")
	public List<BanJi_gbt> getAll(String where) {
		return this.getHibernateTemplate().find("from BanJi_gbt where 1=1 "+where+" order by id");
	}
	
	public void insertBanJi(BanJi_gbt banji){
		this.getHibernateTemplate().save(banji);
	}
	
	public void delBanJi(BanJi_gbt banji) {
		this.getHibernateTemplate().delete(banji);
	}
	
	public void updateBanJi(BanJi_gbt banji) {
		this.getHibernateTemplate().update(banji);
		
	}

	@SuppressWarnings("unchecked")
	public List<BanJi_gbt> selectAllBanJi(final int start,final int limit) {
		return (List<BanJi_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<BanJi_gbt> list = session.createQuery("from BanJi_gbt  order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public int selectAllBanJiCount() {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from BanJi_gbt").get(0);
		return (int)count;
	}

	public BanJi_gbt selectBanJi(int id) {
		return this.getHibernateTemplate().get(BanJi_gbt.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<BanJi_gbt> selectAllBanJiBy(final int start,final int limit,final String keyword) {
		return (List<BanJi_gbt>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<BanJi_gbt> list = session.createQuery("from BanJi_gbt where 1=1 "+keyword+" order by id desc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}
	
}
