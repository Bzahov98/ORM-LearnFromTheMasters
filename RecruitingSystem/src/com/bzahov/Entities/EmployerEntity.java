package com.bzahov.Entities;

import com.bzahov.exceptions.MyDataErrorException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employer", schema = "dbo", catalog = "RecruitmentSystem")
public class EmployerEntity extends BaseEntity {
	public static final int MAX_EMPLOYER_JOB_ADDS = 10;

	private Integer jobAdsCount = 0;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employer")
	public Set<JobAdsEntity> getJobAdsSet() {
		return jobAdsSet;
	}

	public void setJobAdsSet(Set<JobAdsEntity> jobAdsSet) {
		this.jobAdsSet = jobAdsSet;
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

	public void addJobAdAndActivate(JobAdsEntity jobAdd) throws MyDataErrorException {
		if (jobAdd.getIsActive()) {
			if (this.jobAdsCount <= MAX_EMPLOYER_JOB_ADDS) {
				if (!getJobAdsSet().contains(jobAdd)) {
					this.getJobAdsSet().add(jobAdd);
					if (jobAdd.getEmployer() != this) {
						jobAdd.addEmployer(this);
					}
					jobAdd.activateJobAd();
				}
			} else {
				MyDataErrorException myDataErrorException = new MyDataErrorException();
				myDataErrorException.setMessage("you try to add more than 10 active jobAds");
				throw myDataErrorException;
			}
		}
	}

	public void removeJobAd(JobAdsEntity jobAdd) throws MyDataErrorException {

		if (!(getJobAdsSet().contains(jobAdd) && jobAdd.getEmployer() == this)) {
			this.getJobAdsSet().remove(jobAdd);
			jobAdd.deactivateJobAd();
			System.out.println("\nremoved job ad"+ jobAdd.getName() +" successful");
		} else {
			MyDataErrorException myDataErrorException = new MyDataErrorException();
			myDataErrorException.setMessage(" this job ad is not owned by this employer");
			throw myDataErrorException;
		}
	}
}
