package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class MainFile {
    public static void main(String[] args) throws IOException {
        String filePath = "./.gitignore";
        File file = new File(filePath);

        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());

        System.out.println(dir.list().toString());
        for (String name : dir.list()) {
            System.out.println(name);
        }

//        before java7
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(filePath);
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }

// now try-with-resources
        System.out.println();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] y = fis.readAllBytes();
            System.out.println(Arrays.toString(y));

            System.out.println(fis.read());
            System.out.println(fis.read());
            System.out.println(fis.read());
            byte[] x = fis.readAllBytes();

            System.out.println(Arrays.toString(x));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        File dir1 = new File("./");
        System.out.println(dir1.list().toString());

        File[] files = dir.listFiles();

        for (File file1 : files) {
            if (!file1.isDirectory()) {
                System.out.println(file1.getName());
            }

        }
// Homework to lesson 8 Recursion
        System.out.println();
        System.out.println("Recursion of file tree");
        printTree1(dir);

    }

//    static void printTree(File dir) throws IOException {
//        File[] files = dir.listFiles();
//        int counter = 0;
//
//        for (File file : files) {
//
//            if (file.isDirectory()) {
//                System.out.println("\nDir: " + file.getName());
//                printTree(file);
//            } else {
//                System.out.println("\nfile in Dir: " + dir + " : " + file.getName());
//            }
//
//        }
//    }


    static void printTree1(File dir) throws IOException {
        File[] files = dir.listFiles();

        for (File file : files) {
            System.out.println("\nfile in Dir: " + dir + " : " + file.getName());
        }

        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("\nDir: " + file.getName());
                printTree1(file);
            }

        }
    }

}
