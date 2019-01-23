angular.module("piclubApp", []).controller("ViewCtrl", ['$http', function($http){
	var me = this;

    me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];

	//retrieve act info & enroll people
    me.act = {};
    var pathArray = window.location.pathname.split('/'),
        actId = pathArray[2],
        url = '/activity/' + actId;

    $http.get(url).then(function(response) {
        me.act = response.data;

        // adapt price and place
        me.act.price /= 100.0;
        for (var i = 0; i < me.places.length; i++) {
            if (me.places[i].id !== me.act.place) continue;
            me.act.place = me.places[i].label;
            break;
        }
    }, function(errResponse) {
        console.error('Error while fetch activity ' + actId);
    });


	me.upload = function() {
		console.log('uploading');
	};

	me.modifyAct = function() {
      console.log('modify act...')
    };

	me.deleteAct = function () {

    }
}]);