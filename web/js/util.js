/**
 * 获取日期yyyy-MM-dd
 * @param obj
 * @returns {*}
 */
function getDateStr(obj) {
    if (obj == null) return "";
    var year = (parseInt(obj.year) + 1900).toString();
    var mouth = parseInt((obj.month) + 1).toString();
    if (mouth.length != 2) {
        mouth = 0 + mouth;
    }
    //  dataLeftCompleting(2, "0", mouth.toString()).toString();
    var day = parseInt((obj.date)).toString();
    //ataLeftCompleting(2, "0", day.toString()).toString();
    if (day.length != 2) {
        day = 0 + day;
    }
    var time1 = year + "-" + mouth + "-" + day;
    return time1;
}

/*时间格式转换*/
function Todate(num) { //Fri Oct 31 18:00:00 UTC+0800 2008
    num = num + "";
    var date = "";
    var month = new Array();
    month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4; month["May"] = 5; month["Jan"] = 6;
    month["Jul"] = 7; month["Aug"] = 8; month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
    var week = new Array();
    week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四"; week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
    str = num.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2];
    return date;
}

/**/
 function toQueryString(obj) {

    var qstring = "?"
    for (var k in obj) {
        qstring += k + "=" + obj[k] + "&"
    }

    return qstring

}
var toHttpParams = (function(){
    function serialize(key, value, param) {
        if(Array.isArray(value)) {
            for(var i = 0; i < value.length; i++) {
                serialize(key + '[' + i + ']', value[i], param)
            }
        } else if(value != undefined && value.constructor === Object) {
            for(var k in value) {
                serialize(key + '.' + k, value[k], param);
            }
        } else {
            param[key] = value;
        }
    }
    function serializeObj(obj) {
        var param = {};
        for(var k in obj) {
            serialize(k, obj[k], param);
        }
        return param;
    }
    return serializeObj;
})();


function printobj(obj){
    //判断说明是数据类型

    // if(typeof obj != "object"){//判断说明不是对象
    //     return obj
    // }
    var qstring = "?"

    for(var k in obj){//遍历对象和数组


        if(typeof obj[k] != "object"){//不是对象
            qstring += k + "=" + obj[k] + "&"
        }
        if(typeof obj[k] == "object"){//判断说明如果是对象则递归
            printobj(obj[k])
            // qstring += k + "=" + obj[k] + "&"

        }




        console.log(k + "\t" + obj[k]);//在控制台输出

    }
    return qstring;
}

/*取数据字典的数据*/
function getDataFromDate(obj) {
    if(obj!=null){
        return obj.name;
    }
    else {
        return ""
    }
}

/*重置*/
function reset() {
    window.location.reload();
}

/**
 * 检验密码格式是否合格(英文大小写，数字，符号任意四选三，长度在8-30之间)
 */
function checkPassWord(password){
    var regex = new RegExp('^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\W_!@#$%^&*`~()-+=]+$)(?![0-9\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\W_!@#$%^&*`~()-+=]{8,30}$');
    if(!regex.test(password)){
        return false;
    }else{
        return true;
    }
}