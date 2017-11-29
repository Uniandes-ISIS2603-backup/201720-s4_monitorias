(function (ng) {

    var mod = ng.module("HorariosModule");
    var fechainit=Cookies.get('fechainicio');
    var numero= parseInt(fechainit);
    var fecha= new Date(numero);
     
    date = fecha;
    date = date.getUTCFullYear() + '-' +
            ('00' + (date.getUTCMonth() + 1)).slice(-2) + '-' +
            ('00' + date.getUTCDate()).slice(-2) + 'T' +
            ('00' + date.getUTCHours()).slice(-2) + ':' +
            ('00' + date.getUTCMinutes()).slice(-2) + ':' +
            ('00' + date.getUTCSeconds()).slice(-2); 
 
    
    
     var fechafin=Cookies.get('fechaFin');
    var numerofin= parseInt(fechafin);
    var fechaf= new Date(numerofin);
    var año= fechaf.getFullYear();
    
    
    
    mod.constant("horariosContex","api/horarios");
    
    mod.controller("horarioCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'horariosContext', function ($scope, $state, $stateParams, $http, horariosContext) {
            
                 $scope.createHorario = function(){
                   $http.post(horariosContext,
                           {
                               
                                horaInicio: date,
                                horaFin: date2,
                                disponibilidad:false  
                                
                            }).then(function (response){
                               console.log(date);
                                console.log(date2);
                                
                                $state.go('horariosList',{horarioId: response.data.id},{reload: true});
                            });
               }
                 
        }
    ]);
}
)(angular);

