package models;

import play.i18n.Messages;

/**
 * Bean MessageNotifier permettant la gestion simplifiée des messages
 */
public class MessageNotifier {

    /**
     * le message à notifier.
     */
    public String message;

    /**
     * Le message complementaire à notifier.
     */
    public String additionnalMessage;

    /**
     * le type de message (true : error, false: info).
     */
    public Type type;

    /**
     *
     */
    public String html;

    /**
     * Enum représentant les Types.
     */
    public enum Type {
        /**
         * type erreur.
         */
        error,
        /**
         * type Confirm.
         */
        confirm,
        /**
         * type info.
         */
        info,
        /**
         * type success (info avec affichage sans disparition auto).
         */
        success
    }

    /**
     * Renvoie un message notifier avec les erreurs du form.
     *
     * @return MessageNotifier avec les erreurs
     */
    public static MessageNotifier newDefaultErrorMessageNotifier(final String html) {
        MessageNotifier messageNotifier = new MessageNotifier();
        messageNotifier.message =  Messages.get("generic.message.form.invalid");
        messageNotifier.type = MessageNotifier.Type.error;
        messageNotifier.html = html;
        return messageNotifier;
    }
}
