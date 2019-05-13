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

		//if (isActive == null) isActive = false;
		if (info == null) info = "";
		if (name == null) throw new MyDataErrorException();

		session.beginTransaction();

		RecordsEntity record = new RecordsEntity(name, applicantName, info);

		record.addRecordToJobAd(jobAd);

		session.save(record);
		session.getTransaction().commit();
		return record;
	}

	public static void addRecordToJobAd(RecordsEntity record,JobAdsEntity jobAd) throws MyDataErrorException {

		Session session = SessionHolder.getSession();
		if (jobAd != null) {

			Set<RecordsEntity> recordsSet = jobAd.getRecordsSet();
			if (!recordsSet.contains(record)) {
				recordsSet.add(record);
			} else {
				//throw new MyDataErrorException();
			}
			record.setJobAds(jobAd);
		}
		session.save(record);
		session.getTransaction().commit();
	}
}
