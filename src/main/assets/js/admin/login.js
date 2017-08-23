(function () {
    var ctrl = function ($scope, $http, $window, $rootScope) {
        var ui = {};
        var user = {};
        var login = function () {
            ui.submitting = true;
            $http.post(Context + '/api/admin/login', user).success(function (result) {
                if (result.code === 200 && result.data) {
                    $rootScope.User = result.data;
                    location.href = Context + '/admin/index.html';
                } else {
                    ui.submitting = false;
                    $window.alert(result.message || '登录失败');
                }
            }).error(function () {
                ui.submitting = false;
                $window.alert('网络错误');
            }).finally(function () {
                $scope.$broadcast('ChangeImageCodeEvent');
            });
        };

        angular.extend($scope, {
            ui: ui,
            user: user,
            login: login
        });
    };
    
    ctrl.$inject = ['$scope', '$http', '$window', '$rootScope'];
    angular.module('app', []).directive('imgCode', ['$timeout', function ($timeout) {
        var link = function (scope, element) {
            var changeCode = function () {
                element.css('background-image',
                            'url(' + Context + '/api/admin/code?random=' + Math.random()
                            + ')');
            };

            element.bind('click', changeCode);
            scope.$on('ChangeImageCodeEvent', changeCode);
            $timeout(changeCode);
        };
        return {
            restrict: 'C',
            link: link
        };
    }]).controller('LoginCtrl', ctrl);
})();
