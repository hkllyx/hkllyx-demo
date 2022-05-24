package com.hkllyx.demo.basic.annotation.apt;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Set;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
@SupportedAnnotationTypes({"persistent", "Id", "Property"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HibernateProcessor extends AbstractProcessor {
    /**
     * 通过将 processingEnv 字段初始化处理器的环境。
     *
     * @param processingEnv 处理器环境参数
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
            RoundEnvironment roundEnv) {
        // 遍历每个被 @Persistent 修饰的 class 文件
        File dir = new File("src/main/resources/apt");
        for (Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)) {
            try {
                String name = t.getSimpleName().toString();
                Persistent per = t.getAnnotation(Persistent.class);
                PrintStream ps = new PrintStream(new File(dir, name + "hbm.xml"));
                ps.println("<?xml version=\"1.0\"?>");
                ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
                ps.println("    \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"");
                ps.println("    \"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">");
                ps.println("<hibernate-mapping>");

                ps.println("        <class name=\"" + name + "\", table=\"" + per.table() + "\">");
                for (Element f : t.getEnclosedElements()) {
                    Id id = f.getAnnotation(Id.class);
                    if (id != null) {
                        ps.println("            <id name=\"" + f.getSimpleName()
                                + "\", column=\"" + id.column() + "\", "
                                + "type=\"" + id.type() + "\", "
                                + "generator=\"" + id.generator() + "\">");
                    }
                    Property p = f.getAnnotation(Property.class);
                    if (p != null) {
                        ps.println("            <property name=\"" + f.getSimpleName()
                                + "\", column=\"" + p.column() + "\", "
                                + "type=\"" + p.type() + "\">");
                    }
                }
                ps.println("    </class>");
                ps.println("</hibernate-mapping>");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 获取 SupportedAnnotationTypes 注解指定的当前处理器支持的注解
     *
     * @return 当前处理器支持的注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
    }

    /**
     * 获取 SupportedSourceVersion 注解指定当前处理器支持的 jdk 版本。
     * 如果类没有如此注释，则返回 SourceVersion.RELEASE_6。
     *
     * @return 当前处理器支持的 jdk 版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }
}
