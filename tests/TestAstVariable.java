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
        Variable v = new Variable("var1", "int");
        assertEquals("var1", v.getName());
        assertEquals(int.class, v.getType());

        v = new Variable("var1", "String");
        assertEquals(String.class, v.getType());

        v = new Variable("var1", "char");
        assertEquals(char.class, v.getType());

        v = new Variable("var1", "boolean");
        assertEquals(boolean.class, v.getType());
    }

    public void testConstructorWithWrongType()
    {
        try { 
            Variable v = new Variable("var1", "float");
            fail("'float' should not be supported");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testProcess()
    {
        Declaration d = new Declaration();
        Text t = new Text("one");
        Variable v = new Variable("var1", "int");

        d = t.process(d);
        d = v.process(d);

        assertEquals("^one/(\\d+)$", d.getPattern());
    }
}
