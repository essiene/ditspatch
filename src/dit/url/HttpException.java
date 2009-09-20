package dit.url;

public class HttpException extends RuntimeException
{
    private int code;

    public HttpException(int code, String message)
    {
        super(message);
        this.code = code;
    }

    public int getCode()
    {
        return this.code;
    }

    public String getMessage()
    {
        return String.valueOf(this.code) + ": Not found - " + super.getMessage();
    }
}
