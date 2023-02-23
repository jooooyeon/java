package kr.or.ddit.basic.example;

import java.util.Comparator;

public class Student implements Comparable<Student> {
	private int id;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;
	public Student(int id, String name, int kor, int eng, int math) {
		super();
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total=kor+eng+math;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", total="
				+ total + ", rank=" + rank + "]";
	}
	@Override
	public int compareTo(Student o) {
		if(this.id > o.id) {//자신의 아이디가 o의 아이디보다 크다면 양수
			return 1;
	}else if(this.id == o.id) {
		return 0;
	}else {
		return -1;
	}
	
	
	}
	
}


class Desc implements Comparator<Student>{

	@Override
	//총점이 같으면 학번의 내림차순정렬
	public int compare(Student o1, Student o2) {
		if(o1.getTotal() == o2.getTotal()) {	//총점이 같으면 학번을 역순으로
			return new Integer(o1.getId()).compareTo(o2.getId())*-1;
		}else if(o1.getTotal()>o2.getTotal()) {
			return -1; //o1 아이디가 o2의 아이디보다 크다면 역순 
		}else {
			return 1;
		}
	}
	
	
	
	
}


