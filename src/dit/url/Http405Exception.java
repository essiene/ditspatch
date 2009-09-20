package dit.url;

public class Http405Exception extends HttpException
{
    public Http405Exception(String method)
    {
        super(405, "Method not supported - " + method);
    }
}
