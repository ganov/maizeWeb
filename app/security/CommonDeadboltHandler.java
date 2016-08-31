package security;

import be.objectify.deadbolt.java.ConfigKeys;
import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.models.Subject;
import models.User;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Ganov on 25/07/2016.
 * de
 */
public class CommonDeadboltHandler implements DeadboltHandler {


    @Override
    public CompletionStage<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public CompletionStage<Optional<? extends Subject>> getSubject(Http.Context context) {
        String email = context.current().session().get("email");
        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(User.findByEmail(email)));
    }

    @Override
    public CompletionStage<Result> onAuthFailure(Http.Context context, Optional<String> content) {
        return null;
    }

    @Override
    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return null;
    }

    @Override
    public String handlerName()
    {
        return ConfigKeys.DEFAULT_HANDLER_KEY;
    }
}
