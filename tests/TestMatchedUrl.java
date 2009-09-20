import junit.framework.*;

import java.util.*;

import dit.url.*;
import dit.url.util.*;

public class TestMatchedUrl extends TestCase
{
    private MatchedUrl mu;

    public TestMatchedUrl(String s)
    {
        super(s);
    }

    public void setUp()
    {
        LinkedHashMap<String,ValueTuple> map = new LinkedHashMap();

        ValueTuple vt = new ValueTuple("A string value", String.class);
        map.put("stringvar", vt);

        ValueTuple vt1 = new ValueTuple("555", int.class);
        map.put("integerval", vt1);

        ValueTuple vt2 = new ValueTuple("c", char.class);
        map.put("characterval", vt2);

        ValueTuple vt3 = new ValueTuple("false", boolean.class);
        map.put("booleanvar", vt3);

        mu = new MatchedUrl(map);
    }

    public void testGetString()
    {
        assertEquals("A string value", mu.getString("stringvar"));
    }

    public void testGetInteger()
    {
        assertTrue(555 == mu.getInt("integerval"));
    }

    public void testGetCharacter()
    {
        assertTrue('c' == mu.getChar("characterval"));
    }

    public void testGetBoolean()
    {
        assertTrue(false == mu.getBoolean("booleanvar"));
    }

    public void testNoSuchVariable()
    {
        try {
            mu.getBoolean("novar");
            fail("Variable not defined");
        } catch (RuntimeException ex) {

        }
    }

    public void testWrongTypeSpec()
    {
        try {
            mu.getString("booleanvar");
            fail("Variable has wrong type");
        } catch (ClassCastException ex) {

        }
    }

}
