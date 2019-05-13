package com.bzahov.Entities;

import com.bzahov.exceptions.MyDataErrorException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employer", schema = "dbo", catalog = "RecruitmentSystem")
public class EmployerEntity extends BaseEntity {
	public static final int MAX_EMPLOYER_JOB_ADDS = 10;

	private Integer jobAdsCount;
	private Set<JobAdsEntity> jobAdsSet = new HashSet<>();

	public EmployerEntity() { }

	public EmployerEntity(String name, String info, Set<JobAdsEntity> jobAdsSet, Integer jobAdsCount) {
		super(name, info);
		this.jobAdsSet = jobAdsSet;
		if (jobAdsCount == null) jobAdsCount = 0;
		this.jobAdsCount = jobAdsCount;
	}

	public EmployerEntity(String name, String info) {
		super(name, info);
	}

	@Basic
	@Column(name = "jobAdsCount")
	public Integer getJobAdsCount() {
		return jobAdsCount;
	}

	public void setJobAdsCount(Integer jobAdsCount) {
		this.jobAdsCount = jobAdsCount;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "employer")
	public Set<JobAdsEntity> getJobAdsSet() {
		return jobAdsSet;
	}

	public void setJobAdsSet(Set<JobAdsEntity> jobAdsSet) {
		this.jobAdsSet = jobAdsSet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EmployerEntity)) return false;
		if (!super.equals(o)) return false;
		EmployerEntity that = (EmployerEntity) o;
		return Objects.equals(getJobAdsSet(), that.getJobAdsSet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getJobAdsSet());
	}

	@Override
	public String toString() {
		return "EmployerEntity{" +
				"jobAdsSet=" + "" +
				", id=" + id +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				'}';
	}

	public void addJobAdd(JobAdsEntity jobAdd) throws MyDataErrorException {
		if (jobAdd.getActive()) {
			if (jobAdsCount >= MAX_EMPLOYER_JOB_ADDS) {
				MyDataErrorException myDataErrorException = new MyDataErrorException();
				myDataErrorException.setMessage("try to add more than 10 active jobAds");
				throw myDataErrorException;
			} else {
				getJobAdsSet().add(jobAdd);
				jobAdsCount++;
			}
		}
	}
}
