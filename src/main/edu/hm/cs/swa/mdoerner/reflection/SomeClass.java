package edu.hm.cs.swa.mdoerner.reflection;

import java.util.Date;

/**
 * Simple class to check if the Renderer is working.
 * 
 * @author Example given - SWA SomSem17
 *
 */
public class SomeClass {
    @RenderMe
    private int foo;
    @RenderMe(with = "edu.hm.cs.swa.mdoerner.reflection.ArrayRenderer")
    private final int[] array = { 1, 2, 3, };
    
    @RenderMe
    private final Date date = new Date(123456789);

    /**
     * Constructor with an integer as parameter to render later.
     * 
     * @param foo
     *            - Integer that is saved in this object and should be rendered.
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }
}
