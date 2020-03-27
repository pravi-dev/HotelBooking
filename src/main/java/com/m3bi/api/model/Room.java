package com.m3bi.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room {

	@Column(name = "rmid", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rmid;

	@Column(name = "userid", nullable = false)
	private int userid;

	@Column(name = "rmstatus", nullable = false)
	private String rmStatus;

	@Column(name = "rmprice", nullable = false)
	private int rmPrice;

	@Column(name = "rmtype")
	private String rmType;

	/**
	 * @return the rmid
	 */
	public int getRmid() {
		return rmid;
	}

	/**
	 * @param rmid the rmid to set
	 */
	public void setRmid(int rmid) {
		this.rmid = rmid;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the rmStatus
	 */
	public String getRmStatus() {
		return rmStatus;
	}

	/**
	 * @param rmStatus the rmStatus to set
	 */
	public void setRmStatus(String rmStatus) {
		this.rmStatus = rmStatus;
	}

	/**
	 * @return the rmPrice
	 */
	public int getRmPrice() {
		return rmPrice;
	}

	/**
	 * @param rmPrice the rmPrice to set
	 */
	public void setRmPrice(int rmPrice) {
		this.rmPrice = rmPrice;
	}

	/**
	 * @return the rmType
	 */
	public String getRmType() {
		return rmType;
	}

	/**
	 * @param rmType the rmType to set
	 */
	public void setRmType(String rmType) {
		this.rmType = rmType;
	}

}
