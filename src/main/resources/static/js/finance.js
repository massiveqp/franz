angular.module("piclubApp", []).controller("EnrollCtrl", ['$http', function($http){
	var me = this;

	me.places = [
        {label: '漾学堂', id: 1},
        {label: '猎学堂', id: 2},
        {label: '常乐书房', id: 3}
    ];

	//retrieve act
    me.act = {};
    var pathArray = window.location.pathname.split('/'),
        actId = pathArray[2],
        url = '/activity/' + actId;

    me.actId = actId;

    $http.get(url).then(function(response) {
        me.act = response.data;

        // adapt price and place
        me.act.price /= 100.0;

        //set place
        me.selectedPlaceId = me.act.place;

        //set date
        var dtp = $('#dtp-editAct')[0];
        dtp.value = me.act.startTime;

    }, function(errResponse) {
        console.error('Error while fetch activity ' + actId);
    });

	// enroll an activity
	me.enrollAct = function() {
		me.act.place = me.selectedPlaceId;

		var dtp = $('#dtp-editAct')[0];
		me.act.startTime = dtp.value;

		// Prevent price changing in the input. Otherwise, if the update fails, the price would be a wrong number.
		var modifiedAct = angular.copy(me.act);
		modifiedAct.price *= 100;

		$http.post('/activity/' + me.actId, modifiedAct).then(function succ(response) {
                alert('修改成功！');

                window.location.href = '/activity/' + me.actId + '/view';
            }, function fail(response) {
                alert('修改失败！');
		        console.log(response);
        });
	};
}]);