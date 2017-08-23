(function () {
    var HttpConfig = function ($httpProvider) {
        $httpProvider.interceptors.push(['$q', '$injector', function ($q, $injector) {
            return {
                request: function (config) {
                    config.headers['X-Requested-With'] = 'XMLHttpRequest';
                    return config;
                },
                response: function (response) {
                    var resp = response.data;
                    if (typeof resp === 'object') {
                        if (resp.code === 200) {
                            response.data =
                                typeof resp.data === 'object' ? resp.data : {data: resp.data};
                        } else {
                            var Notification = $injector.get('Notification');
                            Notification.error({
                                title: '错误: ' + resp.code,
                                message: resp.message
                            });
                            if (resp.code === 401) {
                                location.href = Context + '/admin/login.html';
                            }
                            return $q.reject(response);
                        }
                    }
                    return response;
                },
                responseError: function (rejection) {
                    var Notification = $injector.get('Notification');
                    Notification.error({message: '网络错误'});
                    return $q.reject(rejection);
                }
            };
        }]);
    };

    HttpConfig.$inject = ['$httpProvider'];
    angular.module('app.http').config(HttpConfig);

})();
