package dit.url;

public class ScopeException extends RuntimeException
{
    public ScopeException(String s)
    {
        super("Variable already defined in this declaration: " + s);
    }
}
