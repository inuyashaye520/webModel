(function () {

    var controller = function ($scope, $rootScope, $uibModal, LogoutApi) { 
        
        var logout = function () {
            var modal = $uibModal.open({
                scope: $scope,
                templateUrl: Context + '/tmpl/admin/profile/alert.html',
                controller: 'AlertCtrl',
                size: 'sm',
                resolve: {}
            });

           modal.result.then(function (result) {
                if (result.isClose == true) {
                    LogoutApi.get(function () {
                        $rootScope.User = null;
                        location.href = Context + '/admin/login.html';
                    });
                }
            });
        };

        var profile = function () {
            var config = {
                scope: $scope,
                templateUrl: Context + '/tmpl/admin/profile/profile.html',
                controller: 'ProfileCtrl'
            };
            $uibModal.open(config).result.then(function (resp) {});
        };
        
        var info = function () {
            var config = {
                scope: $scope,
                templateUrl: Context + '/tmpl/admin/profile/info.html',
                controller: 'InfoCtrl'
            };
            $uibModal.open(config).result.then(function (resp) {});
        };

        angular.extend($scope, {
            logout: logout,
            profile: profile,
            info: info
        });
    };

    controller.$inject = ['$scope', '$rootScope', '$uibModal', 'LogoutApi'];
    angular.module('app.core').controller('IndexCtrl', controller);

})();
