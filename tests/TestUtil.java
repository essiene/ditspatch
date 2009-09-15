import junit.framework.*;

import dit.url.util.*;


public class TestUtil extends TestCase
{
    public TestUtil(String s)
    {
        super(s);
    }

    public void testTrim() 
    {
        assertEquals("one", Util.trim("///one//////", '/'));
        assertEquals("one/.two\\asdf.foo", Util.trim("...one/.two\\asdf.foo.........", '.'));
    }

}
