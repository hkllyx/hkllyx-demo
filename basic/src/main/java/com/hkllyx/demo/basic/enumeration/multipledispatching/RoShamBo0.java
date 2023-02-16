package com.hkllyx.demo.basic.enumeration.multipledispatching;

import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Item;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Paper;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Rock;
import com.hkllyx.demo.basic.enumeration.multipledispatching.entity.Scissors;

import java.util.Random;

/**
 * @author HKLLY
 * @date 2019-07-10
 */
public class RoShamBo0 {
    private static final Random RANDOM = new Random(47);

    public static void main(String[] args) {
        RoShamBo0 rsb = new RoShamBo0();
        for (int i = 0; i < 10; i++) {
            rsb.match(rsb.newItem(), rsb.newItem());
        }
    }

    /**
     * 随机生成石头剪刀布
     *
     * @return 石头剪刀布中任一个
     */
    public Item newItem() {
        switch (RANDOM.nextInt(3)) {
            case 0:
                return new Paper();
            case 1:
                return new Scissors();
            case 2:
                return new Rock();
            default:
                return null;
        }
    }

    /**
     * 输出两个实体之间的比较结果
     *
     * @param i1 实体1
     * @param i2 实体2
     */
    public void match(Item i1, Item i2) {
        System.out.println(i1 + " vs. " + i2 + ": " + i1.compete(i2));
    }
}
