(function (ng) {

    var mod = ng.module("recursosModules");

    mod.constant("recursosContext","recursos");
    mod.constant("bibliotecasContext", "api/bibliotecas");
    
    mod.controller("recursosCtrl", ['$scope', '$state', '$stateParams', '$http', 'recursosContext','bibliotecasContext'
        , function ($scope, $state, $stateParams, $http, recursosContext,bibliotecasContext) {
               
               $http.get(bibliotecasContext + '/' + $state.params.bibliotecaId + '/' + recursosContext).then(function(response){
                   $scope.recursosRecords = response.data;
               });
               
               if($state.params.recursoId !== undefined){
                   $http.get(bibliotecasContext +'/'+ $state.params.bibliotecaId + '/' + recursosContext + '/' + $state.params.recursoId).then(function (response){
                       $scope.currentRecurso = response.data;
                       console.log($scope.currentRecurso);
                   });
               }
        }
    ]);
})(angular);

