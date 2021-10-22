package com.epam.asyncstub;

public class Class3 {

    private boolean b;

    public void method1() {
        if (b) {
            System.out.println("First if " + this.getClass() + " " + new RuntimeException().getStackTrace()[0].getMethodName());
        } else {
            System.out.println("Second if " + this.getClass() + " " + new RuntimeException().getStackTrace()[0].getMethodName());
        }
    }

    public void method2() {
        if (true) {
            System.out.println("First if " + this.getClass() + " " + new RuntimeException().getStackTrace()[0].getMethodName());
        } else {
            System.out.println("Second if " + this.getClass() + " " + new RuntimeException().getStackTrace()[0].getMethodName());
        }
    }

    public void method3() {
        if (!b) {
            System.out.println("First if " + this.getClass() + " " + new RuntimeException().getStackTrace()[0].getMethodName());
        } else {
            System.out.println("Second if " + this.getClass() + " " + new RuntimeException().getStackTrace()[0].getMethodName());
        }
    }
}
