(function () {
    var run = function ($rootScope, $state, $http, $interval) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
            if (!$rootScope.User) {
               event.preventDefault();
               $http.get(Context + '/api/admin/login').success(function (result) {
                   if (result && typeof result === 'object') {
                       $rootScope.User = result;
                       $state.go(toState, toParams);
                   } else {
                       location.href = Context + '/admin/login.html';
                   }
               });
            }
        });
    };

    run.$inject = ['$rootScope', '$state', '$http', '$interval'];
    angular.module('app.login').run(run);

})();
