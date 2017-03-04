package cn.base;

/**
 * Created on 2017/2/28
 *
 * @author feng.wei
 */
public interface MyObject {
}


interface YourObject extends MyObject {

}

abstract class HerObject implements MyObject {

}

abstract class Programmer extends Person {

    public Programmer(String name, Integer age) {
        super(name, age);
    }
}