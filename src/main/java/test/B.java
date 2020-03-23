package test;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class B {


    public static void fun(){
        System.out.println("xx");
    }
    public static void main(String[] args) {
       // HashMap hashMap =new HashMap();
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        hashMap.put(1,1);
    }
}
