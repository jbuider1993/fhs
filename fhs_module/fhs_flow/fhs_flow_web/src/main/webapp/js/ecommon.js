var clientWidth = document.documentElement.clientWidth;
var clientHeight = document.documentElement.clientHeight;
// 给string扩展trim方法
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}
String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
            replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
}

//从url获取参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

// 判断是否为空
function isNullOrEnpty(str) {
    return (str == null || str == '');
}

/**
 * 判断是否为空或者是否为udefined是返回true
 *
 * @param str
 * @returns {Boolean}
 */
function isEmpty(str) {
    return (str == null || str == '' || str == undefined || str == 'undefined');
}

// 获取cookie
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else
        return null;
}

// 写入cookie
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires="
        + exp.toGMTString();
}

// 获取grid选中的行，然后执行 funName
function addSelectRowFun(gridId, funName) {
    var row = $('#' + gridId).datagrid('getSelected');
    if (row) {
        funName(row);
    } else {
        $.messager.alert('操作提示', '请选择一条数据进行操作!', 'info');
    }
}

// 将当前的window url改为为url
function sendHref(url) {
    window.location.href = url;

}

//监听esc 关闭dialog
$(function () {
    $(window).keydown(function (event) {
        if (event.keyCode == 27) {
            closeDialog();
        }
    });
});

//弹出一个错误的提示框
function EalertE(message) {
    $.toast({
        heading: '警告',
        text: message,
        showHideTransition: 'fade',
        icon: 'error',
        hideAfter: 2000,
        position: {
            top: 50,
            right: 100
        }
    });
}

// 提示
function Ealert(message) {
    if (message.indexOf('失败') >= 0) {
        $.toast({
            heading: '警告',
            text: message,
            showHideTransition: 'fade',
            icon: 'error',
            hideAfter: 2000,
            position: {
                top: 50,
                right: 100
            }
        });
    } else {
        $.toast({
            heading: '提示',
            text: message,
            showHideTransition: 'fade',
            icon: 'success',
            hideAfter: 2000,
            position: {
                top: 50,
                right: 100
            }
        });
    }
    //$.messager.alert('操作提示', message, 'info'); 重复提示：qiuhang：2018/10/15
}

// 给select 添加option
function appendOptions(jsondata, showKey, valKey, val, selectId) {
    var optionHtml = '<option value="-1">请选择</option>'
    for (var i in jsondata) {
        optionHtml += '<option value="' + jsondata[i][valKey] + '"';
        if (jsondata[i][valKey] == val) {
            optionHtml += ' selected="selected" ';
        }
        optionHtml += '>' + jsondata[i][showKey] + '</option>';
    }
    $('#' + selectId).html(optionHtml);
}

// 设置select的tempvalue项为选中
function setSelectVal(id, tempVal) {

    $('#' + id).children().each(function () {
        if (this.value == tempVal) {

            this.selected = true;
        }
    });
}

// 获取多选控件的值
function getComboGridVal(gridId) {
    return $('#' + gridId).combogrid('getValues') + '';
}

// 传入一个td对象，删除TD对象这一行
function removeRow(obj) {
    $($(obj).parent().parent().remove());
}

// 检查Combobox的值是否为选择的
function checkComboboxVal(boxId, idField) {
    if (idField == undefined) {
        idField = 'value';
    }
    var options = $('#' + boxId).combobox('getData');
    var val = $('#' + boxId).combobox('getValue');
    if (val == '') {
        return true;
    }
    for (var i = 0; i < options.length; i++) {
        if (options[i][idField] == val) {

            return true;
        }
    }
    return false;
}

// 检查Combogrid的值是否在grid里面选择的
function checkCombogridVal(gridId, idField) {
    var g = $('#' + gridId).combogrid('grid'); // get datagrid object
    var vals = $('#' + gridId).combogrid('getValues');
    var json = g.datagrid('getData'); // get the selected row
    var rows = json.rows;
    var row = '';
    var val = '';
    for (var j = 0; j < vals.length; j++) {
        val = vals[j];
        var result = false;
        for (var i = 0; i < rows.length; i++) {
            row = rows[i];
            if (row[idField] == val) {
                result = true;
                break;
            }
        }
        if (!result) {
            return false;
        }
    }
    return true;
}

// 关闭窗口
function closeDialog(id) {
    $('#' + id).dialog('close');
}

// 获取iframe windows对象
function getIframeWin(id) {
    var ifr = document.getElementById(id);
    var win = ifr.window || ifr.contentWindow;
    return win;
}

/**
 * 将字符串转换成json
 */
function strToJson(str) {
    var json = eval('(' + str + ')');
    return json;
}

/**
 * 将字符串转换成json
 */
function str2json(str) {
    return strToJson(str);
}

/**
 * json转换为字符串
 */
function json2str(jsonobj) {
    return JSON.stringify(jsonobj);
}


// 检查combox是否为空
function checkComboxEmpty(setting) {
    for (i in setting) {
        if (isNullOrEnpty($('#' + setting[i].id).combobox('getValue'))) {
            Ealert(setting[i].msg + '不能为空');
            return false;
        }
    }
    return true;
}

// 检查input是否为空
function checkInputEmpty(setting) {
    for (i in setting) {
        if (isNullOrEnpty($('#' + setting[i].id).val())) {
            Ealert(setting[i].msg + '不能为空');
            return false;
        }
    }
    return true;
}

// 设置表单readonly
function setFormReadOnly(formId) {
    setElementReadOnly(formId);
}

// 设置某个元素下面的所有的 表单项只读 readonly
function setElementReadOnly(emId) {
    var em = $('#' + emId);
    em.find('INPUT').each(function () {
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly');
        } else {
            this.readOnly = true;
            if ($(this).hasClass('Wdate')) {
                $(this)[0].ondblclick = $(this)[0].onclick;
                $(this)[0].onclick = function () {
                };
            }
        }
    });
    em.find('SELECT').each(function () {
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly');
        } else if ($(this).hasClass('easyui-combotree')) {
            $('#' + this.id).combotree('readonly', true);
        } else if ($(this).hasClass('easyui-combogrid')) {
            $('#' + this.id).combogrid('readonly', true);
        } else {
            this.readOnly = true;
        }
    });

    em.find('TEXTAREA').each(function () {
        this.readOnly = true;
    });
}

// 取消readyonly
function unsetElementReadOnly(emId) {
    var em = $('#' + emId);
    em.find('INPUT').each(function () {
        $(this).css('background-color', 'white');
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly', false);
        } else {
            this.readOnly = false;
            if ($(this).hasClass('Wdate')) {
                if ($(this)[0].ondblclick != null) {
                    $(this)[0].onclick = $(this)[0].ondblclick;
                    $(this)[0].ondblclick = function () {
                    };
                }
            }
        }
    });
    em.find('SELECT').each(function () {
        $(this).css('background-color', 'white');
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly', false);
        } else if ($(this).hasClass('easyui-combotree')) {
            $('#' + this.id).combotree('readonly', false);
        } else if ($(this).hasClass('easyui-combogrid')) {
            $('#' + this.id).combogrid('readonly', false);
        } else {
            this.readOnly = false;
        }
    });
    em.find('TEXTAREA').each(function () {
        $(this).css('background-color', 'white');
        this.readOnly = false;
    });
}

// 初始化为view
function renderView(elId) {
    var em = $('#' + elId);
    em.find('.Wdate').removeClass('Wdate');
    em.find('.textbox').addClass('noborder');
    em.find('.textbox-icon').hide();
    em.find('INPUT').each(function () {
        $(this).addClass('noborder');
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly', true);
        } else if ($(this).hasClass('easyui-combotree')) {
            $('#' + this.id).combotree('readonly', true);
        } else if ($(this).hasClass('easyui-combogrid')) {
            $('#' + this.id).combogrid('readonly', true);
        } else {
            this.readOnly = true;
            if ($(this).hasClass('Wdate')) {
                $(this)[0].ondblclick = $(this)[0].onclick;
                $(this)[0].onclick = function () {
                };
                $(this).css({
                    background: "none"
                });
                $(this).attr("disabled", "disabled");
            }
        }
    });


    em.find('.uploadifive-button').each(function () {
        $(this).hide();
    });
    em.find('SELECT').each(function () {
        $(this).addClass('noborder');
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly', true);
        } else if ($(this).hasClass('easyui-combotree')) {
            $('#' + this.id).combotree('readonly', true);
        } else if ($(this).hasClass('easyui-combogrid')) {
            $('#' + this.id).combogrid('readonly', true);
        } else {
            this.readOnly = true;
        }
    });
    em.find('TEXTAREA').each(function () {
        $(this).addClass('noborder');
        this.readOnly = true;
        if ($(this).hasClass('ueEditorText')) {
            var _tempUe = UE.getEditor($(this).attr('name'));
            _tempUe.addListener('ready', function () {
                _tempUe.setDisabled();
            });
        }
    });


    $('.chooseUnit').hide();
    $('.uploadify').hide();
    $('.addRowBtn').hide();
    $('.delRowBtn').hide();
    $('.form-field-required').hide();
    // 将checkbox的属性设置为disabled
    $('input[type="checkbox"]').attr("disabled", "disabled");
}

