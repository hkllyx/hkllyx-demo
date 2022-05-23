package com.hkllyx.demo.basic.annotation.apt;

/**
 * @author HKLLY
 * @date 2019-08-12
 */
@Persistent(table = "person")
public class Person {
    @Id(column = "id", type = "long", generator = "identity")
    private long id;

    @Property(column = "name", type = "string")
    private String name;

    @Property(column = "age", type = "byte")
    private byte age;

    public Person() {
    }

    public Person(long id, String name, byte age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getter and setter
}
