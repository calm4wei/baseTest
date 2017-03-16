package cn.base.io;

import java.io.*;

/**
 * Created by fengwei on 17/3/4.
 */
public class TransientPerson {
    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("transient.txt"));
            Person person = new Person("lisi", 20);
            oos.writeObject(person);
            ois = new ObjectInputStream(new FileInputStream("Transient.txt"));
            Person p = (Person) ois.readObject();
            System.out.println(p.getName() + " , " + p.getAge());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


class Person implements Serializable {
    private String name;
    private transient int age;
//    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}