// 检测 滚动条滚动的时候error信息跟着滚动
function initError(divId) {
    $("#" + divId).scroll(
        function () {
            $('.Validform_checktip').each(
                function () {
                    $(this).removeClass('Validform_wrong');
                    $(this).addClass('Validform_right');
                    $(this).html('');
                });
        });
}

// 将view取消
function unsetView(elId) {
    var em = $('#' + elId);
    em.find('.textbox').removeClass('noborder');
    em.find('.textbox-icon').show();
    em.find('INPUT').each(function () {
        $(this).removeClass('noborder');
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly', false);
        } else if ($(this).hasClass('easyui-combotree')) {
            $('#' + this.id).combotree('readonly', false);
        } else if ($(this).hasClass('easyui-combogrid')) {
            $('#' + this.id).combogrid('readonly', false);
        } else {
            this.readOnly = false;
            if ($(this).hasClass('Wdate')) {
                if ($(this)[0].ondblclick != null) {
                    $(this)[0].onclick = $(this)[0].ondblclick;
                    $(this)[0].ondblclick = function () {
                    };
                }
            }
        }
    });
    em.find('SELECT').each(function () {
        $(this).removeClass('noborder');
        if ($(this).hasClass('easyui-combobox')) {
            $('#' + this.id).combobox('readonly', false);
        } else if ($(this).hasClass('easyui-combotree')) {
            $('#' + this.id).combotree('readonly', false);
        } else if ($(this).hasClass('easyui-combogrid')) {
            $('#' + this.id).combogrid('readonly', false);
        } else {
            this.readOnly = true;
        }
    });
    em.find('TEXTAREA').each(function () {
        $(this).removeClass('noborder');
        this.readOnly = false;
    });
    $('.chooseUnit').show();
    $('.uploadify').show();
    $('.delRowBtn').show();
    $('.addRowBtn').show();
    $('.form-field-required').show();
    // 取消checkbox disabled
    $('input[type="checkbox"]').removeAttr("disabled");
}

// 检查显示的输入项参数 参数 form validateform对象以及form的id
function showCheck(form, formId) {
    var finalResult = true;
    $('#' + formId + ' :input').each(
        function () {
            if ($(this).attr('datatype') != null
                && (!$(this).parent().is(':hidden'))) {
                result = form.check(false, '#' + $(this).attr('id'));
                if (!result) {
                    finalResult = result;
                }
            }
        })
    return finalResult;
}

// 右下角显示消息
function showMsg(msg) {
    $.messager.show({
        title: '消息',
        msg: msg,
        showType: 'show'
    });
}

// 显示加载中
function showLoading(msg) {
    return $.messager.progress({
        title: '请稍后',
        msg: msg
    });
}

// 公共的删除方法
function pubDel(gridId, url, id, callback, messager) {
    if (messager == null || messager == undefined) {
        messager = '确认要删除吗？';
    }
    var row = $('#' + gridId).datagrid('getSelected');
    if (row) {
        $.messager.confirm('提示', messager, function (r) {
            var _delParam = url.indexOf('?') != -1 ? '&jackToken=' + getCookie(getReferer()) : '?jackToken=' + getCookie(getReferer());
            if (r) {
                $.ajax({
                    type: 'POST',
                    url: url + row[id] + _delParam,
                    dataType: 'json',
                    success: function (data) {
                        if (data.result == true) {
                            Ealert('数据删除成功');
                            if (callback == null || callback == undefined) {
                                reload();
                            } else {
                                callback();
                            }
                        } else {
                            Ealert('数据删除失败');
                        }
                    }
                });
            }
        });
    } else {
        $.messager.alert('操作提示', '请选择一条数据进行操作!', 'info');
    }

}

// 公共的删除方法v2
function pubDel_v2(url, messager) {
    if (messager == null || messager == undefined) {
        messager = '确认要删除吗？';
    }
    $.messager.confirm('提示', messager, function (r) {
        if (r) {
            $.ajax({
                type: 'POST',
                url: url + '&jackToken=' + getCookie(getReferer()),
                dataType: 'json',
                success: function (data) {
                    if (data.result == true) {
                        Ealert('数据删除成功');
                        reload();
                    } else {
                        Ealert('数据删除失败');
                    }
                }
            });
        }
    });

}

// 生成uuid
function uuid() {

    var len = 32;
    var radix = 10;
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
        .split('');

    var uuid = [], i;

    radix = radix || chars.length;

    if (len) {

        // Compact form

        for (i = 0; i < len; i++)
            uuid[i] = chars[0 | Math.random() * radix];

    } else {

        // rfc4122, version 4 form

        var r;

        // rfc4122 requires these characters

        uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';

        uuid[14] = '4';

        // Fill in random data. At i==19 set the high bits of clock sequence as

        // per rfc4122, sec. 4.1.5

        for (i = 0; i < 36; i++) {

            if (!uuid[i]) {

                r = 0 | Math.random() * 16;

                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];

            }

        }

    }

    return uuid.join('');

}

// 去除validfrom的验证（只是去掉显示）
function removeValidate(form) {
    $('#' + form).Validform().resetForm();
    $('.Validform_checktip').empty();
}


// 初始化收藏
function initChangeMenuCollectSate(menuId) {
    $.ajax({
        url: pms_main_url
            + "collectAction/getCollectIsExist.do?jsonpCallback=?",
        type: "GET",
        dataType: "json",
        data: {
            menuId: menuId
        },
        success: function (data) {
            changeMenuSuccess(data);
        },
        error: function () {
        }
    });
}

// 添加移除收藏
function changeMenuCollectSate(menuId) {
    $.ajax({
        url: pms_main_url
            + "collectAction/changeMenuCollectSate.do?jsonpCallback=?",
        type: "GET",
        dataType: "json",
        data: {
            menuId: menuId
        },
        success: function (data) {
            changeMenuSuccess(data);
        },
        error: function () {
        }
    });
}

// 对收藏是否存在的操作
function changeMenuSuccess(data) {
    icon = $('.collectIcon');
    if (data.result == true) {
        // 添加收藏
        $('.collectMsg').text('已收藏');
        icon.removeClass("icon-uncollect");
        icon.addClass("icon-collect");
    } else {
        // 移除收藏
        $('.collectMsg').text('未收藏');
        icon.removeClass("icon-collect");
        icon.addClass("icon-uncollect");
    }
}


// 获取当前时间
function getNowTime(formart) {
    var result = '';
    var oDate = new Date(); // 实例一个时间对象；
    if (formart.indexOf('yyyy') != -1) {
        formart = formart.replace('yyyy', oDate.getFullYear());
    }
    if (formart.indexOf('MM') != -1) {
        formart = formart.replace('MM', NumberFormatDigit(
            (oDate.getMonth() + 1), 2));
    }
    if (formart.indexOf('dd') != -1) {
        formart = formart.replace('dd', NumberFormatDigit(oDate.getDate(), 2));
    }
    if (formart.indexOf('hh') != -1) {
        formart = formart.replace('hh', NumberFormatDigit(oDate.getHours(), 2));
    }
    if (formart.indexOf('mm') != -1) {
        formart = formart.replace('mm',
            NumberFormatDigit(oDate.getMinutes(), 2));
    }
    if (formart.indexOf('ss') != -1) {
        formart = formart.replace('ss',
            NumberFormatDigit(oDate.getSeconds(), 2));
    }
    return formart;
}

