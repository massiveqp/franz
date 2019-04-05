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

    me.getAct = function () {
        $http.get('/activity/' + actId).then(function (resp) {
            console.log(resp.data);
            me.act = resp.data;
        }, function (reason) {
            console.log(reason);
        });
    };
    me.getAct();


    // retrieve all enrollments
    me.enrollments = [];
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
            console.log('Error when fetching enrollments! reason:' + reason);
        });
    };

    me.refreshEnroll();
    
    me.togglePayStatus = function (enrollment) {
        console.log('togglePayStatus ' + enrollment);
        if (enrollment.payStatus === 1) enrollment.payStatus = 0;
        else enrollment.payStatus = 1;

        console.log(enrollment);

        $http.post('/enrollment/' + me.actId + '/payStatus', enrollment).then(function (resp) {
            console.log(resp);
            me.refreshEnroll();
        }, function (reason) {
            alert('改变支付状态失败！')
            console.log(reason)
        });
    };
    
    me.toggleCheckedIn = function (enrollment) {

    };

    //
    me.calc = {};
    me.calculate = function () {
        var price = me.act.price / 100;
        var manCount = me.enrollments.length;

        var actIncome = price * manCount - (me.calc.cost === undefined ? 0 : me.calc.cost);
        var incomeToTransfer = actIncome + (me.calc.otherIncome === undefined ? 0 : me.calc.otherIncome);

        alert('活动创收：' + actIncome + '\n应转总计：' + incomeToTransfer);
    }

}]);