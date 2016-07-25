package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MyHandlerCache implements HandlerCache
{
    private final DeadboltHandler defaultHandler = new CommonDeadboltHandler();
    private final Map<String, DeadboltHandler> handlers = new HashMap<>();

    public MyHandlerCache()
    {
        handlers.put(defaultHandler.handlerName(), defaultHandler);
    }

    @Override
    public DeadboltHandler apply(final String key)
    {
        return handlers.get(key);
    }

    @Override
    public DeadboltHandler get()
    {
        return defaultHandler;
    }
}
