(function (ng) {
    var mod = ng.module("IdiomasModule");
    mod.constant("idiomasContex","api/idiomas");
    mod.controller("idiomasUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'idiomasContext',
        function ($scope, $state, $stateParams, $http, idiomasContext) {
             if($state.params.idiomaId !== undefined){
                   $http.get(idiomasContext +'/' + $state.params.idiomaId).then(function (response){
                       var idioma = response.data;
                       $scope.idiomaIdioma = idioma.idioma;
                       $scope.idiomaRecursos = idioma.recursos;
                   });
            }
             $scope.updateIdioma = function(){
             $http.put( idiomasContext +'/' + $state.params.idiomaId,
             {
                    idioma: $scope.idiomaIdioma
                    ,recursos: $scope.idiomaRecursos
                    }).then(function (response){
                   
                    $state.go('idiomasList',{idiomaId: response.data.id},{reload: true});                     });
               };
        }
    ]);
}
)(angular);

