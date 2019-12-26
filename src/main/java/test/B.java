package test;

public class B extends A{
    private int xxx;
    public static void main(String[] args) {
        B b = new B();
        B.b=3;
        System.out.println(B.b);
    }
}
