package com.bzahov.Factories;

import com.bzahov.Entities.CategoriesEntity;
import com.bzahov.Entities.JobAdsEntity;
import com.bzahov.Utils.SessionHolder;
import com.bzahov.exceptions.MyDataErrorException;
import org.hibernate.Session;

import java.util.Set;

public class CategoryFactory {

	public static CategoriesEntity createCategory(String name, String info) throws MyDataErrorException {
		return createCategoryEntity(name, info, null);
	}

	public static CategoriesEntity createCategoryWithJobAds(String name, String info, Set<JobAdsEntity> jobAdsSet) throws MyDataErrorException {
		return createCategoryEntity(name, info, jobAdsSet);
	}

	public static CategoriesEntity createCategoryEntity(String name, String info, Set<JobAdsEntity> jobAdsSet) throws MyDataErrorException {
		Session session = SessionHolder.getSession();
		CategoriesEntity category = null;
		try {
			session.beginTransaction();

			if (name == null) throw new MyDataErrorException();

			category = new CategoriesEntity(name, info, jobAdsSet);
			if (jobAdsSet != null) {
				category.addJobAdSetToCategory(jobAdsSet);
			}
		} catch (Exception e) {
			System.err.println("\nException was catched in create Category Entity");
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				return null;
			}
		}
		session.save(category);
		session.getTransaction().commit();
		return category;
	}
}
