import junit.framework.*;

import dit.url.*;

public class TestRoute extends TestCase
{
    private Route route;

    public TestRoute(String s)
    {
        super(s);
    }

    public void setUp()
    {
        route = new Route("/user/$username:String/$sex:Character");
    }

    public void testMatchingSuccessfull()
    {

        assertEquals(true, route.matches("/user/bradpitt/m/"));
        assertEquals(true, route.matches("user/sallynox/f"));

    }

    public void testMatchingFails()
    {
        assertEquals(false, route.matches("/muser/bradpitt/f"));
        assertEquals(false, route.matches("/user/bradpitt/50"));
        assertEquals(false, route.matches("/user/m/sallynox"));
   }

   public void testMatchResults()
   {
        MatchedUrl mu = route.matchUrl("/user/bradpitt/m/");

        assertEquals("bradpitt", mu.getString("username"));
        assertTrue('m' == mu.getCharacter("sex"));
   }

   public void testMatchResultsInvalidTypes()
   {

       try { 
           MatchedUrl mu = route.matchUrl("/user/bradpitt/m/"); 
           mu.getCharacter("username"); 
           fail("'$username' is not of type Character");
       } catch (ClassCastException ex) {
       }
   }

}
