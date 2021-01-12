package Util;

public class Test {

    public void func() {
        Son s = new Son();
        int b = s.a;
    }

}

class Son {
    public int a;

    public Son() {
        a = 1;
    }

}