package test;

import javax.sound.midi.Soundbank;

public class C implements itf1{
    @Override
    public void impl1() {

    }

    @Override
    public void impl2() {

    }

    private static int inc(A x){ x.setA(11);
        return 1;}
    public static void main(String[] args) {
        A a = new A(10);

        A x = new A(10);
        inc(x);
        System.out.println(x.getA());
    }
}
