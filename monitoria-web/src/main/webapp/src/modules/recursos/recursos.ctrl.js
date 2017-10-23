(function (ng) {

    var mod = ng.module("BibliotecasModule");

    mod.constant("bibliotecasContex","api/biblitecas");
    
    mod.controller("bibliotecasCtrl", ['$scope', '$state', '$stateParams', '$http', 'bibliotecasContext', function ($scope, $state, $stateParams, $http, bibliotecasContext) {
               
               $http.get('data/bibliotecas.json').then(function(response){
                   $scope.bibliotecasRecords = response.data;
               });
               
               if($state.params.bibliotecaName !== undefined){
                   $http.get(bibliotecasContext + '/' + $state.params.bibliotecasName).then(function (response){
                       $scope.recursosRecords = response.data.recursos;
                       $scope.currentBiblioteca = response.data;
                   });
               }
        }
    ]);
})(angular);

