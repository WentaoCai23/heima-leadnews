package com.heima.minio;

import com.heima.file.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MinIOTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/caiwentao/Downloads/cat.jpg");
        String path = fileStorageService.uploadImgFile("", "cat.jpg", fileInputStream);
        System.out.println(path);
    }


//    public static void main(String[] args) {
//
//        FileInputStream fileInputStream = null;
//        try {
//
//            fileInputStream =  new FileInputStream("/Users/caiwentao/Downloads/leadnews_user.sql");;
//
//            //1.创建minio链接客户端
//            MinioClient minioClient = MinioClient.builder().credentials("minio", "minio123").endpoint("http://localhost:9000").build();
//            //2.上传
//            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
//                    .object("leadnews_user.sql")//文件名
//                    .contentType("text/sql")//文件类型
//                    .bucket("leadnews")//桶名词  与minio创建的名词一致
//                    .stream(fileInputStream, fileInputStream.available(), -1) //文件流
//                    .build();
//            minioClient.putObject(putObjectArgs);
//
//            System.out.println("http://localhost:9000/leadnews/leadnews_user.sql");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}
