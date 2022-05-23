package com.hkllyx.demo.basic.annotation.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 根据Filter获取query语句
 *
 * @author HKLLY
 * @date 2019/7/8
 */
public class Query {

    /**
     * 获取query语句
     *
     * @param filter Filter对象
     * @return 拼装的Query语句
     */
    public static String getSql(GoodsFilter filter) {
        StringBuilder sb = new StringBuilder();
        String tableName = getTableName(filter);
        sb.append("SELECT * FROM ").append(tableName);
        String condition = getCondition(filter);
        if (condition != null) {
            sb.append(" WHERE ").append(condition);
        }
        return sb.toString();
    }

    /**
     * 获取表名
     *
     * @param filter Filter对象
     * @return 表名
     */
    public static String getTableName(GoodsFilter filter) {
        Class c = filter.getClass();
        if (!c.isAnnotationPresent(Table.class)) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        return table.value();
    }

    /**
     * 获取query条件
     *
     * @param filter Filter对象
     * @return query条件
     */
    public static String getCondition(GoodsFilter filter) {
        StringBuilder sb = new StringBuilder();
        String result = null;
        //getDeclaredFields获取本类所有字段，getFields获取本类及父类public字段
        Field[] fields = filter.getClass().getDeclaredFields();
        for (Field field : fields) {
            //判断域是否定义了Column注解
            if (field.isAnnotationPresent(Column.class)) {
                //获取定义为Column的域的name
                Column column = field.getAnnotation(Column.class);
                String columnName = column.value();
                String fieldName = field.getName();
                String fieldValue = null;
                try {
                    //通过getXxx()方法获取域的值
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    Method getMethod =
                            filter.getClass().getDeclaredMethod(getMethodName);
                    fieldValue = getMethod.invoke(filter).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //拼装条件
                if (fieldValue != null) {
                    fieldValue = fieldValue.trim();
                    if (fieldValue.indexOf(',') != -1
                            && fieldValue.lastIndexOf(',')
                            == fieldValue.length()) {
                        fieldValue = fieldValue.substring(0, fieldValue.lastIndexOf(','));
                    }
                    sb.append(columnName).append(" sdIn('").append(fieldValue)
                            .append("')").append(" AND ");
                }
            }
        }
        if (sb.length() > 0) {
            result = sb.toString();
            //去除最后一个多余的AND
            result = result.substring(0, result.lastIndexOf(" AND "));
        }
        return result;
    }
}
