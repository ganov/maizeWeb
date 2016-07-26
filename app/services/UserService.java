package services;

import models.User;
import org.apache.commons.lang3.StringUtils;
import play.cache.CacheApi;
import play.mvc.Http;
import utils.ConstantUtils;
import utils.PasswordUtils;

import java.util.Arrays;

/**
 * Services to deal with User things.
 */
public class UserService {

    /**
     * authenticate : is submitted login/password match to an unique user.
     * @param email the email submited in form
     * @param clearPassword the Password submited in form
     * @return the user if authenticate performed, null otherwise
     * @throws Exception
     */
    public static User authenticate(final String email, final String clearPassword, final CacheApi cache) throws Exception {
        // get the user with email only to keep the salt password
        User user = User.findByEmail(email);
        if (user != null) {
            if (Arrays.equals(user.getPassword(), PasswordUtils.getSha512(clearPassword))) {
                return putUserInCache(user, cache);
            }
        }
        return null;
    }

    /**
     * Return connected user from cache.
     *
     * @return the connected user
     */
    public static User getUserFromCache(CacheApi cache) {
        User user = null;
        if (isConnected()) {
            user = (User) cache.get(getUuid() + ConstantUtils.EMAIL);
            if (user == null) {
                Http.Context.current().session().clear();
            }
        }
        return user;
    }

    /**
     * Put the user in cache, his unique identifier (email)
     * and Usertime (session expired) in the session.
     *
     * @param user the user to put in cache
     * @param cache the cache Api
     * @return true if all is ok, false otherwise.
     */
    public static User putUserInCache(final User user, CacheApi cache) {
        if (user == null) {
            Http.Context.current().session().clear();
        } else {
            // generate unique UUID for the user or reuse the one in session
            String uuid = Http.Context.current().session().get(ConstantUtils.UUID);
            if (uuid == null) {
                uuid = java.util.UUID.randomUUID().toString();
                Http.Context.current().session().put(ConstantUtils.UUID, uuid);
            }
            Http.Context.current().session().put(ConstantUtils.EMAIL, user.getEmail());
            // Put user in cache
            cache.set(uuid + ConstantUtils.EMAIL, user);
        }
        return user;
    }

    /**
     * To know if user is connected.
     *
     * @return Boolean depending user is connected
     */
    public static boolean isConnected() {
        return StringUtils.isNotBlank(Http.Context.current().session().get(ConstantUtils.EMAIL));
    }

    /**
     * Return connected user's unique identifier.
     *
     * @return String random UUID of the connected user
     */
    public static String getUuid() {
        return Http.Context.current().session().get(ConstantUtils.UUID);
    }
}
