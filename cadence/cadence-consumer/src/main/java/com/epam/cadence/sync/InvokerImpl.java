package com.epam.cadence.sync;

import com.epam.stub.*;

public class InvokerImpl implements Invoker {

    @Override
    public void invoke(Integer clazz, Integer method) {
        //method to invoke
        switch (clazz) {
            case 1:
                switch (method) {
                    case 1:
                        new Class1().method1();
                        break;
                    case 2:
                        new Class1().method2();
                        break;
                    case 3:
                        new Class1().method3();
                        break;
                    default:
                        System.out.println("do nothing");
                }
                break;
            case 2:
                switch (method) {
                    case 1:
                        new Class2().method1();
                        break;
                    case 2:
                        new Class2().method2();
                        break;
                    case 3:
                        new Class2().method3();
                        break;
                    default:
                        System.out.println("do nothing");
                }
                break;
            case 3:
                switch (method) {
                    case 1:
                        new Class3().method1();
                        break;
                    case 2:
                        new Class3().method2();
                        break;
                    case 3:
                        new Class3().method3();
                        break;
                    default:
                        System.out.println("do nothing");
                }
                break;
            default:
                System.out.println("do nothing");
        }
    }
}
