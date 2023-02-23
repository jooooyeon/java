package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
	애너테이션에 대하여...
	
	프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속한 형식으로 포함시킨 것(JDK1.5부터 지원)
	
	주석처럼 프로그램언어 코드에 영향을 미치지 않으면서도 다른프로그램에게 유용한 정보를 제공함.
	
	종류 : 1. 표준(일반) 애너테이션
	      2. 메타 애너테이션 (애너테이션을 위한 애너테이션, 즉 애너테이션을 정의할 때 사용하는 애너테이션)
	      
	애너테이션 타입 정의하기
	@interface 애너테이션이름{
		요소타입 타입요소이름(); //반환값이 있고 매개변수는 없는 추상메서드의 형태
		...
		
	}
	
	애너테이션 요소의 규칙
	1. 요소타입은 기본형, String, enum, annotation, class만 허용된다.
	2. ()안에 매개변수를 선언할 수 없다.
	3. 예외를 선언할 수 없다.
	4. 요소의 타입에 타입매개변수(제너릭 타입 글자)를 사용 할 수 없다.
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 에너테이션이 유지되는 기간
public @interface PrintAnnotation {
	int id = 100;               //상수 선언 가능
	String value() default "-"; //기본값을 "-"로 지정
	int count() default 20;     //기본값을 20으로 지정
	
}