// 获取上一个月
function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; // 获取当前日期的年份
    var month = arr[1]; // 获取当前日期的月份
    var day = arr[2]; // 获取当前日期的日
    var days = new Date(year, month, 0);
    days = days.getDate(); // 获取当前日期中月的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '-' + month2 + '-' + day2;
    return t2;
}

// 获取当前月的第一天
function getFirstDayInCurrtMonth() {
    var date = new Date()
    with (date) {
        year = date.getYear() + 1900;
        month = date.getMonth() + 1;
    }
    return year + "-" + month + "-1";
}

// 获取下个月的第一天
function getFirstDayInNextMonth() {
    var date = new Date()
    with (date) {
        year = date.getYear() + 1900;
        month = date.getMonth() + 2;
        if (date.getMonth() == 11) {
            month = 1;
            year = year + 1;
        }
    }
    return year + "-" + month + "-1";
}

// 获取当前月的最后一天
function getLastDayInCurrMonth() {
    var date = new Date()
    with (date) {
        year = date.getYear() + 1900;
        month = date.getMonth() + 1;
    }
    var day = new Date(year, month, 0);

    return year + '-' + month + '-' + day.getDate() + " 23:59:59";// 获取当月最后一天日期
}

// 当前日期添加多少天
function addDate(date, days) {
    var d = new Date(date);
    d.setDate(d.getDate() + days);
    var m = d.getMonth() + 1;
    if (m < 10) {
        m = '0' + m;
    }
    return d.getFullYear() + '-' + m + '-' + d.getDate();
}

// 格式化数字位数 num为要格式化的数字，n为位数 不够前面补0
function NumberFormatDigit(num, n) {
    if (isNaN(num)) {
        return num;
    } else if (Number(num) < 0) {
        return num;
    } else {
        var digit = Number(num).toString().length;
        if (digit >= n) {
            return num;
        } else {
            var temp = '';
            for (i = 0; i < n - digit; i++) {
                temp += '0'
            }
            return temp + num;
        }
    }
}

// 给easyui的datagrid扩展clear方法，清空数据，不过清空后会重新用url加载，所以会把url设为空

if ($.fn.datagrid) {
    $.extend($.fn.datagrid.methods, {
        clear: function (jq) {
            return jq.each(function () {
                $(this).datagrid({
                    url: ''
                }).datagrid('loadData', {
                    total: 0,
                    rows: []
                });
            });
        }
    });
}


// 添加流程启动时，判断当前流程是否已经添加该小区的角色用户配置
// url ${pms_main_url}, workflow 流程类别 如crm_Repair
function checkExistProject(projectIdE, url, workflow) {
    var url = url
        + "workflowDefinition/checkExistProject.do?&jsonpCallback=?&projectIdE="
        + projectIdE + "&workflow=" + workflow;
    $
        .ajax({
            url: url,
            method: 'get',
            dataType: "jsonp",
            success: function (result) {
                if (result.result) {
                    var buildingUrl = url
                        + 'houseBuilding/getBuildingList.do?jsonpCallback=?&projectIdE='
                        + projectIdE;
                    isSelect.project = true;
                } else {
                    Ealert('请先配置报修流程下该小区的默认处理角色和用户！');
                    $('#projectId').combobox('select', '');
                }
            }
        })
}

// html转义字符替换，将&lt;替换为>等
function htmlTransferReplace(str) {
    str = str.replace(/&amp;/g, "&");
    str = str.replace(/&nbsp;/g, " ");
    str = str.replace(/&lt;/g, "<");
    str = str.replace(/&gt;/g, ">");
    return str;
}

// 限制输入文本内容长度 obj-this val是限制输入的文字长度
function checkLen(obj, val) {
    var maxChars = val;// 最多字符数
    if (obj.value.length > maxChars) {
        obj.value = obj.value.substring(0, maxChars);
        var curr = maxChars - obj.value.length;
        Ealert("输入内容过多，请缩小范围填写！最多可输入" + maxChars + "个字符")
    }
}

// 显示吐司
function showToast(message) {
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "rtl": false,
        "positionClass": "toast-bottom-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": 500,
        "hideDuration": 500,
        "timeOut": 2000,
        "extendedTimeOut": 500,
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    var $toast = toastr['info'](message, '');
}

// 关闭dialog
function closeDialog() {
    $('#addOrUpdateDialog').dialog('close');
}

// 打开dialog
function openDialog(url, title) {
    $('#addOrUpdateDialog').load(url, function () {
        $('#addOrUpdateDialog').dialog('open').dialog('setTitle', title);
        $.parser.parse($('#addOrUpdateDialog'));
    });
}


$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

// 加载form数据
function loadForm4Json(formId, jsonValue) {
    var obj = $('#' + formId);
    $.each(jsonValue, function (name, ival) {
        var $oinput = obj.find("input[name=" + name + "]");
        if ($oinput.attr("type") == "checkbox") {
            if (ival !== null) {
                var checkboxObj = $("[name=" + name + "]");
                var checkArray = ival.split(";");
                for (var i = 0; i < checkboxObj.length; i++) {
                    for (var j = 0; j < checkArray.length; j++) {
                        if (checkboxObj[i].value == checkArray[j]) {
                            checkboxObj[i].click();
                        }
                    }
                }
            }
        } else if ($oinput.attr("type") == "radio") {
            $oinput.each(function () {
                var radioObj = $("[name=" + name + "]");
                for (var i = 0; i < radioObj.length; i++) {
                    if (radioObj[i].value == ival) {
                        radioObj[i].click();
                    }
                }
            });
        } else if ($oinput.attr("type") == "textarea") {
            obj.find("[name=" + name + "]").html(ival);
        } else {
            obj.find("[name=" + name + "]").val(ival);
        }
    })

}

/**
 * EasyUI datagrid 合并列方法 根据列的值相同与否进行合并所以返回的数据必须组装好
 *
 * [要求第一列的数据相同的放前面 下面是第二列...最后一列这样]
 *
 * @param t_id
 *            需要合并的table的id
 * @param col_list
 *            需要合并的列的field名称集合 按照","拼接 field正序
 */
function merge_cells_by_field(t_id, col_list) {
    // 得到需要合并的列的field名
    var col_array = col_list.split(",");
    // table jquery对象
    var $table = $("#" + t_id);
    // table 的所有行
    var table_rows = $table.datagrid("getRows").length;
    // 需要合并的行的index
    var marge_cell_index;
    // 临时变量
    var temp_index;
    // 当前列上一个文本的值
    var pre_text = undefined;
    // 当前列文本的值
    var cur_text = "";
    // 循环需要合并的列
    for (j = col_array.length - 1; j >= 0; j--) {
        // 上一个文本值
        pre_text = "";
        pre_col_cur_text = "";
        marge_cell_index = 1;
        temp_index = 0;
        for (i = 0; i <= table_rows; i++) {
            // 如果是最后一行设置当前列的文本值为空
            if (i == table_rows) {
                cur_text = "";
                pre_col_cur_text = "";
            }
            // 如果不是最后一行获取当前列的文本值
            else {
                cur_text = $table.datagrid("getRows")[i][col_array[j]];
            }
            // 如果当前列上一行的文本值等于当前行的文本值 那么 判断当前列的上一列 marge_cell_index+1
            if (pre_text == cur_text) {
                // 如果当前列的上一列存在 并且他的上一列当前行的rowspan不小于marge_cell_index 那么对
                // marge_cell_index ++;否则执行合并并进行下次合并准备
                // 如果有上一列才判断他的上一行和当前行的值
                // 如果上一列的上一行值和当前值相同那么继续找他的上一列的上一行值和当前行的值 直到没有 上一行
                if (j == 0) {
                    marge_cell_index++;
                }
                // 上一列上一文本的值
                var pre_col_text = undefined;
                // 上一列当前行的文本值
                var pre_col_cur_text = "";
                var temp_i = i;
                // console.log(temp_i)
                var cur_row = undefined;
                var pre_row = undefined;
                for (var i_index = j - 1; i_index >= 0 && temp_i >= 1; i_index--) {
                    cur_row = $table.datagrid("getRows")[temp_i];
                    pre_row = $table.datagrid("getRows")[temp_i - 1];
                    if (isEmpty(cur_row) || isEmpty(pre_row)) {
                        continue;
                    }
                    pre_col_cur_text = $table.datagrid("getRows")[temp_i][col_array[i_index]];
                    pre_col_text = $table.datagrid("getRows")[temp_i - 1][col_array[i_index]];
                    // console.log("第"+temp_i+"行第"+i_index+"列"+pre_col_cur_text)
                    // console.log("第"+(temp_i-1)+"行第"+i_index+"列"+pre_col_text)
                    if (pre_col_cur_text == pre_col_text) {
                        if (i_index != 0) {
                            // 如果当前列的上一列的上一行和当前列的上一列的当前行相等继续判断上一列的上一列
                            continue;
                        } else {
                            marge_cell_index++;
                        }

                    } else {

                        temp_index += marge_cell_index;
                        $table.datagrid("mergeCells", {
                            index: i - marge_cell_index,
                            field: col_array[j],
                            rowspan: marge_cell_index,
                            colspan: null
                        });
                        marge_cell_index = 1;
                        // 把当前值作为当前列的上一行的值继续循环
                        continue;
                    }
                }

            }
            // 如果不等于那么对temp_index+marge_cell_index 同时执行合并 因为没有相同的了 需要合并的列为
            // col_array[j] 行为i-marge_cell_index 合并多少行?-marge_cell_index
            else {
                temp_index += marge_cell_index;
                $table.datagrid("mergeCells", {
                    index: i - marge_cell_index,
                    field: col_array[j],
                    rowspan: marge_cell_index,
                    colspan: null
                });
                marge_cell_index = 1;
            }
            // 把当前值作为当前列的上一行的值继续循环
            pre_text = cur_text;
            pre_col_text = pre_col_cur_text;
        }
    }
}

