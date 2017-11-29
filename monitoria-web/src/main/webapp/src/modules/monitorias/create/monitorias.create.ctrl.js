/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("MonitoriasModule");

    mod.constant("monitoriasContex","api/monitorias");
    
    mod.controller("monitoriasCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'monitoriasContext', function ($scope, $state, $stateParams, $http, monitoriasContext) {
               
                 $scope.createMonitoria = function(){
                   $http.post(monitoriasContext,
                           {
                                nombreMonitor: $scope.nombreMonitor,
                                nombreEstudiante:$scope.nombreEstudiante
                                ,tipo: $scope.tipo,
                                estado:$scope.estado
                                
                            }).then(function (response){
                                //autor create successfully
                                $state.go('monitoriasList',{monitoriaId: response.data.id},{reload: true});
                            });
               }
               
               
               //////////////////////////////////////////////////////////////WIZARD/////////////
                    $(document).ready(function () {
                            //Initialize tooltips
                            jQuery('.nav-tabs > li a[title]').tooltip();

                            //Wizard
                            $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

                                var $target = $(e.target);

                                if ($target.parent().hasClass('disabled')) {
                                    return false;
                                }
                            });

                            $(".next-step").click(function (e) {

                                var $active = $('.wizard .nav-tabs li.active');
                                $active.next().removeClass('disabled');
                                nextTab($active);

                            });
                            $(".prev-step").click(function (e) {

                                var $active = $('.wizard .nav-tabs li.active');
                                prevTab($active);

                            });
                    });

                    function nextTab(elem) {
                        $(elem).next().find('a[data-toggle="tab"]').click();
                    }
                    function prevTab(elem) {
                        $(elem).prev().find('a[data-toggle="tab"]').click();
                    }
               
        }
    ]);
}
)(angular);
