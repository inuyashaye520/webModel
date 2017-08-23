(function () {
    angular.module('app.core', [
        'ngLocale',
        'app.http',
        'app.router',
        'app.datepicker',
        'ui.bootstrap',
        'angularSpinner',
        'ngResource',
        'ngMessages',
        //'ngCookies',
        'app.api'
    ]);
})();
