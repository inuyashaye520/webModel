(function () {
    var route = function (Router) {
        Router.state('dashboard', {
            url: '/dashboard.html',
            templateUrl: '/dashboard/dashboard.html',
            controller: 'DashboardCtrl',
            require: []
        });
    };

    route.$inject = ['Router'];
    angular.module('app.dashboard').run(route);
})();
