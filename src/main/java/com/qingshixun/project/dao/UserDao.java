package com.qingshixun.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qingshixun.project.model.Page;
import com.qingshixun.project.model.UserModel;

@Repository
public class UserDao {

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//查询全部记录
	@SuppressWarnings("unchecked")
	public List<UserModel> queryAll() {
		List<UserModel> list=this.sessionFactory.getCurrentSession().createQuery("from UserModel").list();
		System.out.println(list);
		return list;	
	}
	
	//根据名称查询用户
	@SuppressWarnings("unchecked")
	public List<UserModel> queryUser(String name) {
		name="%"+name+"%";
		List<UserModel> list=this.sessionFactory.getCurrentSession().createQuery("from UserModel where name like ?").setParameter(0, name).list();
		return list;	
	}
	
	//根据名称查询用户
	@SuppressWarnings("unchecked")
	public UserModel queryUser1(int no) {
		UserModel user = (UserModel) this.sessionFactory.getCurrentSession().createQuery("from UserModel where no=?").setParameter(0, no).uniqueResult();
		return user;
	}
	
	//添加用户
	public int addUser(UserModel user) {
		int n=(int) this.sessionFactory.getCurrentSession().save(user);
		return n;	
	}
	
	//修改用户
	public void updateUser(UserModel user) {
		this.sessionFactory.getCurrentSession().update(user);	
	}
	
	// 删除用户
	public void deleteUser(UserModel user) {
		this.sessionFactory.getCurrentSession().delete(user);
	}
	
	// 添加或删除用户
	public void addOrUpdateUser(UserModel user) {
		System.out.println(user.getNo());
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
	
	/*
	 * 分页——查询总页数
	 */
	public long queryPage() {
		long pageSize=5;
		Session session=sessionFactory.getCurrentSession();
		long totalSize=(long) session.createQuery("select count(no) from UserModel").uniqueResult();
		long pageCount=0;
		if(totalSize%pageSize==0) {
			pageCount=(totalSize/pageSize);
		}else {
			pageCount=(totalSize/pageSize)+1;
		}
		return pageCount;
		
	}
	/*
	 * 分页——查询总页数
	 */
	public int queryPage1(Page page) {
		int pageSize=5;
		Session session=sessionFactory.getCurrentSession();
		long totalSize= (long) session.createQuery("select count(no) from UserModel").uniqueResult();
		page.setTotal((int) totalSize);
		long pageCount=0;
		if(totalSize%pageSize==0) {
			pageCount=(totalSize/pageSize);
		}else {
			pageCount=(totalSize/pageSize)+1;
		}
		return (int) pageCount;
		
	}
	/*
	 * 分页——查询总页数,加上name条件
	 */
	public int queryPage2(Page page,String name) {
		int pageSize=5;
		name="%"+name+"%";
		Session session=sessionFactory.getCurrentSession();
		long totalSize= (long) session.createQuery("select count(no) from UserModel where name like ?").setParameter(0, name).uniqueResult();
		System.out.println(totalSize);
		page.setTotal((int) totalSize);
		long pageCount=0;
		if(totalSize%pageSize==0) {
			pageCount=(totalSize/pageSize);
		}else {
			pageCount=(totalSize/pageSize)+1;
		}
		return (int) pageCount;
		
	}
	/*
	 * 分页——查询指定记录
	 */
	@SuppressWarnings("unchecked")
	public List<UserModel> queryStu(int pageNo){
		long pageSize=5;
		Session session=sessionFactory.getCurrentSession();
		Query<UserModel> query=session.createQuery("from UserModel");
		query.setFirstResult((int) ((pageNo-1)*pageSize));
		query.setMaxResults((int) pageSize);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public Page queryStu1(Page page){
		Session session=sessionFactory.getCurrentSession();
		Query<UserModel> query=session.createQuery("from UserModel");
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		page.setList((query.list()));
		return page;
		
	}
	
	@SuppressWarnings("unchecked")
	public Page queryStu2(Page page,String name){
		name="%"+name+"%";
		Session session=sessionFactory.getCurrentSession();
		Query<UserModel> query=session.createQuery("from UserModel where name like ?").setParameter(0, name);
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		page.setList((query.list()));
		return page;
		
	}
	
	/*
	 * 获取根据性别分组统计数据
	 */
	@SuppressWarnings("rawtypes")
	public List getUserGenderNumbers() {
		//根据gender分组统计用户性别人数
		String hql="select count(no) as number,sex from UserModel group by sex";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/*
	 * 获取根据创建分组统计数据
	 */
	@SuppressWarnings("rawtypes")
	public List getUserCreateDateNumbers() {
		//根据createtime分组统计用户创建人数
		String hql="select count(no) as number,DATE_FORMAT(createDate,'%Y-%m-%d') from UserModel group by DATE_FORMAT(createDate,'%Y-%m-%d')";
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
}
