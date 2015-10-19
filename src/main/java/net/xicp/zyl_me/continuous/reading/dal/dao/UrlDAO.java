package net.xicp.zyl_me.continuous.reading.dal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.xicp.zyl_me.continuous.reading.dal.entity.Url;
import net.xicp.zyl_me.continuous.reading.dal.entity.User;
import net.xicp.zyl_me.continuous.reading.util.HibernateUtil;

@Repository
public class UrlDAO extends DataAccessObject<Url>{

	private Session getSession() {
		return HibernateUtil.getSession();
	}


	@Override
	public Url findById(int id) {
		Session session = HibernateUtil.getSession();
		Url url = (Url) session.get(Url.class, id);
		return url;
	}

	public List<Url> findByUrlname(String urlname){
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Url where name=?");
		query.setString(0, urlname);
		ArrayList<Url> urls = (ArrayList<Url>) query.list();
		return urls;
	}

	@Override
	public List<Url> list() {
		Session session = HibernateUtil.getSession();
		List<Url> urls = new ArrayList<Url>();
		Query query = session.createQuery("from Url");
		urls.addAll(query.list());
		return urls;
	}

	public List<Url> listByUserId(int userid){
		Session session = HibernateUtil.getSession();
		List<Url> urls = new ArrayList<Url>();
		Query query = session.createQuery("from Url as url where url.user.id=?");
		query.setInteger(0, userid);
		urls.addAll(query.list());
		return urls;
	}



	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void delete(Url url) {
		Session session = HibernateUtil.getSession();
		 Transaction transaction = session.beginTransaction();
		System.out.println("delete: "+url.getId());
		session.delete(url);
		 transaction.commit();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void update(Url url) {
		Session session = HibernateUtil.getSession();
		session.update(url);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void save(Url url) {
		Session session = HibernateUtil.getSession();
		session.save(url);
	}


}
