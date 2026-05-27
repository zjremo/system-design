package net.jrz;

import net.jrz.basic.JsonSerializer;
import net.jrz.basic.Writer;
import org.junit.Test;

public class BasicTest {
    @Test
    public void testAnnotation() {
        try {
            Writer writer = new Writer(22, "zjr", "Java Ordinary Road");
            String serialize = JsonSerializer.serialize(writer);
            System.out.println(serialize);
        } catch (IllegalAccessException e) {
            e.printStackTrace(System.out);
        }
    }
}
