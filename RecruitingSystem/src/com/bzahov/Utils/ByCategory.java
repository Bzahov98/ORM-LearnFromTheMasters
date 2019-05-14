package com.bzahov.Utils;

import com.bzahov.Entities.CategoriesEntity;
import com.bzahov.Entities.JobAdsEntity;
import com.bzahov.Entities.RecordsEntity;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public class ByCategory {

	private final static String sqlQueryCategories =
			"select c.name as 'CategoryName'," +
			"       count(c.id) as 'JobAds total count' , " +
			"from jobAds\n" +
			"join categories c on c.id = jobAds.CategoryID\n" +
			"where jobAds.isActive = 1\n" +
			"group by  c.id, c.name\n" +
			"order by count(c.id) desc,c.name desc";
	public static void findActiveCategoriesCount() {
		//показва колко активни обяви имаме по категории
		Session session = SessionHolder.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<JobAdsEntity> jobRoot = query.from(JobAdsEntity.class);
		Root<CategoriesEntity> categoryRoot = query.from(CategoriesEntity.class);
		//select
		query.multiselect(categoryRoot.get("name").alias("CategoryName"),builder.count(categoryRoot.get("id")) );
		//join condition
		Predicate joinCond
				= builder.equal(jobRoot.get("category"),categoryRoot.get("id"));
		// is active
		Predicate isActive
				= builder.equal(jobRoot.get("isActive"),true);
		query.where(isActive);

		query.where(builder.and(joinCond,isActive));

		//group by Categories.Id,Categories.Name
		query.groupBy(categoryRoot.get("id"),categoryRoot.get("name"));

		//order by count(c.id) desc, CategoryName asc
		query.orderBy(
				builder.desc(builder.count(categoryRoot.get("id"))),
				builder.asc (categoryRoot.get("name"))
		);

		//printResult
		TypedQuery<Object[]> typedQuery = session.createQuery(query).setMaxResults(50);
		typedQuery.getResultList().forEach(object -> {
			System.out.println(">> " + Arrays.toString(object));
		});
	}

	private final static String sqlQueryPeopleCount = "select count(r.id) as 'People total count'," +
			"j.name as jobName\n" +
			"from records r\n" +
			"            join jobAds j on j.id = r.jobAdsID\n" +
			"    /*where isActive = 1*/\n" +
			"group by j.id, j.name\n" +
			"order by count(r.id) desc";
	public static void findPeopleCountPerProfession() {
		//колко хора са кандидствали по всяка професия
		// (QA, Developer, Manager, DevOps
		Session session = SessionHolder.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<JobAdsEntity> jobRoot = query.from(JobAdsEntity.class);
		Root<RecordsEntity> records = query.from(RecordsEntity.class);

		// select
		query.multiselect(builder.count(records.get("id")), jobRoot.get("name"));

		//where is active
		//join condition
		Predicate joinCond
				= builder.equal(jobRoot.get("id"),records.get("jobAd"));

		query.where(joinCond);
		//group by Categories.Id,Categories.Name
		query.groupBy(jobRoot.get("id"),jobRoot.get("name"));

		//order by sum(jobAdsID) desc, CategoryName asc, jobAds.name
		query.orderBy(builder.desc (
				builder.count(records.get("id")))
		);

		//printResult*/
		TypedQuery<Object[]> typedQuery = session.createQuery(query).setMaxResults(50);
		typedQuery.getResultList().forEach(object -> {
			System.out.println(">> " + Arrays.toString(object) + "\n");
		});
	}

	@Deprecated
	public static void findActiveCategoriesAndPeopleCount() {
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
				= builder.equal(jobRoot.get("qwwerty"),true);
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
	@Deprecated
	public static void test2(){
		Session session = SessionHolder.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

//		Root<BaseEntity> baseRoot = query.from(BaseEntity.class);
		Root<JobAdsEntity> jobRoot = query.from(JobAdsEntity.class);
		Root<CategoriesEntity> categoryRoot = query.from(CategoriesEntity.class);

		//Join<BaseEntity, JobAdsEntity> jobAdsjoin = baseRoot.join("jobAds");
		query.multiselect(jobRoot.get("isActive"));

		/*Predicate isActive
				= builder.equal(jobRoot.get("isActive"),true);
		query.where(isActive);*/

		System.out.println(Arrays.toString(session.createQuery(query).getResultList().toArray()));
		/*Stream<Object[]> typedQuery = session.createQuery(query).setMaxResults(50).getResultStream();
		typedQuery.forEach(object -> {
			System.out.println(">> " + Arrays.toString(object) + "\n");
		});*/
	}

	/*public static void test3(){

		Session session = SessionHolder.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<JobAdsEntity> teacher = query.from(JobAdsEntity.class);
		Root<RecordsEntity> recordsRoot = query.from(RecordsEntity.class);

		query.multiselect(teacher.get("id"),recordsRoot.get("id"));

		List<Object[]> teachers = builder.createQuery(query).getResultList();
	}*/
	@Deprecated
	public static void test(){
		Session session = SessionHolder.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

//		Root<BaseEntity> baseRoot = query.from(BaseEntity.class);
		Root<JobAdsEntity> jobRoot = query.from(JobAdsEntity.class);
		Root<CategoriesEntity> categoryRoot = query.from(CategoriesEntity.class);
		Root<RecordsEntity> recordsRoot = query.from(RecordsEntity.class);

		//Join<BaseEntity, JobAdsEntity> jobAdsjoin = baseRoot.join("jobAds");
		query.multiselect(jobRoot.get("isActive"),jobRoot.get("name"));

		/*Predicate isActive
				= builder.equal(jobRoot.get("isActive"),true);
		query.where(isActive);*/

		System.out.println(Arrays.toString(session.createQuery(query).getResultList().toArray()));
		/*Stream<Object[]> typedQuery = session.createQuery(query).setMaxResults(50).getResultStream();
		typedQuery.forEach(object -> {
			System.out.println(">> " + Arrays.toString(object) + "\n");
		});*/
	}

}

