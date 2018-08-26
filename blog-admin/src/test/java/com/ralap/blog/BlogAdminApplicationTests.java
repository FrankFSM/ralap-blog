package com.ralap.blog;

import com.ralap.blog.business.enums.QiniuUploadType;
import com.ralap.blog.plugin.QiniuApi;
import com.ralap.blog.business.enums.QiniuUploadType;
import com.ralap.blog.plugin.QiniuApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogAdminApplicationTests {

	@Test
	public void contextLoads() {
		File file = new File("C:\\x\\x.png");
		try {
			String filePath = QiniuApi.getInstance()
                    .withFileName(file.getName(), QiniuUploadType.SIMPLE)
                    .upload(file);
			System.out.println(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
