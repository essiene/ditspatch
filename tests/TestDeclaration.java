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

    public static void checkPattern(Class c, String s)
    {
        Pattern p = Pattern.compile(Declaration.toPattern(c));
        Matcher m = p.matcher(s);
        assertTrue(m.matches());
        assertEquals(1, m.groupCount());
    }
}
