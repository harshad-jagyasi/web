package com.example.web.configuration;

import java.util.Optional;

public class test {
    public static void main(String[] args) {
        String a = null;
        Optional<String> b = Optional.empty();
        try{
            System.out.println(a.length());
            System.out.println(b.orElse("").length());
        }
        catch (Exception e)
        {
            System.out.println(a);
        }
        finally {
            a = "String";
            System.out.println(a.length());
            b = Optional.ofNullable("");
            System.out.println(b.get().length());
        }
    }
}
