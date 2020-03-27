package com.m3bi.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users {

	@Column(name = "userid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;

	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "bonuspoint", nullable = false)
	private int bonusPoint;

	@Column(name = "age")
	private int age;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBonusPoints() {
		return bonusPoint;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoint = bonusPoints;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + age; result = prime * result + bonusPoint; result =
	 * prime * result + ((firstName == null) ? 0 : firstName.hashCode()); result =
	 * prime * result + ((gender == null) ? 0 : gender.hashCode()); result = prime *
	 * result + ((lastName == null) ? 0 : lastName.hashCode()); result = prime *
	 * result + userid; return result; }
	 */

	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; Users other = (Users) obj; if (age != other.age) return false; if
	 * (bonusPoint != other.bonusPoint) return false; if (firstName == null) { if
	 * (other.firstName != null) return false; } else if
	 * (!firstName.equals(other.firstName)) return false; if (gender == null) { if
	 * (other.gender != null) return false; } else if (!gender.equals(other.gender))
	 * return false; if (lastName == null) { if (other.lastName != null) return
	 * false; } else if (!lastName.equals(other.lastName)) return false; if (userid
	 * != other.userid) return false; return true; }
	 */

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", lastName=" + lastName + ", firstName=" + firstName + ", gender=" + gender
				+ ", bonusPoints=" + bonusPoint + ", age=" + age + "]";
	}

}
