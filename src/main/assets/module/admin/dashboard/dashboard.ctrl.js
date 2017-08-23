(function () {
    var controller = function ($rootScope, $scope, $state, $timeout) {

        var countList = {};

        var getData = function () {
        };

        $timeout(getData, 0);

        angular.extend($scope, {
            countList: countList


        });
    };

    controller.$inject = ['$rootScope', '$scope', '$state', '$timeout'];
    angular.module('app.dashboard').controller('DashboardCtrl', controller);

})();
