var modelConfig = {
    title: '停车场', pkey: 'id', type: 'uuid', orderBy: 'update_time Desc',
    namespace: "parking", table: 't_park_parking', trans: true, extendsParam: 'parent_park_id=${param.parent_park_id}',
    joinColumns: JSON.stringify({park_name: 'parkName'}), db: "park", dp: JSON.stringify({id: 'parkIds'})
};
var listPage = {
    listFieldSett: function () {
        return [
            {name: 'park_name', title: '停车场名称', width: '20%', align: 'center'},
            {name: 'space_count', title: '车位数', width: '7%', align: 'center'},
            {name: 'address', title: '位置', width: '19%', align: 'center'},
            {
                name: 'operation_way',
                title: '运营模式',
                width: '10%',
                align: 'center',
                trans: 'book',
                key: 'operation_way',
                showField: 'transMap.operationWayName'
            },
            {name: 'business_unit', title: '运营单位', width: '10%', align: 'center'},
            {
                name: 'park_type',
                title: '停车场类型',
                width: '10%',
                align: 'center',
                trans: 'book',
                key: 'park_type',
                showField: 'transMap.parkTypeName'
            },
            {
                name: 'is_disable',
                title: '是否禁用',
                width: '10%',
                formart: 'formatRowColor',
                align: 'center',
                trans: 'book',
                key: 'is_disable',
                showField: 'transMap.isDisableName'
            },
            {
                name: 'create_user',
                title: '创建人',
                width: '8%',
                align: 'center',
                trans: 'user',
                showField: 'transMap.create_userUserName'
            },
            {
                name: 'is_sync',
                title: '是否已下发',
                width: '5%',
                align: 'center',
                trans: 'book',
                key: 'yesOrNo',
                showField: 'transMap.isSyncName'
            },
        ]
    },

    isColumnButton: function () {
        return false;
    },
    filters: function () {
        return [
            {name: 'park_name', type: 'input', title: '停车场名称', filterType: 'like'},
            {name: 'address', type: 'input', title: '位置', filterType: 'like'},
        ];
    },
    buttons: function () {
        return [
            {title: '查看密钥', fun: 'showSecret', permissionsCode: 'parking:see', isRow: true},
            {title: '查看二维码', fun: 'showQR', permissionsCode: 'parking:see', isRow: true},
            {title: '子车厂管理', fun: 'childParking', permissionsCode: 'parking:see', isRow: true, id: 'childParkingBTN'},
        ];
    },
    disableButtons: function () {
        return [];
    },
    otherFunctions: function () {
        return {
            showSecret: function (row) {
                openDialog('${path.basePath}/ms/pagex/parking_add_update.jsp?showSecret=true&isView=true&id='
                    + row.id,
                    '查看停车场密钥');
            },
            showQR: function (row) {
                openDialog('${path.basePath}/ms/pagex/parking_add_update.jsp?showQR=true&isView=true&id='
                    + row.id,
                    '查看二维码');
            },
            childParking: function (row) {
                var _openFrameMsg = {
                    url: '${path.basePath}/ms/pagex/parking_list.jsp?parent_park_id=' + row.id,
                    title: row.parkName + '的子车场管理'
                }
                top.postMessage(_openFrameMsg, '*');
            },
            onListPageReady: function () {
                if ('${param.parent_park_id}' != '' && '${param.parent_park_id}' != '0') {
                    $('#childParkingBTN').remove();
                }
            },
        }
    }
};

