package controllers;

import play.mvc.Result;
import views.html.admin.dashboard;

import javax.inject.Inject;

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
        return ok(dashboard.render("", webJarAssets));
    }
}
