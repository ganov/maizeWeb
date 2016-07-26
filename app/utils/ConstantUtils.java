package utils;

/**
 * Common Utils Class for the App.
 */
public final class ConstantUtils {

    /**
     * Application's Current Version.
     */
    public static final String APP_ADM_VERSION = CommonUtils.getConfigString("appAdmin.version");

    /**
     * Application's name.
     */
    public static final String APP_ADM_NAME = CommonUtils.getConfigString("appAdmin.name");

    /**
     * User Key for cache and session.
     */
    public static final String EMAIL = "email";

    /**
     * UUID Key for cache.
     */
    public static final String UUID = "uuid";

    /**
     * Default constructor.
     */
    private ConstantUtils() {
        super();
    }
}