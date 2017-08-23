(function () {
    var route = function (Router) {
        Router.state('profile', {
            url: '/profile.html',
            templateUrl: '/profile/profile.html',
            controller: 'ProfileCtrl'
        });
    };

    route.$inject = ['Router'];
    angular.module('app.profile').run(route);
})();

