(function (ng) {
    //Definición del módulo
var mod = ng.module("MonitoresModule", ['ui.router']);

      mod.constant("monitoresContext", "api/monitores");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/monitores/';
            //Mostrar la lista de bibliotecas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/monitores");
            //Definición del estado 'monitor' donde se listan los monitores.
            $stateProvider.state('monitoresList', {
                //Url que aparecerá en el navegador
                url: '/monitores',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'getMonitores.html',
                        controller: 'monitoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('monitorDelete', {
                url: '/monitores/delete/{monitorCodigo:int}',
                param: {
                    monitorCodigo: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'delete/monitor.delete.html',
                        controller: 'monitorDeleteCtrl'
                    }
                }
            }).state('monitorDetail', {
                url: '/monitores/detail/{monitorCodigo:int}',
                param: {
                    monitorCodigo: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'detail.monitor.html',
                        controller: 'monitoresCtrl'
                    }
                }
            }).state('monitorUpdate', {
                url: '/monitores/update/{monitorCodigo:int}',
                param: {
                    monitorCodigo: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'update/monitor.update.html',
                        controller: 'monitoresUpdateCtrl'
                    }
                }
            }).state('monitorCreate', {
                url: '/monitores/create',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/monitor.create.html',
                        controller: 'monitoresCreateCtrl'
                    }
                }
            });
        }
    ]);

})(window.angular);

