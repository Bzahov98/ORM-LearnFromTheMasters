package com.bzahov.Entities;

import com.bzahov.exceptions.MyDataErrorException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories", schema = "dbo", catalog = "RecruitmentSystem")
public class CategoriesEntity extends BaseEntity {

	private Set<JobAdsEntity> jobAdsSet = new HashSet<>();

	public CategoriesEntity(String name, String info, Set<JobAdsEntity> jobAdsSet) {
		super(name, info);
		this.jobAdsSet = jobAdsSet;
	}

	public CategoriesEntity() {}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	public Set<JobAdsEntity> getJobAdsSet() {
		return jobAdsSet;
	}

	public void setJobAdsSet(Set<JobAdsEntity> jobAdsSet) throws MyDataErrorException {
		this.jobAdsSet = jobAdsSet;
	}

	public void addJobAdSetToCategory(Set<JobAdsEntity> newJobAdsSet) throws MyDataErrorException {
		if (newJobAdsSet != null) {
			//this.jobAdsSet = newJobAdsSet;
			for (JobAdsEntity jobEntity : jobAdsSet) {
				//jobEntity.addCategory(this);
				addJobAdToCategory(jobEntity);
			}
		} else {
			System.err.println("error in setJobAdsSet");
			throw new MyDataErrorException();
		}
	}

	public void addJobAdToCategory(JobAdsEntity newJobAd) throws MyDataErrorException {
		if (newJobAd != null) {
			if (getJobAdsSet() == null) {
				HashSet<JobAdsEntity> jobAdsEntities = new HashSet<>();
				jobAdsEntities.add(newJobAd);
				setJobAdsSet(jobAdsEntities);
			}else getJobAdsSet().add(newJobAd);

			newJobAd.addCategory(this);
		} else {
			System.err.println("error in setJobAdsSet");
			throw new MyDataErrorException();
		}
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
