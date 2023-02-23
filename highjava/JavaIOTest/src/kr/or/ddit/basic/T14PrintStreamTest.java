package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터 기능을 제공하는 보조 스트림
 */
public class T14PrintStreamTest {
	public static void main(String[] args) throws IOException {

		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");

		// PrintStream은 모든 데이터를 출력 할 수 있는 기능을 제공하는 보조스트림이다.

		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream입니다. \n");
		out.println("안녕하세요. PrintStream입니다2.");
		out.println("안녕하세요. PrintStream입니다3.");
		out.println(out); // 객체출력
		out.println(3.14);

		/*
		 * [결과] 안녕하세요. PrintStream입니다. 
		 * 안녕하세요. PrintStream입니다2. 안녕하세요. 
		 * PrintStream입니다3.
		 * java.io.PrintStream@15db9742 3.14
		 */

		System.out.println();

		///////////////////////////////////////////

		/*
		 * PrintWriter가 PrintStream보다 다양한 인코딩의 문자처리에 적합하지만 
		 * (PrintStream이) 예전부터 써 오던 객체라 계속 사용중임.
		 * 
		 */

		PrintWriter pw = new PrintWriter(
				new OutputStreamWriter(new FileOutputStream("d:/D_Other/print2.txt"), "utf-8"));

		pw.print("안녕하세요. PrintWriter 입니다.\n");
		pw.println("안녕하세요. PrintWriter입니다2.");
		pw.println("안녕하세요. PrintWriter입니다3.");

		pw.close();

		/*
		 * 안녕하세요. PrintWriter 입니다. 
		 * 안녕하세요. PrintWriter입니다2. 
		 * 안녕하세요. PrintWriter입니다3.
		 */

	}

}
