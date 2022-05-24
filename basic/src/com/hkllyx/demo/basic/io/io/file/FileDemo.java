package com.hkllyx.demo.basic.io.io.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author HKLLY
 * @date 2019-07-14
 */
public class FileDemo {
    
    public static void main(String[] args) {
        //文件（目录）src/main/resources/dir1/dir2
        File dir = new File("src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "dir1");
        //是否存在
        System.out.println("dir.exists() = " + dir.exists());
        //是否是目录
        System.out.println("dir.isDirectory() = " + dir.isDirectory());
        //是否为文件
        System.out.println("dir.isFile() = " + dir.isFile());
        //是否隐藏
        System.out.println("dir.isHidden() = " + dir.isHidden());
        //是否是绝对路径
        System.out.println("dir.isAbsolute() = " + dir.isAbsolute());
        //获取长度
        System.out.println("dir.length() = " + dir.length());
        //获取该路径部分未分配空间
        System.out.println("dir.getFreeSpace() = " + dir.getFreeSpace());
        //获取该路径部分的总空间
        System.out.println("dir.getTotalSpace() = " + dir.getTotalSpace());
        //获取该路径部分可以空间
        System.out.println("dir.getUsableSpace() = " + dir.getUsableSpace());
        System.out.println();
        
        //是否可读
        System.out.println("dir.canRead() = " + dir.canRead());
        //是否可写
        System.out.println("dir.canWrite() = " + dir.canWrite());
        //是否可执行
        System.out.println("dir.canExecute() = " + dir.canExecute());
        //获取最后修改时间
        System.out.println("dir.lastModified() = " + dir.lastModified());
        //设置是否可读
        System.out.println("dir.setReadable(true) = " + dir.setReadable(true));
        System.out.println("dir.setReadable(true, true) = " + dir.setReadable(true, true));
        //设置是否可写
        System.out.println("dir.setWritable(true) = " + dir.setWritable(true));
        System.out.println("dir.setWritable(true, true) = " + dir.setWritable(true, true));
        //设置是否可执行
        System.out.println("dir.setExecutable(true) = " + dir.setExecutable(true));
        System.out.println("dir.setExecutable(true, true) = " + dir.setExecutable(true, true));
        //设置是否只读
        System.out.println("dir.setReadOnly() = " + dir.setReadOnly());
        //设置最后修改时间
        System.out.println("dir.setLastModified(1000L) = " + dir.setLastModified(1000L));
        System.out.println();
        
        //获取文件名，路径中的最内层
        System.out.println("dir.getName() = " + dir.getName());
        //转为Path对象
        System.out.println("dir.toPath() = " + dir.toPath());
        //转为URI对象
        System.out.println("dir.toURI() = " + dir.toURI());
        //重命名（相当于移动文件）
        System.out.println("dir.renameTo(new File(dir, \"dir3\")) = "
                + dir.renameTo(new File(dir, "dir3")));
        System.out.println();
        
        //获取相对路径
        System.out.println("dir.getPath() = " + dir.getPath());
        //获取绝对路径
        System.out.println("dir.getAbsolutePath() = " + dir.getAbsolutePath());
        //获取完全限定路径
        try {
            System.out.println("dir.getCanonicalPath() = "
                    + dir.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取父文件的路径（是否为相对路径同dir）
        System.out.println("dir.getParent() = " + dir.getParent());
        //获取父文件（保存的路径是否为相对路径同dir）
        System.out.println("dir.getParentFile() = " + dir.getParentFile());
        //获取父文件（保存的路径为绝对路径）
        System.out.println("dir.getAbsoluteFile() = " + dir.getAbsoluteFile());
        //获取父文件（保存的路径为完全限定路径）
        try {
            System.out.println("dir.getCanonicalFile() = " + dir.getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        
        //创建目录（若父文件未创建，则创建失败，若已存在返回false）
        System.out.println("dir.mkdir() = " + dir.mkdir());
        //创建目录（若父文件未创建，则创建父文件，若已存在返回false）
        System.out.println("dir.mkdirs() = " + dir.mkdirs());
        //创建文件（若父文件不存在则抛出IO异常，若已存在返回false）
        File file = new File(dir, "demo1.txt");
        try {
            System.out.println("file.createNewFile() = " + file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    
        //获取目录下所有文件（不显示子目录内文件）
        File[] files = dir.listFiles();
        System.out.println("dir.listFiles() = " + Arrays.toString(files));
        //使用FileFilter过滤文件
        files = dir.listFiles(pathname -> pathname.getName().contains("io"));
        System.out.println("dir.listFiles(FileFilter) = " + Arrays.toString(files));
        //使用FilenameFilter过滤文件
        files = dir.listFiles((dir1, name) -> name.contains("io"));
        System.out.println("dir.listFiles(FilenameFilter) = " + Arrays.toString(files));
        //获取目录下所有文件的文件名（不显示子目录内文件）
        String[] fileNames = dir.list();
        System.out.println("dir.list() = " + Arrays.toString(fileNames));
        fileNames = dir.list((dir1, name) -> name.contains("io"));
        //使用FilenameFilter过滤文件名
        System.out.println("dir.list(FilenameFilter) = " + Arrays.toString(fileNames));
        System.out.println();
    
        //比较文件路径名
        System.out.println("dir.compareTo(file) = " + dir.compareTo(file));
        System.out.println("dir.equals(file) = " + dir.equals(file));
        System.out.println();
    
        //获取当前文件系统root目录
        System.out.println("File.listRoots() = " + Arrays.toString(File.listRoots()));
        //获取当前文件系统的文件分隔符
        System.out.println("File.separator = " + File.separator);
        //获取当前文件系统的文件路径分隔符
        System.out.println("File.pathSeparator = " + File.pathSeparator);
        try {
            //创建临时文件
            File tmp = File.createTempFile("prefix", "suffix");
            System.out.println("File.createTempFile(\"prefix\", \"suffix\") = " + tmp);
            //删除文件（只能删除文件或空文件夹）
            System.out.println("tmp.delete() = " + tmp.delete());
            //制定目录创建临时文件
            tmp = File.createTempFile("prefix", "suffix", dir);
            System.out.println("File.createTempFile(\"prefix\", \"suffix\", dir) = " + tmp);
            //删除文件（一般用于删除临时文件，只能删除文件或空文件夹）
            tmp.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
