package edu.hm.cs.swa.mdoerner.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to render/display objects in a human-friendly method.
 * It uses the Renderer-Class as the default renderer, but this option can be
 * overridden by specifying the renderer class using the 'with' parameter. It is
 * used for fields and methods of an object.
 * 
 * @author Martin Doerner
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface RenderMe {
    /**
     * 
     * @return The class ID that will be used to render the annotated element -
     *         class or method. If not explicitly specified and left blank then
     *         use the default Renderer.
     */
    String with() default "";
}
