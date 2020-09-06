package ru.job4j.shildt.io.g20;

import java.io.File;
import java.io.FilenameFilter;

public class DirListOnly {
    public static void main(String[] args) {
        String dirname = "c:\\projects\\job4j_junior";
        File f1 = new File(dirname);
        FilenameFilter only = new OnlyExt("dat");
        String s[] = f1.list(only);

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}
