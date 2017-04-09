package edu.hm.cs.swa.mdoerner.reflection;

/**
 * Class for rendering Arrays marked with the RenderMe-Annotation.
 * 
 * @author Martin Doerner
 *
 */
public class ArrayRenderer {
    /**
     * Renders an int-array into a string.
     * 
     * @param array
     *            The int-array that has to be rendered.
     * @return The string representation of this array.
     */
    public String render(int[] array) {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            result += array[i] + ", ";
        }
        result += "]";
        return result;
    }

    /**
     * Renders a String-array into a string.
     * 
     * @param array
     *            The String-array that has to be rendered.
     * @return The string representation of this array.
     */
    public String render(String[] array) {
        String result = "[";
        for (int i = 0; i < array.length; i++) {
            result += array[i] + ", ";
        }
        result += "]";
        return result;
    }
}
