(function () {
    var Config = function ($ocLazyLoadProvider) {
        $ocLazyLoadProvider.config({
            debug: false,
            events: true
        });
    };
    Config.$inject = ['$ocLazyLoadProvider'];
    angular.module('app.router').config(Config);

})();
