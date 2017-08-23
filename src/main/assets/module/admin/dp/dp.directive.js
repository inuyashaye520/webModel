(function () {
    var dp = function () {
        var link = function (scope, element, attr, ngModel) {
            element.val(ngModel.$viewValue);

            function changed(dp) {
                var date = dp.cal.getNewDateStr();
                scope.$apply(function () {
                    ngModel.$setViewValue(date);
                });
            }

            function onclearing() {
                scope.$apply(function () {
                    ngModel.$setViewValue('');
                });
            }

            element.bind('click', function () {
                var option = {};
                angular.extend(option, scope.option || {});
                option.onpicking = changed;
                option.ychanged = changed;
                option.Mchanged = changed;
                option.dchanged = changed;
                option.Hchanged = changed;
                option.mchanged = changed;
                option.schanged = changed;
                option.onclearing = onclearing;
                if (!option.dateFmt) {
                    option.dateFmt = 'yyyy-MM-dd';
                }
                WdatePicker(option);
            });
        };
        var directive = {
            restrict: 'A',
            require: 'ngModel',
            scope: {
                option: '=?wDatePicker'
            },
            link: link
        };
        return directive;
    };
    angular.module('app.datepicker').directive('wDatePicker', dp);

})();
