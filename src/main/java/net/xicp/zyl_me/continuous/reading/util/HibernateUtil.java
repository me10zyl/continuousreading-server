package net.xicp.zyl_me.continuous.reading.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    private static Session session;
    public static void setSession(Session session) {
		HibernateUtil.session = session;
	}

	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public  void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

    public static Session getSession()
    {
    	Session session = threadLocal.get();
    	if(session == null || !session.isOpen())
    	{
    		session = sessionFactory.openSession();
    		threadLocal.set(session);
    	}
		return session;
    }


}