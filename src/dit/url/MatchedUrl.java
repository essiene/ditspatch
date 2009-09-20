package dit.url;

import java.util.*;
import java.util.regex.*;

import dit.url.util.*;

public class MatchedUrl
{
    LinkedHashMap<String,ValueTuple> map;

    public MatchedUrl(LinkedHashMap<String,ValueTuple> map)
    {
        this.map = map;
    }

    public String getString(String name)
    {
        return (String) getValue(name);
    }

    public int getInt(String name)
    {
        return (Integer) getValue(name);
    }

    public char getChar(String name)
    {
        return (Character) getValue(name);
    }

    public boolean getBoolean(String name)
    {
        return (Boolean) getValue(name);
    }

    private Object getValue(String name)
    {
        if(!this.map.containsKey(name)) {
            throw new RuntimeException("No such variable defined - " + name);
        }

        ValueTuple vt = this.map.get(name);
        return vt.getValue();
    }
}
