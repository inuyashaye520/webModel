(function () {
    var coreConfig = function ($controllerProvider, $compileProvider, $filterProvider, $provide,
                               NotificationProvider) {
        var core = angular.module('app.core');

        // registering components after bootstrap
        core.controller = $controllerProvider.register;
        core.directive = $compileProvider.directive;
        core.filter = $filterProvider.register;
        core.factory = $provide.factory;
        core.service = $provide.service;
        core.constant = $provide.constant;
        core.value = $provide.value;

        NotificationProvider.setOptions({
                                            delay: 1500,
                                            startTop: 20,
                                            startRight: 10,
                                            verticalSpacing: 20,
                                            horizontalSpacing: 20,
                                            positionX: 'right',
                                            positionY: 'top'
                                        });
    };

    coreConfig.$inject =['$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
         'NotificationProvider'];
    angular.module('app.core').config(coreConfig);

})();
