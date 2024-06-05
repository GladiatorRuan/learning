package SpringBoot.learning;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        ThreadLocal threadLocal = new ThreadLocal();
        new Thread(() -> {
            threadLocal.set("消炎");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        }, "蓝色").start();

        new Thread(() -> {
            threadLocal.set("姚晨");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
        }, "绿色").start();
    }
}
