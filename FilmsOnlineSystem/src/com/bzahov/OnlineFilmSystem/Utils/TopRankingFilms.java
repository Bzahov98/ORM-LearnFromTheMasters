package com.bzahov.OnlineFilmSystem.Utils;

import com.bzahov.OnlineFilmSystem.Entities.CategoriesEntity;
import com.bzahov.OnlineFilmSystem.Entities.FilmsEntity;
import com.bzahov.OnlineFilmSystem.joinEntities.Films_;
import com.bzahov.SessionHolder;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class TopRankingFilms {

	private final static String sql =
			"SELECT films.name as filmName," +
			"       c.name as categoryName," +
			"       films.watchCount," +
			"       films.earnings," +
			"       c.info " +
			"from films " +
			"  join filmsCategory f on films.id = f.filmID " +
			"  join categories c on f.categoryID = c.id " +
			"group by films.watchCount,c.name, films.earnings, films.name, c.info " +
			"order by c.name asc , films.watchCount desc ,films.earnings, films.name asc ";

	public static void findTopRankings() {
		Session session = SessionHolder.getINSTANCE();
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<FilmsEntity> film = query.from(FilmsEntity.class);
		Root<CategoriesEntity> categoryRoot = query.from(CategoriesEntity.class);

		//select films.name as filmName,
		//       c.name as categoryName,
		//       watchCount, earnings, c.info
		query.multiselect(film.get("name"), categoryRoot.get("name"), film.get("watchCount") , film.get("earnings")).distinct(true);

		/* join filmsCategory fc on films.id = fc.filmID
		 * join categories     c on fc.categoryID = c.id */
		film.join("allCategoriesSet");

		//where watchCount > 0
		Predicate watchCountGreatherThanZERO
				= builder.greaterThan(film.get("watchCount"), 0);
		query.where(watchCountGreatherThanZERO);

		//group by watchCount,c.name, earnings, films.name, info
		query.groupBy(film.get("watchCount"), categoryRoot.get("name"), film.get("earnings"), film.get("name"), categoryRoot.get("info"));

		//order by c.name asc , watchCount desc ,films.name asc
		query.orderBy(builder.asc (categoryRoot.get("name"))
				, builder.desc(film.get("watchCount"))
				, builder.desc(film.get("earnings"))
				, builder.asc (film.get("name")));

		//printResult
		TypedQuery<Object[]> typedQuery = session.createQuery(query).setMaxResults(50);
		typedQuery.getResultList().forEach(object -> {
			System.out.println(">> " + Arrays.toString(object) + "\n");
		});
	}

	public static void filterFilmsByNameAndPrice(String byName, BigDecimal byPrice) {
		Session session = SessionHolder.getINSTANCE();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();


		CriteriaQuery<FilmsEntity> criteriaQuery = criteriaBuilder.createQuery(FilmsEntity.class);
		Root<FilmsEntity> filmRoot = criteriaQuery.from(FilmsEntity.class);

		Predicate predicateFilmsByName
				= criteriaBuilder.equal(filmRoot.get("name"), byName);
		Predicate predicateFilmsByPrice
				= criteriaBuilder.equal(filmRoot.get("price"), byPrice);
		Predicate finalPredicat
				= criteriaBuilder.and(predicateFilmsByName, predicateFilmsByPrice);

		criteriaQuery.where(finalPredicat);
		List<FilmsEntity> result = session.createQuery(criteriaQuery).getResultList();

		result.forEach(System.out::println);
	}


	public static Set<FilmsEntity> calculateRanking() {
		Session session = SessionHolder.getINSTANCE();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FilmsEntity> query = builder.createQuery(FilmsEntity.class);
		Root<FilmsEntity> filmsRoot = query.from(FilmsEntity.class);

		Join<FilmsEntity, CategoriesEntity> filmCategoriesJoin = filmsRoot.join("categories", JoinType.INNER);

		//query.select(filmsRoot).where(builder.equal(filmCategoriesJoin.get()))
		query.select(filmsRoot).groupBy();
		Query<FilmsEntity> q = session.createQuery(query);
		List<FilmsEntity> list = q.getResultList();
		list.forEach(filmsEntity -> System.out.println(filmsEntity.toString()));
		return null;
	}

	public static void nativeSql() {
		Session session = SessionHolder.getINSTANCE();
		String sql2 = "SELECT {films.*},{c.*}, FROM EMPLOYEE WHERE id = :employee_id";

		//SQLQuery<Object> query = session.createSQLQuery(sql);

		//session.getCriteriaBuilder().createQuery(FilmsEntity.class);

		//query.addEntity("fe",FilmsEntity.class);
		//query.addJoin("fc","films.id");
		//query.addJoin("c","fc.categoryID");

		//List<Object> results = query.list();

		//results.forEach(System.out::println);
	}

	public static void fff() {
		Session session = SessionHolder.getINSTANCE();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<FilmsEntity> cq = builder.createQuery(FilmsEntity.class);


		Root<FilmsEntity> filmsRoot = cq.from(FilmsEntity.class);
		Root<CategoriesEntity> categoryRoot = cq.from(CategoriesEntity.class);


		Root<CategoriesEntity> rootAnswer = cq.from(CategoriesEntity.class);

		//(or rootAnswer.join(Answer_.collaborators); if you've created the metamodel with JPA2
		Join<FilmsEntity, CategoriesEntity> filmsCategoriesJoin = rootAnswer.join("allFilmsSet");

		//cq.from(filmsCategoriesJoin);
		cq.multiselect(filmsRoot.get("name"), categoryRoot.get("name"), filmsRoot.get("watchCount"));

		List<FilmsEntity> resultList = session.createQuery(cq).getResultList();

		resultList.forEach(FilmsEntity::toString);

		System.out.println(filmsCategoriesJoin.toString());
	}

	public static void sss() {
		Session session = SessionHolder.getINSTANCE();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQ = builder.createQuery(Object[].class);

		Root<FilmsEntity> filmsRoot = criteriaQ.from(FilmsEntity.class);
		Root<CategoriesEntity> categoryRoot = criteriaQ.from(CategoriesEntity.class);

		criteriaQ.multiselect(filmsRoot.get("name"), categoryRoot.get("name"), filmsRoot.get("watchCount"));
        /* ListJoin<FilmsEntity,CategoriesEntity> joined = filmsRoot.join(Category_.getAllCategoriesSet(),JoinType.INNER);
        criteriaQ.multiselect(filmsRoot.get("name"),filmsRoot.get("watchCount"),categoryRoot.get("name"));
        criteriaQ.where(builder.equal(filmsRoot.join()))
		//CriteriaQuery<Object[]> multiselect = criteriaQ.multiselect(filmsRoot, categoryRoot);*/
		criteriaQ.where(builder.isMember(Films_.id, categoryRoot.get("allFilmsSet")));
		List<Object[]> resultList = session.createQuery(criteriaQ).getResultList();
		resultList.forEach(Object::toString);
		resultList.forEach(System.out::println);
	}
}
