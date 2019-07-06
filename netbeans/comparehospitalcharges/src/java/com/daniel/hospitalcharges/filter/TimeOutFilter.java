package com.daniel.hospitalcharges.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This filter redirects timed out sessions to the base index page of the
 * application
 *
 * @author Bryan Daniel
 */
public class TimeOutFilter implements Filter {

    /**
     * The debug indicator
     */
    private static final boolean debug = true;

    /**
     * The filter configuration
     */ 
    private FilterConfig filterConfig = null;

    /**
     * Default constructor
     */
    public TimeOutFilter() {
    }

    /**
     * Filters the requests for timed out sessions
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        String url = "/WEB-INF/errorpages/expired.xhtml";

        if (session != null) {
            chain.doFilter(request, response);
        } else {
            httpRequest.getSession(true).getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return the filter configuration
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Initialization method for this filter
     *
     * @param filterConfig the filter configuration
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("TimeOutFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("TimeOutFilter()");
        }
        StringBuilder sb = new StringBuilder("TimeOutFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    /**
     * Logs a message
     *
     * @param msg the message
     */
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
