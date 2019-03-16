angular.module("piclubApp", []).controller("FinanceCtrl", ['$http', function($http){
	var me = this;

	me.payStatusEnum = [
        '未付款',
        '已付款'
    ];

	//retrieve act
    me.act = {};
    var pathArray = window.location.pathname.split('/'),
        actId = pathArray[2],
        url = '/activity/' + actId;

    me.actId = actId;


    // retrieve all enrollments
    me.enrollments = {};
    me.refreshEnroll = function() {
        $http.get('/enrollments?actId=' + me.actId).then(function (resp) {
            me.enrollments = resp.data;

            //todo create a codeStore
            for (var i = 0; i < me.enrollments.length; i++) {
                var code = me.enrollments[i].payStatus;
                me.enrollments[i].payStatus = me.payStatusEnum[code];
            }

        }, function (reason) {
            alert('获取报名信息失败！');
            console.log('Error when fetching enrollments!');
        });
    };

    me.refreshEnroll();
    
    me.togglePayStatus = function (enrollmentId) {
        
    };
    
    me.toggleCheckedIn = function () {

    };
    
    me.calculate =function () {

    }
}]);