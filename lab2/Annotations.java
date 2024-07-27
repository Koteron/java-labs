import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

class MyClass
{
    @SampleAnnotation(2)
    private static void method1(String a, float b)
    {
        System.out.println("Private method1: " + a + " " + b);
    }
    
    @SampleAnnotation(5)
    private static void method2(double a, double b, boolean d)
    {
        System.out.println("Private method2: " + a + " " + b + " " + d);
    }

    protected static void method3(double a, double b, char c)
    {
        System.out.println("Protected method3: " + a + " " + b + " " + c);
    }
    
    @SampleAnnotation(3)
    protected static void method4(int a, double b)
    {
        System.out.println("Protected method4: " + a + " " + b);
    }
    
    @SampleAnnotation
    public static void method5(double a, byte b, String m)
    {
        System.out.println("Public method5: " + a + " " + b + " " + m);
    }
    
    @SampleAnnotation(4)
    public static void method6(double a, double b)
    {
        System.out.println("Public method6: " + a + " " + b);
    }
}

public class Annotations
{
    public static void executeMyClassMethods(Method method) throws IllegalAccessException, InvocationTargetException
    {
        if (method.isAnnotationPresent(SampleAnnotation.class) && 
        (Modifier.isPrivate(method.getModifiers()) || Modifier.isProtected(method.getModifiers())))
        {
            int count = method.getAnnotation(SampleAnnotation.class).value();
            method.setAccessible(true);
            try
            {
                while (count > 0)
                {
                    Object[] parameters = getParametersForMethod(method);
                    method.invoke(null, parameters);
                    --count;
                }
            }
            finally
            {
                method.setAccessible(false);
            }
        }
        else
        {
            System.out.println("Method " + method.getName() + " doesn't have the annotation");
        }
        System.out.println("\n");
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

    public static void main(String[] args) 
    {
        Class<MyClass> cl = MyClass.class;
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods)
        {
            try
            {  
                executeMyClassMethods(method);
            }
            catch(NullPointerException | IllegalAccessException | InvocationTargetException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}