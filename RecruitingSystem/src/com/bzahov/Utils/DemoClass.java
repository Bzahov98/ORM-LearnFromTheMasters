package com.bzahov.Utils;

import com.bzahov.Entities.CategoriesEntity;
import com.bzahov.Entities.EmployerEntity;
import com.bzahov.Entities.JobAdsEntity;
import com.bzahov.Entities.RecordsEntity;
import com.bzahov.Factories.CategoryFactory;
import com.bzahov.Factories.EmployerFactory;
import com.bzahov.Factories.JobAdsFactory;
import com.bzahov.Factories.RecordsFactory;
import com.bzahov.exceptions.MyDataErrorException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class DemoClass {
	public static void objectDemoCreationWithoutReferences() throws MyDataErrorException {
		try {
			CategoryFactory.createCategory("Demo QA", "bla bla");
			EmployerFactory.createEmployer("Demo1", "bla bla");
			JobAdsFactory  .createJobAd   ("Dev Demo1", "info", true);
			RecordsFactory .createRecord  ("demoRecord", "Mr.Demo");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void objectDemoCreationWithReferences() throws MyDataErrorException {
		try {
			CategoriesEntity category = CategoryFactory.createCategory("Demo QA", "bla bla");
			EmployerEntity employer = EmployerFactory.createEmployer("Demo1", "bla bla");

			Set<RecordsEntity> recordsEntitySet = new HashSet<>();

			//will create and add 3 new records into set
			IntStream.range(0, 3).forEach(i -> {
				try {
					recordsEntitySet.add(
							RecordsFactory.createRecord("demoRecord" + i, "Mr.Demo" + i)
					);
				} catch (MyDataErrorException e) {
					e.printStackTrace();
				}
			});

			JobAdsEntity jobAdsEntity = JobAdsFactory.createJobAdWithAllRelations(
					"Dev    elop Job11211", "infso", true
					, category, employer, recordsEntitySet
			);

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("successfully added jobAds Entity with all relations ");
	}

	public static void demoMaxJobAdsPerEmployerError() {
		try {
			Set<JobAdsEntity> jobAdsEntityList = new HashSet<>();

			//will create and add 11 new JobAds into set
			IntStream.range(0, 11).forEach(value -> {
				try {
					jobAdsEntityList.add(
							JobAdsFactory.createJobAd("Demo Junior Dev Job" + value
													, "info" + value, true)
					);
				} catch (MyDataErrorException e) {
					e.printStackTrace();
				}
			});
			System.out.println("\n size of Entity list " + jobAdsEntityList.size());

			EmployerEntity employer = EmployerFactory.createEmployer("Demo1", "bla bla");
			EmployerFactory.addJobAddToEmployer(jobAdsEntityList, employer);
		} catch (MyDataErrorException e) {
			e.printStackTrace();
			System.out.println("Exception Found as we expected");
		}
	}

	public static void demoMultipleRecordsOnOneJobAddError() {
		try {
			RecordsEntity record = RecordsFactory.createRecord("demoRecord7", "Mr.Demo7");
			JobAdsEntity jobAd = JobAdsFactory.createJobAd("Dev Demo7", "info7", true);

			Set<RecordsEntity> jobAdsEntityList = new HashSet<>();
			jobAdsEntityList.add(record);

			JobAdsFactory.setJobAdsEntityToRecord(jobAdsEntityList,jobAd);
			JobAdsFactory.setJobAdsEntityToRecord(jobAdsEntityList,jobAd);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("\n>> Found Expected error");
		}

	}

	public static void queriesDemoCreation() {

		System.out.println("\nQueries demo: findActiveCategoriesCount");
		ByCategory.findActiveCategoriesCount();

		System.out.println("\nQueries demo: findPeopleCountPerProfession");
		ByCategory.findPeopleCountPerProfession();

	}
}
