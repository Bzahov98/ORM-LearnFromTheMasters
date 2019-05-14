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
		return createJobAdsEntity(name, info, isActive, null, null, null);
	}

	public static JobAdsEntity createJobAdWithAllRelations(String name, String info, Boolean isActive, CategoriesEntity category, EmployerEntity employer, Set<RecordsEntity> recordsSet) throws MyDataErrorException {
		return createJobAdsEntity(name, info, isActive, category, employer, recordsSet);
	}

	public static JobAdsEntity createJobAdWithEmployer(String name,
			String info,
			Boolean isActive,
			EmployerEntity employer)
			throws MyDataErrorException {
		return createJobAdsEntity(name, info, isActive, null, employer, null);
	}

	public static JobAdsEntity createJobAdWithRecords(String name,
			String info,
			Boolean isActive,
			Set<RecordsEntity> recordSet)
			throws MyDataErrorException {
		return createJobAdsEntity(name, info, isActive, null, null, recordSet);
	}

	public static JobAdsEntity createJobAdWithCategories(String name, String info,
			Boolean isActive, CategoriesEntity category)
			throws MyDataErrorException {
		return createJobAdsEntity(name, info, isActive, category, null, null);
	}

	public static JobAdsEntity createJobAdsEntity(String name, String info, Boolean isActive, CategoriesEntity category, EmployerEntity employer, Set<RecordsEntity> recordsSet) throws MyDataErrorException {
		Session session = SessionHolder.getSession();
		JobAdsEntity jobAdsEntity = null;
		try {
			if (isActive == null) isActive = false;
			session.beginTransaction();
			if (name == null) throw new MyDataErrorException();

			jobAdsEntity = new JobAdsEntity(name, info, isActive);/*, category, employer, recordsSet);  */
			if (recordsSet != null) {
				jobAdsEntity.registerRecordSet(recordsSet);
			}
			if (employer != null) {

				employer.addJobAdAndActivate(jobAdsEntity);
			}
			if (category != null) {
				category.addJobAdToCategory(jobAdsEntity);
				//setJobAdToCategory(category, jobAdsEntity);
			}
		} catch (Exception e) {
			System.err.println("\nException was catched in create JobAd Entity");
			e.printStackTrace();
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				return null;
			}
		}
		session.save(jobAdsEntity);
		if (session.getTransaction().isActive()) {
			session.getTransaction().commit();
		}    //session.getTransaction().commit();
		return jobAdsEntity;
	}

		public static void setJobAdsEntityToRecord (Set < RecordsEntity > recordsSet, JobAdsEntity jobAdsEntity) throws
		MyDataErrorException {
			if (recordsSet != null) {

				jobAdsEntity.setRecordsSet(recordsSet);
				recordsSet.forEach(recordsEntity ->
						recordsEntity.setJobAd(jobAdsEntity));
			}
		}

		public static void setJobAdToCategory (CategoriesEntity category, JobAdsEntity jobAdsEntity) throws
		MyDataErrorException {
			if (category != null && jobAdsEntity != null) {
				jobAdsEntity.addCategory(category);
				category.getJobAdsSet().add(jobAdsEntity);
			} else System.err.println("error in job ad to category");
			;
		}

		public static void setEmployerOfJobAd (EmployerEntity employer, JobAdsEntity jobAdsEntity) throws
		MyDataErrorException {
			if (employer != null) {
				employer.addJobAdAndActivate(jobAdsEntity);
				jobAdsEntity.setEmployer(employer);
			} else System.err.println("error in set employer to jobAd");
		}


	}
