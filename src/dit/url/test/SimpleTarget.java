package dit.url.test;

public class SimpleTarget
{
    public SimpleTarget()
    {
    }

    public String echo(String s)
    {
        return s;
    }

    public String echo(String s, int i, char c)
    {
        return s + ": " + String.valueOf(i) + " - " + String.valueOf(c);
    }
}
