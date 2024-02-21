package com.newsTicker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="newsTicker")
public class NewsTickerVO implements java.io.Serializable{
	public NewsTickerVO() {
		super();
	}
	
//	newsTickerId int AI PK 
//	newsTickerContent varchar(100) 
//	sort int 
//	startTime datetime 
//	endTime datetime 
//	isDisplay tinyint(1)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newsTickerId", updatable = false)
	private Integer newsTickerId;

	@Column(name = "newsTickerContent")
    private String newsTickerContent;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date startTime= new Date();

    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date endTime= new Date();
    
    @Column(name = "isDisplay")
    private Boolean isDisplay = true;

	public Integer getNewsTickerId() {
		return newsTickerId;
	}

	public void setNewsTickerId(Integer newsTickerId) {
		this.newsTickerId = newsTickerId;
	}

	public String getNewsTickerContent() {
		return newsTickerContent;
	}

	public void setNewsTickerContent(String newsTickerContent) {
		this.newsTickerContent = newsTickerContent;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	@Override
	public String toString() {
		return "NewsTickerVO [newsTickerId=" + newsTickerId + ", newsTickerContent=" + newsTickerContent + ", sort="
				+ sort + ", startTime=" + startTime + ", endTime=" + endTime + ", isDisplay=" + isDisplay + "]";
	}
       
}
