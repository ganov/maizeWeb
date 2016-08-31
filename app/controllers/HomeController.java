package controllers;

import models.FoodTrucker;
import models.MessageNotifier;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.foodTruckerFormSection;

import javax.inject.Inject;
import static play.libs.Json.toJson;

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
            return ok(toJson(MessageNotifier.newDefaultErrorMessageNotifier(foodTruckerFormSection.render(foodTruckerForm).toString())));
        } else {
            FoodTrucker foodTrucker = foodTruckerForm.get();
            foodTrucker.save();
            MessageNotifier tmpMsg = new MessageNotifier();
            tmpMsg.type = MessageNotifier.Type.info;
            tmpMsg.message = Messages.get("page.foodTrucker.form.label.ok");
            return ok(toJson(tmpMsg));
        }
    }

}
