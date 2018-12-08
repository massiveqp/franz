angular.module("piclubApp", []).controller("MainCtrl", ['$http', function($http){
	var me = this;
	
	me.items = [];
	$http.get('./activities').then(function(response) {
		me.items = response.data;
	}, function(errResponse) {
		console.error('Error while fetch activities');
	});

	me.feed = function() {
		//request acts

		//render

	};

	me.createAct = function(){
		console.log('create act...');
		//submit
	};

	me.upload = function(){
		console.log('uploading');
	};

	me.modifyAct = function () {
      console.log('modify act...')
    };

}]);