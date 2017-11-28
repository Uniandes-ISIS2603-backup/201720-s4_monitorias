(function (ng) {
    //Definición del módulo de estudiantes
var mod = ng.module("EstudiantesModule", ['ui.router']);

      mod.constant("estudiantesContext", "api/estudiantes");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/estudiantes/';
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/estudiantesList");
            $stateProvider.state('estudiantes',{
             url:'/estudiantes',
             abstract: true,
           
             views:{
                 'mainView':{
                     templateUrl: basePath + 'estudiantes.html',
                     controller:'estudiantesCtrl',
                     controllerAs:'ctrl'
                 }
             }
            }).state('estudiantesList', {
                //Url que aparecerá en el navegador
                url: '/estudiantes-list',
                parent:'estudiantes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'estudiantes.list.html'         
                    }
                }

            }).state('estudianteDetail', {
                        url:'/{estudianteId:int}/detail',
                        parent:'estudiantes',
                        param:{
                            estudianteId: null
                        },
                        views:{
                            'detailView':{
                                templateUrl:basePath + 'estudiantes.preview.html',
                                controller: 'estudiantesCtrl',
                                controlerAs:'ctrl'
                            }  
                        }
            }).state('estudianteCreate',{
                        url:'/estudiantes_create',
                        parent:'estudiantes',
                     views:{
                         'detailView':{
                             templateUrl: basePath + 'create/estudiantesCreate.html',
                             controller: 'estudianteCreateCtrl'
                         }

                     }
            }).state('estudianteDelete', {
                        url:'/delete/{estudianteId:int}',
                        parent: 'estudiantes',
                        param:{
                            estudianteId: null
                        },
                        views:{
                            'detailView':{
                                templateUrl: basePath + '/delete/estudiantes.delete.html',
                                controller: 'estudianteDeleteCtrl'
                            }
                        }
            }).state('estudianteUpdate',{
                url:'/{estudianteId:int}/update'
                ,parent:'estudiantes'
                ,param:{
                   estudianteId: null
                }
                ,views:{
                    'listView':{
                        templateUrl: basePath + 'update/estudiantes.update.html'
                        ,controller:'estudiantesUpdateCtrl'
                    }
                }
            });
        }
    ]);

}
)(window.angular);