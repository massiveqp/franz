angular.module("piclubApp", []).controller("MainCtrl", ['$http', function($http){
	var me = this;

    me.places = [
        'Placeholder',  //0
        '漾学堂',        //1
        '猎学堂',        //2
        '常乐书房'       //3
    ];

	// Get Activities
	me.acts = [];
	$http.get('/activities').then(function(response) {
		me.acts = response.data;

		//modify place
        for (var i = 0; i < me.acts.length; i++) {
            me.acts[i].place = me.places[me.acts[i].place];
        }

	}, function(errResponse) {
		console.error('Error while fetch activities');
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