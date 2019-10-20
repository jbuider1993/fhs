(function($) {

	var
		undefined,
		_plugins = {
			static   : [],
			instance : []
		},
		_options = {};



	$.extend($.plugin = {}, _init(false));

	$.fn.extend($.fn.plugin = {}, _init(true));

	$.config = function(plugin, opts) {
		if (typeof opts === 'undefined')
			return _options[plugin] || undefined;

		_options[plugin] = $.extend(_options[plugin], opts);

		return $;
	};

	$.fn.config = function(plugin, opts) {
		var $this = $(this);

		if (typeof opts === 'undefined')
			return $this.data(plugin + '.config');

		return $this.each(function() {
			var
				$this   = $(this),
				curOpts = $this.data(plugin + '.config') || {};

			$this.data(plugin + '.config', $.extend(curOpts, opts));
		});
	};


	function _init(fn) {
		return {


			add : function(name, func) {
				return _add(fn, name, func);
			},

			remove : function(name) {
				return _remove(fn, name);
			},

			has : function(name) {
				return _has(fn, name);
			},

			list : function() {
				return _list(fn);
			}
		};
	};

	function _add(fn, name, func) {
		var
			func             = $.isFunction(func) ? func : function() { return this; },
			applyTo          = _applyTo(fn),
			staticOrInstance = _staticOrInstance(fn);


		function _constructor(jq, args) {
			if (fn) {

				this.length   = 0;
				this.selector = jq.selector;
				this.context  = jq.context;


				Array.prototype.push.apply(this, $.makeArray(jq));
			}


			return func.apply(this, args);
		};


		applyTo[name] = function() {
			return new _constructor(this, arguments);
		};


		for (var i in func) {
			applyTo[name][i] = _constructor[i] = func[i];
		}


		applyTo[name].fn = applyTo[name].prototype = _constructor.prototype = func.prototype;


		applyTo[name].fn.$ = applyTo[name].fn.jQuery = function() {
			return $(this);
		};


		_plugins[staticOrInstance][_plugins[staticOrInstance].length] = name;


		return applyTo;
	};

	function _remove(fn, name) {
		var
			applyTo          = _applyTo(fn),
			staticOrInstance = _staticOrInstance(fn),
			index            = $.inArray(name, _plugins[staticOrInstance]);

		if (index > -1) {
			_plugins[staticOrInstance][index] = undefined;
			applyTo[name]                     = undefined;
		}

		return applyTo;
	};


	function _has(fn, name) {
		return $.inArray(name, _plugins[_staticOrInstance(fn)]) > -1;
	};

	function _list(fn) {
		return _plugins[_staticOrInstance(fn)];
	};


	function _applyTo(fn) {
		return fn ? $.fn : $;
	};


	function _staticOrInstance(fn) {
		return fn ? 'instance' : 'static';
	};

})(jQuery);

