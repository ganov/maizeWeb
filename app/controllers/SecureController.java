package controllers;

import play.mvc.Controller;
import play.mvc.Security;

/**
 * Abstract controller to insure user is authenticated.
 */
@Security.Authenticated(Secure.class)
public abstract class SecureController extends Controller {

}