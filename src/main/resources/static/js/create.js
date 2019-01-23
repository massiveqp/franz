angular.module("piclubApp", []).controller("CreateCtrl", ['$http', function($http){
	var me = this;

	me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];
	me.selectedPlaceId = 1;

	// Create act
	me.createAct = function() {
		me.act.place = me.selectedPlaceId;

		var dtp = $('#dtp')[0];
		me.act.startTime = dtp.value;
		console.log(me.act);

		$http.post('/activities', me.act).then(function succ(response) {
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