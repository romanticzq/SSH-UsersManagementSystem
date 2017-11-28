package com.qingshixun.project.action;

import java.util.Date;
import java.util.List;

import javax.xml.ws.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageInfo;
import com.qingshixun.project.model.CharData;
import com.qingshixun.project.model.Page;
import com.qingshixun.project.model.UserModel;
import com.qingshixun.project.service.UserService;

@Component
public class UserAction {

	@Autowired
	private UserService userService;
	
	private boolean flag;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private List<UserModel> list=null;

	public List<UserModel> getList() {
		return list;
	}

	public void setList(List<UserModel> list) {
		this.list = list;
	}
	
	private UserModel user;
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	
	private long pageCount;
	private int pageNo=1;
	
	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	private Page page=new Page();
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	private CharData userGenderData;
	private CharData userCreateData;
	
	public CharData getUserGenderData() {
		return userGenderData;
	}

	public void setUserGenderData(CharData userGenderData) {
		this.userGenderData = userGenderData;
	}

	public CharData getUserCreateData() {
		return userCreateData;
	}

	public void setUserCreateData(CharData userCreateData) {
		this.userCreateData = userCreateData;
	}

	// 查询所有记录
	public String queryAll() {
		list=userService.queryAll(pageNo);
		return "list";
	}

	// 根据名称查询记录
	public String queryUser() {
		list=userService.queryUser(user.getName());
		return "list";
	}
	
	//根据no查询用户
	public UserModel queryUser1(int no) {
		return userService.queryUser1(no);
	}
		
	//去到form.jsp页面
	public String to_form() {
		user=queryUser1(user.getNo());
		return "form";
	}
	
	//去到form.jsp页面
	public String to_form1() {
		return "form";
	}
	
	// 添加用户
	public String addUser() {
		userService.addUser(user);
//		queryAll();
		queryPage1();
		return "list";
	}

	// 修改用户
	public String updateUser() {
		userService.updateUser(user);
//		queryAll();
		queryPage1();
		return "list";
	}

	// 删除用户
	public String deleteUser() {
		userService.deleteUser(user);
//		queryAll();
		queryPage1();
		return "list";
	}
	
	//添加或者修改用户
	public String addOrUpdateUser() {
		if(user.getNo()!=0) {
			user.setUpdateDate(new Date());
		} else {
			user.setCreateDate(new Date());
			user.setUpdateDate(new Date());
		}
		userService.addOrUpdateUser(user);
//		queryAll();
		queryPage1();
		return "list";
	}

	// 去到analyze界面
	public String to_analyze() {
		return "analyze";
	}
	
	/*
	 * 分页——查询总页数
	 */
	public String queryPage() {
		list=this.userService.queryStu1(pageNo);
		pageCount=userService.queryPage();
		return "list";
	}
	/*
	 * 分页——查询总页数
	 */
	public String queryPage1() {
		page.setPageNum(pageNo);
		page=this.userService.queryStu2(page);
		page.setPages(userService.queryPage1(page));
		page.print();
		flag=true;
		return "list";
	}
	
	/*
	 * 分页——查询总页数,加上姓名条件
	 */
	public String queryPage1AndName() {
//		page.setPageNum(pageNo);
		page=this.userService.queryStu3(page,user);
		page.setPages(userService.queryPage2(page,user));
		page.print();
		flag=false;
		return "list";
	}
	
	/*
	 * 用户统计分析
	 */
//	@Action(value="analyze",results={ @Result(name=SUCCESS,location="/WEB-INF/views/user/analyze.jsp")})
	public String analyzeUser() {
		//根据userid查询用户信息
		userGenderData=userService.getUserGenderData();
		userCreateData=userService.getUserCreateData();
		return "analyze";
	}
}
