package edu.hm.cs.swa.mdoerner.reflection.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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
}