package com.bzahov.Factories;

import com.bzahov.Entities.EmployerEntity;
import com.bzahov.Entities.JobAdsEntity;
import com.bzahov.Utils.SessionHolder;
import com.bzahov.exceptions.MyDataErrorException;
import org.hibernate.Session;

import java.util.Set;

public class EmployerFactory {
	public static EmployerEntity createEmployer(String name, String info) throws MyDataErrorException {
		return createEmployerEntity(name, info, null);
	}

	public static EmployerEntity createEmployerWithJobAds(String name, String info, Set<JobAdsEntity> jobAdsSet) throws MyDataErrorException {
		return createEmployerEntity(name, info, jobAdsSet);
	}

	public static EmployerEntity createEmployerEntity(String name, String info, Set<JobAdsEntity> jobAdsSet) throws MyDataErrorException {
		if (name == null) throw new MyDataErrorException();
		EmployerEntity employer = new EmployerEntity(name, info);
		employer.setJobAdsCount(0);
		if (jobAdsSet!= null){
		for (JobAdsEntity jobAdsEntity : jobAdsSet) {
			employer.addJobAdd(jobAdsEntity);
		}}
		//employer.addJobAdd(jobAdsSet);
		//addJobAddToEmployer(jobAdsSet, employer);
		return employer;
	}

	public static void addJobAddToEmployer(Set<JobAdsEntity> jobAdsSet, EmployerEntity employer) throws MyDataErrorException {
		Session session = SessionHolder.getSession();
		session.beginTransaction();

		if (jobAdsSet != null) {

			for (JobAdsEntity jobAd : jobAdsSet) {
				employer.addJobAdd(jobAd);
				for (JobAdsEntity jobEntity : jobAdsSet) {
					jobEntity.setEmployer(employer);
				}
			}
			employer.setJobAdsCount(jobAdsSet.size());
			/*	throw new MyDataErrorException();
			} else {
				employer.setJobAdsSet(jobAdsSet);
				jobAdsSet.forEach(jobEntity ->
						jobEntity.setEmployer(employer));
			}
*/
		}
		session.save(employer);
		session.getTransaction().commit();
	}
}
