(function (ng) {

    var mod = ng.module("recursosNewModules");

    mod.constant("recursosContext","recursos");
    mod.constant("bibliotecasContext", "api/bibliotecas");
    
    mod.controller("recursosCtrl", ['$scope', '$state', '$stateParams', '$http', 'recursosContext','bibliotecasContext'
        , function ($scope, $state, $stateParams, $http, recursosContext,bibliotecasContext) {
               $scope.createRecurso = function(){
                   $http.post(bibliotecasContext + '/' + $state.params.bibliotecaId + '/' + recursosContext,
                           {
                                name: $scope.recursoName
                                ,editorial: $scope.recursoEditorial
                                ,disponibilidad: $scope.recursoDisponibilidad
                                ,biblioteca: $scope.recursoBiblioteca
                                ,idioma: $scope.recursoIdioma
                            }).then(function (response){
                                //autor create successfully
                                $state.go('recursosList',{recursoId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
})(angular);

