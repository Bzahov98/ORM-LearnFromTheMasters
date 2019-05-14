package com.bzahov.Entities;

import com.bzahov.exceptions.MyDataErrorException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "records", schema = "dbo", catalog = "RecruitmentSystem")
public class RecordsEntity extends BaseEntity {
	private String applicantName;
	private JobAdsEntity jobAd;


	public RecordsEntity() { }

	public RecordsEntity(String recordsName, String applicantName, String info, JobAdsEntity jobAd) {
		super(recordsName, info);
		this.applicantName = applicantName;
		/*this.isActive = isActive;*/
		this.jobAd = jobAd;
	}

	public RecordsEntity(String recordsName, String applicantName, String info) {
		super(recordsName, info);
		this.applicantName = applicantName;
		/*this.isActive = isActive;*/
	}

	@Basic
	@Column(name = "applicantName", nullable = false, length = 255)
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobAdsID")
	public JobAdsEntity getJobAd() {
		return jobAd;
	}

	public void setJobAd(JobAdsEntity jobAds) {
		this.jobAd = jobAds;
	}

	@Override
	public String toString() {
		return "RecordsEntity{" +
				"applicantName='" + applicantName + '\'' +
				", jobAds=" + jobAd +
				", id=" + id +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				'}';
	}
	public void addRecordToJobAd(JobAdsEntity jobAd) throws MyDataErrorException {
		if (jobAd != null) {

			Set<RecordsEntity> recordsSet = jobAd.getRecordsSet();
			if (jobAd.getRecordsSet() == null) {
				jobAd.setRecordsSet(new HashSet<>());
			}
			if (!recordsSet.contains(this) ) {
				recordsSet.add(this);
				this.jobAd = jobAd;
				return;
			}

			System.err.println("\nRecord already in jobAd list\n");
			throw new MyDataErrorException();
		}
	}

}
