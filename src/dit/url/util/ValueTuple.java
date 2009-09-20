package dit.url.util;

public class ValueTuple
{
    private Object value;
    private Class type;

    public ValueTuple(String value, Class type)
    {
        this.value = checkType(type, value);
        this.type = type;
    }

    public Object getValue()
    {
        return this.value;
    }

    public Class getType()
    {
        return this.type;
    }

    private Object checkType(Class type, String value)
    {
        if(type == String.class) {
            return value;
        }

        if(type == int.class) {
            return new Integer(value);
        }

        if(type == char.class) {
            if(value.length() > 1) {
                throw new ClassCastException("Expected a char, but got - '" + value + "'");
            }

            return new Character(value.charAt(0));
        }

        if(type == boolean.class) {
            return new Boolean(value);
        }

        throw new IllegalArgumentException("Unsupported type - " + type.toString());
    }
}
