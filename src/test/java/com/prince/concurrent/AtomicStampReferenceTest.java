package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/26 22:32
 */

public class AtomicStampReferenceTest {
    @Test
    public void test() {
        AtomicStampedReference<Integer> as = new AtomicStampedReference<Integer>(11, 0);
        Integer reference = as.getReference();
        System.out.println(reference);
        int stamp = as.getStamp();
        System.out.println(stamp);
    }
}
