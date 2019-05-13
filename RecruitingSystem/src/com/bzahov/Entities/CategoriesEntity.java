package com.bzahov.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories", schema = "dbo", catalog = "RecruitmentSystem")
public class CategoriesEntity extends BaseEntity{

	private Set<JobAdsEntity> jobAdsSet = new HashSet<>();

	public CategoriesEntity(String name, String info, Set<JobAdsEntity> jobAdsSet) {
		super(name, info);
		this.jobAdsSet = jobAdsSet;
	}

	public CategoriesEntity() {}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
	public Set<JobAdsEntity> getJobAdsSet() {
		return jobAdsSet;
	}

	public void setJobAdsSet(Set<JobAdsEntity> jobAdsSet) {
		this.jobAdsSet = jobAdsSet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CategoriesEntity)) return false;
		if (!super.equals(o)) return false;
		CategoriesEntity that = (CategoriesEntity) o;
		return Objects.equals(getJobAdsSet(), that.getJobAdsSet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getJobAdsSet());
	}

	@Override
	public String toString() {
		return "CategoriesEntity{" +
				"jobAdsSet=" + "" +
				", id=" + id +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				'}';
	}
}
