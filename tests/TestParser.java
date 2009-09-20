import junit.framework.*;

import java.util.*;

import dit.url.*;
import dit.url.ast.*;
import dit.url.parser.*;


public class TestParser extends TestCase
{
    private Parser p;

    public TestParser(String s)
    {
        super(s);
    }

    public void setUp()
    {
        p = new Parser(System.in);
    }

    public void testSingleText() throws ParseException
    {
        p.ReInit("one");

        List<Ast> l = p.UrlDeclaration();

        assertEquals("[AST{value:one}]", l.toString());
    }

    public void testSingleUntypedVariable() throws ParseException
    {
        p.ReInit("$one");

        List<Ast> l = p.UrlDeclaration();

        assertEquals("[AST{name:one,type:class java.lang.String}]", l.toString());
    }

    public void testSingleTypedVariable() throws ParseException
    {
        p.ReInit("$one:boolean");

        List<Ast> l = p.UrlDeclaration();

        assertEquals("[AST{name:one,type:boolean}]", l.toString());
    }

    public void testMultipleText() throws ParseException
    {
        p.ReInit("one/two/3");

        List<Ast> l = p.UrlDeclaration();

        assertEquals("[AST{value:one}, AST{value:two}, AST{value:3}]", l.toString());
    }

    public void testMultipleMixed() throws ParseException
    {
        p.ReInit("/one/$two:int/3/$flag:boolean/");

        List<Ast> l = p.UrlDeclaration();

        assertEquals("[AST{value:one}, AST{name:two,type:int}, AST{value:3}, AST{name:flag,type:boolean}]", l.toString());
    }

    public void testDeclaringTypeForNormalTestShouldFail()
    {
        try {
            p.ReInit("two:int");
            List<Ast> l = p.UrlDeclaration();
            fail("Allowing type definition for normal text");
        } catch (ParseException ex) {
        }
    }
}
