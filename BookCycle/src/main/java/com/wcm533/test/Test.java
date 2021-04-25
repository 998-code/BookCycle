package com.wcm533.test;

import com.wcm533.utils.FileUtils;

/**
 * @ClassName Test
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/22 19:16
 **/
public class Test {
    public static void main(String[] args) {
//        byte[] headImg = FileUtils.fileToByte("BookCycle/web/static/img/img1914.png");
//        FileUtils.byteToFile(headImg,"BookCycle/web/static/img/userImg/","img1914.png");
        String originalFilename = "tim.g (1).jpg";//timg (1).jpg
        //获取最后一个.的位置
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //获取文件的后缀名 .jpg
        String suffix = originalFilename.substring(lastIndexOf);

        System.out.println("suffix = " + suffix);
    }
}
