package dit.url.ast;

import dit.url.*;

public class Text implements Ast
{
    private String value;

    public Text(String value)
    {
        this.value = value;
    }

    public Declaration process(Declaration d)
    {
        d.addText(value);
        return d;
    }
}
