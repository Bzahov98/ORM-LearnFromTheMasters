import com.bzahov.Factories.RecordsFactory;
import com.bzahov.Utils.SessionHolder;
import org.hibernate.Session;

public class Main {

	public static void main(final String[] args) throws Exception {
		final Session session = SessionHolder.getSession();
		RecordsFactory.createRecordEntity("pesho","Gosho","",null);
		//ByCategory.findTopRankings();
		//session.beginTransaction();
		try {
			//CategoriesEntity category = CategoryFactory.createCategory("QA2", "bla bla");
			/*EmployerEntity employer = EmployerFactory.createEmployer("Musala2", "bla bla");
			JobAdsEntity jobAdsEntity = JobAdsFactory.createJobAdWithEmployer("Develop Job11","info",true,employer);
			JobAdsEntity jobAdsEntity2 = JobAdsFactory.createJobAd("Develop Job2","info",true);
			JobAdsEntity jobAdsEntity3 = JobAdsFactory.createJobAd("Develop Job3","info",true);
			JobAdsEntity jobAdsEntity4 = JobAdsFactory.createJobAd("Develop Job4","info",true);
			JobAdsEntity jobAdsEntity5 = JobAdsFactory.createJobAd("Develop Job5","info",true);
			JobAdsEntity jobAdsEntity6 = JobAdsFactory.createJobAd("Develop Job6","info",true);
			JobAdsEntity jobAdsEntity7 = JobAdsFactory.createJobAd("Develop Job7","info",true);
			JobAdsEntity jobAdsEntity8 = JobAdsFactory.createJobAd("Develop Job8","info",true);
			JobAdsEntity jobAdsEntity9 = JobAdsFactory.createJobAd("Develop Job9","info",true);
			JobAdsEntity jobAdsEntity10 = JobAdsFactory.createJobAd("Develop Job10","info",true);
			JobAdsEntity jobAdsEntity11 = JobAdsFactory.createJobAd("Develop Job11","info",true);
			//RecordsEntity recordsEntity = RecordsFactory.createRecord("record12","Gosho",false);
			Set<JobAdsEntity> jobAdsEntityList = new HashSet<>();
			jobAdsEntityList.add(jobAdsEntity);
			jobAdsEntityList.add(jobAdsEntity2);
			jobAdsEntityList.add(jobAdsEntity3);
			jobAdsEntityList.add(jobAdsEntity4);
			jobAdsEntityList.add(jobAdsEntity5);
			jobAdsEntityList.add(jobAdsEntity6);
			jobAdsEntityList.add(jobAdsEntity7);
			jobAdsEntityList.add(jobAdsEntity8);
			jobAdsEntityList.add(jobAdsEntity9);
			jobAdsEntityList.add(jobAdsEntity10);
			jobAdsEntityList.add(jobAdsEntity11);/*
			EmployerEntity employer = EmployerFactory.createEmployer("Musala2", "bla bla");
			RecordsEntity recordsEntity = RecordsFactory.createRecord("record12","Gosho",false);
			JobAdsEntity jobAdsEntity = JobAdsFactory.createJobAdWithEmployer("Develop Job11","info",true,employer);

			recordsEntity.addRecordToJobAd(jobAdsEntity);
			//EmployerFactory.addJobAddToEmployer(jobAdsEntityList,employer);
*/
			//ByCategory.findActiveCategoriesAndPeopleCount();
			/*
			System.out.println("querying all the managed entities...");
			final Metamodel metamodel = session.getSessionFactory().getMetamodel();
			for (EntityType<?> entityType : metamodel.getEntities()) {
				final String entityName = entityType.getName();
				final Query query = session.createQuery("from " + entityName);
				System.out.println("executing: " + query.getQueryString());
				for (Object o : query.list()) {
					System.out.println("  " + o);
				}
			}*/
		} finally {
			SessionHolder.closeSession();
		}
	}
}