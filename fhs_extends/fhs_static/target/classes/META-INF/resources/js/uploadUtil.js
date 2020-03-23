var fileArrayMap = {};
//初始化文件上传插件
//上传图片 fileInputId 承载插件的
var multi_static = true;

function inituploadifyIMG(fileInputId, multi, buttonText,maxFileNumer) {
	buttonText = buttonText == null ? "图片上传" : buttonText;
	multi_static = multi;
	maxFileNumer = maxFileNumer == null ? 5 : maxFileNumer;
	var _that = $('#' + fileInputId);
	fileArrayMap[fileInputId] = [];
	$('#' + fileInputId + 'Show').listUploadFile().init({maxFileNumer:maxFileNumer});
	$('#' + fileInputId + 'Show').listUploadFile().clear(fileInputId + 'Show');
	_that.uploadifive({
		'fileType': IMG,
		'uploadScript': fileUploadUrl,
		'buttonText': buttonText,
		'auto': true,
		'multi': multi,
		'uploadLimit': 0,
		'onUploadComplete': function(file, data, response) {
			file = eval('(' + data + ')');
			if(!IMG_REG.test(file.fileName)) {
				return false;
			}
			var tempobj = $('#' + fileInputId + 'Show').listUploadFile().addItem({ item: file });
			if(!multi) {
				_that.uploadify('disable', true);
			}
		},
		'onSelect': function(fileObj) {
			if(fileObj.files.length + $('#' + fileInputId + 'Show').listUploadFile().getUploadFileSize(fileInputId + 'Show')>maxFileNumer)
			{
				EalertE('文件已达到上限，不可继续上传');
				return false;
			}
			return true;
		}
	});
}

//上传其他文件   _acceptFileType = video/*  视频  audio/* 音频 *支持所有的文件，其他的请百度 _timeLength如果是视频或者音频的话长度判断
function inituploadifyALL(fileInputId, multi,buttonText,maxFileNumer,_acceptFileType,_timeLength) {
	buttonText = buttonText == null ? "文件上传" : buttonText;
	maxFileNumer = maxFileNumer == null ? 5 : maxFileNumer;
	multi_static = multi;
	var _that = $('#' + fileInputId);
	fileArrayMap[fileInputId] = [];
	$('#' + fileInputId + 'Show').listUploadFile().init({maxFileNumer:maxFileNumer});
	$('#' + fileInputId + 'Show').listUploadFile().clear(fileInputId + 'Show');
	_that.uploadifive({
		'uploadScript': fileUploadUrl,
		'buttonText': '文件上传',
		'auto': true,
		'multi': multi,
		'fileType':_acceptFileType,
		'uploadLimit': 0,
		'onUploadComplete': function(file, data, response) {
			
			file = eval('(' + data + ')');
			if(_timeLength)
			{
				if(file.timeLength > _timeLength)
				{
					EalertE('媒体文件时长超标,最大为:' + _timeLength + '秒，您上传的文件时长为:' + file.timeLength + '秒');
					return;
				}
			}
			
			if(!multi) {
				 $('#' + fileInputId + 'Show').listUploadFile().clear(fileInputId + 'Show');
			}
			var tempobj = $('#' + fileInputId + 'Show').listUploadFile().addItem({ item: file });
		},
		'onSelect': function(fileObj) {
			
			if(fileObj.files.length + $('#' + fileInputId + 'Show').listUploadFile().getUploadFileSize(fileInputId + 'Show')>maxFileNumer)
			{
				EalertE('文件已达到上限，不可继续上传');
				return false;
			}
			
			
			return true;
		}
	});
}

//上传头像带回显
function inituploadifyHeader(fileInputId, buttonText,maxSize) {
	buttonText = buttonText == null ? "头像上传" : buttonText;
	var _that = $('#' + fileInputId);
	fileArrayMap[fileInputId] = [];
	var viewer = new Viewer(document.getElementById(fileInputId + 'Img'), {});
	_that.uploadifive({
		'fileType': IMG,
		'uploadScript': fileUploadUrl,
		'buttonText': buttonText,
		'multi': false,
		'onUploadComplete': function(file, data, response) {

			file = eval('(' + data + ')');
			if(!IMG_REG.test(file.fileName)) {
				return false;
			}
			tempArray = fileArrayMap[fileInputId];
			tempArray[tempArray.length] = file.fileId;
			$('#' + fileInputId + 'Img').attr('src', fileDownUrl + '?fileName=' + file.fileId + file.fileSuffix + '&showFileName=header');
			$('#' + fileInputId + 'Val').val(file.fileId);
		},
		'onSelect': function(fileObj) {
			if((maxSize && fileObj.files[0].size/1000) > maxSize)
			{
				EalertE('文件过大，最大上传' + maxSize + 'kb的文件');
				return false;
			}

			if(!IMG_REG.test(fileObj.files[0].name)) {
				return false;
			}
			return true;
		}
	});
}

//初始化头像
function initHeader(fileInputId, fileId) {
	$.ajax({
		type: 'GET',
		url: fileListUrl + '?fileIds=' + fileId + '&jsonpCallback=?',
		dataType: 'json',
		success: function(files) {
			for(i = 0; i < files.length; i++) {
				file = files[0];
				$('#' + fileInputId + 'Img').attr('src', fileDownUrl + '?fileName=' + file.fileId + file.fileSuffix + '&showFileName=header');
			}
		}
	});
}


// 初始化文件列表
function initFiles(fileInputId, fileIds, multi, isView) { // 如果文件列表为空，直接返回
	if(fileIds == null) { return; }
	$.ajax({
		type: 'GET',
		url: fileListUrl + '?fileIds=' + fileIds + '&jsonpCallback=?',
		dataType: 'json',
		success: function(files) {
			for(i = 0; i < files.length; i++) {
				var tempobj = $('#' + fileInputId + 'Show').listUploadFile().addItem({ item: files[i] });
			}
			//如果是查看页面，那么将删除文件删除按钮去掉
			if(isView) {
				$('.delete').hide();
			}
		}
	});
}

//通过文件编号获取文件路径
function readFilePath(fileIds) {
	fileArrayMap[fileInputId] = fileIds.split(',');
	$.ajax({
		type: 'GET',
		async: false,
		url: fileListUrl + '?fileIds=' + fileIds + '&jsonpCallback=?',
		dataType: 'json',
		success: function(files) {
			for(i = 0; i < files.length; i++) {
				var tempobj = $('#' + fileInputId + 'Show').listUploadFile().addItem({ item: files[i] });

				var imgpath = $('#' + fileInputId + 'Img').listUploadFile().showImgURL();
				return imgpath;
			}

		}
	});
}