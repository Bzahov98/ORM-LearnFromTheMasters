import com.bzahov.OnlineFilmSystem.Utils.TopRankingFilms;
import com.bzahov.SessionHolder;

public class Main {

	public static void main(final String[] args) throws Exception {

		//TopRankingFilms.sss();
		try {
			TopRankingFilms.findTopRankings();

		} finally {
			SessionHolder.closeSession();
		}
	}
        /*
        *
        * /*   AccountEntity account = AccountFactory.createAccount("MiAz1", "sadassd@gas.bg");
        FilmsEntity film = FilmsFactory.createFilm("Lost in desert2","Beliq","BeliqProducent",BigDecimal.valueOf(5));
        FilmsEntity film2 = FilmsFactory.createFilm("Bulgar4","Beliq","BeliqProducent",BigDecimal.valueOf(2));

        AccountFactory.depositMoney(account,BigDecimal.valueOf(22));
        AccountFactory.registerForFilm(account,film);
        //
        AccountFactory.watchFilm(account,film);
        AccountFactory.watchFilm(account,film);
        AccountFactory.watchFilm(account,film);
        AccountFactory.watchFilm(account,film);
        AccountFactory.watchFilm(account,film);

        AccountFactory.registerForFilm(account,film2);

        AccountFactory.watchFilm(account,film2);
        AccountFactory.watchFilm(account,film2);
        AccountFactory.watchFilm(account,film2);
        AccountFactory.watchFilm(account,film2);
        AccountFactory.watchFilm(account,film2);
        AccountFactory.watchFilm(account,film2);
        AccountFactory.watchFilm(account,film2);

        Set<FilmsEntity> filmSet = new HashSet<>();
        filmSet.add(film);
        filmSet.add(film2);
        CategoriesEntity categoriesEntity = CategoryFactory.createCategoryWithFilmSet("History Documentary","category", filmSet);
       TopRankingFilms.calculateRanking();

        Session session = SessionHolder.getINSTANCE();

        */
            /* System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }*/
}