package kr.or.ddit.basic.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class StudentExample {

	public static void main(String[] args) {
		
		List <Student> list =new ArrayList<Student>();
		list.add(new Student(3,"RM",60,90,60));
		list.add(new Student(2,"진",50,50,40));
		list.add(new Student(6,"슈가",70,90,60));
		list.add(new Student(7,"제이홉",70,100,50));
		list.add(new Student(8,"지민",70,90,80));
		list.add(new Student(4,"뷔",80,70,70));
		list.add(new Student(1,"정국",80,90,70));
		list.add(new Student(5,"공유",60,60,40));
		
		for (Student student : list) {
			int rank =1;
			for (Student student2 : list) {
				if(student.getTotal()<student2.getTotal()) {
					rank++;
				}
			}
			student.setRank(rank);
			
		}
		
		System.out.println("정렬전==============================================================");
		for(Student s: list)
		System.out.println(s);
		
		System.out.println("학번오름차순정렬후======================================================");
		Collections.sort(list);
		for(Student s: list)
			System.out.println(s);
			
		
		System.out.println("총점 내림차순 정렬후====================================================");
		Collections.sort(list,new Desc());
		for(Student s: list)
			System.out.println(s);
		
		
		
		
		
		
		
	}

}
