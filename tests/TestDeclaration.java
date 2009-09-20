import junit.framework.*;

import java.util.regex.*;

import dit.url.*;
import dit.url.parser.*;

public class TestDeclaration extends TestCase
{
    private Parser p;

    public TestDeclaration(String s)
    {
        super(s);
    }

    public void setUp()
    {
        p = new Parser(System.in);
    }

    public void testAddTextInitial()
    {
        Declaration d = new Declaration();
        d.addText("one");

        assertEquals("^one$", d.getPattern());
    }

    public void testAddTextSubsequent()
    {
        Declaration d = new Declaration();
        d.addText("one");
        d.addText("two");

        assertEquals("^one/two$", d.getPattern());

        d.addText("three");
        assertEquals("^one/two/three$", d.getPattern());
    }

    public void testVariablePatternRegexp()
    {
        TestDeclaration.checkPattern(int.class, "123");
        TestDeclaration.checkPattern(char.class, "a");
        TestDeclaration.checkPattern(String.class, "abc");
        TestDeclaration.checkPattern(boolean.class, "true");
        TestDeclaration.checkPattern(boolean.class, "false");
    }

    public void testAddVariableInitial()
    {
        Declaration d = new Declaration();
        d.addVariable("var1", int.class);

        assertEquals(int.class, d.getVariableType("var1"));
        assertEquals("var1", d.getGroupName(1));
        checkPattern(d.getPattern(), "123", 1);
    }

    public void testAddVariableSubsequent()
    {
        Declaration d = new Declaration();
        d.addText("one");
        d.addVariable("var1", int.class);

        checkPattern(d.getPattern(), "one/123", 1);
        checkPattern(d.getPattern(), "one/123", 1);

        d.addVariable("var2", boolean.class);

        assertEquals(boolean.class, d.getVariableType("var2"));
        assertEquals("var2", d.getGroupName(2));
        checkPattern(d.getPattern(), "one/123/false", 2);
    }

    public void testAddAlreadyDefinedVariable()
    {
        Declaration d = new Declaration();
        d.addVariable("var1", int.class);
        try {
            d.addVariable("var1", boolean.class);
            fail("'var1' already defined and should not be added");
        } catch (ScopeException ex) {
        }
    }

    public void testFromStringSimple()
    {
        Declaration d = Declaration.fromString(p, "/one/");
        assertEquals("^one$", d.getPattern());

        d = Declaration.fromString(p, "/$id:int/");
        assertEquals("^(\\d+)$", d.getPattern());
    }

    public void testFromStringComplex()
    {
        Declaration d = Declaration.fromString(p, "/one/two/three");
        assertEquals("^one/two/three$", d.getPattern());

        d = Declaration.fromString(p, "user/$id:int/$activate:boolean/ok");
        assertEquals("^user/(\\d+)/(true|false)/ok$", d.getPattern());
    }

    private static void checkPattern(Class c, String s)
    {
        checkPattern(Declaration.toPattern(c), s, 1);
    }
    
    private static void checkPattern(String pattern, String s, int size)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);
        assertTrue(m.matches());
        assertEquals(size, m.groupCount());
    }

}
