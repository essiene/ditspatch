package dit.url;

public class Http404Exception extends HttpException
{
    public Http404Exception(String message)
    {
        super(404, message);
    }
}
