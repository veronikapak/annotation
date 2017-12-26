package com.company;

/**
 * Created by Gran1 on 24/12/2017.
 */
public class TestClass {

    @SetUp
    public static void setUp() {
        System.out.println("setUp");
    }

    @Test (isEnable = false)
    public static void sum(){
        int a = 5;
        int b = 7;
        System.out.println(a+b);
    }

    @Test (isEnable = true)
    @ExeptionAnn(exceptionType = RuntimeException.class)
    public static void print(){
        System.out.println("Print text");
        throw new RuntimeException("Run exeption");
    }

    @Test
//    @ExeptionAnn
    public static void mult(){
        int a=5;
        int b = 2;
        System.out.println(a+b);
       //System.out.println(new RuntimeException());
        //throw new RuntimeException("Run exeption");
    }



    @Destroy
    public static void destroy() {
        System.out.println("destroy");
    }

    @Before
    public static void before() {
        System.out.println("before");
    }

    @After
    public static void after() {
        System.out.println("after");
    }
}
