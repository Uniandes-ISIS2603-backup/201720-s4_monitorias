(function (ng) {

    var mod = ng.module("MonitoresModule");

    mod.constant("monitoresContext","api/monitores");
    
    mod.controller("monitoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoresContext', function ($scope, $state, $stateParams, $http, context) {
               var listMonitores={};
                $http.get(context).then(function (response) {  
                     listMonitores=response.data;                   
                $scope.monitoresRecords = listMonitores;
            });
            if (($state.params.monitorCodigo !== undefined)&& ($state.params.monitorCodigo !== null)) {
                $http.get(context + '/' + $state.params.monitorCodigo).then(function (response) {
                    var a=response.data;
                    if(a.tipo==1)
                    {a.tipo="Profesor";}
                    if(a.tipo==2)
                    { a.tipo="Monitor especializado";}
                    if(a.tipo==3){
                        a.tipo="Alumno Avanzado";}
                    
                    $scope.currentMonitor = a;
                });
            }
        }
    ]);
})(window.angular);

