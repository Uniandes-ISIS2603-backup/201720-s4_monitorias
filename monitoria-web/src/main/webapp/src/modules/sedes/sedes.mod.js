(function (ng) {
    //Definición del módulo
var mod = ng.module("SedesModule", ['ui.router']);

      mod.constant("sedesContext", "api/sedes");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/sedes/';
            var basePathRecursos = 'src/modules/salones';
           
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/sedesList");
            $stateProvider.state('sedes',{
             url:'/sedes',
             abstract: true,
             views:{
                 'mainView':{
                     templateUrl: basePath + 'sedes.html',
                     controller:'sedesCtrl',
                     controllerAs:'ctrl'
                 }
             }
            }).state('sedesList', {
                //Url que aparecerá en el navegador
                url: '/list',
                parent:'sedes',
                views: {
                    'listView': {
                        templateUrl: basePath + 'sedes.list.html'         
                    }
                }

            }).state('sedesCreate',{
                        url:'/create',
                        parent:'sedes',
                     vierws:{
                         'detailView':{
                             templateUrl: basePath + '/new/sedes.create.html',
                             controller: 'sedeNewCtrl'
                         }

                     }
            }).state('sedeDetail', {
                        url:'/{sedeName:String}/detail',
                        parent:'sedes',
                        param:{
                            sedeName: null
                        },
                        views:{
                            'listView':{
                                templateUrl:basePathRecursos + 'salones.list.html',
                                controller: 'sedesCtrl',
                                controlerAs:'ctrl'
                            },
                            'detailView':{
                                templateUrl:basePath + 'sedes.detail.html',
                                controller: 'sedesCtrl',
                                controlerAs:'ctrl'
                            }
                            
                        }
            });
        }
    ]);
})(window.angular);

