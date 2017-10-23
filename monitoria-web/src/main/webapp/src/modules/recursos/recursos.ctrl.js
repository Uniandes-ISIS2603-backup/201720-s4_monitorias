(function (ng) {

    var mod = ng.module("recursosModules");

    mod.constant("recursosContext","recursos");
    mod.constant("bibliotecasContext", "api/bibliotecas");
    
    mod.controller("recursosCtrl", ['$scope', '$state', '$stateParams', '$http', 'recursosContext','bibliotecasContext'
        , function ($scope, $state, $stateParams, $http, recursosContext,bibliotecasContext) {
               
               $http.get(bibliotecasContext + '/' + $state.params.bibliotecaId + '/' + recursosContext).then(function(response){
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

