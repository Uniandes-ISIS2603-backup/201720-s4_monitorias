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
                url: '/{salonId:int}/horarios_list',
                parent:'horarios',
               param:{
                    monitoriaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'horarios.list.html'         
                    }
                }

            }).state('horarioDetail', {
                        url:'/{horarioId:int}/horario_detail',
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
                        url:'/horario_create',
                        parent:'horarios',
                     views:{
                         'detailView':{
                             templateUrl: basePath + 'create/horariosCreate.html',
                             controller: 'horarioCreateCtrl'
                         }

                     }
            }).state('horarioDelete', {
                        url:'/delete_horario/{horarioId:int}',
                        parent: 'horarios',
                        param:{
                            horarioId: null
                        },
                        views:{
                            'detailView':{
                                templateUrl: basePath + '/delete/horarios.delete.html',
                                controller: 'horarioDeleteCtrl'
                            }
                        }
            }).state('horarioUpdate',{
                url:'/{horarioId:int}/update_horario'
                ,parent:'horarios'
                ,param:{
                   horarioId: null
                }
                ,views:{
                    'listView':{
                        templateUrl: basePath + 'update/horarios.update.html'
                        ,controller:'horarioUpdateCtrl'
                    }
                }
            });
        }
    ]);

}
)(window.angular);