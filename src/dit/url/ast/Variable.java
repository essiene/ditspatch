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
        if(type.equals("int")) {
            this.type = int.class;
            return;
        } 

        if(type.equals("String")) {
            this.type = String.class;
            return;
        }

        if(type.equals("char")) {
            this.type = char.class;
            return;
        }

        if(type.equals("boolean")) {
            this.type = boolean.class;
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
