(function (ng) {

    var mod = ng.module("PagosModule");

    mod.constant("pagosContext","api/pagos");
    
    mod.controller("pagosCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagosContext', function ($scope, $state, $stateParams, $http, context) {
               
                $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
        }
    ]);
})(window.angular);

