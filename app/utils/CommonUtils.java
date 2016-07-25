package utils;

import play.Play;

/**
 * Common Utils Class for the App.
 */
public final class CommonUtils {

    /**
     * default Constructor.
     */
    private CommonUtils() {
        super();
    }

    /**
     * Retrieve config value as string.
     *
     * @param key key to retrieve
     * @return key's value.
     */
    public static String getConfigString(final String key) {
        return Play.application().configuration().getString(key);
    }

    /**
     * Retrieve config value as boolean.
     *
     * @param key key to retrieve
     * @return key's value.
     */
    public static boolean getConfigBoolean(final String key) {
        Boolean res = Play.application().configuration().getBoolean(key);
        return (res != null) && res;

    }

    /**
     * Retrieve config value as long.
     *
     * @param key key to retrieve
     * @return key's value.
     */
    public static Long getConfigLong(final String key) {
        return Play.application().configuration().getLong(key);
    }

    /**
     * Retrieve config value as int.
     *
     * @param key key to retrieve
     * @return key's value.
     */
    public static int getConfigInt(final String key) {
        return Play.application().configuration().getInt(key);
    }

}
