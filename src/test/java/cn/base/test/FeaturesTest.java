package cn.base.test;

import cn.base.reflect.Single;
import cn.base.reflect.SingletonEnum;
import cn.base.reflect.StaticSingle;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created on 2017/2/28
 *
 * @author feng.wei
 */
public class FeaturesTest {


    @Test
    public void singleTest() {
        Single single = Single.getInstance();
        Single s2 = Single.getInstance();
        System.out.println(single);
        System.out.println(s2);
    }

    @Test
    public void singleReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor con = Single.class.getDeclaredConstructor();
        con.setAccessible(true);
        System.out.println("instance....");
        Single s1 = (Single) con.newInstance();
        Single s2 = (Single) con.newInstance();

        Single s3 = Single.getInstance();
        Single s4 = Single.getInstance();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1.equals(s2));
        System.out.println(s3.equals(s4));
        System.out.println(s1.equals(s3));
    }

    @Test
    public void test_static_singleReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor con = StaticSingle.class.getDeclaredConstructor();
        con.setAccessible(true);
        System.out.println("instance....");
        StaticSingle s1 = StaticSingle.getInstance();
        StaticSingle s2 = StaticSingle.getInstance();

        StaticSingle s3 = (StaticSingle) con.newInstance();
        StaticSingle s4 = (StaticSingle) con.newInstance();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
    }


    @Test
    public void test_enum_single() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingletonEnum s1 = SingletonEnum.INSTANCE;

        System.out.println(s1);
    }

    @Test
    public void testCase() {
        /**
         * switch（expr1）中,expr1可以是byte,short,char,int
         */
        byte index = 3;
        // String index = "";
        switch (index) {
            case 1:
                System.out.println("1111");
                break;
            case 2:
                System.out.println("2222");
                break;
            default:
                System.out.println("default...");
                break;
        }
    }

    @Test
    public void testShort() {
        short s1 = 1;
        // 编译器报错：s1 + 1表达式类型变为了int
        // s1 = s1 + 1;
        // += 是java语言规定的运算符，java编译器会对它进行特殊处理
        s1 += 1;
        System.out.println(s1);
    }

    @Test
    public void testChar() {
        char c = '哈';
        System.out.println(c);
        String s = "啊";
        System.out.println(s.getBytes().length);

        int i = 2;
        System.out.println(i * 8);

        i = 2;
        /**
         * 位运算cpu直接支持的，效率最高
         */
        System.out.println(i << 3);
    }

    @Test
    public void testInt() {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int sum = a + b;
        System.out.println("a=" + a + " ,b=" + b + " ,sum=" + (a + b));

    }

    @Test
    public void testStatic() {
        VariantTest variantTest = new VariantTest();
        new VariantTest();
    }

    @Test
    public void testList() {
        Collection collection = new ArrayList();
        collection.add("zhangsan");
        collection.add(10);
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        List linedList = new LinkedList();
        linedList.add("lisi");
        linedList.add("wangwu");
        linedList.add(1);
        for (Object o : linedList) {
            System.out.println(o);
        }
        linedList.remove(0);

    }

    @Test
    public void testSet() {
        Set set = new HashSet();
        set.add("lisi");
        set.add("zhansan");
        set.remove("lisi");
        System.out.println(set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        set.clear();
        System.out.println(set.size());
    }

    @Test
    public void testQueue() {
        Queue queue = new PriorityQueue();
        queue.add(20);
        queue.add(5);
        queue.add(6);
        queue.add(21);
        queue.offer(3);
        System.out.println("size=" + queue.size() + " ," + queue.peek());
        System.out.println(queue.poll());
        System.out.println("size=" + queue.size() + " ," + queue.peek());
    }

    @Test
    public void testArray() {
        int[] arr1 = new int[]{1, 2, 3, 4};
        System.out.println(arr1.length);
        for (int arr : arr1) {
            System.out.print(arr + " ");
        }
        System.out.println();
        int[] arr2 = Arrays.copyOf(arr1, 10);
        System.out.println(arr2.length);
        for (int arr : arr2) {
            System.out.print(arr + " ");
        }
        System.out.println();
        int[] arr3 = Arrays.copyOfRange(arr1, 2, 4);
        System.out.println(arr3.length);
        for (int arr : arr3) {
            System.out.print(arr + " ");
        }
        System.out.println();
        int[] arr4 = new int[8];
        System.arraycopy(arr1, 0, arr4, 1, 2);
        System.out.println(arr4.length);
        for (int arr : arr4) {
            System.out.print(arr + " ");
        }
    }

    @Test
    public void test_single() {
        System.out.println(Single.getInstance());
        System.out.println(Single.getInstance());
    }

}
