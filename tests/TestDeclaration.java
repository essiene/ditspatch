import junit.framework.*;


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


}