;(function($) {

	var _table = {}; //表格
	var _options = {}; //选项
	var _onDeleteFun = {}; //选项

	function _listUploadFile() {
		return this;
	}

	//初始默认配置
	_listUploadFile.defaultOptions = {
			item:{},
			onItemAdd: function() {},
			onDelete: function() {},
			onDownload: function() {},
			tableObj:null,
			fileLength:10,
			fileBaseUrl:'',
			delId:'',
			downloadAction: fileDownUrl,
			deleteAction : fileDelUrl,
			table:null,
			options:null,
			maxFileNumer:5,
			data:{}
		};

	_listUploadFile.prototype = {

		//初始化
		init : function(options) {
			//_popupContent = '<div style="display:none"><div id="inline" style="width:auto; height:auto;overflow:auto"><img id="imgPan" src="images" alt="" style="float:left; margin:4px;widht:300px;height:300px;;" /></div></div>';
			//$(_popupContent).appendTo($(document.documentElement));
			var pid = $(this).attr("id");

			_options[pid] = $.extend(_listUploadFile.defaultOptions, options);

			_table[pid] =$('<ul class="img_dis imgtag" id="ul' + pid + '"></url>');
			_table[pid].appendTo(this);
			_onDeleteFun[pid] = options.onDelete;
			return this;
		},

		//添加
		addItem : function(options) {
			var pid = $(this).attr("id");
			var item = options.item;
			if (!_table[pid])
			{

				$(this).listUploadFile().init();
			}
			_options[pid].data[item.fileId]=true;
			var rows = [];

			rows.push("<li class='fileShowLi' id='" + item.fileId + "'>");
			item.fileSaveName  = item.fileId +  item.fileSuffix;
			var regImgPtn = /\.jpg$|\.jpeg$|\.gif$|\.png$/ig;
			var isImgType = regImgPtn.test(item.fileSuffix);
			var showFileName = String(item.fileName);
			var max_length_file_name = 10;
			var suffixName = "...";
			var fileType = item.fileSuffix.substr(1);
			var fileClassName;
			if (showFileName.length > _options.fileLength)
			{
				showFileName = showFileName.substr(0,max_length_file_name) + suffixName;
			}

			if (isImgType)
			{
				fileClassName = 'file-img';
			}
			else
			{
				switch(fileType)
				{
					case 'txt':
					{fileClassName = 'txt';}break;
					case 'rar':;
					case 'zip':
					{fileClassName = 'rar';}break;
					case 'doc':;
					case 'docx':
					{fileClassName = 'word';}break;
					case 'xlsx':;
					case 'xls':
					{fileClassName = 'excel';}break;
					case 'mp4':;
					case 'webm':;
					case 'ogg':;
					case 'MP4':
					{fileClassName = 'video';}break;
					case 'mp3':;
					case 'MP3':;
					case 'wav':
					{fileClassName = 'audio';}break;
					default:
					{
						//其他类型
						{fileClassName = 'file';}break;
					}

				}

			}

			//rows.push("<td style=\"border:0;\">");
			//rows.push("<a  class='" + fileClassName + "' href='javascript:void(0)'></a>");
			//rows.push("</td>");


			if (!isImgType)
			{
				rows.push("<a   pid='" + $(this).attr("id") + "'  fileSaveName='" + item.fileSaveName + "' fileName='" + item.fileName + "' onclick=\'$(this).listUploadFile().download()' href='javascript:void(0)'>");
				rows.push("<img src='" + staticPath +"/images/" + fileClassName + ".png' class='imgbac'/>");
			}
			else
			{
				rows.push("<a   pid='" + $(this).attr("id") + "'  fileSaveName='" + item.fileSaveName + "' fileName='" + item.fileName + "' class='showImg' href='#inline'  >");
				rows.push("<img src='" + downForId +"?fileId=" +  item.fileId  + "' class='imgbac'/>");
			}


			rows.push("</a>");
			rows.push('<a fileId="' + item.fileId + '" pid="' + $(this).attr("id") + '"  class="delete" onclick="$(this).listUploadFile().deleteFile()" /></a>');
			rows.push("</li>");
			var rowData = rows.join("");
			_table[pid].append(rowData);
			if(!_options[pid].viewer)
			{
				var viewer = new Viewer(document.getElementById('ul' + pid), {
					url: 'data-original'
				});
				_options[pid].viewer=viewer;
			}
			_options[pid].viewer['update']();
			return this;
		},

		//删除
		deleteFile:function(options) {

			var pid = $(this).attr('pid');
			_options[pid].delFileId = $(this).attr('fileId');

			var url =  _options[pid].deleteAction + '?jsonpCallback=?&fileId=' + $(this).attr('fileId') + '&fileName=' + $(this).attr('fileSaveName') ;

			$.messager.confirm('确认','你想删除上传的文件吗?',function(r){
				if (r){
					var fid = _options[pid].delFileId;
					_options[pid].data[fid] = false;
					$('#' + fid).remove();
					$.messager.alert("操作提示", "删除成功！");
					_options[pid].onDelete = _onDeleteFun[pid];
					if(_options[pid].onDelete)
					{
						_options[pid].onDelete.call(_options[pid],pid);
					}
				}
			});
			return this;
		},
		getUploadFileSize:function(pid){
			var size = 0;
			for(var key in _options[pid].data)
			{
				if(_options[pid].data[key] == true)
				{
					size+=1;
				}
			}
			return size;
		},
		//下载
		download : function(options) {

			var param =  '?fileName=' + $(this).attr('fileSaveName') + '&showFileName=' + $(this).attr('fileName');
			var pid = $(this).attr('pid');
            location.href = _getDownloadURL(param,pid);

			return this;
		},

		//显示路径
		showImgURL : function(options) {

			var param =  '?fileName=' + $(this).attr('fileSaveName') + '&showFileName=' + $(this).attr('fileName');
			var pid = $(this).attr('pid');
            return _getDownloadURL(param,pid);
		},
		clear:function(pid)
		{
			$('#ul' + pid).html('');
			_options[pid].data=[];
			_options[pid].viewer=null;
		},
		getFileId:function(pid){
			var result = [];
			for(var key in _options[pid].data)
			{
				if(_options[pid].data[key] == true)
				{
					result.push(key);
				}
			}
			return result.join(',');
		},
		getOpt:function(options){

			var pid = $(this).attr('id');

			return _options[pid];
		}

	};

	function _getBasePath()
	{
		return basePath;
	}

	function _getDownloadURL(param,pid)
	{
		return  _options[pid].downloadAction + param;
	}

	$.fn.plugin.add('listUploadFile', _listUploadFile);

})(jQuery);