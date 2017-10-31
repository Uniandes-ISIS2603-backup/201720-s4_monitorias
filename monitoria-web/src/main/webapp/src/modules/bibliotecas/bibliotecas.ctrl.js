(function (ng) {

    var mod = ng.module("BibliotecasModule");

    mod.constant("bibliotecasContex","api/bibliotecas");
    
    mod.controller("bibliotecasCtrl", ['$scope', '$state', '$stateParams', '$http', 'bibliotecasContext', function ($scope, $state, $stateParams, $http, bibliotecasContext) {
               
              $scope.buscar = function (){
                  $scope.bibliotecasRecords = $scope.bibliotecasRecords.filter(function(biblioteca){
                       
                    return biblioteca.name.match(".*" + $scope.bibliotecaNameBuscada + ".*")
                   });
               }
               
               $http.get(bibliotecasContext).then(function(response){
                   $scope.bibliotecasRecords = response.data;
               });
               
               if($state.params.bibliotecaId !== undefined){
                   $http.get(bibliotecasContext + '/' + $state.params.bibliotecaId).then(function (response){
                       $scope.recursosRecords = response.data.recursos;
                       $scope.varBibliotecaId = $state.params.bibliotecaId;
                       $scope.currentBiblioteca = response.data;
                   });
               }
        }
    ]);
}
)(angular);

