package com.daniel.hospitalcharges.utility;

import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import org.omnifaces.exceptionhandler.FullAjaxExceptionHandler;

/**
 * This class extends <code>FullAjaxExceptionHandler</code> to avoid printing
 * stack traces for each occurrence of <code>ViewExpiredException</code>.
 *
 * @author Bryan Daniel
 */
public class SilentAjaxExceptionHandler extends FullAjaxExceptionHandler {

    /**
     * The constructor passes the <code>ExceptionHandler</code> to the
     * constructor of the superclass.
     *
     * @param wrapped the ExceptionHandler object
     */
    public SilentAjaxExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    /**
     * The method, <code>logException</code>, is overridden to avoid printing
     * the stack traces for instances of <code>ViewExpiredException</code>.
     *
     * @param context the faces context
     * @param exception the exception thrown
     * @param location the location
     * @param message the message
     * @param parameters the parameters
     */
    @Override
    protected void logException(FacesContext context, Throwable exception, String location,
            String message, Object... parameters) {
        if (exception instanceof ViewExpiredException) {
            super.logException(context, null, location, message, parameters);
        } else {
            super.logException(context, exception, location, message, parameters);
        }
    }
}
