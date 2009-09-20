package dit.url;

import java.util.*;
import java.lang.reflect.*;

import javax.servlet.http.*;

public class Router
{
    private List<RouteEntry> routes;

    public Router()
    {
        routes = new LinkedList();
    }

    public void addRoute(String declaration, Class target)
    {
        RouteEntry re = new RouteEntry(new Route(declaration), target);
        this.routes.add(re);
    }

    public HttpServletResponse dispatch(HttpServletRequest r)
    {
        String url = r.getServletPath();
        RouteEntry re = findEntry(url);


        Class target = re.getTarget();
        String methodName = r.getMethod();

        MatchedUrl mu = re.getRoute().matchUrl(url);

        List<Class> types = mu.getTypes();
        types.add(0, HttpServletRequest.class);

        List<Object> values = mu.getValues();
        values.add(0, r);

        return (HttpServletResponse) dispatch(target, methodName, types, values);
    }

    public Object dispatch(Class targetClass, String methodName, List<Class> methodParamTypes, List<Object> methodParams)
    {
        try {
            Class[] types = methodParamTypes.toArray(new Class[methodParamTypes.size()]);
            Object[] values = methodParams.toArray(new Object[methodParams.size()]);

            Method method = targetClass.getDeclaredMethod(methodName, types);
            return method.invoke(targetClass.newInstance(), values);
        } catch (NoSuchMethodException ex) {
            throw new Http405Exception(methodName);
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

    public RouteEntry findEntry(String url)
    {
        for(RouteEntry re: this.routes) {
            Route r = re.getRoute();

            if(!r.matches(url)) {
                continue;
            }

            return re;
        }

        throw new Http404Exception(url);
    }
}

class RouteEntry 
{
    private Route route;
    private Class target;

    public RouteEntry(Route route, Class target)
    {
        this.route = route;
        this.target = target;
    }

    public Route getRoute()
    {
        return this.route;
    }

    public Class getTarget()
    {
        return this.target;
    }
}
