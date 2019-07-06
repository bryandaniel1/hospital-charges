<%-- 
    Document   : index
    Created on : Mar 1, 2016, 5:27:44 PM
    Author     : Bryan Daniel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compare Hospital Charges</title>
        <meta charset="utf-8">
        <meta name="robots" content="index, follow">
        <meta name="keywords" content="hospital, charges, providers, comparison, payments, diagnosis-related group">
        <meta name="description" content="This site offers a few helpful tools to compare the costs of procedures performed by healthcare providers across the country.  The data for this site is taken directly from the latest inpatient charge data released by the Centers for Medicare &amp; Medicaid Services.">
        <meta name=viewport content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="<c:url value='/images/favicon-hospital-o.ico'/>" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/media.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/jquery/jquery-ui.min.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/jquery/jquery-ui.structure.min.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/jquery/jquery-ui.theme.min.css'/>">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <![endif]-->
        <script type="text/javascript" async src="https://static.addtoany.com/menu/page.js"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/A2A.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.7.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/stats.js'/>"></script>
    </head>
    <body>
        <header>
            <h1 id="title">Compare Hospital Charges</h1>
            <nav id="nav-main">
                <ul>
                    <li><a href="<c:url value='/inpatient'/>">Find Inpatient Charges by Region</a></li>
                    <li><a href="<c:url value='/inpatient/comparetwo'/>">Compare Two Inpatient Charges</a></li>
                    <li><a href="<c:url value='/outpatient'/>">Find Outpatient Charges By Region</a></li>
                    <li><a href="<c:url value='/outpatient/comparetwo'/>">Compare Two Outpatient Charges</a></li>
                    <li><a href="<c:url value='/statistics'/>">Statistics</a></li>                
                </ul>
            </nav>
        </header>
        <!-- AddToAny BEGIN -->
        <div id="share" class="a2a_kit a2a_kit_size_32 a2a_default_style" data-a2a-url="http://www.healthcare-topics.com/hospitalcharges" data-a2a-title="Compare Hospital Charges">
            <a class="a2a_dd" href="https://www.addtoany.com/share"></a>
            <a class="a2a_button_facebook"></a>
            <a class="a2a_button_twitter"></a>
            <a class="a2a_button_google_plus"></a>
        </div>
        <!-- AddToAny END -->
        <section class="pagecontent statscontent">
            <h2>Price Statistics</h2>
            <div id="leftSelect">
                <c:if test="${drgs!=null}">
                    <form action="<c:url value='/statistics'/>" method="post">
                        <input type="hidden" name="action" value="selectDRG">
                        <select class="drgStatSelect" name="selectedDRG" onchange="this.form.submit()">
                            <option value="" selected>Choose a diagnosis-related group</option>
                            <c:forEach var="drg" items="${drgs}">                   
                                <option value="${drg.drgId}"><c:out value="${drg.drgDefinition}"/></option>                   
                            </c:forEach>
                        </select>
                        <noscript>
                        <input class="button ui-button" type="submit" value="Submit">
                        </noscript>
                    </form>
                </c:if>
                <p class="error">
                    <c:if test="${statsDRGMessage!=null}">
                        <c:out value="${statsDRGMessage}"/>
                    </c:if>        
                </p>
            </div>
            <span id="mid">-OR-</span>
            <div id="rightSelect">
                <c:if test="${apcs!=null}">
                    <form action="<c:url value='/statistics'/>" method="post">
                        <input type="hidden" name="action" value="selectAPC">
                        <select class="apcStatSelect" name="selectedAPC" onchange="this.form.submit()">
                            <option value="" selected>Choose an ambulatory payment classification</option>
                            <c:forEach var="apc" items="${apcs}">                   
                                <option value="${apc.apcId}"><c:out value="${apc.apcDefinition}"/></option>                   
                            </c:forEach>
                        </select>
                        <noscript>
                        <input class="button ui-button" type="submit" value="Submit">
                        </noscript>
                    </form>
                </c:if>
                <p class="error">
                    <c:if test="${statsAPCMessage!=null}">
                        <c:out value="${statsAPCMessage}"/>
                    </c:if>        
                </p>
            </div>
            <c:if test="${statsResult != null}">
                <h3 class="compare"><c:out value="${statsResult.header}"/></h3>
                <h4 class="statsListHeader">Central Tendency</h4>
                <ul class="statsList">
                    <li>
                        <span>Average Charge:&nbsp;</span><span class="prices">&#36;<c:out value="${statsResult.avgCharge}"/></span>
                    </li>
                    <li>
                        <span>Median Charge:&nbsp;</span><span class="prices">&#36;<c:out value="${statsResult.median}"/></span>
                    </li>
                </ul> 
                <h4 class="statsListHeader">Dispersion</h4>
                <ul class="statsList">
                    <li>
                        <span>Range:&nbsp;</span><span class="prices">&#36;<c:out value="${statsResult.range}"/></span>
                    </li>
                    <li>
                        <span>Minimum Charge:&nbsp;</span><span class="prices">&#36;<c:out value="${statsResult.minCharge}"/></span>
                    </li>
                    <li>
                        <span>Maximum Charge:&nbsp;</span><span class="prices">&#36;<c:out value="${statsResult.maxCharge}"/></span>
                    </li>
                    <li>
                        <span>Standard Deviation:&nbsp;</span><span class="prices">&#36;<c:out value="${statsResult.stdDeviation}"/></span>
                    </li>
                </ul>
                <div id="minCharge">
                    <table class="result ui-corner-all">
                        <tr>
                            <th>Minimum Price:</th>
                        </tr>
                        <tr class="hospital">
                            <th colspan="4"><c:out value="${statsResult.minProvider.name}"/></th>
                        </tr>
                        <tr>
                            <td colspan="4"><c:out value="${statsResult.minProvider.street}"/></td>
                        </tr>
                        <tr>
                            <td colspan="3"><c:out value="${statsResult.minProvider.city}"/>,&nbsp;
                                <c:out value="${statsResult.minProvider.state}"/>&nbsp;
                                <c:out value="${statsResult.minProvider.zipCode}"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="pricesLabel" colspan="2">Average Charges:</td>
                            <td class="prices" colspan="2">&#36;<c:out value="${statsResult.minCharge}"/></td>
                        </tr>                
                    </table>
                </div>
                <div id="maxCharge">
                    <table class="result ui-corner-all">
                        <tr>
                            <th>Maximum Price:</th>
                        </tr>
                        <tr class="hospital">
                            <th colspan="4"><c:out value="${statsResult.maxProvider.name}"/></th>
                        </tr>
                        <tr>
                            <td colspan="4"><c:out value="${statsResult.maxProvider.street}"/></td>
                        </tr>
                        <tr>
                            <td colspan="3"><c:out value="${statsResult.maxProvider.city}"/>,&nbsp;
                                <c:out value="${statsResult.maxProvider.state}"/>&nbsp;
                                <c:out value="${statsResult.maxProvider.zipCode}"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="pricesLabel" colspan="2">Average Charges:</td>
                            <td class="prices" colspan="2">&#36;<c:out value="${statsResult.maxCharge}"/></td>
                        </tr>                
                    </table>
                </div>
                <div class="chart">
                    <img src='<c:url value="drawchart">
                             <c:param name="action" value="showhistogram"/>
                             <c:param name="time" value="${requestScope.timeActionExecuted}"/>
                         </c:url>' 
                         alt="histogram"/>
                </div>
            </c:if>    
        </section>
        <footer id="site-info">
            <!--<nav id="nav-info">
              <ul>
                  <li><a href="<c:url value='/about'/>">About Us</a></li>
                <li><a href="<c:url value='/contact'/>">Contact Us</a></li>
              </ul>
            </nav>
      
            <p class="slogan"></p>-->

            <!--<p class="copyright">Copyright &copy; 2015 Compare Hospital Charges</p>-->
        </footer>
    </body>
</html>
