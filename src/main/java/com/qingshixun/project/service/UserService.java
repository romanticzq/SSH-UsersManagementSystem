package com.qingshixun.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qingshixun.project.dao.UserDao;
import com.qingshixun.project.model.CharData;
import com.qingshixun.project.model.Page;
import com.qingshixun.project.model.UserModel;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	//查询所有记录
	public List<UserModel> queryAll(int pageNo) {
		List<UserModel> list=userDao.queryAll();
		return list;	
	}
	
	// 根据名称查询记录
	public List<UserModel> queryUser(String name) {
		return userDao.queryUser(name);
	}
	
	//根据no查询用户
	public UserModel queryUser1(int no) {
		return userDao.queryUser1(no);
	}
		
	// 添加用户
	public int addUser(UserModel user) {
		return userDao.addUser(user);
	}
	
	// 修改用户
	public void updateUser(UserModel user) {
		userDao.updateUser(user);
	}

	// 删除用户
	public void deleteUser(UserModel user) {
		userDao.deleteUser(user);
	}
	
	// 添加或删除用户
	public void addOrUpdateUser(UserModel user) {
		userDao.addOrUpdateUser(user);
	}
	/*
	 * 分页——查询总页数
	 */
	public long queryPage(){
		return userDao.queryPage();
	}
	
	public int queryPage1(Page page){
		return userDao.queryPage1(page);
	}
	
	public int queryPage2(Page page,UserModel user){
		String name=user.getName();
		return userDao.queryPage2(page,name);
	}
	/*
	 * 分页——查询指定记录
	 */
	public List<UserModel> queryStu1(int pageNo){
		return userDao.queryStu(pageNo);
	}
	
	public Page queryStu2(Page page){
		return userDao.queryStu1(page);
	}
	
	public Page queryStu3(Page page,UserModel user){
		String name="";
		if(user.getName()!=null) {
			name=user.getName();
		}
		return userDao.queryStu2(page,name);
	}
	/*
	 * 获取用户性别统计数据
	 */
	@SuppressWarnings("rawtypes")
	public CharData getUserGenderData() {
		//获取根据性别分组统计数据
		List genderNumberList=userDao.getUserGenderNumbers();
		int genderNumberCount=genderNumberList.size();
		String[] names=new String[genderNumberCount];
		String[] values=new String[genderNumberCount];
		//遍历统计数据，转换成chart.js所需要的数据格式
		for(int i=0,n=genderNumberCount;i<n;i++) {
			Object[] genderNumbers=(Object[]) genderNumberList.get(i);
			//性别标签名称，如male、female
			names[i]="\""+String.valueOf(genderNumbers[1])+"\"";
			//性别对应的用户数据
			values[i]=String.valueOf(genderNumbers[0]);
		}
		CharData userGenderData=new CharData();
		userGenderData.setNames("["+String.join(",", names)+"]");
		userGenderData.setValues("["+String.join(",", values)+"]");
		return userGenderData;
	}
	
	/*
	 * 获取用户创建日期统计数据
	 */
	@SuppressWarnings("rawtypes")
	public CharData getUserCreateData() {
		//获取根据创建日期分组统计数据
		List createDateNumberList=userDao.getUserCreateDateNumbers();
		int createDateNumberCount=createDateNumberList.size();
		String[] names=new String[createDateNumberCount];
		String[] values=new String[createDateNumberCount];
		
		//遍历统计数据，装换成Chart.js所需要的数据格式
		for(int i=0,n=createDateNumberCount;i<n;i++) {
			Object[] createDateNumbers=(Object[]) createDateNumberList.get(i);
			//日期标签，如：2017-03-21
			names[i]="\""+String.valueOf(createDateNumbers[1])+"\"";
			//日期对应的创建用户数量
			values[i]=String.valueOf(createDateNumbers[0]);
		}
		CharData userCreateData=new CharData();
		userCreateData.setNames("["+String.join(",", names)+"]");
		userCreateData.setValues("["+String.join(",", values)+"]");
		
		return userCreateData;
	}
}
