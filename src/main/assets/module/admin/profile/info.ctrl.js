(function () {
    var ctrl = function ($scope, $rootScope, $stateParams, AccountApi, $state, $uibModalInstance, Notification) {
        var account = $rootScope.User;
        var successCallback = function (result) {
            if (result.data) {
                Notification.success({message: '保存成功'});
            } else {
                Notification.error({message: '保存失败'});
            }
            $uibModalInstance.close('fresh');
        };
        var submit = function () {

            AccountApi.save({id: account.id}, account, successCallback);
            $state.go('dashboard');
        };
        var cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        angular.extend($scope, {
            account: account,
            submit: submit,
            cancel: cancel
        });
    };

    ctrl.$inject = ['$scope', '$rootScope', '$stateParams', 'AccountApi', '$state', '$uibModalInstance', 'Notification'];
    angular.module('app.profile').controller('InfoCtrl', ctrl);

})();
