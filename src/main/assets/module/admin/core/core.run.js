/**
 * Resize function without multiple trigger
 *
 * Usage:
 * $(window).smartresize(function(){
 *     // code here
 * });
 */
(function ($, sr) {
    // debouncing function from John Hann
    // http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
    var debounce = function (func, threshold, execAsap) {
        var timeout;

        return function debounced() {
            var obj = this, args = arguments;

            function delayed() {
                if (!execAsap)
                    func.apply(obj, args);
                timeout = null;
            }

            if (timeout)
                clearTimeout(timeout);
            else if (execAsap)
                func.apply(obj, args);

            timeout = setTimeout(delayed, threshold || 100);
        };
    };

    // smartresize
    jQuery.fn[sr] = function (fn) {
        return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr);
    };

})(jQuery, 'smartresize');

(function () {
    var run = function ($rootScope, $window) {
        var CURRENT_URL = window.location.href.split('?')[0],
            $BODY = $('body'),
            $SIDEBAR_MENU = $('#sidebar-menu'),
            $SIDEBAR_FOOTER = $('.sidebar-footer'),
            $LEFT_COL = $('.left_col'),
            $RIGHT_COL = $('.right_col'),
            $NAV_MENU = $('.nav_menu'),
            $FOOTER = $('footer');

        var setContentHeight = function () {
            // reset height
            $RIGHT_COL.css('min-height', $(window).height());

            var bodyHeight = $BODY.outerHeight(),
                footerHeight = $BODY.hasClass('footer_fixed') ? -10 : $FOOTER.height(),
                leftColHeight = $LEFT_COL.eq(1).height() + $SIDEBAR_FOOTER.height(),
                contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;

            // normalize content
            contentHeight -= $NAV_MENU.height() + footerHeight;

            $RIGHT_COL.css('min-height', contentHeight);
        };

        // recompute content when resizing
        $(window).smartresize(function () {
            setContentHeight();
        });

        setContentHeight();

        // fixed sidebar
        //if ($.fn.mCustomScrollbar) {
        //    $('.menu_fixed').mCustomScrollbar({
        //        autoHideScrollbar: true,
        //        theme: 'minimal',
        //        mouseWheel: {preventDefault: true}
        //    });
        //}

        $rootScope.getUploadedFile = function (path, width, height) {            
            if (path.indexOf('assets') != -1) {
                return path;
            } else {
                var url = Context + "/file/" + path;
                if (width && height) {
                    url = url + '?width=' + width + '&height=' + height;
                }
                return url;
            }
        };

        $rootScope.getUploadedFile = function (path, width, height) {
            if (path.indexOf('assets') != -1) {
                return path;
            } else {
                var url = Context + "/file/" + path;
                if (width && height) {
                    url = url + '?width=' + width + '&height=' + height;
                }
                return url;
            }
        };
    };

    run.$inject = ['$rootScope', '$window'];
    angular.module('app.core').run(run);

})();
