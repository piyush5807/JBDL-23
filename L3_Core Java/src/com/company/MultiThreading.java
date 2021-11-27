package com.company;

public class MultiThreading {

    public static void main(String[] args) {

        System.out.println("In main function, thread is " +
                Thread.currentThread().getName());

//        testFunc(100);

//        System.out.println("Invoking a new thread....");
        MyThread thread = new MultiThreading().new MyThread();
        thread.start();
//
//        for(int i = 0; i < 1000; i++){
//            Math.pow(i, 2);
//        }
//
//        System.out.println("New thread is started .....");

        MyThreadUsingRunnable myThreadUsingRunnable = new MyThreadUsingRunnable();
        Thread thread2 = new Thread(myThreadUsingRunnable);
        thread2.run();

//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println(Runtime.getRuntime().freeMemory());
//        System.out.println(Runtime.getRuntime().totalMemory());

    }

    private class MyThread extends Thread{

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("In run function, thread is " + Thread.currentThread().getName());
        }
    }

    private static class MyThreadUsingRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("In run function of MyThreadUsingRunnable class, thread is " +
                    Thread.currentThread().getName());
        }
    }

//    public static void testFunc(int num){
//
//        int ans = num * 10;
//        System.out.println("ans is " + ans);
//        System.out.println("In test func, thread is " + Thread.currentThread().getName());
//    }
}
