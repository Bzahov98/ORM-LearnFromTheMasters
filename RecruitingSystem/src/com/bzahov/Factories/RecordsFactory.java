package com.bzahov.Factories;

import com.bzahov.Entities.JobAdsEntity;
import com.bzahov.Entities.RecordsEntity;
import com.bzahov.Utils.SessionHolder;
import com.bzahov.exceptions.MyDataErrorException;
import org.hibernate.Session;

import java.util.Set;

public class RecordsFactory {
	public static RecordsEntity createRecord(String name, String applicantName) throws MyDataErrorException {
		return createRecordEntity(name, applicantName, null, null);
	}

	public static RecordsEntity createRecordWithJobAd(String name, String applicantName, String info, JobAdsEntity jobAd) throws MyDataErrorException {
		return createRecordEntity(name, applicantName, info, jobAd);
	}

	public static RecordsEntity createRecordEntity(String name, String applicantName, String info, JobAdsEntity jobAd) throws MyDataErrorException {
		Session session = SessionHolder.getSession();
		RecordsEntity record = null;
		try {
			if (info == null) info = "no description";
			if (name == null) throw new MyDataErrorException();
			session.beginTransaction();
			record = new RecordsEntity(name, applicantName, info);
			if (jobAd!= null) {
				record.addRecordToJobAd(jobAd);
			}
		} catch (Exception e) {
			System.err.println("\nException was catched in create Record Entity");
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				return null;
			}
		}
		session.save(record);
		if (session.getTransaction().isActive()) {
			session.getTransaction().commit();
		}    //session.getTransaction().commit();
		return record;
	}

	public static void addRecordToJobAd(RecordsEntity record, JobAdsEntity jobAd) throws MyDataErrorException {

		Session session = SessionHolder.getSession();
		if (jobAd != null) {

			Set<RecordsEntity> recordsSet = jobAd.getRecordsSet();
			if (!recordsSet.contains(record)) {
				recordsSet.add(record);
			} else {
				//throw new MyDataErrorException();
			}
			record.setJobAd(jobAd);
		}
		session.save(record);
		session.getTransaction().commit();
	}
}
