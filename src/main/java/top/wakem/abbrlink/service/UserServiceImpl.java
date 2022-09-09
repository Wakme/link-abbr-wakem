package top.wakem.abbrlink.service;

import lombok.Data;

import java.io.*;

public class UserServiceImpl {


    @Data
    static class A implements Cloneable, Serializable {
        B b = new B();
        String a = "a";

        @Override public Object clone () throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Data
    static class B implements Serializable{
        String b = "b";
    }

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tmp"));
        A a = new A();
        oos.writeObject(a);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tmp"));
        A a1 = (A) ois.readObject();

        System.out.println(a == a1);
        System.out.println(a.getB() == a1.getB());
    }
}