/**
 * Adds jQuery styling to specific element types
 */
$(document).ready(function () {
    $('section').addClass("ui-corner-all");
    $('li').addClass("ui-corner-all");
    prepareStatSelectMenus();
});

/**
 * Defines the statistics select menus as a jQuery select menus
 * @returns {undefined}
 */
function prepareStatSelectMenus() {
    
    // DRG select menu
    $(".drgStatSelect").selectmenu({
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
    $(".drgStatSelect").selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
    
    // making sure instruction is visible
    var text = $('.drgStatSelect').find("option:selected").text();
    if (text === 'Choose a diagnosis-related group') {
        var arrowWidth = 60;
        var $test = $("<span>").html(text);

        // add to body, get width, and get out
        $test.appendTo('body');

        // set select width
        $('.drgStatSelect').selectmenu({width: ($test.width() + arrowWidth)});
        $test.remove();
    }
    
    // APC select menu
    $(".apcStatSelect").selectmenu({
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
    $(".apcStatSelect").selectmenu().selectmenu("menuWidget")
            .css("max-height", "300px");
    
    // making sure instruction is visible
    var text = $('.apcStatSelect').find("option:selected").text();
    if (text === 'Choose an ambulatory payment classification') {
        var arrowWidth = 60;
        var $test = $("<span>").html(text);

        // add to body, get width, and get out
        $test.appendTo('body');

        // set select width
        $('.apcStatSelect').selectmenu({width: ($test.width() + arrowWidth)});
        $test.remove();
    }
}