/**
 * 将datagrid 转为普通的table 用于导出 适用多表头设置 连续合并行
 *
 * @param param {
 *            tableId table的id notExportCol 不需要导出的列 多个用","分隔 rows 导出数据源
 *            exportHiddenCol 导出的隐藏列在数据表格的最后 [为了转换方便] colTrans
 *            列的翻译[{fieldName:filed,fieldNameTrans:{1:是0:否}},{}...]只是针对导出的列
 *            这里的key必须是fieldNam & fieldNameTrans tableclass table的类
 *            主要是加背景{field:color} striped
 *            [加背景色的时候必须加入这个key判断是否对表格进行条纹化]rowSingular[单行颜色] rowDual[双行颜色]
 *            rowHeader[表头颜色]、 specialExportData 特殊导出要求
 *            对金额之类的进行保留两位小数处理空的则赋值0.00 除非传入不需要处理的列否则都执行这个操作 rowspanCol
 *            合并列的field 用逗号拼接 headRowCol 表头最前面加的列 列合并等于 所有列的数量 ,footRowCol 底部加入
 *            列合并等于所有列的数量,isExportRowNumber是否 导出序号列} 这些key必须跟这里的相同
 */
function change_to_table(param) {
    var sTId = param.tableId;
    var sNoNeedExCol = param.notExportCol;
    var rows = param.rows;
    var sExHidCol = param.exportHiddenCol;
    var trans_ = param.colTrans;
    var oTCss = param.tableClass
    var sRowSpan = param.rowspanCol;
    var oSpecExParam = param.specialExportData;
    var aHeadCol = param.headRowCol;
    var aFootCol = param.footRowCol;
    var aRowNum = param.rowNumberData;
    // 额外的导出列
    var aExExtraCol = param.additionalRowColData;
    // 不需要导出的列的数组
    var aNoNeedExCol = isEmpty(sNoNeedExCol) ? new Array() : sNoNeedExCol.split(",");
    // 特殊导出
    var oSepcExObj = isEmpty(oSpecExParam) ? new Object() : oSpecExParam;
    var isToFixed = "isToFixed";
    // 多个列用逗号拼接
    var noFixedCol = "noFixedCol";
    // 默认所有的列进行格式化小数点两位
    // 如果需要特殊处理则在此参数中设置{colField:field的值,fixedVal:格式化小数点几位数字,defaultVal:如果为空默认值}
    var fixedColParam = "fixedColParam";
    var bFixedCol = oSepcExObj[isToFixed];
    var sNoFixedCol = oSepcExObj[noFixedCol];
    var sFixedColParam = oSepcExObj[fixedColParam]
    var aNoFixedCol = isEmpty(sNoFixedCol) ? new Array() : sNoFixedCol.split(",");
    // 格式化列参数数组
    var aFixedColParam = isEmpty(sFixedColParam) ? new Array() : sFixedColParam;
    // 需要导出的隐藏列的数组
    var aExHidCol = isEmpty(sExHidCol) ? new Array() : sExHidCol.split(",");
    var $table = $('#' + sTId)
    var sHtml = '<table cellspacing="0">';
    var frozenColumns = $table.datagrid("options").frozenColumns; // 得到frozenColumns对象
    var columns = $table.datagrid("options").columns; // 得到columns对象
    var aColList = new Array();
    var oCss = isEmpty(oTCss) ? new Object() : oTCss
    var headerColor = "rowHeader";
    var sHeadColor = oCss[headerColor];
    // 表头总共有几列
    var iMaxColLen = 0;
    // 载入title
    if (typeof columns != 'undefined' && columns != '') {
        $(columns).each(function (index, obj) {
            // 初始化额外的列的表头
            if (aHeadCol && index == 0) {
                sHtml = initHeadRow(aHeadCol, sHtml);
            } else {
                sHtml += '\n<tr>';
            }
            if (aRowNum && index == 0) {
                sHtml = initRow(aRowNum, sHtml);
            }
            if (aExExtraCol && index == 0) {
                sHtml = initRow(aExExtraCol, sHtml);
            }
            // 如果这列是隐藏的那么不导出 但是如果是隐藏仍然要导出的那么继续导出
            if (typeof frozenColumns != 'undefined' && typeof frozenColumns[index] != 'undefined') {
                var frozenColObj = undefined;
                for (var i = 0; i < frozenColumns[index].length; ++i) {
                    frozenColObj = frozenColumns[index][i];
                    if ((!frozenColObj.hidden && $.inArray(frozenColObj.field, no_ex_col_array) == -1) || $.inArray(frozenColObj.field, aExHidCol) != -1) {
                        sHtml += '\n<th width="' + frozenColObj.width + '"';
                        if (typeof frozenColObj.rowspan != 'undefined' && frozenColObj.rowspan > 1 && $.inArray(frozenColObj.field, aNoNeedExCol) == -1) {
                            sHtml += ' rowspan="' + frozenColObj.rowspan + '"';
                            iMaxColLen = Number(frozenColObj.rowspan) > iMaxColLen ? frozenColObj.rowspan : iMaxColLen;
                        }
                        if (typeof frozenColObj.align != 'undefined' && $.inArray(frozenColObj.field, aNoNeedExCol) == -1) {
                            if (sHeadColor) {
                                sHtml += ' style="background-color:' + sHeadColor + ';text-align:' + frozenColObj.align;
                            } else {
                                sHtml += ' style="text-align:' + frozenColObj.align;
                            }
                            sHtml += ';"';
                        } else {
                            if (sHeadColor) {
                                sHtml += ' style="background-color:' + sHeadColor + ';"';
                            }
                        }
                        if (typeof frozenColObj.colspan != 'undefined' && frozenColObj.colspan > 1 && $.inArray(frozenColObj.field, aNoNeedExCol) == -1) {
                            sHtml += ' colspan="' + frozenColObj.colspan + '"';
                        }
                        if (typeof frozenColObj.field != 'undefined' && frozenColObj.field != '' && $.inArray(frozenColObj.field, aNoNeedExCol) == -1) {
                            aColList.push(frozenColObj);
                        }
                        sHtml += '>' + frozenColumns[0][i].title + '</th>';
                    }
                }
            }
            var colObj = undefined;
            for (var i = 0; i < columns[index].length; ++i) {
                colObj = columns[index][i];
                if ((!columns[index][i].hidden && $.inArray(colObj.field, aNoNeedExCol) == -1) || $.inArray(colObj.field, sExHidCol) != -1) {
                    sHtml += '\n<th width="' + colObj.width + '"';
                    if (!isEmpty(colObj.field) && $.inArray(colObj.field, aNoNeedExCol) == -1) {
                        sHtml += ' rowspan="' + colObj.rowspan + '"';
                        iMaxColLen = Number(colObj.rowspan) > iMaxColLen ? colObj.rowspan : iMaxColLen;
                    }
                    if (!isEmpty(colObj.field) && $.inArray(colObj.field, aNoNeedExCol) == -1) {
                        sHtml += ' colspan="' + colObj.colspan + '"';
                    }
                    if (!isEmpty(colObj.field) && $.inArray(colObj.field, aNoNeedExCol) == -1) {
                        if (sHeadColor) {
                            sHtml += ' style="background-color:' + sHeadColor + ';text-align:' + colObj.align;
                        } else {
                            sHtml += ' style="text-align:' + colObj.align;
                        }
                        sHtml += ';"';
                    } else {
                        if (sHeadColor) {
                            sHtml += ' style="background-color:' + sHeadColor + ';"';
                        }
                    }
                    if (!isEmpty(colObj.field) && $.inArray(colObj.field, aNoNeedExCol) == -1) {
                        aColList.push(colObj);
                    }
                    sHtml += '>' + colObj.title + '</th>';
                }
            }
            sHtml += '\n</tr>';
        });
    }
    // 循环所有的列将合并列的去除
    // 这里同时处理将有合并列的
    var aExColList = new Array();
    // 上面 aExColList是现在所有有数据的列 现在需要进行排序按照 表格中展示的列顺序进行排序循环 不用担心会找不到没有数据的列了
    // 获取有顺序的列
    var aSortCol = $table.datagrid("getColumnFields");
    // 循环将列进行排序
    $.each(aSortCol, function (i, o) {
        var o_index = $.inArray(o, aColList);
        var col_o;
        for (var l = 0; l < aColList.length; l++) {
            col_o = aColList[l].field;
            if (col_o == o) {
                aExColList.push(aColList[l]);
                break;
            }
        }
    })
    // 载入内容
    // 这段代码是获取当前页的所有行
    var rows = isEmpty(rows) ? $table.datagrid("getRows") : rows;
    var transRows = isEmpty(trans_) ? new Array() : trans_;
    var striped = "striped";
    var rowDual = "rowDual";
    var rowSingular = "rowSingular";
    var changeColor = "changeColorByFieldVal";
    var fieldColor = "fieldColorArray";
    var onlyAppTheCol = "onlyAppTheCol";
    // [field的值:field的value值:对应颜色值]
    var fieldColorArray = isEmpty(oCss[fieldColor]) ? new Object() : oCss[fieldColor];
    var isStrped = oCss[striped];
    // 根据某一个字段的值不同使得table的行的颜色不同
    var changeColorByFieldVal = oCss[changeColor];
    // 是否只应用传入的列
    var isApplicationAllCol = oCss[onlyAppTheCol];
    // 循环所有的行 合并行
    // 所有的列
    // 如果有要合并的列那么 就获取合并列的rowSpan
    var aRowSpanArray;
    var aRowSpan = new Array();
    if (sRowSpan) {
        aRowSpan = sRowSpan.split(",")
        var aRowSpanArray = getRowspan(rows, sRowSpan);
    }
    for (var i = 0; i < rows.length; ++i) {
        var fieldKey = "";
        var fieldVal = "";
        var fieldColor = "";
        // 如果当前行的某个列的值满足条件了设置对应的颜色
        sHtml += '\n<tr>';
        if (changeColorByFieldVal) {
            $.each(fieldColorArray, function (index, o) {
                fieldKey = o.split("_")[0];
                fieldVal = o.split("_")[1];
                fieldColor = o.split("_")[2];
                if (rows[i][fieldKey] == fieldVal) {
                    fieldColor = fieldColor;
                    return false;
                } else {
                    fieldColor = "";
                }
            })
        }
        if (!isEmpty(aRowNum)) {
            $.each(aRowNum, function (index, obj) {
                var rowVal = i + 1;
                var col_align = obj.align;
                var col_col_span = obj.content_colspan;
                var col_row_span = obj.content_rowspan;
                sHtml += '\n<td '
                if (col_col_span) {
                    sHtml += 'colspan = "' + col_col_span + '"'
                }
                if (col_row_span) {
                    sHtml += 'rowspan = "' + col_row_span + '"'
                }
                if (col_align) {
                    sHtml += ' style="text-align:' + col_align
                } else {
                    sHtml += ' style="text-align:center"'
                }
                sHtml += ';">' + rowVal + '</td>';
            })
        }
// 如果有额外的列
        if (aExExtraCol) {
            // 额外的列
            $.each(aExExtraCol, function (index, obj) {
                var field_ = obj.field;
                var rowVal = !isEmpty(rows[i][field_]) ? rows[i][field_] : "";
                var col_align = obj.align;
                var col_col_span = obj.content_colspan;
                var col_row_span = obj.content_rowspan;
                var e_ = field_.lastIndexOf('_0');
                if (aRowSpanArray && $.inArray(field_, aRowSpan) != -1) {
                    $.each(aRowSpanArray, function (o_i, o) {
                        if (o.index == i && field_ == o.field) {
                            sHtml += '\n<td rowspan="' + o.rowspan + '"';
                            if (!isEmpty(col_align)) {
                                sHtml += ' style="text-align:' + col_align;
                                if ($.inArray(field_, aRowSpan) == -1) {
                                    if (isStrped) {
                                        if (i % 2 == 0) {
                                            sHtml += ';background-color:' + oCss[rowDual]
                                        } else {
                                            sHtml += ';background-color:' + oCss[rowSingular]
                                        }
                                    } else {
                                        if (oCss[field_]) {
                                            sHtml += ';background-color:' + oCss[field_]
                                        }
                                        if (changeColorByFieldVal && !isEmpty(fieldColor)) {
                                            // 如果仅仅应用此列
                                            if (isApplicationAllCol) {
                                                if (field_ == fieldKey) {
                                                    sHtml += ';background-color:' + fieldColor
                                                }
                                            } else {
                                                sHtml += ';background-color:' + fieldColor
                                            }
                                        }
                                    }
                                }
                                sHtml += ';"';
                            } else {
                                if ($.inArray(field_, aRowSpan) == -1) {
                                    if (isStrped) {
                                        if (i % 2 == 0) {
                                            sHtml += ';background-color:'
                                                + oCss[rowDual]
                                        } else {
                                            sHtml += ';background-color:' + oCss[rowSingular]
                                        }
                                    } else {
                                        if (oCss[field_]) {
                                            sHtml += ' style="background-color:' + oCss[field_] + ';"'
                                        }
                                    }
                                }
                            }
                            sHtml += '>';
                            if (e_ + 2 == field_.length) {
                                sHtml += rows[i][field_.substring(0, e_)];
                            } else {
                                var th_field = field_;
                                var td_field_val = rows[i][th_field];
                                var td_html_trans = rows[i][field_];
                                if (bFixedCol && $.inArray(th_field, aNoFixedCol) == -1) {
                                    // 将列的值转换为数字 如果转换失败则返回原值
                                    var td_number_val = Number(td_field_val);
                                    var fixedVal = 2;
                                    var defaultVal = "0.00";
                                    $.each(aFixedColParam, function (i, o) {
                                        var fixedField = o.colField;
                                        var fixedFieldVal = o.fixedVal;
                                        var defaultFixedVal = o.defaultVal;
                                        if (fixedField == th_field) {
                                            fixedVal = fixedFieldVal;
                                            defaultVal = defaultFixedVal;
                                            return false;
                                        }
                                    })
                                    if (!isNaN(td_number_val)) {
                                        td_number_val = td_number_val.toFixed(fixedVal);
                                    } else {
                                        td_number_val = td_field_val;
                                    }
                                    td_field_val = !isEmpty(td_number_val) ? td_number_val : defaultVal
                                    td_html_trans = !isEmpty(td_number_val) ? td_number_val : defaultVal
                                }
                                $.each(transRows, function (i, o) {
                                    if (o.fieldName == th_field) {
                                        if (!isEmpty(td_field_val)) {
                                            td_html_trans = o.fieldNameTrans[td_field_val];
                                        }
                                    }
                                })
                                sHtml += isEmpty(td_html_trans) ? "" : td_html_trans;
                            }
                            sHtml += '</td>';
                            return false;
                        }
                    })
                } else {
                    sHtml += '\n<td '
                    if (col_col_span) {
                        sHtml += 'colspan = "' + col_col_span + '"'
                    }
                    if (col_row_span) {
                        sHtml += 'rowspan = "' + col_row_span + '"'
                    }
                    if (col_align) {
                        sHtml += ' style="text-align:' + col_align
                    } else {
                        sHtml += ' style="text-align:center"'
                    }
                    if (isStrped) {
                        if (i % 2 == 0) {
                            sHtml += ';background-color:' + oCss[rowDual]
                        } else {
                            sHtml += ';background-color:' + oCss[rowSingular]
                        }
                    } else {
                        if (oCss[obj.field]) {
                            sHtml += ';background-color:' + oCss[obj.field]
                        }
                        if (changeColorByFieldVal && !isEmpty(fieldColor)) {
                            // 如果仅仅应用此列
                            if (isApplicationAllCol) {
                                if (obj.field == fieldKey) {
                                    sHtml += ';background-color:' + fieldColor
                                }
                            } else {
                                sHtml += ';background-color:' + fieldColor
                            }

                        }
                    }
                    sHtml += ';">' + rowVal + '</td>';
                }
            })
        }
        for (var j = 0; j < aExColList.length; ++j) {
            var e = aExColList[j].field.lastIndexOf('_0');
            if (aRowSpanArray && $.inArray(aExColList[j].field, aRowSpan) != -1) {
                // 循环合并行的数组
                $.each(aRowSpanArray, function (o_i, o) {
                    if (o.index == i && aExColList[j].field == o.field) {
                        sHtml += '\n<td rowspan="' + o.rowspan + '"';
                        if (aExColList[j].align != 'undefined' && aExColList[j].align != '') {
                            sHtml += ' style="text-align:' + aExColList[j].align;
                            if ($.inArray(aExColList[j].field, aRowSpan) == -1) {
                                if (isStrped) {
                                    if (i % 2 == 0) {
                                        sHtml += ';background-color:' + oCss[rowDual]
                                    } else {
                                        sHtml += ';background-color:' + oCss[rowSingular]
                                    }
                                } else {
                                    if (oCss[aExColList[j].field]) {
                                        sHtml += ';background-color:' + oCss[aExColList[j].field]
                                    }
                                    if (changeColorByFieldVal && !isEmpty(fieldColor)) {
                                        // 如果仅仅应用此列
                                        if (isApplicationAllCol) {
                                            if (aExColList[j].field == fieldKey) {
                                                sHtml += ';background-color:' + fieldColor
                                            }
                                        } else {
                                            sHtml += ';background-color:' + fieldColor
                                        }
                                    }
                                }
                            }
                            sHtml += ';"';
                        } else {
                            if ($.inArray(aExColList[j].field, aRowSpan) == -1) {
                                if (isStrped) {
                                    if (i % 2 == 0) {
                                        sHtml += ';background-color:' + oCss[rowDual]
                                    } else {
                                        sHtml += ';background-color:' + oCss[rowSingular]
                                    }
                                } else {
                                    if (oCss[aExColList[j].field]) {
                                        sHtml += ' style="background-color:' + oCss[aExColList[j].field] + ';"'
                                    }
                                }
                            }
                        }
                        sHtml += '>';
                        if (e + 2 == aExColList[j].field.length) {
                            sHtml += rows[i][aExColList[j].field.substring(0, e)];
                        } else {
                            var th_field = aExColList[j].field;
                            var td_field_val = rows[i][th_field];
                            var td_html_trans = rows[i][aExColList[j].field];
                            if (bFixedCol && $.inArray(th_field, aNoFixedCol) == -1) {
                                // 将列的值转换为数字 如果转换失败则返回原值
                                var td_number_val = Number(td_field_val);
                                var fixedVal = 2;
                                var defaultVal = "0.00";
                                $.each(aFixedColParam, function (i, o) {
                                    var fixedField = o.colField;
                                    var fixedFieldVal = o.fixedVal;
                                    var defaultFixedVal = o.defaultVal;
                                    if (fixedField == th_field) {
                                        fixedVal = fixedFieldVal;
                                        defaultVal = defaultFixedVal;
                                        return false;
                                    }
                                })
                                if (!isNaN(td_number_val)) {
                                    td_number_val = td_number_val.toFixed(fixedVal);
                                } else {
                                    td_number_val = td_field_val;
                                }
                                td_field_val = !isEmpty(td_number_val) ? td_number_val : defaultVal
                                td_html_trans = !isEmpty(td_number_val) ? td_number_val : defaultVal
                            }
                            $.each(transRows, function (i, o) {
                                if (o.fieldName == th_field) {
                                    if (!isEmpty(td_field_val)) {
                                        td_html_trans = o.fieldNameTrans[td_field_val];
                                    }
                                }
                            })
                            sHtml += isEmpty(td_html_trans) ? "" : td_html_trans;
                        }
                        sHtml += '</td>';
                        return false;
                    }
                })
            } else {
                sHtml += '\n<td';
                if (!isEmpty(aExColList[j].align)) {
                    sHtml += ' style="text-align:' + aExColList[j].align;
                    if ($.inArray(aExColList[j].field, aRowSpanArray) == -1) {
                        if (isStrped) {
                            if (i % 2 == 0) {
                                sHtml += ';background-color:' + oCss[rowDual]
                            } else {
                                sHtml += ';background-color:' + oCss[rowSingular]
                            }
                        } else {
                            if (oCss[aExColList[j].field]) {
                                sHtml += ';background-color:' + oCss[aExColList[j].field]
                            }
                            if (changeColorByFieldVal && !isEmpty(fieldColor)) {
                                // 如果仅仅应用此列
                                if (isApplicationAllCol) {
                                    if (aExColList[j].field == fieldKey) {
                                        sHtml += ';background-color:' + fieldColor
                                    }
                                } else {
                                    sHtml += ';background-color:' + fieldColor
                                }
                            }
                        }
                    }
                    sHtml += ';"';
                } else {
                    if ($.inArray(aExColList[j].field, aRowSpanArray) == -1) {
                        if (isStrped) {
                            if (i % 2 == 0) {
                                sHtml += ';background-color:' + oCss[rowDual]
                            } else {
                                sHtml += ';background-color:' + oCss[rowSingular]
                            }
                        } else {
                            if (oCss[aExColList[j].field]) {
                                sHtml += ' style="background-color:' + oCss[aExColList[j].field] + ';"'
                            }
                        }
                    }
                }
                sHtml += '>';
                if (e + 2 == aExColList[j].field.length) {
                    sHtml += rows[i][aExColList[j].field.substring(0, e)];
                } else {
                    var th_field = aExColList[j].field;
                    var td_field_val = rows[i][th_field];
                    var td_html_trans = rows[i][th_field];
                    if (bFixedCol && $.inArray(th_field, aNoFixedCol) == -1) {
                        // 将列的值转换为数字 如果转换失败则返回原值
                        var td_number_val = Number(td_field_val);
                        var fixedVal = 2;
                        var defaultVal = "0.00";
                        $.each(aFixedColParam, function (i, o) {
                            var fixedField = o.colField;
                            var fixedFieldVal = o.fixedVal;
                            var defaultFixedVal = o.defaultVal;
                            if (fixedField == th_field) {
                                fixedVal = fixedFieldVal;
                                defaultVal = defaultFixedVal;
                                return false;
                            }
                        })
                        if (!isNaN(td_number_val)) {
                            td_number_val = td_number_val.toFixed(fixedVal);
                        } else {
                            td_number_val = td_field_val;
                        }
                        td_field_val = !isEmpty(td_number_val) ? td_number_val : defaultVal
                        td_html_trans = !isEmpty(td_number_val) ? td_number_val : defaultVal
                    }
                    $.each(transRows, function (i, o) {
                        if (o.fieldName == th_field) {
                            // 针对状态中有可能是0
                            if (!isEmpty(td_field_val) || td_field_val == 0) {
                                td_html_trans = o.fieldNameTrans[td_field_val];
                            }
                        }
                    })
                    sHtml += isEmpty(td_html_trans) ? "" : td_html_trans;
                }
                sHtml += '</td>';
            }
        }
        sHtml += '\n</tr>';
    }
    // 初始化页脚的列的table信息
    if (aFootCol) {
        sHtml = initRowInfo(aFootCol, sHtml);
    }
    sHtml += '\n</table>';
    return sHtml;
}

