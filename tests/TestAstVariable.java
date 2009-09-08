import junit.framework.*;

import dit.url.*;
import dit.url.ast.*;

public class TestAstVariable extends TestCase
{
    public TestAstVariable(String s)
    {
        super(s);
    }

    public void testConstructor()
    {
        Variable v = new Variable("var1", "Integer");
        assertEquals("var1", v.getName());
        assertEquals(Integer.class, v.getType());

        v = new Variable("var1", "String");
        assertEquals(String.class, v.getType());

        v = new Variable("var1", "Character");
        assertEquals(Character.class, v.getType());

        v = new Variable("var1", "Boolean");
        assertEquals(Boolean.class, v.getType());
    }

    public void testConstructorWithWrongType()
    {
        try { 
            Variable v = new Variable("var1", "Float");
            fail("Float should not be supported");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testProcess()
    {
        Declaration d = new Declaration();
        Text t = new Text("one");
        Variable v = new Variable("var1", "Integer");

        d = t.process(d);
        d = v.process(d);

        assertEquals("^one/(\\d+)$", d.getPattern());
    }
}
