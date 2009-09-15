import junit.framework.*;

import dit.url.util.*;

public class TestValueTuple extends TestCase
{
    public TestValueTuple(String s)
    {
        super(s);
    }

    public void testValidStringDeclaration()
    {
        ValueTuple vt = new ValueTuple("A String", String.class);

        assertEquals(String.class, vt.getType());
        assertEquals("A String", (String) vt.getValue());
    }

    public void testValidIntegerDeclaration()
    {
        ValueTuple vt = new ValueTuple("555", Integer.class);

        assertEquals(Integer.class, vt.getType());
        assertTrue(555 == (Integer) vt.getValue());
    }

    public void testInvalidTypeDeclaration()
    {
        try {
            ValueTuple vt = new ValueTuple("A String", Integer.class);
            fail("'A String' is not a valid integer value");
        } catch (NumberFormatException ex) {
        }
    }

}
