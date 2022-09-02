package manage.dao;

import java.util.List;

import manage.model.Kecheng_gbt;



public interface KechengDao_gbt  {
	
	public List<Kecheng_gbt> getAll(String where);
	
	public void delKecheng(Kecheng_gbt kecheng_gbt);
	
	public void insertKecheng(Kecheng_gbt kecheng_gbt);
	
	public List<Kecheng_gbt> selectAllKecheng(final int start, final int limit);
	
	public int selectAllKechengCount();
	
	public void updateKecheng(Kecheng_gbt kecheng_gbt);
	
	public Kecheng_gbt selectKecheng(int id);
	
	public List<Kecheng_gbt> selectAllKechengBy(final int start,final int limit,final String keyword);
	
}
