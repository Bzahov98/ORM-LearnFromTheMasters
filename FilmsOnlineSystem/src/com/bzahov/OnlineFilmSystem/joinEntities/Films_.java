package com.bzahov.OnlineFilmSystem.joinEntities;

import com.bzahov.OnlineFilmSystem.Entities.FilmsEntity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = FilmsEntity.class)
public class Films_ {

	public static volatile SingularAttribute<FilmsEntity, Integer> id;


}
