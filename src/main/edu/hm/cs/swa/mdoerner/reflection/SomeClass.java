package edu.hm.cs.swa.mdoerner.reflection;

import java.util.*;

/**
 * 
 * @author Martin Doerner based on the example given
 *
 */
public class SomeClass {
    @RenderMe
    private int foo;
    @RenderMe(with = "edu.hm.cs.swa.mdoerner.reflection.ArrayRenderer")
    int[] array = { 1, 2, 3, };
    @RenderMe
    private Date date = new Date(123456789);
    

    public SomeClass(int foo) {
        this.foo = foo;
    }
    
    public SomeClass(String[] array){
        
    }

    @RenderMe
    public int someInt() {
        return 1;
    }

    @RenderMe
    public void someVoid() {
    }

    @RenderMe
    public int[] someIntArray() {
        int[] res = { 3, 1, 4 };
        return res;
    }

    @RenderMe(with = "edu.hm.cs.swa.mdoerner.reflection.ArrayRenderer")
    public int[] someIntArrayRen() {
        int[] res = { 4, 5, 6 };
        return res;
    }

    @RenderMe
    public String[] someStringArray(){
        String[] res = {"hm", "edu"};
        return res;
    }
    
    @RenderMe(with = "edu.hm.cs.swa.mdoerner.reflection.ArrayRenderer")
    public String[] someStringArrayRen(){
        String[] res = {"truck", "bus"};
        return res;
    }
}
