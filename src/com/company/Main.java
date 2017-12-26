package com.company;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] methods = TestClass.class.getDeclaredMethods();
        ArrayList<String> passed = new ArrayList();
        ArrayList<String> failed = new ArrayList();
        ArrayList<String> skipped = new ArrayList();

        Map<String, ArrayList<String>> map = new HashMap<>();
        getMethodByAnnotation(SetUp.class);
        for (Method m: methods) {


                if (m.getAnnotation(Test.class) != null && m.getAnnotation(Test.class).isEnable()) {
                    try {
                        getMethodByAnnotation(Before.class);
                        m.invoke(TestClass.class);
                        getMethodByAnnotation(After.class);
                        passed.add(m.getName());
                        map.put("passed", passed);

                    } catch (Exception e) {
                        if (m.getAnnotation(ExeptionAnn.class) != null
                                && m.getAnnotation(ExeptionAnn.class).exceptionType().isInstance(e.getCause())) {
                            passed.add(m.getName());
                            getMethodByAnnotation(After.class);
                            map.put("passed", passed);
                        } else {
                            failed.add(m.getName());
                            map.put("failed", failed);
                        }
                    }
                }
                if (m.getAnnotation(Test.class) != null && !(m.getAnnotation(Test.class).isEnable())) {
                    skipped.add(m.getName());
                    map.put("skipped", skipped);
                }


            /*if(m.getAnnotation(Destroy.class)!=null){
                m.invoke(TestClass.class);
            }*/
        }
        //System.out.println(map);
        getMethodByAnnotation(Destroy.class);

    }

    private static void getMethodByAnnotation(Class tClass) throws InvocationTargetException, IllegalAccessException {
        Method[] listMethod = TestClass.class.getDeclaredMethods();

        for (Method m:listMethod) {
            //if(m.isAnnotationPresent(tClass)==true){
            if (m.getAnnotation(tClass) != null){
                m.invoke(TestClass.class);
                break;
            }
        }

    }
}
