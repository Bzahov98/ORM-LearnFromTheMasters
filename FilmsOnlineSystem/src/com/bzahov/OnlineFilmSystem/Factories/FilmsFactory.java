package com.bzahov.OnlineFilmSystem.Factories;

import com.bzahov.OnlineFilmSystem.Entities.AccountEntity;
import com.bzahov.OnlineFilmSystem.Entities.CategoriesEntity;
import com.bzahov.OnlineFilmSystem.Entities.FilmsEntity;
import com.bzahov.SessionHolder;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Set;


public class FilmsFactory {

    public static FilmsEntity createFilm(final String name, final String directorName,
                                         final String producerName,
                                         final BigDecimal price) {
        return getFilmsEntity(name, directorName, producerName, price, null, null);
    }

    public static FilmsEntity createFilmWithAccountSet(final String name, final String directorName,
                                                       final String producerName,
                                                       final BigDecimal price, final Set<AccountEntity> accountSet) {
        return getFilmsEntity(name, directorName, producerName, price, accountSet, null);
    }

    public static FilmsEntity createFilmWithCategorySet(final String name, final String directorName,
                                                        final String producerName,
                                                        final BigDecimal price,
                                                        final Set<CategoriesEntity> categorySet) {

        return getFilmsEntity(name, directorName, producerName, price, null, categorySet);
    }

    public static FilmsEntity createFilmWithAccountAndCategorySet(final String name, final String directorName,
                                                                  final String producerName,
                                                                  final BigDecimal price,
                                                                  final Set<AccountEntity> accountSet,
                                                                  final Set<CategoriesEntity> categorySet) {

        return getFilmsEntity(name, directorName, producerName, price, accountSet, categorySet);
    }

    private static FilmsEntity getFilmsEntity(final String name, final String directorName,
                                              final String producerName, final BigDecimal price,
                                              final Set<AccountEntity> accountSet,
                                              final Set<CategoriesEntity> categorySet) {

        Session session = SessionHolder.getINSTANCE();
        session.beginTransaction();

        FilmsEntity result = new FilmsEntity(name, directorName, producerName, price, FilmsEntity.DEFAULT_WATCH_COUNT);

        if (accountSet != null) {
            result.setAllAccountsSet(accountSet);
            accountSet.forEach(accountEntity -> accountEntity.getAllFilmsSet().add(result));
        }
        if (categorySet != null) {
            result.setAllCategoriesSet(categorySet);
            categorySet.forEach(categoriesEntity -> categoriesEntity.getAllFilmsSet().add(result));
        }
        session.save(result);
        session.getTransaction().commit();
        return result;
    }
}
