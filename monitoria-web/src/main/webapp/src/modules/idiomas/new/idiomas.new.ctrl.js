(function (ng) {

    var mod = ng.module("IdiomasModule");

    mod.constant("idiomasContex","api/idiomas");
    
    mod.controller("idiomaNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'idiomasContext', function ($scope, $state, $stateParams, $http, idiomasContext) {
               
                   $scope.createIdioma = function(){
                   $http.post(idiomasContext,
                           {
                                idioma: $scope.idiomaIdioma
                               
                            }).then(function (response){
                               
                                $state.go('idiomasList',{idiomaId: response.data.id},{reload: true});
                            });
               }
        }
    ]);
}
)(angular);

