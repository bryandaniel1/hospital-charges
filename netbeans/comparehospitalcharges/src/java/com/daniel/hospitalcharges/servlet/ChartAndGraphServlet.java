package com.daniel.hospitalcharges.servlet;

import com.daniel.hospitalcharges.model.StatisticsResult;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

/**
 * Creates the histograms for the statistics page
 *
 * @author Bryan Daniel
 */
public class ChartAndGraphServlet extends HttpServlet {

    /**
     * The ending x coordinate for the histogram bar color gradient
     */
    private static final float BAR_GRADIENT_END_X_COORDINATE = 15.0f;

    /**
     * The x-axis offset for the chart shadow
     */
    private static final double SHADOW_X_OFFSET = 3.0;

    /**
     * The y-axis offset for the chart shadow
     */
    private static final double SHADOW_Y_OFFSET = 1.5;

    /**
     * The number of bins for a dataset
     */
    private static final int DATASET_BINS = 30;

    /**
     * The width of the stroke for the border
     */
    private static final float STROKE_WIDTH = 5.0f;

    /**
     * The width of the chart
     */
    private static final int CHART_WIDTH = 750;

    /**
     * The height of the chart
     */
    private static final int CHART_HEIGHT = 550;

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -1841973865169496804L;

    /**
     * The logger for this class
     */
    private static Logger logger = LogManager.getLogger(ChartAndGraphServlet.class);

    /**
     * Invokes the getChart method when a request is received
     *
     * @param request the request
     * @param response the response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            getChart(request, response);
        } catch (IOException e) {
            logger.error("An IOException occurred in the doGet method.", e);
        }
    }

    /**
     * Creates a histogram displaying the distribution of charges
     *
     * @param request the request
     * @param response the response
     * @throws IOException
     */
    public void getChart(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        switch (action) {
            case "showhistogram": {
                StatisticsResult stats
                        = (StatisticsResult) session.getAttribute("chartResult");

                if (stats == null) {
                    break;
                }

                HistogramDataset dataset = new HistogramDataset();
                ArrayList<BigDecimal> charges = stats.getAllCharges();
                if (charges.size() > 0) {
                    double[] vector = new double[charges.size()];

                    for (int i = 0; i < vector.length; i++) {
                        vector[i] = charges.get(i).doubleValue();
                    }

                    //add the data
                    dataset.addSeries("number of providers", vector, DATASET_BINS);

                    //remove reference
                    session.setAttribute("chartResult", null);

                    boolean legend = true;
                    boolean tooltips = false;
                    boolean urls = false;

                    //get the chart
                    JFreeChart chart = ChartFactory.createHistogram(
                            "Price Distribution", "charge in dollars", "number of providers",
                            dataset, PlotOrientation.VERTICAL, legend, tooltips, urls);

                    chart.setBorderPaint(Color.BLACK);
                    chart.setBorderStroke(new BasicStroke(STROKE_WIDTH));
                    chart.setBorderVisible(true);

                    XYPlot plot = chart.getXYPlot();

                    //setting bar color
                    final XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
                    GradientPaint gp = getCoolPaint();
                    renderer.setSeriesPaint(0, gp);

                    //creating a shadow
                    renderer.setShadowXOffset(SHADOW_X_OFFSET);
                    renderer.setShadowYOffset(SHADOW_Y_OFFSET);
                    renderer.setShadowVisible(true);

                    ChartUtilities.writeChartAsPNG(outputStream, chart, CHART_WIDTH, CHART_HEIGHT);
                }
                break;
            }
            default:
                break;
        }
    }

    /**
     * Returns a nice gradient paint for the histogram bars.
     *
     * @return the gradient paint
     */
    private GradientPaint getCoolPaint() {
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(BAR_GRADIENT_END_X_COORDINATE, 0);
        GradientPaint p
                = new GradientPaint(start, Color.GREEN, end, Color.GREEN.darker().darker());
        return p;
    }
}
