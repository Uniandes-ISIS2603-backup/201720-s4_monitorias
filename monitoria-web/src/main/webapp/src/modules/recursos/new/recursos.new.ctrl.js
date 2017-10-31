(function (ng) {

    var mod = ng.module("recursosModules");

    mod.constant("recursosContext","recursos");
    mod.constant("bibliotecasContext", "api/bibliotecas");
        mod.constant("idiomasContext", "api/idiomas");

    mod.controller("recursosNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'recursosContext','bibliotecasContext','idiomasContext'
        , function ($scope, $state, $stateParams, $http, recursosContext,bibliotecasContext,idiomasContext) {
              
            $http.get(idiomasContext).then(function (response) {
                $scope.idiomasRecords = response.data;
            });
            
            $scope.createRecurso = function(){
                   $http.post(bibliotecasContext + '/' + $state.params.bibliotecaId + '/' + recursosContext,
                           {
                                name: $scope.recursoName
                                ,editorial: $scope.recursoEditorial
                                ,idioma: {idioma:$scope.recursoIdioma}
                            }).then(function (response){
                                //autor create successfully
                                $state.go('recursosList',{recursoId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
})(angular);

