package dit.url;

import java.util.*;

public class Declaration
{
    private Map<String,Class> variables = new HashMap();
    private Map<Integer, String> groups = new HashMap();
    private String pattern = "";
    private int currentGroup;

    public Declaration()
    {
        this.currentGroup = 0;
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
