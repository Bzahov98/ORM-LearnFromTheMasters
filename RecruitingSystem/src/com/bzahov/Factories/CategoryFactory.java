package com.bzahov.Factories;

import com.bzahov.Entities.CategoriesEntity;
import com.bzahov.Entities.JobAdsEntity;
import com.bzahov.Utils.SessionHolder;
import com.bzahov.exceptions.MyDataErrorException;
import org.hibernate.Session;

import java.util.Set;

public class CategoryFactory {

	public static CategoriesEntity createCategory(String name, String info) throws MyDataErrorException {
		return createCategoryEntity(name,info,null);
	}

	public static CategoriesEntity createCategoryWithJobAds(String name, String info, Set<JobAdsEntity> jobAdsSet) throws MyDataErrorException {
		return createCategoryEntity(name,info,jobAdsSet);
	}

	public static CategoriesEntity createCategoryEntity(String name, String info, Set<JobAdsEntity> jobAdsSet) throws MyDataErrorException {
		Session session = SessionHolder.getSession();

		session.beginTransaction();

		if (name == null) throw new MyDataErrorException();

		CategoriesEntity category = new CategoriesEntity(name, info, jobAdsSet);
		if (jobAdsSet != null) {
			category.setJobAdsSet(jobAdsSet);
			jobAdsSet.forEach(jobEntity ->
					jobEntity.setCategory(category));
		}
		session.save(category);
		session.getTransaction().commit();
		return category;
	}
}
