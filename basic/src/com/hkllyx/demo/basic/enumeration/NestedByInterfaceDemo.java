package com.hkllyx.demo.basic.enumeration;

/**
 * 通过接口嵌套枚举
 *
 * @author HKLLY
 * @date 2019/4/8
 */
public class NestedByInterfaceDemo {

    public static void main(String[] args) {
        for (Meal meal : Meal.values()) {
            System.out.println("Meal的实例: " + meal);
            Food food = meal.randomSelection();
            System.out.println("Meal实例的实例: " + food);
        }
    }

    public interface Food {
    }

    enum Meal {
        /** 各种食物,每一个字段对应一个实现Food接口的枚举的类对象 */
        APPETIZER(Appetizer.class),
        MAIN_COURSE(MainCourse.class),
        DESSERT(Dessert.class),
        COFFEE(Coffee.class);

        /** 获取字段对应的值,此处为实现Food接口的枚举的类对象 */
        private final Food[] values;

        /**
         * @param kind 实现Food接口的枚举的类对象
         */
        Meal(Class<? extends Food> kind) {
            values = kind.getEnumConstants();
        }

        public Food randomSelection() {
            return EnumUtils.random(values);
        }

        enum Appetizer implements Food {
            SALAD, SOUP, SPRING_ROLLS
        }

        enum MainCourse implements Food {
            LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMUS, VINDALOO
        }

        enum Dessert implements Food {
            TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL
        }

        enum Coffee implements Food {
            BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA
        }
    }
}
