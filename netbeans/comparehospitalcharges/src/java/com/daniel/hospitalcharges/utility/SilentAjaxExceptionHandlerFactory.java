package com.daniel.hospitalcharges.utility;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * This class is used in place of
 * <code>org.omnifaces.exceptionhandler.FullAjaxExceptionHandlerFactory</code>
 * to utilize the <code>SilentAjaxExceptionHandler</code> rather than the
 * <code>FullAjaxExceptionHandler</code> to handle instances of
 * <code>ViewExpiredException</code>.
 *
 * @author Bryan Daniel
 */
public class SilentAjaxExceptionHandlerFactory extends ExceptionHandlerFactory {

    /**
     * The ExceptionHandlerFactory object
     */
    private ExceptionHandlerFactory wrapped;

    /**
     * The constructor passes the ExceptionHandlerFactory to the constructor of
     * the superclass.
     *
     * @param wrapped the ExceptionHandlerFactory object
     */
    public SilentAjaxExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * The method, <code>getExceptionHandler</code>, is overridden to return an
     * instance of <code>SilentAjaxExceptionHandler</code>.
     *
     * @return the ExceptionHandler object
     */
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new SilentAjaxExceptionHandler(getWrapped().getExceptionHandler());
    }

    /**
     * Gets the value of wrapped
     *
     * @return the value of wrapped
     */
    @Override
    public ExceptionHandlerFactory getWrapped() {
        return wrapped;
    }
}
