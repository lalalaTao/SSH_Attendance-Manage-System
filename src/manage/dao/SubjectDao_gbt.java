package manage.dao;

import java.util.List;

import manage.model.Subject_gbt;



public interface SubjectDao_gbt  {
	
	public List<Subject_gbt> getAll(String where);
	
	public void delSubject(Subject_gbt banji);
	
	public void insertSubject(Subject_gbt banji);
	
	public List<Subject_gbt> selectAllSubject(final int start, final int limit);
	
	public int selectAllSubjectCount();
	
	public void updateSubject(Subject_gbt banji);
	
	public Subject_gbt selectSubject(int id);
	
	public List<Subject_gbt> selectAllSubjectBy(final int start,final int limit,final String keyword);
	
}
