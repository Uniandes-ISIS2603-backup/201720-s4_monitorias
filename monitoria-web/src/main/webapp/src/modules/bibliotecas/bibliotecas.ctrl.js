(function (ng) {

    var mod = ng.module("BibliotecasModule");

    mod.constant("bibliotecasContex","api/biblitecas");
    
    mod.controller("bibliotecasCtrl", ['$scope', '$state', '$stateParams', '$http', 'bibliotecasContext', function ($scope, $state, $stateParams, $http, bibliotecasContext) {
               
               $http.get('data/bibliotecas.json').then(function(response){
                   $scope.bibliotecasRecords = response.data;
               });
        }
    ]);
})(window.angular);

