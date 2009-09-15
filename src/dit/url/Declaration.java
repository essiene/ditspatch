package dit.url;

import java.util.*;

import dit.url.ast.*;
import dit.url.parser.*;

public class Declaration
{
    private Map<String,Class> variables = new HashMap();
    private Map<Integer, String> groups = new HashMap();
    private String pattern = "";
    private int currentGroup;

    public static Declaration fromString(String s)
    {
        Parser p = new Parser(System.in);
        return fromString(p, s);
    }

    public static Declaration fromString(Parser p, String s)
    {

        try {
            p.ReInit(s); 
            List<Ast> l = p.UrlDeclaration();

            Declaration d = new Declaration();
            for(Ast ast: l) {
                d = ast.process(d);
            }

            return d;
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Declaration()
    {
        //Regex group indexes effectively start from 1 as
        //index 0 is the entire pattern itself
        this.currentGroup = 1;
    }

    public void addText(String s)
    {
        this.addPattern(s);
    }

    public void addVariable(String name, Class type)
    {
        if(hasVariable(name)) {
            throw new ScopeException(name);
        }

        this.variables.put(name, type);

        this.groups.put(currentGroup, name);
        this.currentGroup++;
        
        this.addPattern(type);
    }

    public String getPattern()
    {
        return this.pattern;
    }

    public Class getVariableType(String s)
    {
        return this.variables.get(s);
    }

    public String getGroupName(int i)
    {
        return this.groups.get(i);
    }

    public static String toPattern(Class c)
    {
        if(c == Integer.class) {
            return "(\\d+)";
        }

        if(c == String.class) {
            return "(\\w+)";
        }

        if(c == Character.class) {
            return "(.)";
        }

        if(c == Boolean.class) {
            return "(true|false)";
        }

        return "";
    }

    private void addPattern(String s)
    {
        if(this.pattern.equals("")) {
            this.pattern += "^" + s + "$";
        } else {
            this.pattern = this.pattern.substring(0, this.pattern.length() - 1); //remove terminating $ sign
            this.pattern += "/";
            this.pattern += s + "$";
        }
    }

    private boolean hasVariable(String s)
    {
        return this.variables.containsKey(s);
    }

    private void addPattern(Class c)
    {
        addPattern(Declaration.toPattern(c));
    }
}
