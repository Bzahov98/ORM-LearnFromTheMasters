package com.bzahov.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionHolder {

	private static SessionFactory ourSessionFactory;
	static{
		try {
			Configuration configuration = new Configuration();
			configuration.configure();

			ourSessionFactory = configuration.buildSessionFactory();

		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	private static final Session INSTANCE = ourSessionFactory.openSession();

	private SessionHolder(){

	}

	public static Session getSession() {
		return INSTANCE;
	}
	public static SessionFactory getfactory() {
		return ourSessionFactory;
	}

	/*public static Session getSession() throws HibernateException {
		return ourSessionFactory.openSession();
	}*/
	public static void closeSession(){
		getSession().close();
	}
}
