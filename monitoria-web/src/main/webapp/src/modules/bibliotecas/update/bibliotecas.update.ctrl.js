(function (ng) {

    var mod = ng.module("BibliotecasModule");

    mod.constant("bibliotecasContex","api/bibliotecas");
    mod.constant("recursosContext","recursos");
    mod.controller("bibliotecasUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'bibliotecasContext','recursosContext',
        function ($scope, $state, $stateParams, $http, bibliotecasContext,recursosContext) {
              
               $http.get(bibliotecasContext+ '/' + $state.params.bibliotecaId ).then(function(response){
                   var biblioteca = response.data;
                   $scope.bibliotecaName = biblioteca.name;
                   $scope.bibliotecaUbicacion = biblioteca.ubicacion;
                   
               });
               
               $http.get(bibliotecasContext+ '/' + $state.params.bibliotecaId +'/'+recursosContext).then(function(response){
                   $scope.bibliotecaRecursos = response.data;
                  
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

