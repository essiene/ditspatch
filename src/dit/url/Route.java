package dit.url;

import java.util.*;
import java.util.regex.*;

import dit.url.util.*;


public class Route
{
    Declaration declaration;
    Pattern pattern;

    public Route(String urlDeclaration)
    {
        this(Declaration.fromString(urlDeclaration));
    }

    public Route(Declaration d)
    {
        this.declaration = d;
        pattern = Pattern.compile(d.getPattern());
    }


    public boolean matches(String url)
    {
        Matcher m = getMatcher(url);
        return m.matches();
    }

    public MatchedUrl matchUrl(String url)
    {
        url = Util.trim(url, '/');
        Matcher m = getMatcher(url);

        if(!m.matches()) {
            throw new IllegalArgumentException("Url does not match: " + url);
        }

        LinkedHashMap<String, ValueTuple> map = new LinkedHashMap();

        for(int i = 1; i <= m.groupCount(); i++) {

            String value = m.group(i);
            String name = this.declaration.getGroupName(i);
            Class type = this.declaration.getVariableType(name);

            map.put(name, new ValueTuple(value, type));
        }

        return new MatchedUrl(map);
    }

    private Matcher getMatcher(String url)
    {
        url = Util.trim(url, '/');
        return this.pattern.matcher(url);
    }
}
