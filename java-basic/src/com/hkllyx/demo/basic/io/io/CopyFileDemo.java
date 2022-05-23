package com.hkllyx.demo.basic.io.io;

import java.io.*;

/**
 * io性能测试：<br>
 * 注意：图片（非纯文本文件）只能用字节流操作<br>
 * 如果使用字符流，会读取两个字节然后按照字符集转换， 而有一些字节对应的字符不存在，jvm就会用一些类似的编码代替<br>
 *
 * @author HKLLY
 * @date 2019-07-18
 */
public class CopyFileDemo {
    
    public static void main(String[] args) throws IOException {
        File src = new File("README.md");
        File dest = new File("src/main/resources/performance/io/readme.md");
        long duration;
        
        //测试FileInputStream/FileOutputStream
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        duration = copyFileByByteStreamDuration(fis, fos);
        System.out.println("FileStream: " + duration);
        fis.close();
        fos.close();

        //测试BufferedInputStream/BufferedOutputStream
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(dest));
        duration = copyFileByByteStreamDuration(bis, bos);
        System.out.println("BufferedStream: " + duration);
        bis.close();
        bos.close();

        //测试InputStreamReader/OutputStreamWriter
        InputStreamReader isr = new FileReader(src);
        OutputStreamWriter osw = new FileWriter(dest);
        duration = copyFileByCharStreamDuration(isr, osw);
        System.out.println("IoStreamRw: " + duration);
        isr.close();
        osw.close();

        //测试BufferedReader/BufferedWriter
        BufferedReader br = new BufferedReader(new FileReader(src));
        BufferedWriter bw = new BufferedWriter(new FileWriter(dest));
        duration = copyFileByCharStreamDuration(br, bw);
        System.out.println("BufferedRw: " + duration);
        br.close();
        bw.close();
    }
    
    public static long copyFileByByteStreamDuration
            (InputStream in, OutputStream out) throws IOException {
        long start = System.currentTimeMillis();
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
            out.flush();
        }
        return System.currentTimeMillis() - start;
    }
    
    public static long copyFileByCharStreamDuration
            (Reader in, Writer out) throws IOException {
        int c;
        long start = System.currentTimeMillis();
        while ((c = in.read()) != -1) {
            out.write(c);
            out.flush();
        }
        return System.currentTimeMillis() - start;
    }
}
