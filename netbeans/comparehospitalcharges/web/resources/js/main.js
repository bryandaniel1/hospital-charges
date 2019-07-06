/**
 * Adds jQuery styling to specific element types
 */
$(document).ready(function () {
    $('section').addClass("ui-corner-all");
    $('li').addClass("ui-corner-all");
    prepareStateSelectMenu();
    prepareCitySelectMenu();
    prepareDRGSelectMenu();
    prepareAPCSelectMenu();
    prepareRegionalInpatientDataTable();
    prepareRegionalOutpatientDataTable();
    prepareDRGToCompareSelectMenu();
    prepareAPCToCompareSelectMenu();
    prepareStateToCompareSelectMenu();
    prepareSecondStateToCompareSelectMenu();
    prepareCityToCompareSelectMenu();
    prepareSecondCityToCompareSelectMenu();
    prepareProviderToCompareSelectMenu();
    prepareSecondProviderToCompareSelectMenu();
});

/**
 * Defines the state select menu as a jQuery select menu and sets behavior for 
 * firing change events
 * @returns {undefined}
 */
function prepareStateSelectMenu() {
    $(".stateSelectMenu").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.stateSelectMenu').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the city select menu as a jQuery select menu and sets behavior for 
 * firing change events
 * @returns {undefined}
 */
function prepareCitySelectMenu() {
    $(".citySelectMenu").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.citySelectMenu').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the DRG select menu as a jQuery select menu and sets behavior for 
 * firing change events
 * @returns {undefined}
 */
function prepareDRGSelectMenu() {
    $(".drgSelectMenu").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.drgSelectMenu').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the APC select menu as a jQuery select menu and sets behavior for 
 * firing change events
 * @returns {undefined}
 */
function prepareAPCSelectMenu() {
    $(".apcSelectMenu").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.apcSelectMenu').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the properties of the datatables holding regional inpatient results
 * @returns {undefined}
 */
function prepareRegionalInpatientDataTable() {
    $('.regionTable').DataTable({
        destroy: true,
        responsive: true,
        columnDefs: [
            {responsivePriority: 1, targets: 0},
            {responsivePriority: 2, targets: 2},
            {responsivePriority: 3, targets: 3},
            {responsivePriority: 4, targets: 4}
        ]
    });
}

/**
 * Defines the properties of the datatables holding regional outpatient results
 * @returns {undefined}
 */
function prepareRegionalOutpatientDataTable() {
    $('.regionTable').DataTable({
        destroy: true,
        responsive: true,
        columnDefs: [
            {responsivePriority: 1, targets: 0},
            {responsivePriority: 2, targets: 2},
            {responsivePriority: 3, targets: 3}
        ]
    });
}

/**
 * Invokes the prepareCitySelectMenu function once the AJAX call is 
 * complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleRegionalStateSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareCitySelectMenu();
            break;
    }
}

/**
 * Invokes the prepareDRGSelectMenu function or the prepareAPCSelectMenu function 
 * once the AJAX call is complete and the DOM is updated
 * @param {type} data
 * @param {type} type
 * @returns {undefined}
 */
function handleRegionalCitySelectAjaxCall(data, type) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            switch (type) {
                case "inpatient":
                    prepareDRGSelectMenu();
                    break;
                case "outpatient":
                    prepareAPCSelectMenu();
                    break;
            }
            break;
    }
}

/**
 * Invokes the prepareRegionalInpatientDataTable function once the AJAX call is 
 * complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleRegionalDRGSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareRegionalInpatientDataTable();
            break;
    }
}

/**
 * Invokes the prepareRegionalOutpatientDataTable function once the AJAX call is 
 * complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleRegionalAPCSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareRegionalOutpatientDataTable();
            break;
    }
}

/**
 * This function defines the DRG select menu on the compare-two page as a jQuery 
 * select menu, sets behavior for firing change events, and sets properties for 
 * height and width.
 * @returns {undefined}
 */
function prepareDRGToCompareSelectMenu() {
    $(".drgsToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.drgsToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
    
    // making sure instruction is visible
    var text = $('.drgsToCompare').find("option:selected").text();
    if (text === 'Choose a diagnosis-related group') {
        var arrowWidth = 60;
        var $test = $("<span>").html(text);

        // add to body, get width, and get out
        $test.appendTo('body');

        // set select width
        $('.drgsToCompare').selectmenu({width: ($test.width() + arrowWidth)});
        $test.remove();
    }
}

/**
 * This function defines the APC select menu on the compare-two page as a jQuery 
 * select menu, sets behavior for firing change events, and sets properties for 
 * height and width.
 * @returns {undefined}
 */
function prepareAPCToCompareSelectMenu() {
    $(".apcsToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.apcsToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
    
    // making sure instruction is visible
    var text = $('.apcsToCompare').find("option:selected").text();
    if (text === 'Choose an ambulatory payment classification') {
        var arrowWidth = 60;
        var $test = $("<span>").html(text);

        // add to body, get width, and get out
        $test.appendTo('body');

        // set select width
        $('.apcsToCompare').selectmenu({width: ($test.width() + arrowWidth)});
        $test.remove();
    }
}

/**
 * Defines the left-side state select menu on a compare-two page as a jQuery select 
 * menu and sets behavior for firing change events
 * @returns {undefined}
 */
function prepareStateToCompareSelectMenu() {
    $(".stateToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.stateToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the right-side state select menu on a compare-two page as a jQuery select 
 * menu and sets behavior for firing change events
 * @returns {undefined}
 */
function prepareSecondStateToCompareSelectMenu() {
    $(".secondStateToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.secondStateToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the left-side city select menu on a compare-two page as a jQuery select 
 * menu and sets behavior for firing change events
 * @returns {undefined}
 */
function prepareCityToCompareSelectMenu() {
    $(".cityToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.cityToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the right-side city select menu on a compare-two page as a jQuery select 
 * menu and sets behavior for firing change events
 * @returns {undefined}
 */
function prepareSecondCityToCompareSelectMenu() {
    $(".secondCityToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.secondCityToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the left-side provider select menu on a compare-two page as a jQuery select 
 * menu and sets behavior for firing change events
 * @returns {undefined}
 */
function prepareProviderToCompareSelectMenu() {
    $(".providerToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.providerToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Defines the right-side provider select menu on a compare-two page as a jQuery select 
 * menu and sets behavior for firing change events
 * @returns {undefined}
 */
function prepareSecondProviderToCompareSelectMenu() {
    $(".secondProviderToCompare").selectmenu({
        /**
         * Fires the change event
         * @param {type} event
         * @param {type} ui
         * @returns {undefined}
         */
        close: function (event, ui) {
            $(this).change();
        }
    });
    $('.secondProviderToCompare').selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
}

/**
 * Invokes the prepareStateToCompareSelectMenu function and the prepareSecondStateToCompareSelectMenu 
 * once the AJAX call is complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleComparisonSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareStateToCompareSelectMenu();
            prepareSecondStateToCompareSelectMenu();
            break;
    }
}

/**
 * Invokes the prepareCityToCompareSelectMenu function once the AJAX call is 
 * complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleStateToCompareSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareCityToCompareSelectMenu();
            break;
    }
}

/**
 * Invokes the prepareSecondCityToCompareSelectMenu function once the AJAX call is 
 * complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleSecondStateToCompareSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareSecondCityToCompareSelectMenu();
            break;
    }
}

/**
 * Invokes the prepareProviderToCompareSelectMenu function once the AJAX call is 
 * complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleCityToCompareSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareProviderToCompareSelectMenu();
            break;
    }
}

/**
 * Invokes the prepareSecondProviderToCompareSelectMenu function once the AJAX call is 
 * complete and the DOM is updated
 * @param {type} data
 * @returns {undefined}
 */
function handleSecondCityToCompareSelectAjaxCall(data) {
    var status = data.status;
    switch (status) {
        case "begin":
            break;
        case "complete":
            break;
        case "success":
            prepareSecondProviderToCompareSelectMenu();
            break;
    }
}

