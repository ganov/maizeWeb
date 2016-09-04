package controllers;

import models.FoodTrucker;
import play.mvc.Result;
import views.html.admin.formContact;

import javax.inject.Inject;
import java.util.List;

/**
 * Dashborad Controller.
 */
public class FormContactControllers extends SecureController {

    @Inject WebJarAssets webJarAssets;

    /**
     * Index : return Dashboard Page.
     * @return Result due page.
     */
    public Result index() {
        List<FoodTrucker> allFoodtrucker = FoodTrucker.FIND.all();
        return ok(formContact.render("", allFoodtrucker, webJarAssets));
    }
}
