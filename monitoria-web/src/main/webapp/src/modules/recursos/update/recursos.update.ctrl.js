(function (ng) {

    var mod = ng.module("recursosModules");

    mod.constant("recursosContext","recursos");
    mod.constant("bibliotecasContext", "api/bibliotecas");
        mod.constant("idiomasContext", "api/idiomas");

    mod.controller("recursosUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'recursosContext','bibliotecasContext','idiomasContext'
        , function ($scope, $state, $stateParams, $http, recursosContext,bibliotecasContext,idiomasContext) {
              
            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
            });
            
            if($state.params.recursoId !== undefined){
                   $http.get(bibliotecasContext +'/'+ $state.params.bibliotecaId + '/' + recursosContext + '/' + $state.params.recursoId).then(function (response){
                       $scope.currentRecurso = response.data;
                   });
            }
            
            
            $scope.updateRecurso = function(){
                   $http.put(bibliotecasContext + '/' + $state.params.bibliotecaId + '/' + recursosContext +'/'+ $state.params.recursoId,
                           {
                                name: $scope.recursoName
                                ,editorial: $scope.recursoEditorial
                                ,idioma: {idioma:$scope.recursoIdioma}
                                ,disponibilidad: $scope.recursoDisponibilidad
                            }).then(function (response){
                                //autor create successfully
                                $state.go('recursosList',{recursoId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
})(angular);

