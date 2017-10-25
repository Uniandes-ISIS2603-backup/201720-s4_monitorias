(function (ng) {

    var mod = ng.module("HorariosModule");

    mod.constant("horariosContext","api/horarios");
    
    mod.controller("horariosCtrl", ['$scope', '$state', '$stateParams', '$http', 'horariosContext', function ($scope, $state, $stateParams, $http, context) {
               
                $http.get(context).then(function (response) {
                $scope.horarios = response.data;
            });
             this.deleteHorarios = function(id) {
                 return $http.delete(context + "/" + id)
                            .then(function () {
                            });
            }
        }
    ]);
})(window.angular);

