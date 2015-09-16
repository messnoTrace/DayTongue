package notrace.daytongue.xmlutils;


public class DomParseException extends RuntimeException
{

    /**
     * serialVersionUID
     * long
     */
    private static final long serialVersionUID = 4964045225307295010L;

    public DomParseException()
    {
        super();
    }

    public DomParseException(String message)
    {
        super(message);
    }

    public DomParseException(String message, Throwable e)
    {
        super(message, e);
    }

}
