package com.hkllyx.demo.basic.annotation.reflect;

/**
 * 商品筛选条件
 *
 * @author HKLLY
 * @date 2019/7/8
 */
@Table("items")
public class GoodsFilter {
    @Column("id")
    private int id;

    @Column("name")
    private String name;

    @Column("city")
    private String city;

    @Column("price")
    private float price;

    @Column("number")
    private long number;

    public static void main(String[] args) {
        GoodsFilter filter = new GoodsFilter();
        filter.setId(1);
        filter.setName("test_name");
        filter.setNumber(200L);
        filter.setCity("test_city");
        filter.setPrice(2.33f);
        System.out.println(Query.getSql(filter));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
