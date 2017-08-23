(function () {
    var controller = function ($scope, $state, $uibModalInstance) {
        var data = {};
        var submit = function (result) {
            data.isClose = result;
            $uibModalInstance.close(data);

        };

        angular.extend($scope, {
            submit: submit
        });
    };
    controller.$inject = ['$scope', '$state', '$uibModalInstance'];
    angular.module('app.profile').controller('AlertCtrl', controller);

})();
