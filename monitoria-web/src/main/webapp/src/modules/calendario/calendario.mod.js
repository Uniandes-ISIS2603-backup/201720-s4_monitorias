(function (ng) {
    //Definición del módulo de estudiantes
var mod = ng.module("CalendarioModule", ['ui.router']);

   
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/calendario/';
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/calendario.html");
            $stateProvider
             .state('calendario',{
             url:'/cal_horarios',
          
             views:{
                
                 'mainView':{
                     templateUrl: basePath + 'calendario.html',
                     controller:'calendarCtrl'
                     
                 }
             }
            });
        }
    ]);

}
)(window.angular);