/**
 * 获取index rowspan的值 用于导出excel时合并行
 *
 * @param table_rows
 * @param sCol
 * @returns {Array}
 */
function getRowspan(table_rows, sCol) {
    // 得到需要合并的列的field名
    var col_array = sCol.split(",");
    var row_span_array = new Array();
    // table jquery对象
    // table 的所有行
    // 需要合并的行的index
    var marge_cell_index;
    // 临时变量
    var temp_index;
    // 当前列上一个文本的值
    var pre_text = undefined;
    // 当前列文本的值
    var cur_text = "";
    // 循环需要合并的列
    for (j = col_array.length - 1; j >= 0; j--) {
        // 上一个文本值
        pre_text = "";
        pre_col_cur_text = "";
        marge_cell_index = 1;
        temp_index = 0;
        for (i = 0; i <= table_rows.length; i++) {
            // 如果是最后一行设置当前列的文本值为空
            if (i == table_rows.length) {
                cur_text = "";
                pre_col_cur_text = "";
            }
            // 如果不是最后一行获取当前列的文本值
            else {
                cur_text = table_rows[i][col_array[j]];
            }
            // 如果当前列上一行的文本值等于当前行的文本值 那么 判断当前列的上一列 marge_cell_index+1
            if (pre_text == cur_text) {
                // 如果当前列的上一列存在 并且他的上一列当前行的rowspan不小于marge_cell_index 那么对
                // marge_cell_index ++;否则执行合并并进行下次合并准备
                // 如果有上一列才判断他的上一行和当前行的值
                // 如果上一列的上一行值和当前值相同那么继续找他的上一列的上一行值和当前行的值 直到没有 上一行
                if (j == 0) {
                    marge_cell_index++;
                }
                // 上一列上一文本的值
                var pre_col_text = undefined;
                // 上一列当前行的文本值
                var pre_col_cur_text = "";
                var temp_i = i;
                // console.log(temp_i)
                var cur_row = undefined;
                var pre_row = undefined;
                for (var i_index = j - 1; i_index >= 0 && temp_i >= 1; i_index--) {
                    cur_row = table_rows[temp_i];
                    pre_row = table_rows[temp_i - 1];
                    if (isEmpty(cur_row) && !isEmpty(pre_row)) {
                        continue;
                    }
                    pre_col_cur_text = table_rows[temp_i][col_array[i_index]];
                    pre_col_text = table_rows[temp_i - 1][col_array[i_index]];
                    // console.log("第"+temp_i+"行第"+i_index+"列"+pre_col_cur_text)
                    // console.log("第"+(temp_i-1)+"行第"+i_index+"列"+pre_col_text)
                    if (pre_col_cur_text == pre_col_text) {
                        if (i_index != 0) {
                            // 如果当前列的上一列的上一行和当前列的上一列的当前行相等继续判断上一列的上一列
                            continue;
                        } else {
                            marge_cell_index++;
                        }

                    } else {

                        temp_index += marge_cell_index;
                        var obj = new Object();
                        obj.index = i - marge_cell_index;
                        obj.field = col_array[j];
                        obj.rowspan = marge_cell_index;
                        row_span_array.push(obj)
                        marge_cell_index = 1;
                        // 把当前值作为当前列的上一行的值继续循环
                        continue;
                    }
                }

            }
            // 如果不等于那么对temp_index+marge_cell_index 同时执行合并 因为没有相同的了 需要合并的列为
            // col_array[j] 行为i-marge_cell_index 合并多少行?-marge_cell_index
            else {
                temp_index += marge_cell_index;
                var obj = new Object();
                obj.index = i - marge_cell_index;
                obj.field = col_array[j];
                obj.rowspan = marge_cell_index;
                row_span_array.push(obj)
                marge_cell_index = 1;
            }
            // 把当前值作为当前列的上一行的值继续循环
            pre_text = cur_text;
            pre_col_text = pre_col_cur_text;
        }
    }
    var aFirstColFieldIndex = new Array();
    $.each(row_span_array, function (i, o) {
        if (o.field == col_array[0]) {
            aFirstColFieldIndex.push(o.index)
        }
    })
    var resultArray = new Array();
    $.each(row_span_array, function (i, o) {
        // 如果不在需要合并的第一列的index值中都remove
        if ($.inArray(o.index, aFirstColFieldIndex) != -1 || o.index == table_rows.length) {
            resultArray.push(o)
        }
    })
    return resultArray;
}

