package net.xicp.zyl_me.continuous.reading.dal.dao;

import java.util.ArrayList;
import java.util.List;

import net.xicp.zyl_me.continuous.reading.dal.entity.User;
import net.xicp.zyl_me.continuous.reading.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO extends DataAccessObject<User>{
	private Session getSession() {
		return HibernateUtil.getSession();
	}


	@Override
	public User findById(int id) {
		Session session = HibernateUtil.getSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	public List<User> findByUsername(String username){
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from User where username=?");
		query.setString(0, username);
		ArrayList<User> users = (ArrayList<User>) query.list();
		return users;
	}

	@Override
	public List<User> list() {
		Session session = HibernateUtil.getSession();
		List<User> users = new ArrayList<User>();
		Query query = session.createQuery("select * from User");
		users.addAll(query.list());
		return users;
	}



	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void delete(User user) {
		Session session = HibernateUtil.getSession();
		// Transaction transaction = session.beginTransaction();
		session.delete(user);
		// transaction.commit();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void update(User user) {
		Session session = HibernateUtil.getSession();
		session.update(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void save(User user) {
		Session session = HibernateUtil.getSession();
		session.save(user);
	}

}
