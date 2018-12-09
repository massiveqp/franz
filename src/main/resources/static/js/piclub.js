angular.module("piclubApp", []).controller("MainCtrl", ['$http', function($http){
	var me = this;
	var act = {};

	me.acts = [];
	$http.get('./activities').then(function(response) {
		me.acts = response.data;
	}, function(errResponse) {
		console.error('Error while fetch activities');
	});

	me.feed = function() {
		//request acts

		//render

	};

	me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];
	me.selectedPlaceId = 1;

	me.createAct = function(){
		console.log('create act...');
		me.act.place = me.selectedPlaceId;
		console.log(me.act);
	};

	me.upload = function(){
		console.log('uploading');
	};

	me.modifyAct = function() {
      console.log('modify act...')
    };

}]);