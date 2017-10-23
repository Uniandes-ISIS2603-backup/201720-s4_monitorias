(function (ng) {
    //Definición del módulo
var mod = ng.module("ActividadsModule", ['ui.router']);

      mod.constant("actividadesContext", "api/actividades");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
          
            var basePath = 'src/modules/actividades/';
            var basePathRecursos = 'src/modules/recursos';
           
            $urlRouterProvider.otherwise("/actividadesList");
            $stateProvider.state('actividades',{
             url:'/actividades',
             abstract: true,
             views:{
                 'mainView':{
                     templateUrl: basePath + 'actividades.html',
                     controller:'actividadesCtrl',
                     controllerAs:'ctrl'
                 }
             }
            }).state('actividadesList', {
              
                url: '/list',
                parent:'actividades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'actividades.list.html'         
                    }
                }

            }).state('actividadesCreate',{
                        url:'/create',
                        parent:'actividades',
                     vierws:{
                         'detailView':{
                             templateUrl: basePath + '/new/actividades.new.html',
                             controller: 'actividadesNewCtrl'
                         }

                     }
            }).state('actividadDetail', {
                        url:'/{tareaAsignadaActividad:String}/detail',
                        parent:'actividades',
                        param:{
                            tareaAsignadaActividad: null
                        },
                        views:{
                            'listView':{
                                templateUrl:basePathRecursos + 'recursos.list.html',
                                controller: 'actividadesCtrl',
                                controlerAs:'ctrl'
                            },
                            'detailView':{
                                templateUrl:basePath + 'actividades.detail.html',
                                controller: 'actividadesCtrl',
                                controlerAs:'ctrl'
                            }
                            
                        }
            });
        }
    ]);

})(window.angular);