package manage.dao;

import java.util.List;

import manage.model.User_gbt;



public interface UserDao_gbt  {
	
	public List<User_gbt> getAll(String where);
	
	public void delUser(User_gbt user_gbt);
	
	public void insertUser(User_gbt user_gbt);
	
	public User_gbt selectUserByusername(String username);
	
	public User_gbt selectUserbByusernameByPassword(String username,String password);
	
	public List<User_gbt> selectAllUser(final int start, final int limit);
	
	public int selectAllUserCount();
	
	public void updateUser(User_gbt user_gbt);
	
	public User_gbt selectUser(int id);
	
	public List<User_gbt> selectAllUserByusername(final int start,final int limit,final String keyword);
	public List<User_gbt> selectAllUserBy(final int start,final int limit,final String keyword);
	
}
