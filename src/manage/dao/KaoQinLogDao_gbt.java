package manage.dao;

import java.util.List;

import manage.model.KaoQinLog_gbt;


public interface KaoQinLogDao_gbt  {
	
	public List<KaoQinLog_gbt> getAll(String where);
	
	public void delKaoQinLog(KaoQinLog_gbt kaoqinlog);
	
	public void insertKaoQinLog(KaoQinLog_gbt kaoqinlog);
	
	public List<KaoQinLog_gbt> selectAllKaoQinLog(final int start, final int limit);
	
	public int selectAllKaoQinLogCount();
	
	public void updateKaoQinLog(KaoQinLog_gbt kaoqinlog);
	
	public KaoQinLog_gbt selectKaoQinLog(int id);
	
	public List<KaoQinLog_gbt> selectAllKaoQinLogBy(final int start,final int limit,final String keyword);
	
}
