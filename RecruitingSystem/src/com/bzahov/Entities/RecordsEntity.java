package com.bzahov.Entities;

import com.bzahov.exceptions.MyDataErrorException;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "records", schema = "dbo", catalog = "RecruitmentSystem")
public class RecordsEntity extends BaseEntity{
	private String applicantName;
	//private Boolean isActive;
	private JobAdsEntity jobAds;


	public RecordsEntity() {	}

	public RecordsEntity(String recordsName,String applicantName, String info, JobAdsEntity jobAds) {
		super(recordsName,info);
		this.applicantName = applicantName;
		/*this.isActive = isActive;*/
		this.jobAds = jobAds;
	}

	public RecordsEntity(String recordsName, String applicantName, String info) {
		super(recordsName,info);
		this.applicantName = applicantName;
		/*this.isActive = isActive;*/
	}

	@Basic
	@Column(name = "aplicantName", nullable = false, length = 255)
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String aplicantName) {
		this.applicantName = aplicantName;
	}

	/*@Basic
	@Column(name = "isActive", nullable = true)
	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}
*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobAdsID")
	public JobAdsEntity getJobAds() {
		return jobAds;
	}

	public void setJobAds(JobAdsEntity jobAds) {
		this.jobAds = jobAds;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RecordsEntity)) return false;
		if (!super.equals(o)) return false;
		RecordsEntity that = (RecordsEntity) o;
		return Objects.equals(getApplicantName(), that.getApplicantName()) &&
				/*Objects.equals(isActive, that.isActive) &&*/
				Objects.equals(getJobAds(), that.getJobAds());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getApplicantName()/*, isActive*/, getJobAds());
	}

	@Override
	public String toString() {
		return "RecordsEntity{" +
				"applicantName='" + applicantName + '\'' +
				/*", isActive=" + isActive +*/
				", jobAds=" + jobAds +
				", id=" + id +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				'}';
	}
	public void addRecordToJobAd(JobAdsEntity jobAd) throws MyDataErrorException {
		if (jobAd != null) {

			Set<RecordsEntity> recordsSet = jobAd.getRecordsSet();
			if (!recordsSet.contains(this)) {
				recordsSet.add(this);
			} else {
				//throw new MyDataErrorException();
			}
			this.setJobAds(jobAd);
		}
	}
}