/**
 * datagrid动态改变列的title值 {field:fieldVal,text:'new Title'}
 */
if ($.fn.datagrid) {
    $.extend($.fn.datagrid.methods, {
        setColumnTitle: function (jq, option) {
            if (option.field) {
                return jq.each(function () {
                    var $panel = $(this).datagrid("getPanel");
                    var $field = $('td[field=' + option.field + ']', $panel);
                    if ($field.length) {
                        var $span = $("span", $field).eq(0);
                        $span.html(option.text);
                        if (option.cs) {
                            $span.attr('class', option.cs)
                        }
                        if (option.title) {
                            $span.attr('title', option.title)
                        }
                    }
                });
            }
            return jq;
        }
    });

}

// 传入一个数字的字符串 按照间隔一进行返回
/**
 * @param numbers
 *            数字组成的字符串用逗号分隔
 * @param splitStr
 *            最终返回的时候的分隔符号
 * @param isFormatter
 * @param formatterVal
 */
function getContinuityNumber(numbers, splitStr, isFormatter, formatterVal) {
    var splitStr = isEmpty(splitStr) ? "~" : splitStr;
    var isFormatter = isEmpty(isFormatter) ? false : isFormatter;
    var formatterVal = isEmpty(formatterVal) ? 0 : formatterVal;
    var result = "";
    var numbersArray = numbers.split(',');
    numbersArray.sort(sortNumber);
    var dimensionArr = arrange(numbersArray);
    $.each(dimensionArr, function (i, arr) {
        if (isFormatter) {
            if (arr[0] === arr[arr.length - 1]) {
                result += pad(arr[0], formatterVal) + ",";
            } else {
                result += pad(arr[0], formatterVal) + splitStr
                    + pad(arr[arr.length - 1], formatterVal) + ",";
            }
        } else {
            if (arr[0] === arr[arr.length - 1]) {
                result += pad(arr[0], formatterVal) + ",";
            } else {
                result += arr[0] + splitStr + arr[arr.length - 1]
            }

            formatterVal + ",";
        }
    });
    return result.substring(0, result.length - 1);
}

