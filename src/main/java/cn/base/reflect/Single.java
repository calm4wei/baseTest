package cn.base.reflect;

/**
 * Created on 2017/2/28
 * 单例模式
 *
 * @author feng.wei
 */
public class Single {

    private static Single instance = null;
    private static boolean flag = false;

    private Single() {
        System.out.println("start...");
        /**
         * 防止反射破解
         */
        if (flag) {
            flag = !flag;
        } else {
            try {
                throw new Exception("duplicate instance create error! " + Single.class.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 懒汉模式
     *
     * @return
     */
    public static Single getInstance() {


        if (null == instance) {
            synchronized (Single.class) {
                if (null == instance) {
                    flag = !flag;
                    instance = new Single();
                }
            }
        }
        return instance;
    }


}
