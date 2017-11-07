(function (ng) {
    var mod = ng.module("IdiomasModule");
    mod.constant("idiomasContex","api/idiomas");
    mod.controller("idiomasUpdateCtrl", ['$scope', '$state', '$stateParams', '$http', 'idiomasContext',
        function ($scope, $state, $stateParams, $http, idiomasContext) {
              
               $http.get(idiomasContext+ '/' + $state.params.idiomaId ).then(function(response)
               {
                   var idioma = response.data;
                   $scope.idiomaIdioma = idioma.idioma;
               });
               
               $http.get(idiomasContext+ '/' + $state.params.idiomaId +'/'+recursosContext).then(function(response)
               {
                   $scope.idiomaRecursos = response.data;
                   console.log($scope.idiomaRecursos);
               });
                
               $scope.updateIdioma = function()
               {
                   $http.put(idiomasContex + '/' + $state.params.idiomaId,
                   {
                       idioma: $scope.idiomaIdioma
                       
                   }).then(function(response)
                   {
                       $state.go('idiomasList',{idiomaId:response.data.id},{reload: true});
                   });
               };
        }
    ]);
}
)(angular);

