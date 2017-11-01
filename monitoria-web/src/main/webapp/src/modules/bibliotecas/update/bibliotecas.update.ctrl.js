(function (ng) {

    var mod = ng.module("BibliotecasModule");

    mod.constant("bibliotecasContex","api/bibliotecas");
    
    mod.controller("bibliotecasUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'bibliotecasContext',
        function ($scope, $state, $stateParams, $http, bibliotecasContext) {
              
               $http.get(bibliotecasContext+ '/' + $state.params.bibliotecaId ).then(function(response){
                   var biblioteca = response.data;
                   $scope.bibliotecaName = biblioteca.name;
                   $scope.bibliotecaUbicacion = biblioteca.ubicacion;
                   $scope.bibliotecaRecursos = biblioteca.recursos;
               });
       
               $scope.updateBiblioteca = function(){
                   $http.put(bibliotecasContext + '/' + $state.params.bibliotecaId,{
                       name: $scope.bibliotecaName
                       ,ubicacion: $scope.bibliotecaUbicacion
                       ,recursos: $scope.bibliotecaRecursos
                   }).then(function(response){
                       $state.go('bibliotecasList',{bibliotecaId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);

