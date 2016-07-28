package controllers;

import models.FoodTrucker;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.index;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject WebJarAssets webJarAssets;
    @Inject FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("", formFactory.form(FoodTrucker.class), webJarAssets));
    }

    public Result foodTruckerContact() {
        Form<FoodTrucker> foodTruckerForm = formFactory.form(FoodTrucker.class).bindFromRequest();

        if (foodTruckerForm.hasErrors()) {
            return badRequest(index.render("", foodTruckerForm, webJarAssets));
        } else {
            FoodTrucker foodTrucker = foodTruckerForm.get();
            flash("info", Messages.get("page.foodTrucker.form.label.ok"));
            foodTrucker.save();
            return Results.redirect(routes.HomeController.index());
        }
    }

}
