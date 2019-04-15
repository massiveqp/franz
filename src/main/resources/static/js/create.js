angular.module("piclubApp", []).controller("CreateCtrl", ['$http', function($http){
	var me = this;

	me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];
	me.selectedPlaceId = 1;

    me.halfPrices = [
        {label: '无', id: 0},
        {label: '有', id: 1}
    ];
    me.selectedHalfPrice = 0;

	// Create act
	me.createAct = function() {
		me.act.place = me.selectedPlaceId;
		me.act.halfPrice = me.selectedHalfPrice;

		var dtp = $('#dtp')[0];
		me.act.startTime = dtp.value;

		var actToPost = angular.copy(me.act);
		actToPost.price *= 100;

		$http.post('/activities', actToPost).then(function succ(response) {
                alert('创建成功');
                // $('.alert').alert();
                // $('.alert').alert('close');
                window.location.href = '/';
            }, function fail(response) {
                alert('创建失败');
		        console.log(response);
        });
	};

	me.upload = function() {
		console.log('uploading');
	};

	me.modifyAct = function() {
      console.log('modify act...')
    };

	me.deleteAct = function () {

    }
}]);