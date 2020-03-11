package test;


public class B {


    public static void fun(){
        System.out.println("xx");
    }
    public static void main(String[] args) {
        MyThread run =new MyThread();
        Thread t1 =new Thread(run);
        Thread t2 =new Thread(run);
        Thread t3 =new Thread(run);
        Thread t4 =new Thread(run);
        Thread t5 =new Thread(run);
     //   t1.setDaemon(true);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
      //  run.start();
        System.out.println("我好了");
       // m t1.setName("aaa");
    }
}
