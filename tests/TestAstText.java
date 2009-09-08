import junit.framework.*;

import dit.url.*;
import dit.url.ast.*;

public class TestAstText extends TestCase
{
    public TestAstText(String s)
    {
        super(s);
    }

    public void testProcess()
    {
        Declaration d = new Declaration();

        Text t = new Text("one");
        d = t.process(d);

        t = new Text("two");
        d = t.process(d);

        assertEquals("one/two", d.getPattern());
    }
}
