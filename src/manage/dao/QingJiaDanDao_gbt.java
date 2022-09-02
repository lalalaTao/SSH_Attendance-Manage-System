package manage.dao;

import java.util.List;

import manage.model.QingJiaDan_gbt;

public interface QingJiaDanDao_gbt  {
	
	public List<QingJiaDan_gbt> getAll(String where);
	
	public void delQingJiaDan(QingJiaDan_gbt qingjiadan);
	
	public void insertQingJiaDan(QingJiaDan_gbt qingjiadan);
	
	public List<QingJiaDan_gbt> selectAllQingJiaDan(final int start, final int limit);
	
	public int selectAllQingJiaDanCount();
	
	public void updateQingJiaDan(QingJiaDan_gbt qingjiadan);
	
	public QingJiaDan_gbt selectQingJiaDan(int id);
	
	public List<QingJiaDan_gbt> selectAllQingJiaDanBy(final int start,final int limit,final String keyword);
	
}
