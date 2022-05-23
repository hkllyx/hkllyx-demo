package com.hkllyx.demo.basic.concurrent.atomic;

/**
 * @author HKLLY
 * @date 2019-07-12
 */
class User {
    private static int counter = 0;
    private final int id = counter++;
    private volatile int age;
    private String name;

    public User() {
        System.out.println("User()");
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
