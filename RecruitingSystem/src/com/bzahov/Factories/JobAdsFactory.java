package com.bzahov.Factories;

import com.bzahov.Entities.CategoriesEntity;
import com.bzahov.Entities.EmployerEntity;
import com.bzahov.Entities.JobAdsEntity;
import com.bzahov.Entities.RecordsEntity;
import com.bzahov.Utils.SessionHolder;
import com.bzahov.exceptions.MyDataErrorException;
import org.hibernate.Session;

import java.util.Set;

public class JobAdsFactory {

	public static JobAdsEntity createJobAd(String name, String info, Boolean isActive) throws MyDataErrorException {
		return createJobAdsEntity( name, info, isActive,null,null,null);
	}

	public static JobAdsEntity createJobAdWithAllRelations(String name, String info, Boolean isActive, CategoriesEntity category, EmployerEntity employer, Set<RecordsEntity> recordsSet) throws MyDataErrorException {
		return createJobAdsEntity( name, info, isActive,category,employer,recordsSet);
	}

	public static JobAdsEntity createJobAdWithEmployer( String name,
														String info,
														Boolean isActive,
														EmployerEntity employer)
														throws MyDataErrorException
	{
		return createJobAdsEntity( name, info, isActive,null,employer,null);
	}

	public static JobAdsEntity createJobAdWithEmployer( String name,
														String info,
														Boolean isActive,
														Set<RecordsEntity> recordSet)
														throws MyDataErrorException
	{
		return createJobAdsEntity( name, info, isActive,null,null,recordSet);
	}

	public static JobAdsEntity createJobAdWithCategories(String name, String info,
														 Boolean isActive, CategoriesEntity category)
														 throws MyDataErrorException
	{
		return createJobAdsEntity( name, info, isActive,category,null,null);
	}

	public static JobAdsEntity createJobAdsEntity(String name, String info, Boolean isActive, CategoriesEntity category, EmployerEntity employer, Set<RecordsEntity> recordsSet) throws MyDataErrorException {
		Session session = SessionHolder.getSession();
		if (isActive == null) isActive = false;
		session.beginTransaction();
		if (name == null) throw new MyDataErrorException();

		JobAdsEntity jobAdsEntity = new JobAdsEntity(name, info, isActive);/*, category, employer, recordsSet);  */
		if (employer!= null) {
			jobAdsEntity.registerRecordSet(recordsSet);
		}//setEmployerOfJobAd(employer, jobAdsEntity);

		setJobAdToCategory(category, jobAdsEntity);
		setJobAdsEntityToRecord(recordsSet, jobAdsEntity);
		session.save(jobAdsEntity);
		session.getTransaction().commit();
		return jobAdsEntity;
	}

	public static void setJobAdsEntityToRecord(Set<RecordsEntity> recordsSet, JobAdsEntity jobAdsEntity) throws MyDataErrorException {
		if (recordsSet != null) {

			jobAdsEntity.setRecordsSet(recordsSet);
			recordsSet.forEach(recordsEntity ->
					recordsEntity.setJobAds(jobAdsEntity));
		}
	}

	public static void setJobAdToCategory(CategoriesEntity category, JobAdsEntity jobAdsEntity) throws MyDataErrorException {
		if (category != null) {
			jobAdsEntity.setCategory(category);
			category.getJobAdsSet().add(jobAdsEntity);
		}
	}

	public static void setEmployerOfJobAd(EmployerEntity employer, JobAdsEntity jobAdsEntity) throws MyDataErrorException {
		if (employer != null) {
			jobAdsEntity.setEmployer(employer);
		}
	}


}
