package edu.hm.cs.swa.mdoerner.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Custom renderer for objects annotated with the RenderMe tag.
 * 
 * @author Martin Doerner
 *
 */
public class Renderer {
    /**
     * The object that is rendered with this instance of the Renderer.
     */
    private Object object;

    /**
     * Custom constructor. It saves the reference to the object that has to be
     * rendered.
     * 
     * @param object
     *            - Object that will be rendered.
     */
    public Renderer(Object object) {
        this.object = object;
    }

    /**
     * Default empty constructor is disabled, because it doesn't reference the
     * required object for rendering.
     */
    @SuppressWarnings("unused")
    private Renderer() {
    }

    /**
     * Renders the fields and methods of an object referenced when constructing
     * the instance of Renderer.
     * 
     * @return String representation of RenderMe-marked fields and methods.
     */
    public String render() {
        String result = "";
        Class< ? > classRendered = this.object.getClass();
        Field[] fields = classRendered.getDeclaredFields();
        Method[] methods = classRendered.getDeclaredMethods();

        // Print out the first information about the object
        result += "Instance of " + this.object.getClass().getName() + ":\n";

        // Go through the fields and methods
        result += renderFields(fields);
        
        result += renderMethods(methods);

        return result;

    }

    /**
     * Iterates through declared fields of an objects and creates a human
     * readable representation of them.
     * 
     * @param fields
     *            - Fields of the object passed to constructor.
     * @return String representation of RenderMe-marked fields.
     */
    private String renderFields(Field[] fields) {
        String result = "";
        for (Field f : fields) {
            f.setAccessible(true);
            // Skip fields that aren't annotated with RenderMe
            if (f.getAnnotation(RenderMe.class) == null) {
                continue;
            }
            // Check the with-parameter of the annotation
            String withParam = f.getAnnotation(RenderMe.class).with();

            // Check with-param, if empty then use just .toString, if specified
            // then try to invoke it
            if (withParam.isEmpty()) {
                try {
                    result += f.getName() + " (Type " + f.getType().getCanonicalName() + "): "
                            + f.get(this.object).toString() + "\n";
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            } else {
                // Renderer is specified, so it is used instead of the default
                Class< ? > customRendererClass = null;
                try {
                    customRendererClass = Class.forName(withParam);
                } catch (ClassNotFoundException e) {
                    // The custom Renderer class was specified but is not
                    // available
                    e.printStackTrace();
                }

                Object customRenderer = null;
                try {
                    customRenderer = customRendererClass.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }

                Method renderMethod = null;
                String customRenderResult = "";

                try {
                    renderMethod = customRendererClass.getDeclaredMethod("render", f.getType());
                } catch (NoSuchMethodException | SecurityException e) {
                    // The custom renderer does not have a matching render
                    // method or this method is not accessible
                    e.printStackTrace();
                }

                try {
                    customRenderResult = (String) renderMethod.invoke(customRenderer, f.get(this.object));
                } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                result += f.getName() + " (Type " + f.getType().getCanonicalName() + ") " + customRenderResult + "\n";
            }
        }
        return result;
    }

    /**
     * Iterates through declared methods of an objects and creates a human
     * readable representation of them.
     * 
     * @param methods
     *            - Methods of the object passed to constructor.
     * @return String representation of RenderMe-marked methods.
     */
    private String renderMethods(Method[] methods) {
        String result = "";
        // Go through the methods
        for (Method m : methods) {
            // Allow access to method
            m.setAccessible(true);
            // Skip fields that aren't annotated with RenderMe and have
            // parameters - we don't know the right param values here
            if (m.getAnnotation(RenderMe.class) == null || m.getParameterCount() > 0) {
                continue;
            }
            // Check the with-parameter of the annotation
            String withParam = m.getAnnotation(RenderMe.class).with();

            // Check with-param, if empty then use just .toString, if specified
            // then try to invoke it

            // mO saves the object that the method returned
            Object mO = null;
            try {
                mO = m.invoke(this.object);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            // If with() param is empty use default renderer
            if (withParam.isEmpty()) {
                result += m.getName() + " (Type " + m.getReturnType().getTypeName() + "): " + mO + "\n";
            } else {
                // with() parameter specified, try to load the custom renderer
                // and use it for this method
                try {
                    Class< ? > rendererClass = Class.forName(withParam);
                    Object customRenderer = rendererClass.newInstance();
                    Method renderMethod = rendererClass.getDeclaredMethod("render", m.getReturnType());
                    String customRenderResult = (String) renderMethod.invoke(customRenderer, m.invoke(this.object));
                    result += m.getName() + " (Type " + m.getReturnType().getTypeName() + "): " + customRenderResult
                            + "\n";
                } catch (NoSuchMethodException nse) {
                    // no fitting render method found for this type, use
                    // toString
                    result += m.getName() + " (Type " + m.getReturnType().getTypeName() + "): " + mO + "\n";
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException
                        | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }

        }
        return result;
    }

}
