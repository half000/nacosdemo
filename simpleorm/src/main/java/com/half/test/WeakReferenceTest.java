package com.half.test;

import com.half.bean.Person;

import java.lang.ref.WeakReference;

/**
 * 弱引用测试
 * gc的时候回被回收
 * 关于引用，都是作用于reference字段，比如Entry是弱引用的子类，key是reference，value不是，则只有key被回收
 *
 * @Author: wangwei
 * @Date: 2020-04-28 10:50
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<Person> str = new WeakReference<>(new Person(1, "jim", 15));
        System.out.println(str.get());
        System.gc();
        System.out.println(str.get());

        Entry entry = new Entry(new ThreadLocal<Integer>(), "ddd");
        System.out.println(entry.get().toString() + " | " + entry.value);
        System.gc();
        System.out.println(entry.get() + " | " + entry.value);

    }

    static class Entry extends WeakReference<ThreadLocal<?>> {

        Object value;

        Entry(ThreadLocal<?> k, Object v) {
            super(k);
            value = v;
        }
    }
}
