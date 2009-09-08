package dit.url.ast;

import dit.url.*;

public class Variable implements Ast
{
    private String name;
    private Class type;

    public Variable(String name, String type)
    {
        this.name = name;
        
        type = type.trim();
        if(type.equals("Integer")) {
            this.type = Integer.class;
            return;
        } 

        if(type.equals("String")) {
            this.type = String.class;
            return;
        }

        if(type.equals("Character")) {
            this.type = Character.class;
            return;
        }

        if(type.equals("Boolean")) {
            this.type = Boolean.class;
            return;
        }

        throw new IllegalArgumentException("Type is unsupported by Ditspatch: " + type);
    }

    public String getName()
    {
        return this.name;
    }

    public Class getType()
    {
        return this.type;
    }

    public String toString()
    {
        return "AST{name:" + this.name + ",type:" + this.type.toString() + "}";
    }

    public Declaration process(Declaration d)
    {
        d.addVariable(name, type);
        return d;
    }
}
