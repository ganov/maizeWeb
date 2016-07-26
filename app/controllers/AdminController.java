package controllers;

import models.User;
import models.formsModel.AuthentForm;
import play.data.Form;
import play.cache.CacheApi;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.UserService;
import views.html.admin.index;
import play.data.FormFactory;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class AdminController extends Controller {

    @Inject WebJarAssets webJarAssets;
    @Inject CacheApi cache;
    @Inject FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        // Check if user is connected then home page else login page.
        if (UserService.isConnected()) {
            User user = UserService.getUserFromCache(cache);
            if (user != null) {
                return Results.redirect( routes.DashboardController.index() );
            } else {
                Http.Context.current().session().clear();
            }
        }
        return ok(index.render("", formFactory.form(AuthentForm.class), webJarAssets));
    }

    /**
     * Login and store the session.
     * @return Result Redirect to Index page
     */
    public Result authenticateUser() {
        Form<AuthentForm> loginForm = formFactory.form(AuthentForm.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(index.render("", loginForm, webJarAssets));
        } else {
            User user = UserService.getUserFromCache(cache);
            Http.Context.current().session().put("email", user.getEmail());
            return Results.redirect( routes.DashboardController.index() );
        }
    }

}
