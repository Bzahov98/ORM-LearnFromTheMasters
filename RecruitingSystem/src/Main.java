import com.bzahov.Factories.RecordsFactory;
import com.bzahov.Utils.DemoClass;
import com.bzahov.Utils.SessionHolder;
import org.hibernate.Session;

public class Main {

	public static void main(final String[] args) throws Exception {
		final Session session = SessionHolder.getSession();
		RecordsFactory.createRecordEntity("pesho","Gosho","",null);
		//ByCategory.findTopRankings();
		//session.beginTransaction();
		try {
			DemoClass.objectDemoCreationWithoutReferences();
			DemoClass.objectDemoCreationWithReferences();
			DemoClass.demoMaxJobAdsPerEmployerError();
			DemoClass.queriesDemoCreation();
			DemoClass.demoMultipleRecordsOnOneJobAddError();


			//CategoriesEntity category = CategoryFactory.createCategory("QA2", "bla bla");
			/*EmployerEntity employer = EmployerFactory.createEmployer("MusalaSoft", "bla bla");
			JobAdsEntity jobAdsEntity = JobAdsFactory.createJobAd("Dev Job11","info",true);
			jobAdsEntity.setEmployer(employer);
			JobAdsEntity jobAdsEntity2 = JobAdsFactory.createJobAd("Dev Job2","info",true);
			*/
			/*JobAdsEntity jobAdsEntity3 = JobAdsFactory.createJobAd("Dev Job3","info",true);
			JobAdsEntity jobAdsEntity4 = JobAdsFactory.createJobAd("Dev Job4","info",true);
			JobAdsEntity jobAdsEntity5 = JobAdsFactory.createJobAd("Dev Job5","info",true);
			JobAdsEntity jobAdsEntity6 = JobAdsFactory.createJobAd("Dev Job6","info",true);
			JobAdsEntity jobAdsEntity7 = JobAdsFactory.createJobAd("Dev Job7","info",true);
			JobAdsEntity jobAdsEntity8 = JobAdsFactory.createJobAd("Dev Job8","info",true);
			JobAdsEntity jobAdsEntity9 = JobAdsFactory.createJobAd("Dev Job9","info",true);
			JobAdsEntity jobAdsEntity10 = JobAdsFactory.createJobAd("Dev Job10","info",true);
			JobAdsEntity jobAdsEntity11 = JobAdsFactory.createJobAd("Dev Job11","info",true);
			//RecordsEntity recordsEntity = RecordsFactory.createRecord("record12","Gosho",false);
			Set<JobAdsEntity> jobAdsEntityList = new HashSet<>();
			//jobAdsEntityList.add(jobAdsEntity);
			//jobAdsEntityList.add(jobAdsEntity2);
			jobAdsEntityList.add(jobAdsEntity3);
			jobAdsEntityList.add(jobAdsEntity4);
			jobAdsEntityList.add(jobAdsEntity5);
			jobAdsEntityList.add(jobAdsEntity6);
			jobAdsEntityList.add(jobAdsEntity7);
			jobAdsEntityList.add(jobAdsEntity8);
			jobAdsEntityList.add(jobAdsEntity9);
			jobAdsEntityList.add(jobAdsEntity10);
			jobAdsEntityList.add(jobAdsEntity11);

			/*System.out.println("\n " + jobAdsEntityList.size());
			for (JobAdsEntity jobAd : jobAdsEntityList) {
				jobAd.addEmployer(employer);
			}*/

			//jobAdsEntityList.add(jobAdsEntity11);
			//EmployerEntity employer = EmployerFactory.createEmployer("Musala2", "bla bla");

		/*	CategoriesEntity category = CategoryFactory.createCategory("QA automatic tester", "bla bla");

			RecordsEntity recordsEntity = RecordsFactory.createRecord("recordIvan","Ivan");
			RecordsEntity recordsEntity2 = RecordsFactory.createRecord("recordIvan2","Ivan2");
			RecordsEntity recordsEntity3 = RecordsFactory.createRecord("recordIvan3","Ivan3");
			Set<RecordsEntity> recordsEntitySet = new HashSet<>();
			recordsEntitySet.add(recordsEntity);
			recordsEntitySet.add(recordsEntity2);
			recordsEntitySet.add(recordsEntity3);

			EmployerEntity employer = EmployerFactory.createEmployer("MusalaSoft", "bla bla");
			JobAdsEntity jobAdsEntity = JobAdsFactory.createJobAdWithAllRelations("Develop Job11211",
					"infso",true,category,employer,recordsEntitySet);

			System.out.println(jobAdsEntity.toString());*/
			//recordsEntity.addRecordToJobAd(jobAdsEntity);
			//EmployerFactory.addJobAddToEmployer(jobAdsEntityList,employer);

			/*ByCategory.findActiveCategoriesCount();
			System.out.println("\n");
			ByCategory.findPeopleCountPerProfession();
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