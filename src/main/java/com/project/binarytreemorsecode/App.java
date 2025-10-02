package com.project.binarytreemorsecode;

import lists.Treee;

public class App {
    public static void main(String[] args) throws Exception {
        Treee t = new Treee();
        t.insert("A", "___.");
        t.insert("B", "._._");
        t.insert("C", ".._");
        t.insert("D", "._._...");

        String code = t.encode("ABCD");
        System.out.println(code);
    }
}
