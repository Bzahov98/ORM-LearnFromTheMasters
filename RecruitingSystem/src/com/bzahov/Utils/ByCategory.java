package com.bzahov.Utils;

import com.bzahov.Entities.CategoriesEntity;
import com.bzahov.Entities.JobAdsEntity;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public class ByCategory {

	private static String sqlQuery = "select categories.Name as CategoryName, Count(JobAds.ID) as numberJobs \n" +
			"from categories \n" +
			"       join JobAds on categories.Id = jobAds.CategoryId \n" +
			"       join Records on jobAds.ID = Records.jobAdsID \n" +
			"where JobAds.isActive = 1 \n" +
			"group by Categories.Id,Categories.Name \n" +
			"order by sum(jobAdsID) desc, CategoryName asc, jobAds.name";

	public static void findActiveCategoriesAndPeopleCount() {
		//показва колко активни обяви имаме по категории
		//и колко хора са кандидствали по всяка професия (QA, Developer, Manager, DevOps
		Session session = SessionHolder.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<JobAdsEntity> jobRoot = query.from(JobAdsEntity.class);
		Root<CategoriesEntity> categoryRoot = query.from(CategoriesEntity.class);

		/*SELECT categories.Name as CategoryName,
		Count(JobAds.ID) as numberJobs \n"*/
		query.multiselect(jobRoot.get("name"), categoryRoot.get("name"),builder.count(jobRoot.get("id")) );

		/* join jobAds on categories.Id = jobAds.categoryId \n" +
		   join records on jobAds.ID = records.jobAdsID \n" */
		jobRoot.join("category");
		jobRoot.join("recordsSet");

		//where is active
		Predicate isActive
				= builder.equal(jobRoot.get("isActive"),true);
		query.where(isActive);

		//group by Categories.Id,Categories.Name
		query.groupBy(categoryRoot.get("id"),categoryRoot.get("name"), jobRoot.get("name"),jobRoot.get("id"));

		//order by sum(jobAdsID) desc, CategoryName asc, jobAds.name
		query.orderBy(builder.desc (builder.count(jobRoot.get("id")))
				, builder.asc(categoryRoot.get("name")),
				builder.asc(jobRoot.get("name")));

		//printResult*/
		TypedQuery<Object[]> typedQuery = session.createQuery(query).setMaxResults(50);
		typedQuery.getResultList().forEach(object -> {
			System.out.println(">> " + Arrays.toString(object) + "\n");
		});
	}

	public static void test(){
		Session session = SessionHolder.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

//		Root<BaseEntity> baseRoot = query.from(BaseEntity.class);
		Root<JobAdsEntity> jobRoot = query.from(JobAdsEntity.class);
		Root<CategoriesEntity> categoryRoot = query.from(CategoriesEntity.class);

		//Join<BaseEntity, JobAdsEntity> jobAdsjoin = baseRoot.join("jobAds");
		query.multiselect(jobRoot.get("name"));

		Predicate isActive
				= builder.equal(jobRoot.get("isActive"),true);
		query.where(isActive);

		System.out.println(Arrays.toString(session.createQuery(query).getResultList().toArray()));
		/*Stream<Object[]> typedQuery = session.createQuery(query).setMaxResults(50).getResultStream();
		typedQuery.forEach(object -> {
			System.out.println(">> " + Arrays.toString(object) + "\n");
		});*/
	}
}

