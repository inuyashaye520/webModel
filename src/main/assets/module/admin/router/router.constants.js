(function () {
    angular.module('app.router').constant('APP_REQUIRES', {
        'datepicker': {
           files: [
               Context + '/assets/lib/My97DatePicker/WdatePicker.js'
           ],
           serie: true
        },
        'ui-select': {
            files: [
                Context + '/webjars/angular-ui-select/0.19.1/dist/select.min.css?version=0.19.1'
                , Context + '/webjars/angular-ui-select/0.19.1/dist/select.min.js?version=0.19.1'
            ]
        }


    });
})();
