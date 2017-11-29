(function (ng) {

    var mod = ng.module("HorariosModule");
    var fechainit=Cookies.get('fechainicio');
    var numero= parseInt(fechainit);
    var fecha= new Date(numero);
      var añoi= fecha.getFullYear();
  
    var diai= fecha.getDay();
    var horai= fecha.getHours();
    var minutoi= fecha.getMinutes();

    date = fecha;
  
 
    
    
     var fechafin=Cookies.get('fechaFin');
    var numerofin= parseInt(fechafin);
    var fechaf= new Date(numerofin);
    var año= fechaf.getFullYear();
    var mes= fechaf.getUTCMonth()+1;
    var mesf;
    if(mes<10)
    {
        mesf="0"+mes;
    }
    var diaa= fechaf.getDay();
    var dia;
    if(diaa<10){
       dia="0"+diaa;
    }
    var hora= fechaf.getHours();
    var minuto= fechaf.getMinutes();
    var stringfecha=""+año+"-"+mesf+"-"+dia+"T"+hora+":"+minuto+":00-05:00";
     var stringfechai=""+añoi+"-"+mesf+"-"+dia+"T"+horai+":"+minutoi+":00-05:00";
    mod.constant("horariosContex","api/horarios");
    
    mod.controller("horarioCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'horariosContext', function ($scope, $state, $stateParams, $http, horariosContext) {
            
                 $scope.createHorario = function(){
                   $http.post(horariosContext,
                           {
                               
                                horaInicio: ""+stringfechai,
                                horaFin: ""+stringfecha,
                                disponibilidad:false  
                                
                            }).then(function (response){
                               console.log(stringfechai);
                                console.log(stringfecha);
                                
                                $state.go('horariosList',{horarioId: response.data.id},{reload: true});
                            });
               }
                 
        }
    ]);
}
)(angular);

