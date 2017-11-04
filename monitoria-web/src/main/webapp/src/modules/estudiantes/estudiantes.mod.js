(function (ng) {
    //Definición del módulo de estudiantes
var mod = ng.module("EstudiantesModule", ['ui.router']);

      mod.constant("estudiantesContext", "api/estudiantes");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/estudiantes/';
            //Mostrar la lista de estudiantes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/estudiantes");
            //Definición del estado 'monitor' donde se listan los monitores.
            $stateProvider.state('estudiantesList', {
                //Url que aparecerá en el navegador
                url: '/estudiantes',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'estudiantes.list.html',
                        controller: 'estudiantesCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
        }
    ]);

})(window.angular);

