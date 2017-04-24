package com.interaction.pojo;

public class Spocscore implements java.io.Serializable{

	private Integer ssid;
	private Seminar seminar;
	private Student student;
	private Double score1;
	private Double score2;
	
	public Spocscore() {
	}

	public Integer getSsid() {
		return ssid;
	}

	public void setSsid(Integer ssid) {
		this.ssid = ssid;
	}

	public Seminar getSeminar() {
		return seminar;
	}

	public void setSeminar(Seminar seminar) {
		this.seminar = seminar;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Double getScore1() {
		return score1;
	}

	public void setScore1(Double score1) {
		this.score1 = score1;
	}

	public Double getScore2() {
		return score2;
	}

	public void setScore2(Double score2) {
		this.score2 = score2;
	}

	@Override
	public String toString() {
		return "Spocscore [ssid=" + ssid + ", seminar=" + seminar.getSeId() + ", student=" + student.getSid() + ", score1=" + score1
				+ ", score2=" + score2 + "]";
	}
}
