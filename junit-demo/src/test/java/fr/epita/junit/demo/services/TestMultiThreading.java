package fr.epita.junit.demo.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMultiThreading {


    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread t = new Thread(r);
//                t.setName("myThread");
//                t.setPriority(Thread.NORM_PRIORITY);
//                return t;
//            }
//        });

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        System.out.println("processors #:" + Runtime.getRuntime().availableProcessors());

        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello " + atomicInteger.getAndAdd(1));
                }
            });
        }
        executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
        System.out.println(atomicInteger);
    }

}
