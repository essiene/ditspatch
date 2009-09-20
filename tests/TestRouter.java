import junit.framework.*;

import java.util.*;

import dit.url.*;
import dit.url.test.*;

public class TestRouter extends TestCase
{
    private Router router;


    public TestRouter(String s)
    {
        super(s);
    }

    public void setUp()
    {
        router = new Router();
        router.addRoute("/echo/$statement:String/", SimpleTarget.class);
    }

    public void testFindExistingRoute()
    {
        router.findEntry("/echo/A_statement_to_echo");
    }

    public void testFindNonExistingRoute()
    {
        try { 
            router.findEntry("/echo/foo/A_statement_to_echo"); 
            fail("Route '/echo/foo/' does not exist");
        } catch (Exception ex) {
            assertEquals(Http404Exception.class, ex.getClass());
        }
    }

    public void testDispatchSingleParameterMethod()
    {
        List<Class> types = new LinkedList();
        types.add(String.class);

        List<Object> values = new LinkedList();
        values.add("One value to echo");

        String s = (String) router.dispatch(SimpleTarget.class, "echo", types, values);

        assertEquals("One value to echo", s);
    }

    public void testDispatchOverloadedMethod()
    {
        List<Class> types = new LinkedList();
        types.add(String.class);
        types.add(int.class);
        types.add(char.class);

        List<Object> values = new LinkedList();
        values.add("One value to echo");
        values.add(5);
        values.add('b');

        String s = (String) router.dispatch(SimpleTarget.class, "echo", types, values);

        assertEquals("One value to echo: 5 - b", s);
    }
}
