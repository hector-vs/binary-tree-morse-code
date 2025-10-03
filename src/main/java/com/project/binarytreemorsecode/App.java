package com.project.binarytreemorsecode;

import lists.Treee;

public class App {
    public static void main(String[] args) throws Exception {
        Treee t = new Treee();
        t.insert("A", "._.");
        t.insert("B", "._._");
        t.insert("C", ".._");
        t.insert("D", "__.");

        String code = t.encode("BABA");
        System.out.println(code);

        String codeToDecode = t.decode("._._ ._. ._._ ._.");
        System.out.println(codeToDecode);
    }
}
