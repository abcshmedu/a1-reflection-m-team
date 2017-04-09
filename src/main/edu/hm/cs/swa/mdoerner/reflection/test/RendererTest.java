package edu.hm.cs.swa.mdoerner.reflection.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.hm.cs.swa.mdoerner.reflection.RenderMe;
import edu.hm.cs.swa.mdoerner.reflection.Renderer;
import edu.hm.cs.swa.mdoerner.reflection.SomeClass;

/**
 * Test class for parameterized test of SomeClass.
 * 
 * @author Martin Doerner
 *
 */
@RunWith(Parameterized.class)
public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;

    /**
     * Parameters for the SomeClass test - input and expected output values.
     * 
     * @return List of input and expected output objects.
     */
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, "Instance of edu.hm.cs.swa.mdoerner.reflection.SomeClass:\nfoo (Type int): 0\narray (Type int[]) [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n" },
                { 1, "Instance of edu.hm.cs.swa.mdoerner.reflection.SomeClass:\nfoo (Type int): 1\narray (Type int[]) [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n" },
                { 2, "Instance of edu.hm.cs.swa.mdoerner.reflection.SomeClass:\nfoo (Type int): 2\narray (Type int[]) [1, 2, 3, ]\ndate (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n" } });
    }

    private int inputNumber;
    private String output;

    /**
     * 
     * @param input
     *            - Value for foo-int in SomeClass.
     * @param expected
     *            - String expected as output.
     */
    public RendererTest(int input, String expected) {
        this.inputNumber = input;
        this.output = expected;
        toRender = new SomeClass(inputNumber);
        renderer = new Renderer(toRender);
    }

    /**
     * Tests SomeClass using the parameters mapped to input int and output
     * String.
     */
    @Test
    public void testRenderer() {
        String result = renderer.render();
        assertEquals(this.output, result);
    }

    /**
     * Checks the output of the rendering of a class with a string[] method.
     */
    @Test
    public void testStringArray() {
        StringMethodCheck mc = new StringMethodCheck();
        renderer = new Renderer(mc);
        assertEquals(renderer.render(),
                "Instance of edu.hm.cs.swa.mdoerner.reflection.test.StringMethodCheck:\nstringCheck (Type java.lang.String[]): [hm, edu, ]\n");
    }

    /**
     * Test a method that returns 45 every time.
     */
    @Test
    public void testSomeInt() {
        IntMethodCheck mc = new IntMethodCheck();
        renderer = new Renderer(mc);
        assertEquals(
                "Instance of edu.hm.cs.swa.mdoerner.reflection.test.IntMethodCheck:\nreturnInt (Type int): 45\n",
                renderer.render());
    }
}

/**
 * Class to check a method that returns a string[].
 * 
 * @author Martin Doerner
 *
 */
class StringMethodCheck {
    /**
     * A method that returns the words hm and edu.
     * 
     * @return An string array with constant set values.
     */
    @RenderMe(with = "edu.hm.cs.swa.mdoerner.reflection.ArrayRenderer")
    public String[] stringCheck() {
        String[] res = { "hm", "edu" };
        return res;
    }
}

/**
 * Class that contains one method that always returns 45.
 * 
 *  @author Martin
 *
 */
class IntMethodCheck {
    /**
     * Just some test to see if correct int returned.
     * 
     * @return Constant int 45.
     */
    @RenderMe
    public int returnInt() {
        final int result = 45;
        return result;
    }

}