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
	protected int id;
	protected String name;
	protected String info;


	public JobAdsEntity() { }

	public JobAdsEntity(String name, String info, Boolean isActive, CategoriesEntity category) throws MyDataErrorException {
		super(name, info);
		this.isActive = isActive;
		setCategory(category);
	}

	public JobAdsEntity(String name, String info, Boolean isActive, CategoriesEntity category, EmployerEntity employer, Set<RecordsEntity> recordsSet) throws MyDataErrorException {
		super(name, info);
		this.isActive = isActive;
		setCategory(category);
		setEmployer(employer);
		setRecordsSet(recordsSet);
	}

	public JobAdsEntity(String name, String info, Boolean isActive) {
		super(name, info);
		this.name = name;
		this.info = info;
		this.isActive = isActive;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = false, length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "info", nullable = true, length = 255)
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "isActive", columnDefinition = "BIT")
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean active) {
		isActive = active;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryID")
	public CategoriesEntity getCategory() {
		return category;
	}

	public void setCategory(CategoriesEntity category) throws MyDataErrorException {
			this.category = category;
	}

	public void addCategory(CategoriesEntity category) throws MyDataErrorException{
		if (category != null) {
			this.category = category;
		}else throw new MyDataErrorException();
	}
	@Deprecated
	public void setCategoryOfAllJobAds(CategoriesEntity category) throws MyDataErrorException {
		if (category != null) {
			this.addCategory(category);
			for (JobAdsEntity jobAdsEntity : category.getJobAdsSet()) {
				jobAdsEntity.addCategory(this.category);
				System.out.println("set jobads to category " + category.getName());
			}
		} else throw new MyDataErrorException();
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employerID")
	public EmployerEntity getEmployer() {
		return employer;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jobAd")
	public Set<RecordsEntity> getRecordsSet() {
		return recordsSet;
	}

	public void setRecordsSet(Set<RecordsEntity> recordsSet){
		this.recordsSet = recordsSet;
	}

	public void registerRecord(RecordsEntity record) throws MyDataErrorException {
		if (record == null) {
			System.err.println("in JobAds register record is null");
			throw new MyDataErrorException();
		}
		if (!getRecordsSet().contains(record)) {
			this.getRecordsSet().add(record);
			record.setJobAd(this);
		} else {
			System.out.println("alreadyRegistred that record");
			throw new MyDataErrorException();
		}
	}

	public void registerRecordSet(Set<RecordsEntity> records) throws MyDataErrorException {
		if (records == null) {
			System.err.println("in JobAds register record is null");
			return;
		}
		for (RecordsEntity record : records) {
			this.registerRecord(record);
			/*if (!getRecordsSet().contains(record)) {
				this.getRecordsSet().add(record);
				record.setJobAd(this);
			} else {
				System.out.println("alreadyRegistered that record");
			}*/
		}
	}

	// todo
	public void setEmployer(EmployerEntity employer) throws MyDataErrorException {
		this.employer = employer;
	}

	public void addEmployer(EmployerEntity employer) throws MyDataErrorException{
		if (employer == null) {
			System.err.println("\nemployer null\n");
			return;
		}
		if (employer.getJobAdsCount() <= 10) {
			this.employer = employer;
			if (!employer.getJobAdsSet().contains(this)) {
				employer.addJobAdAndActivate(this);
			}
		} else {
			System.err.println("\n too much accounts");
			throw new MyDataErrorException();
		}
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

	public void activateJobAd(){
		setIsActive(true);
		this.employer.setJobAdsCount(this.employer.getJobAdsCount()+1);
	}

	public void deactivateJobAd(){
		setIsActive(false);
		this.employer.setJobAdsCount(this.employer.getJobAdsCount()-1);
	}

}
