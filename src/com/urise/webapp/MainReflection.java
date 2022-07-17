package com.urise.webapp;

import com.urise.webapp.model.Resume;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        Resume r = new Resume("newName");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        System.out.println(r);
        field.set(r, "new_uuid");

//        Выводит массив методов класса. Ищем toString() - индекс  2
        Class<Resume> mClassObject = Resume.class;
        Method[] methods = mClassObject.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(i + " " + methods[i]);
        }
        System.out.println();
        System.out.println(mClassObject.getMethods()[2].invoke(r));
        System.out.println();
// вариант коммита
        Class<? extends Resume> resumeClass = r.getClass();
        Method method = resumeClass.getMethod("toString");
        String result = (String) method.invoke(r);
        System.out.println(result);

    }
}
