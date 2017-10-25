(function (ng) {
    //Definición del módulo de estudiantes
var mod = ng.module("HorariosModule", ['ui.router']);

      mod.constant("horarioContext", "api/horarios");
    
    //Configuración de los estados del módulo 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            //En basePath se encuentran los templates y controladores del módulo
            var basePath = 'src/modules/horarios/';
            //Mostrar la lista de estudiantes será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/horarios");
            //Definición del estado 'estudiante' donde se listan los monitores.
            $stateProvider.state('horariosList', {
                //Url que aparecerá en el navegador
                url: '/horarios',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'getHorarios.html',
                        controller: 'horariosCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
        }
    ]);

})(window.angular);

