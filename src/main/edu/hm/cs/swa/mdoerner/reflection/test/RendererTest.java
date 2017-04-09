package edu.hm.cs.swa.mdoerner.reflection.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.hm.cs.swa.mdoerner.reflection.Renderer;
import edu.hm.cs.swa.mdoerner.reflection.SomeClass;

@RunWith(Parameterized.class)
public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;

    @Before
    public void setUp() {
        toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
    }

    @Test
    public void testRendering() throws Exception {
        assertEquals(
                "Instance of edu.hm.cs.swa.mdoerner.reflection.SomeClass:\n"
                        + "foo (Type int): 5\narray (Type int[]) [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n",
                renderer.render());
    }
}