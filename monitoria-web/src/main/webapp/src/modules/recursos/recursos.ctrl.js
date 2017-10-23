(function (ng) {

    var mod = ng.module("recursosModule");

    mod.constant("recursosContex","recursos");
    mod.constant("bibliotecasContext", "api/bibliotecas");
    
    mod.controller("recursosCtrl", ['$scope', '$state', '$stateParams', '$http', 'recursosContext','bibliotecasContext'
        , function ($scope, $state, $stateParams, $http, recursosContext,bibliotecasContext) {
               
               $http.get(bibliotecasContext + '/' + $state.param.bibliotecaId + '/' + recursosContext).then(function(response){
                   $scope.recursosRecords = response.data;
               });
               
               if($state.params.recursosId !== undefined){
                   $http.get(recursosContext + '/' + $state.params.recursosId).then(function (response){
                       $scope.recursosRecords = response.data.recursos;
                       $scope.currentBiblioteca = response.data;
                   });
               }
        }
    ]);
})(angular);

