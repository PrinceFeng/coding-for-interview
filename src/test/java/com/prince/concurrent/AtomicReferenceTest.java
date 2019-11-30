package com.prince.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description TODO
 * @Author prince Chen
 * @Date 2019/11/26 21:40
 */

public class AtomicReferenceTest {

    @Test
    public void testAtomicReference() {
        Simple prince = new Simple("prince", 24);
        AtomicReference<Simple> af = new AtomicReference<>(prince);
        Simple simple = af.get();
        System.out.println("===>" + simple);

        boolean isSame = af.compareAndSet(prince, new Simple("chen", 27));
        System.out.println(isSame);
        System.out.println("===>" + af.get());

    }


    class Simple {
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
