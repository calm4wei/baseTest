package cn.base.reflect;

/**
 * Created by fengwei on 17/3/17.
 */
public class StaticSingle {

    private StaticSingle() {
    }

    public static StaticSingle getInstance() {
        return SingleHandle.instance;
    }

    static class SingleHandle {
        private static final StaticSingle instance = new StaticSingle();
    }


}
