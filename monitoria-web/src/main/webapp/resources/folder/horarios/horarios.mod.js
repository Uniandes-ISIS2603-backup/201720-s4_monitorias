(function (ng) {
    //Definición del módulo de estudiantes
var mod = ng.module("HorariosModule", ['ui.router']);

      mod.constant("horariosContext", "api/horarios");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/horarios/';
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/horariosList");
            $stateProvider.state('horarios',{
             url:'/horarios',
             abstract: true,
             views:{
                 'mainView':{
                     templateUrl: basePath + 'horarios.html',
                     controller:'horariosCtrl',
                     controllerAs:'ctrl'
                 }
             }
            }).state('horariosList', {
                //Url que aparecerá en el navegador
                url: '/list',
                parent:'horarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'horarios.list.html'         
                    }
                }

            }).state('horarioDetail', {
                        url:'/{horarioId:int}/detail',
                        parent:'horarios',
                        param:{
                            horarioId: null
                        },
                        views:{
                            'detailView':{
                                templateUrl:basePath + 'horarios.detail.html',
                                controller: 'horariosCtrl',
                                controlerAs:'ctrl'
                            }  
                        }
            }).state('horariosCreate',{
                        url:'/create',
                        parent:'estudiantes',
                     views:{
                         'detailView':{
                             templateUrl: basePath + 'create/estudiantesCreate.html',
                             controller: 'estudianteCreateCtrl'
                         }

                     }
            });
        }
    ]);

}
)(window.angular);