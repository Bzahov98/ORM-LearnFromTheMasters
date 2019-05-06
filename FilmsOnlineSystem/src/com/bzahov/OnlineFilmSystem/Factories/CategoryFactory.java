package com.bzahov.OnlineFilmSystem.Factories;

import com.bzahov.OnlineFilmSystem.Entities.CategoriesEntity;
import com.bzahov.OnlineFilmSystem.Entities.FilmsEntity;
import com.bzahov.OnlineFilmSystem.exceptions.MyDataErrorException;
import com.bzahov.SessionHolder;
import org.hibernate.Session;

import java.util.Set;


public class CategoryFactory {

    public static CategoriesEntity createCategory(final String name, final String info) throws MyDataErrorException {
        return createCategoryEntity(name, info, null);
    }

    public static CategoriesEntity createCategoryWithFilmSet(final String name, final String info, Set<FilmsEntity> filmsSet) throws MyDataErrorException {
        return createCategoryEntity(name, info, filmsSet);
    }

    public static CategoriesEntity createCategoryEntity(final String name, final String info, Set<
            FilmsEntity> filmsSet) throws MyDataErrorException {
        if (name == null) throw new MyDataErrorException();

        final Session session = SessionHolder.getINSTANCE();
        session.beginTransaction();

        CategoriesEntity category = new CategoriesEntity(name, info);
        if (filmsSet != null) {
            category.setAllFilmsSet(filmsSet);
            filmsSet.forEach(filmsEntity -> filmsEntity.getAllCategoriesSet().add(category));
        }
        session.save(category);
        session.getTransaction().commit();
        return category;

    }
}
