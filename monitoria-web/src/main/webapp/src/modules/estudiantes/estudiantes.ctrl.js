(function (ng) {

    var mod = ng.module("EstudiantesModule");

    mod.constant("estudiantesContext","api/estudiantes");
    
    mod.controller("estudiantesCtrl", ['$scope', '$state', '$stateParams', '$http', 'estudiantesContext', function ($scope, $state, $stateParams, $http, context) {
               
                $http.get(context).then(function (response) {
                $scope.estudiantes = response.data;
            });
             this.deleteEstudiante = function(id) {
                 return $http.delete(context + "/" + id)
                            .then(function () {
                            });
            }
        }
    ]);
})(window.angular);

