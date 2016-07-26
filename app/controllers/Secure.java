package controllers;

import models.User;
import play.cache.CacheApi;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import services.UserService;

import javax.inject.Inject;

/**
 * Security Model.
 */
public class Secure extends Security.Authenticator {

    @Inject CacheApi cache;

    @Override
    public String getUsername(final Http.Context ctx) {
        User user = UserService.getUserFromCache();
        return user != null ? user.getIdentifier() : null;
    }

    @Override
    public Result onUnauthorized(final Http.Context ctx) {
        return redirect(routes.AdminController.index());
    }
}
