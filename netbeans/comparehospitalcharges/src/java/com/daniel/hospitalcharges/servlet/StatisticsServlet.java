package com.daniel.hospitalcharges.servlet;

import com.daniel.hospitalcharges.model.AmbulatoryPaymentClassification;
import com.daniel.hospitalcharges.model.DiagnosisRelatedGroup;
import com.daniel.hospitalcharges.model.StatisticsResult;
import com.daniel.hospitalcharges.data.OutpatientIO;
import com.daniel.hospitalcharges.data.InpatientIO;
import com.daniel.hospitalcharges.data.StatisticsIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the statistics page
 *
 * @author Bryan Daniel
 */
@WebServlet(name = "StatisticsServlet", urlPatterns = {"/StatisticsServlet"})
public class StatisticsServlet extends HttpServlet {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 6657798581546532113L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = "/statistics/index.jsp";
        String message;
        int drgId;
        int apcId;
        String action = request.getParameter("action");
        if (action == null) {
            action = "get lists";
        } else {
            request.setAttribute("timeActionExecuted", new Date().getTime());
        }
        switch (action) {
            case "get lists": {
                session.setAttribute("statsResult", null);
                ArrayList<DiagnosisRelatedGroup> drgs = InpatientIO.getDiagnosisRelatedGroups();
                ArrayList<AmbulatoryPaymentClassification> apcs
                        = OutpatientIO.getAmbulatoryPaymentClassifications();
                session.setAttribute("drgs", drgs);
                session.setAttribute("apcs", apcs);
                break;
            }
            case "selectDRG": {
                try {
                    drgId = Integer.parseInt(request.getParameter("selectedDRG"));
                    StatisticsResult statsResult = StatisticsIO.getDiagnosisRelatedGroupStats(drgId);
                    ArrayList<DiagnosisRelatedGroup> drgs
                            = (ArrayList<DiagnosisRelatedGroup>) session.getAttribute("drgs");
                    for (DiagnosisRelatedGroup d : drgs) {
                        if (d.getDrgId() == drgId) {
                            statsResult.setHeader(d.getDrgDefinition());
                        }
                    }
                    session.setAttribute("chartResult", statsResult);
                    session.setAttribute("statsResult", statsResult);
                } catch (NumberFormatException nfe) {
                    message = "You must select a diagnosis-related group.";
                    request.setAttribute("statsDRGMessage", message);
                    session.setAttribute("chartResult", null);
                    session.setAttribute("statsResult", null);
                }
                break;
            }
            case "selectAPC": {
                try {
                    apcId = Integer.parseInt(request.getParameter("selectedAPC"));
                    StatisticsResult statsResult = StatisticsIO.getAmbulatoryPaymentClassificationStats(apcId);
                    ArrayList<AmbulatoryPaymentClassification> apcs
                            = (ArrayList<AmbulatoryPaymentClassification>) session.getAttribute("apcs");
                    for (AmbulatoryPaymentClassification a : apcs) {
                        if (a.getApcId() == apcId) {
                            statsResult.setHeader(a.getApcDefinition());
                        }
                    }
                    session.setAttribute("chartResult", statsResult);
                    session.setAttribute("statsResult", statsResult);
                } catch (NumberFormatException nfe) {
                    message = "You must select an ambulatory payment classification.";
                    request.setAttribute("statsAPCMessage", message);
                    session.setAttribute("chartResult", null);
                    session.setAttribute("statsResult", null);
                }
                break;
            }
            default:
                break;
        }
        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
