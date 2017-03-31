package cn.base.test;

class VariantTest {
    private static int staticVar = 0;
    private int instanceVar = 0;

    public VariantTest() {
        staticVar++;
        instanceVar++;
        System.out.println("staticVar=" + staticVar + ",instanceVar=" + instanceVar);
    }
}
