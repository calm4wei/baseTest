package cn.base.test;

public class VariantTest {
    public static int staticVar = 0;
    public int instanceVar = 0;

    public VariantTest() {
        staticVar++;
        instanceVar++;
        System.out.println("staticVar=" + staticVar + ",instanceVar=" + instanceVar);
    }
}
