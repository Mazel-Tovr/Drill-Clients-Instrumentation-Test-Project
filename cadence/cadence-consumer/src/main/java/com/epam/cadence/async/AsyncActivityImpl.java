package com.epam.cadence.async;

import com.epam.asyncstub.*;

public class AsyncActivityImpl implements AsyncActivity {
    @Override
    public String invokeAsyncActivity(Integer clazz, Integer method) {
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
        return "Async invoke class:" + clazz + " method:" + method;
    }
}
