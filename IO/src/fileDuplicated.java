

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileDuplicated {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
		FileOutputStream fos = new FileOutputStream("d:/D_Other/蹂듭궗蹂�_Tulips.jpg");
		
		byte[] buf = new byte[1024];
		
		int data = 0;
		
		while((data = fis.read(buf))!=-1) {
			fos.write(buf,0,data);
		}
		
		System.out.println("�옉�뾽�셿猷�");
		fis.close();
		fos.close();
		
		
		
	}

}
