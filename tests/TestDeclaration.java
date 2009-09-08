import junit.framework.*;

import java.util.regex.*;

import dit.url.*;

public class TestDeclaration extends TestCase
{
    public TestDeclaration(String s)
    {
        super(s);
    }

    public void testAddTextInitial()
    {
        Declaration d = new Declaration();
        d.addText("one");

        assertEquals("one", d.getPattern());
    }

    public void testAddTextSubsequent()
    {
        Declaration d = new Declaration();
        d.addText("one");
        d.addText("two");

        assertEquals("one/two", d.getPattern());

        d.addText("three");
        assertEquals("one/two/three", d.getPattern());
    }

    public void testVariablePatternRegexp()
    {
        TestDeclaration.checkPattern(Integer.class, "123");
        TestDeclaration.checkPattern(Character.class, "a");
        TestDeclaration.checkPattern(String.class, "abc");
        TestDeclaration.checkPattern(Boolean.class, "true");
        TestDeclaration.checkPattern(Boolean.class, "false");
    }

    public void testAddVariableInitial()
    {
        Declaration d = new Declaration();
        d.addVariable("var1", Integer.class);

        assertEquals(Integer.class, d.getVariableType("var1"));
        assertEquals("var1", d.getGroupName(0));
        checkPattern(d.getPattern(), "123", 1);
    }

    public void testAddVariableSubsequent()
    {
        Declaration d = new Declaration();
        d.addText("one");
        d.addVariable("var1", Integer.class);

        checkPattern(d.getPattern(), "one/123", 1);
        checkPattern(d.getPattern(), "one/123", 1);

        d.addVariable("var2", Boolean.class);

        assertEquals(Boolean.class, d.getVariableType("var2"));
        assertEquals("var2", d.getGroupName(1));
        checkPattern(d.getPattern(), "one/123/false", 2);
    }

    public void testAddAlreadyDefinedVariable()
    {
        Declaration d = new Declaration();
        d.addVariable("var1", Integer.class);
        try {
            d.addVariable("var1", Boolean.class);
            fail("'var1' already defined and should not be added");
        } catch (ScopeException ex) {
        }
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
