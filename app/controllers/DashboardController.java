package controllers;

import models.FoodTrucker;
import play.mvc.Result;
import views.html.admin.dashboard;

import javax.inject.Inject;
import java.util.List;

/**
 * Dashborad Controller.
 */
public class DashboardController extends SecureController {

    @Inject WebJarAssets webJarAssets;

    /**
     * Index : return Dashboard Page.
     * @return Result due page.
     */
    public Result index() {
        List<FoodTrucker> allFoodtrucker = FoodTrucker.FIND.all();
        return ok(dashboard.render("", allFoodtrucker, webJarAssets));
    }
}
