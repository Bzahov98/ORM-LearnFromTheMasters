package com.bzahov.Entities;

import com.bzahov.exceptions.MyDataErrorException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobAds", schema = "dbo", catalog = "RecruitmentSystem")
public class JobAdsEntity extends BaseEntity {
	private Boolean isActive;
	private CategoriesEntity category;
	private EmployerEntity employer;
	private Set<RecordsEntity> recordsSet = new HashSet<>();

	public JobAdsEntity() { }

	public JobAdsEntity(String name, String info, Boolean isActive, CategoriesEntity category) {
		super(name, info);
		this.isActive = isActive;
		this.category = category;
	}

	public JobAdsEntity(String name, String info, Boolean isActive, CategoriesEntity category, EmployerEntity employer, Set<RecordsEntity> recordsSet) {
		super(name, info);
		this.isActive = isActive;
		this.category = category;
		this.employer = employer;
		this.recordsSet = recordsSet;
	}

	public JobAdsEntity(String name, String info, Boolean isActive) {
		super(name, info);
		this.isActive = isActive;
	}

	public void registerRecord(RecordsEntity record) throws MyDataErrorException {
		if (!getRecordsSet().contains(record)) {
			this.getRecordsSet().add(record);
			record.setJobAds(this);
		} else {
			System.out.println("alreadyRegistred that record");
			throw new MyDataErrorException();
		}
	}

	public void registerRecordSet(Set<RecordsEntity> records) throws MyDataErrorException {
		records.forEach(record -> {
			if(!getRecordsSet().contains(record)){
				this.getRecordsSet().add(record);
				record.setJobAds(this);
			} else{
				System.out.println("alreadyRegistered that record");
			}
		});
	}

	@Basic
	@Column(name = "IsActive")
	public boolean getActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryID")
	public CategoriesEntity getCategory() {
		return category;
	}

	public void setCategory(CategoriesEntity categories) {
		this.category = categories;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employerID")
	public EmployerEntity getEmployer() {
		return employer;
	}

	// todo
	public void setEmployer(EmployerEntity employer) throws MyDataErrorException {
		if (employer == null) {
			System.err.println("employer null\n");
			return;
		}
		if (employer.getJobAdsCount() <= 10) {
			this.employer = employer;
			employer.addJobAdd(this);
		} else {
			System.err.println("\ntoo much accounts");
			throw new MyDataErrorException();
		}
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jobAds")
	public Set<RecordsEntity> getRecordsSet() {
		return recordsSet;
	}

	public void setRecordsSet(Set<RecordsEntity> recordsSet) {
		this.recordsSet = recordsSet;
	}

	@Override
	public String toString() {
		return "JobAdsEntity{" +
				"isActive=" + isActive +
				", category=" + category +
				", employer=" + employer +
				", recordsSet=" + "" +
				", id=" + id +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				'}';
	}
}
