package test;

public class B extends A{
    private int b;
    public B(){
        super(4);
        b = 3;
    }
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.getA());
    }

}
