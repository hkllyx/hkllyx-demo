package com.hkllyx.demo.basic.io.io.file;

import java.io.*;
import java.util.Scanner;

/**
 * 1）FileDescriptor可以被用来表示开放文件、开放套接字(open socket)、字节的源或接受等。<br>
 * 2）FileDescriptor表示文件时，可以将FileDescriptor看成是该文件。 但是，不能直接通过FileDescriptor对该文件进行操作，
 * 需要新创建FileDescriptor对应的 FileOutputStream，再对文件进行操作。<br>
 * 3）FileDescriptor有三个静态常量：sdOut（对应System.sdOut）、 sdIn（System.sdIn）、sdErr（System.sdErr）<br>
 *
 * @author HKLLY
 * @date 2019-07-16
 */
public class FileDescriptorDemo {
    public static void main(String[] args) throws IOException {
        //因为都是使用的标准流，他们会相互干扰
        //sdErr();
        //sdIn();
        //sdOut();
        File file = new File("src\\main\\resources\\dir1\\demo1.txt");
        //用处不大。。。
        out(new FileOutputStream(file).getFD());
        in(new FileInputStream(file).getFD());
    }
    
    public static void sdOut() {
        //使用FileDescriptor做参数生成FileOutputStream不会产生异常
        try (FileOutputStream out = new FileOutputStream(FileDescriptor.out)) {
            //在console中以char的形式输出
            out.write('A');
            out.write(65);
            out.write(256);
            out.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void sdIn() {
        //使用FileDescriptor做参数生成FileOutputStream会产生IO异常
        try (FileInputStream in = new FileInputStream(FileDescriptor.in)) {
            Scanner scanner = new Scanner(in);
            if (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void sdErr() {
        try (FileOutputStream err = new FileOutputStream(FileDescriptor.err)) {
            err.write('A');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void out(FileDescriptor fd) {
        try (FileOutputStream out = new FileOutputStream(fd)) {
            out.write('A');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void in(FileDescriptor fd) {
        try (FileInputStream in = new FileInputStream(fd)) {
            System.out.println("unreadByte.read() = " + in.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