/**
 * 获取数组中连续的数组返回一个临时二维数组
 *
 * @param source
 * @returns {Array}
 */
function arrange(source) {
    var t;
    var ta;
    var r = [];
    source.forEach(function (v) {
        v = Number(v)
        if (t === v) {
            ta.push(t);
            t++;
            return;
        }

        ta = [v];
        t = v + 1;
        r.push(ta);
    });
    return r;
}

/**
 * <初始化额外的列table信息>
 *
 * @param row_col_data
 *            额外添加的列的数据信息
 *            顺序确定不变[{title:'',align:'',colspan:'',color:'',rowspan:''}]
 * @param sHtml
 *            最终组装的table的字符串
 */
function initHeadRow(row_col_data, sHtml) {
    $.each(row_col_data, function (i, o) {
        sHtml += '<tr>';
        var col_color = o.color;
        var col_align = o.align;
        var col_col_span = o.colspan;
        var col_row_span = o.rowspan;
        sHtml += '\n<th '
        if (col_col_span) {
            sHtml += 'colspan = "' + col_col_span + '"'
        }
        if (col_row_span) {
            sHtml += 'rowspan = "' + col_row_span + '"'
        }
        if (col_align) {
            sHtml += ' style="text-align:' + col_align + ';"'
        } else {
            sHtml += ' style="text-align:center;"'
        }
        if (col_color) {
            sHtml += 'background-color:' + col_color;
        }
        sHtml += '">' + o.title + '</th>\n</tr>';
    })

}

/**
 * <初始化额外的列table信息>
 *
 * @param row_col_data
 *            额外添加的列的数据信息
 *            顺序确定不变[{title:'',align:'',colspan:'',color:'',rowspan:''}]
 * @param sHtml
 *            最终组装的table的字符串
 */
function initHeadRow(row_col_data, sHtml) {
    $.each(row_col_data, function (i, o) {
        sHtml += '\n<tr>';
        var col_color = o.color;
        var col_align = o.align;
        var col_col_span = o.colspan;
        var col_row_span = o.rowspan;
        sHtml += '\n<th '
        if (col_col_span) {
            sHtml += 'colspan = "' + col_col_span + '"'
        }
        if (col_row_span) {
            sHtml += 'rowspan = "' + col_row_span + '"'
        }
        if (col_align) {
            sHtml += ' style="text-align:' + col_align + ';'
        } else {
            sHtml += ' style="text-align:center;"'
        }
        if (col_color) {
            sHtml += 'background-color:' + col_color;
        }
        sHtml += '">' + o.title + '</th>\n</tr>';
    })
    sHtml += '\n<tr>';
    return sHtml;
}

