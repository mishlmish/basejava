package com.urise.webapp;

import com.urise.webapp.model.Resume;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws
            InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Resume r = new Resume("newName");
        Field field = r.getClass().getDeclaredFields()[0];
        Field field1 = r.getClass().getDeclaredFields()[1];
        field.setAccessible(true);
        field1.setAccessible(true);
        System.out.println("field.getName() - " + field.getName());
        System.out.println("field1.getName() - " + field1.getName());

        System.out.println("field.get(r) - " + field.get(r));
        System.out.println("field1.get(r) - " + field1.get(r));

        System.out.println("System.out.println(r) - " + r);
        field.set(r, "new_uuid");
        System.out.println("System.out.println(r) - " + r);
        System.out.println();

//        Выводит массив методов класса. Ищем toString() - индекс  2
        Class<Resume> mClassObject = Resume.class;
        Method[] methods = mClassObject.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(i + " " + methods[i]);
        }
        System.out.println();
        System.out.println(mClassObject.getMethods()[6].invoke(r));
        System.out.println();
// вариант коммита
        Class<? extends Resume> resumeClass = r.getClass();
        Method method = resumeClass.getMethod("toString");
        String result = (String) method.invoke(r);
        System.out.println(result);

    }
}
