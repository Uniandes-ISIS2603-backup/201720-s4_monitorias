(function (ng) {

    var mod = ng.module("CalendarioModule");


    
    mod.controller('calendarCtrl',
    
    
    function ($scope) {
  $scope.today = function() {
    $scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function() {
    $scope.dt = null;
  };

  $scope.options = {
    customClass: getDayClass,
    minDate: new Date(),
    showWeeks: true
  };
 $scope.options.minDate = $scope.options.minDate ? null : new Date();
  // Disable weekend selection
  function disabled(data) {
    var date = data.date,
      mode = data.mode;
    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
  }
   


  $scope.setDate = function(year, month, day) {
    $scope.dt = new Date(year, month, day);
  };

  var tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  var afterTomorrow = new Date(tomorrow);
  afterTomorrow.setDate(tomorrow.getDate() + 1);
  $scope.events = [
    {
      date: tomorrow,
      status: 'full'
    },
    {
      date: afterTomorrow,
      status: 'partially'
    }
  ];
 $scope.toggleMin = function() {
    $scope.options.minDate = $scope.options.minDate ? null : new Date();
  };

  $scope.toggleMin();
  function getDayClass(data) {
    var date = data.date,
      mode = data.mode;
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i = 0; i < $scope.events.length; i++) {
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  }
 
   
for(i=8;i<22;i++){
     var x = document.createElement("OPTION");
    x.setAttribute("value", i);
    var t = document.createTextNode(i+"");
    x.appendChild(t);
    document.getElementById("hora").appendChild(x);
  
}
var min=0;
      var max=60;
 for( min;min<max;min+=5){
     var x = document.createElement("OPTION");
    x.setAttribute("value", min);
    var t = document.createTextNode(min+"");
    x.appendChild(t);
    document.getElementById("minuto").appendChild(x);
      }
  
  
     $scope.elegirHorario=function (data)
  {
var selectid= document.getElementById("hora");
var opciones=selectid.options[selectid.selectedIndex];
var hora=opciones.getAttribute('value');
var selectid2= document.getElementById("minuto");
var opciones2=selectid2.options[selectid2.selectedIndex];
var hora2=opciones2.getAttribute('value');

var selectid= document.getElementById("tipo");
var opciones=selectid.options[selectid.selectedIndex];
var duracion=opciones.getAttribute('value');




var fecha= $scope.dt;
fecha.setMinutes(hora2)
fecha.setHours(hora);
fecha.setMilliseconds(00);
fecha.setSeconds(00);
var dia= $scope.dt;

var fechafin = new Date(fecha.getTime());
  var minutos=fechafin.getMinutes();
  

var selectid= document.getElementById("tipo");
var opciones=selectid.options[selectid.selectedIndex];
var duracion=opciones.getAttribute('value');
if((minutos+duracion)>60)
{
    fechafin.setHours(fecha.getHours()+1);
    minutos-=duracion;
    fechafin.setMinutes(minutos);
}


  }
});
}
)(angular);
       