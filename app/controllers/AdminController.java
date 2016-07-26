package controllers;

import enums.ApplicationProfile;
import models.User;
import models.formsModel.AuthentForm;
import play.data.Form;
import play.cache.CacheApi;
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
    @Inject FormFactory formFactory;

    private CacheApi cache;

    @Inject
    public AdminController(CacheApi cache) {
        this.cache = cache;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        if(User.findByEmail("ganovelli@gmail.com") == null){
            User U = new User();
            U.setEmail("ganovelli@gmail.com");
            U.setFullName("Jean GANOVELLI");
            U.setPassword("Azerty321*");
            U.setValidated(true);
            U.setProfile(ApplicationProfile.R3);
            U.setCreateOwner("ganovelli@gmail.com");
            U.save();
        }
        if(User.findByEmail("eliemonge@gmail.com") == null){
            User U = new User();
            U.setEmail("eliemonge@gmail.com");
            U.setFullName("Elie MONGE");
            U.setPassword("Monge789*");
            U.setValidated(true);
            U.setProfile(ApplicationProfile.R3);
            U.setCreateOwner("ganovelli@gmail.com");
            U.save();
        }
        if(User.findByEmail("cedric.christensen@gmail.com") == null){
            User U = new User();
            U.setEmail("cedric.christensen@gmail.com");
            U.setFullName("Cedric O. CHRISTENSEN");
            U.setPassword("Cedric456*");
            U.setValidated(true);
            U.setProfile(ApplicationProfile.R3);
            U.setCreateOwner("ganovelli@gmail.com");
            U.save();
        }

        if(User.findByEmail("pashu.christensen@gmail.com") == null) {
            User U = new User();
            U.setEmail("pashu.christensen@gmail.com");
            U.setFullName("Pashû Pathina CHRISTENSEN");
            U.setPassword("Pashu123*");
            U.setValidated(true);
            U.setProfile(ApplicationProfile.R3);
            U.setCreateOwner("ganovelli@gmail.com");
            U.save();
        }


        // Check if user is connected then home page else login page.
        if (UserService.isConnected()) {
            User user = UserService.getUserFromCache();
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
            User user = UserService.getUserFromCache();
            Http.Context.current().session().put("email", user.getEmail());
            return Results.redirect( routes.DashboardController.index() );
        }
    }

}
