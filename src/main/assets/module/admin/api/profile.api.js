(function () {
    var logoutApi = function ($resource) {
        return $resource(Context + '/logout', {}, {});
    };
    logoutApi.$inject = ['$resource'];
    angular.module('app.profile').service('LogoutApi', logoutApi);
})();

