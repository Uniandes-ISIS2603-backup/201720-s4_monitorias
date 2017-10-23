(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("monitoriasContext","api/monitorias");
    
    mod.controller("monitoriasCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoriasContext', function ($scope, $state, $stateParams, $http, context) {
               
                $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
        }
    ]);
})(window.angular);


