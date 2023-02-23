package homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*'d:/D_Other/'에 있는 'Tulips.jpg'파일을

'복사본_Tulips.jpg'로 복사하는 프로그램을

작성하시오.
*/
public class Ex1 {
	public static void main(String[] args) {
		
//		FileInputStream fis = null;
//		FileOutputStream fos = null;
		
		try {
			FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			FileOutputStream fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			
			int data = 0;
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
			
			fis.close();
			fos.close();
			System.out.println("복사완료");
			
		}catch (IOException e){
			e.printStackTrace();
			
		}
}
}	