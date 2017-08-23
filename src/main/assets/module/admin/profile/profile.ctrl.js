(function () {
    var ctrl = function ($scope, $rootScope, $stateParams, AccountApi, $state, $uibModalInstance, Notification) {
        var account = $rootScope.User;
        var pwd = {};
        var successCallback = function (result) {
            if (result.data) {
                Notification.success({message: '保存成功'});
            } else {
                Notification.error({message: '保存失败'});
            }
            $uibModalInstance.close('fresh');
        };
        var submit = function () {
            if (pwd.pass == pwd.password) {
                AccountApi.editPassword({id: account.id}, pwd, successCallback);
            } else {
                Notification.error({message: '密码和确认密码不一致'});
            }
            $state.go('dashboard');
        };
        var cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
        angular.extend($scope, {
            pwd: pwd,
            submit: submit,
            cancel: cancel
        });
    };

    ctrl.$inject = ['$scope', '$rootScope', '$stateParams', 'AccountApi', '$state', '$uibModalInstance', 'Notification'];
    angular.module('app.profile').controller('ProfileCtrl', ctrl);

})();