var add = {
    formFields: function () {
        return [
            {type: 'divStart', id: 'parkingMainDiv'},
            {name: 'park_name', title: '停车场名称', required: true, type: 'input'},
            {name: 'area_count', title: '区域编码', type: 'input', dataType: 'n1-11'},
            {name: 'space_count', title: '车位总数', required: true, type: 'input', dataType: 'n1-3'},
            {name: 'zoom_rate', title: '放大率', type: 'input', dataType: 'n1-2'},
            {name: 'is_disable', title: '是否禁用', type: 'switch', dft: false},
            {name: 'free_time', title: '免费时长(分钟)', required: true, type: 'input', dataType: 'n1-11'},
            {name: 'exit_time', title: '提前缴费后出场时间(分钟)', required: true, type: 'input', dataType: 'n1-11'},
            {name: 'black_list_remark', title: '黑名单提示语', required: true, type: 'input'},
            {name: 'one_day_max_fee', title: '每天最高收费', required: true, type: 'input', dataType: 'double2'},
            {name: 'operation_way', title: '运营模式', type: 'book', code: 'operation_way'},
            {name: 'business_unit', title: '运营单位', type: 'input'},
            {name: 'park_type', title: '车场类型', type: 'book', code: 'park_type'},
            {name: 'service_life', title: '使用年限', type: 'input', dataType: 'n1-2|double2'},
            {name: 'right_unit', title: '产权单位', type: 'input', dataType: 's1-64'},
            {name: 'right_charge', title: '产权负责人', type: 'input', dataType: 's1-20'},
            {name: 'right_phone', title: '产权负责人电话', type: 'input', dataType: 'tel|tel_p'},
            {name: 'business_charge', title: '运营负责人', type: 'input'},
            {name: 'business_phone', title: '运营负责人电话', type: 'input', dataType: 'tel|tel_p'},
            {name: 'description', title: '车场描述', type: 'text', required: true},
            {name: 'url', title: '网络地址', type: 'bigInput', dataType: 'url'},
            {name: 'img', title: '图片', type: 'up', required: true, placeholder: '请上传图片'},
            {name: 'only_vip', title: '是否仅月租', type: 'book', code: 'yesOrNo'},
            {name: 'longitude,latitude,address,provinceid,cityid,areaid', type: 'address'},
            {type: 'divEnd'},
            {type: 'divStart', id: 'parkingSecretDvi', style: 'display:none;'},
            {name: 'secret', title: '密钥', type: 'bigInput'},
            {name: '', title: '停车场id', type: 'bigInput', class: 'parking_id'},
            {type: 'divEnd'},
            {type: 'divStart', id: 'qrCodeDiv', style: 'display:none;width:90%;'},
            {type: 'divEnd'},
            {name: 'parent_park_id', type: 'hide'},
            {name: 'is_sync', type: 'hide'}
        ];
    },
    otherFunctions: function () {
        return {
            ready: function () {
                if ('${param.isAdd}' == 'true') {
                    if ('${param.parent_park_id}' != '') {
                        $('#parentParkId').val('${param.parent_park_id}');
                    } else {
                        $('#parentParkId').val('0');
                    }
                }
            },
            loadSuccess: function (info) {
                $('.parking_id').val('${param.id}');
                if ('${param.showSecret}' == 'true') {
                    $('#parkingMainDiv').hide();
                    $('#parkingSecretDvi').show();
                }
                if ('${param.showQR}' == 'true') {
                    $('#parkingMainDiv').hide();
                    $("#qrCodeDiv").qrcode({
                        width: 500,
                        height: 500,
                        text: "'${path.park_qr_pase_path}?parkingId=' +info.id"
                    });

                    $('#qrCodeDiv').show();
                }
            },
            onSave: function () {
                if ('${param.isAdd}' == 'true') {
                    $('#secret').val(uuid());
                }
                $('#isSync').val(0);
            },
            saveSucess: function () {
            },
            saveError: function () {

            },
        }
    }
}

//给前段用户的接口
// inF代表哪些字段需要返回给前段
// outF代表除了哪些字段要返回给前段
// where 后面支持哪些参数
var front = {
    apis: function () {
        return [
            {name: 'info', type: 'one', inF: 'parkName', where: 'id'},
            {name: 'list', type: 'list', inF: 'parkName,id', jsonp: 'true'},
        ]
    }
};