/**
 * <初始化额外的列table信息>
 *
 * @param row_col_data
 *            额外添加的列的数据信息
 *            顺序确定不变[{title:'',align:'',colspan:'',color:'',rowspan:''}]
 * @param sHtml
 *            最终组装的table的字符串
 */
function initRow(row_col_data, sHtml) {
    $.each(row_col_data, function (i, o) {
        var col_color = o.color;
        var col_align = o.align;
        var col_col_span = o.colspan;
        var col_row_span = o.rowspan;
        sHtml += '\n<th '
        if (col_col_span) {
            sHtml += 'colspan = "' + col_col_span + '"'
        }
        if (col_row_span) {
            sHtml += 'rowspan = "' + col_row_span + '"'
        }
        if (col_align) {
            sHtml += ' style="text-align:' + col_align + ';'
        } else {
            sHtml += ' style="text-align:center;"'
        }
        if (col_color) {
            sHtml += 'background-color:' + col_color;
        }
        sHtml += '">' + o.title + '</th>';
    });
    return sHtml;
}

/**
 * <初始table信息带数据>
 *
 * @param row_col_data
 *            列的数据信息 顺序确定不变[{title:'',align:'',colspan:'',color:'',rowspan:''}]
 * @param sHtml
 *            最终组装的table的字符串
 */
function initRowInfo(row_col_data, sHtml) {
    $.each(row_col_data, function (i, o) {
        sHtml += '<tr>';
        var col_color = o.color;
        var col_align = o.align;
        var col_col_span = o.colspan;
        var col_row_span = o.rowspan;
        sHtml += '\n<td '
        if (col_col_span) {
            sHtml += 'colspan = "' + col_col_span + '"'
        }
        if (col_row_span) {
            sHtml += 'rowspan = "' + col_row_span + '"'
        }
        if (col_align) {
            sHtml += ' style="text-align:' + col_align + ';'
        } else {
            sHtml += ' style="text-align:center;"'
        }
        if (col_color) {
            sHtml += 'background-color:' + col_color;
        }
        sHtml += '">' + o.title + '</td>\n</tr>';
    })
    return sHtml;
}

// 格式化为2位小数
function formatPrice(val, row) {
    if (!val) {
        return '';
    }
    return '<div style="width:100%;text-align:right;">' + new Number(val).toFixed(2) + '</div>';
}

// 弹出加载层
function load() {
    $("<div class=\"datagrid-mask\"></div>").css({
        display: "block",
        width: "100%",
        height: $(window).height(),
        'z-index': 1000000
    }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").css({'z-index': 1000001}).html("正在请求，请稍候。。。").appendTo("body").css({
        display: "block",
        left: ($(document.body).outerWidth(true) - 190) / 2,
        top: ($(window).height() - 45) / 2
    });
}

// 弹出加载层
function openLoad() {
    load()
}

// 取消加载层
function disLoad() {
    $(".datagrid-mask").remove();
    $(".datagrid-mask-msg").remove();
}

// easyui datagrid 列tips
function formatTips(val, row, index) {
    var _tempId = uuid();
    var tips;
    if (null != val && ('' != val || 0 == val || '0' == val) && val != 'null' && val != undefined) {
        setTimeout("$('#" + _tempId + "').tipso({useTitle: false});", 50);
        tips = '<a href="#" class="dataGridCell easyui-data-cell-ellipsis" id="' + _tempId + '" data-tipso="' + val + '" onclick="javascript:void(0)">' + val + '</a>';
    }
    return tips;
}

//将时间转换为日期
function time2Day(_val, _row, _index) {
    if (_val) {
        return _val.substring(0, 10);
    }
    return '';
}

// easyui datagrid 列tips
function formatRowColor(val, row, index) {
    var _color = '#1ab394';
    if (val.indexOf('禁') != -1 || val.indexOf('未') != -1 || val.indexOf('异') != -1) {
        _color = 'red';
    }
    return '<a href="javascript:void(0)" style="background-color: ' + _color + ';padding: 5px;border-radius: 2px;color: white;">' + val + '</a>';
}


$(function () {

    if ($(window).width() < 1300) {
        $('#btnLable').html('</br>&nbsp;');

    }
})

//获取 jackToken
function getReferer() {
    return 'jackToken';
}

//去左右空格;
function trim(s) {
    return s.replace(/(^\s*)|(\s*$)/g, "");
}

(function ($) {
    $.fn.extend({
        selectBox: function (options) {
            var defaults = {};
            var htmls = '';
            if (options.attr) {
                $.each(options.attr, function (i, item) {
                    htmls += '<div class="boxs'
                    if (options.selected == i) {
                        htmls += ' active';
                    }
                    htmls += '" rel="' + item + '">' + i + '</div>';
                });
            } else {
                $.each(defaults.attr, function (i, item) {
                    htmls += '<div class="boxs'
                    if (defaults.selected == i) {
                        htmls += ' active';
                    }
                    htmls += '" rel="' + item + '">' + i + '</div>';
                });
            }
            options = $.extend({}, defaults, options);
            return $(this).each(function () {
                $(this).html(htmls);
                $(this).find('.boxs').on('click', function () {
                    if (!$(this).hasClass('active')) {
                        $(this).addClass('active').siblings().removeClass("active");
                        options.onSelect.call($(this), $(this).attr('rel'));
                    }
                });
            });
        }
    });

})(window.jQuery);//此处也可写成(jQuery);


//判断函数是否存在
function isExitsFunction(funcName) {
    try {
        if (typeof (eval(funcName)) == "function") {
            return true;
        }
    } catch (e) {
    }
    return false;
}

/**
 * 下载列表数据  url ${basePath}/ms/bbsArticle/ 只到这里  dataGridId写easyui datagrid 的id
 如果easyui 列表的字段不够，你可以通过自己在页面上写一个excelSett 方法来扩展字段
 //扩展字段实例
 function excelSett(){
        return [{"field":"id","title":"id"}];
	}
 */
function exportExcelPub(url, dataGridId) {
    var columnFields = $("#listGrid").datagrid('getColumnFields');
    var fieldArray = [];
    for (i = 0; i < columnFields.length; i++) {
        fieldArray.push($("#listGrid").datagrid('getColumnOption', columnFields[i]));
    }
    // 如果自定义了其他的列，则加上这些列
    if (isExitsFunction('excelSett')) {
        var appendFieldArray = excelSett();
        for (i = 0; i < appendFieldArray.length; i++) {
            fieldArray.push(appendFieldArray[i]);
        }
    }
    $.ajax({
        url: url + "/setExportField",
        type: "post",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data: json2str(fieldArray),
        success: function (result) {
            window.location.href = url + "/pubExportExcel";
        }
    });
}

//打开一个frame
function openMainFrame(_baseUrl, _url, _title) {
    if ($("#openFrameIframe").length == 0) {
        $('body').append('<div style="display:none;" ><iframe id="openFrameIframe"></iframe></div>')
    }
    $('#openFrameIframe').attr('src', _baseUrl + '/ms/index');
    setTimeout(function () {
        $('#openFrameIframe').attr('src', ucenterPath + '/page/ms/pc_gateway_index/open_frame.jsp?title=' + _title
            + '&encode=true&url=' + encodeURIComponent(_baseUrl + _url));
    }, 1000);
}

//分钟计算
function minutesAdd(_sourceTime, _addMinutes) {
    var _tempDate = new Date();
    var _sourceMinutes = new Number(_sourceTime.split(":")[1]);
    _tempDate.setHours(new Number(_sourceTime.split(":")[0]));
    _tempDate.setMinutes(_sourceMinutes);
    var _temp_s = _tempDate.getTime();//转化为时间戳毫秒数
    _tempDate.setTime(_temp_s + 1000 * 60 * _addMinutes);
    return _tempDate.getHours() + ':' + _tempDate.getMinutes();
}

$.extend({
    includePath: '',
    include: function (file) {
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++) {
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + $.includePath + name + "'";
            if ($(tag + "[" + link + "]").length == 0) document.write("<" + tag + attr + link + "></" + tag + ">");
        }
    }
});