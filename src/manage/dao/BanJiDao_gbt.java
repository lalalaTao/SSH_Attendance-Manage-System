package manage.dao;

import java.util.List;

import manage.model.BanJi_gbt;


public interface BanJiDao_gbt  {
	
	public List<BanJi_gbt> getAll(String where);
	
	public void delBanJi(BanJi_gbt banji);
	
	public void insertBanJi(BanJi_gbt banji);
	
	public List<BanJi_gbt> selectAllBanJi(final int start, final int limit);
	
	public int selectAllBanJiCount();
	
	public void updateBanJi(BanJi_gbt banji);
	
	public BanJi_gbt selectBanJi(int id);
	
	public List<BanJi_gbt> selectAllBanJiBy(final int start,final int limit,final String keyword);
	
}
