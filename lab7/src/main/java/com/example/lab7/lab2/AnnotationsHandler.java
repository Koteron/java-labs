package com.example.lab7.lab2;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface SampleAnnotation 
{
    int count() default 0;
}

class MyClass
{
    @SampleAnnotation(count = 2)
    private static String method1(String a, float b)
    {
        return "Private method1: " + a + " " + b;
    }
    
    @SampleAnnotation(count = 5)
    private static String method2(double a, double b, boolean d)
    {
        return "Private method2: " + a + " " + b + " " + d;
    }

    protected static String method3(double a, double b, char c)
    {
        return "Protected method3: " + a + " " + b + " " + c;
    }
    
    @SampleAnnotation(count = 3)
    protected static String method4(int a, double b)
    {
        return "Protected method4: " + a + " " + b;
    }
    
    @SampleAnnotation
    public static String method5(double a, byte b, String m)
    {
        return "Public method5: " + a + " " + b + " " + m;
    }
    
    @SampleAnnotation(count = 4)
    public static String method6(double a, double b)
    {
        return "Public method6: " + a + " " + b;
    }
}

public class AnnotationsHandler
{
    public static String executeMyClassMethods(Method method) throws IllegalAccessException, InvocationTargetException
    {
        StringBuilder output = new StringBuilder();
        if (method.isAnnotationPresent(SampleAnnotation.class) && 
        (Modifier.isPrivate(method.getModifiers()) || Modifier.isProtected(method.getModifiers())))
        {
            int count = method.getAnnotation(SampleAnnotation.class).count();
            boolean wasAccessible = true;
            if (!Modifier.isPublic(method.getModifiers()))
            {
                method.setAccessible(true);
                wasAccessible = false;
            }
            while (count > 0)
            {
                Object[] parameters = getParametersForMethod(method);
                output.append(method.invoke(null, parameters) + "\n");
                --count;
            }
            if (!wasAccessible)
            {
                method.setAccessible(false);
            }
        }
        else
        {
            output.append("Method " + method.getName() + " doesn't have the annotation");
        }
        output.append("\n");
        return output.toString();
    }

    public static Object[] getParametersForMethod(Method method) 
    {
        Object[] parameters = new Object[method.getParameterCount()];
        for (int i = 0; i < parameters.length; ++i)
        {
            if (method.getParameters()[i].getType().equals(int.class)) 
            {
                parameters[i] = ThreadLocalRandom.current().nextInt();
            }
            else if (method.getParameters()[i].getType().equals(double.class)) 
            {
                parameters[i] = ThreadLocalRandom.current().nextDouble();
            }
            else if (method.getParameters()[i].getType().equals(boolean.class)) 
            {
                parameters[i] = ThreadLocalRandom.current().nextBoolean();
            }
            else if (method.getParameters()[i].getType().equals(char.class))
            {
                parameters[i] = (char) (ThreadLocalRandom.current().nextInt(65, 91));
            }
            else if (method.getParameters()[i].getType().equals(float.class)) 
            {
                parameters[i] = ThreadLocalRandom.current().nextFloat();
            }
            else if (method.getParameters()[i].getType().equals(byte.class)) 
            {
                parameters[i] = (byte) ThreadLocalRandom.current().nextInt(-128, 127);
            }
            else
            {
                parameters[i] = "Some String";
            }
        }
        return parameters;
    }

    public static String start()
    {
        StringBuilder output = new StringBuilder();
        Class<MyClass> cl = MyClass.class;
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods)
        {
            try
            {  
                output.append(executeMyClassMethods(method));
            }
            catch(NullPointerException | IllegalAccessException | InvocationTargetException e)
            {
                output.append(e.getMessage());
            }   
        }
        return output.toString();
    }
}