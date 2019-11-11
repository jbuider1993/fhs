rmal";
							}
							temp_suc(data, curform);
						}
					}
					if (ajaxsetup.error) {
						var temp_err = ajaxsetup.error;
						ajaxsetup.error = function(data) {
							settings.callback && settings.callback(data);
							curform[0].validform_status = "normal";
							curform[0].validform_ajax = null;
							temp_err(data, curform);
						}
					}
					var localconfig = {
						type : "POST",
						async : true,
						data : curform.serializeArray(),
						success : function(data) {
							if ($.trim(data.status) === "y") {
								curform[0].validform_status = "posted";
								Validform.util.showmsg.call(curform, data.info,
										settings.tiptype, {
											obj : curform,
											type : 2,
											sweep : settings.tipSweep
										}, "byajax");
							} else {
								curform[0].validform_status = "normal";
								Validform.util.showmsg.call(curform, data.info,
										settings.tiptype, {
											obj : curform,
											type : 3,
											sweep : settings.tipSweep
										}, "byajax");
							}
							settings.callback && settings.callback(data);
							curform[0].validform_ajax = null;
						},
						error : function(data) {
							var msg = "status: " + data.status
									+ "; statusText: " + data.statusText;
							settings.callback && settings.callback(data);
							curform[0].validform_status = "normal";
							curform[0].validform_ajax = null;
						}
					}
					ajaxsetup = $.extend( {}, localconfig, ajaxsetup, {
						dataType : "json"
					});
					curform[0].validform_ajax = $.ajax(ajaxsetup);
				} else {
					if (!settings.postonce) {
						curform[0].validform_status = "normal";
					}
					var url = url || config.url;
					if (url) {
						curform.attr("action", url);
					}
					return settings.callback && settings.callback(curform);
				}
			}
			return false;
		},
		resetForm : function() {
			var brothers = this;
			brothers.each(function() {
				this.reset();
				this.validform_status = "normal";
			});
			brothers.find(".Validform_right").text("");
			brothers.find(".passwordStrength").children().removeClass(
					"bgStrength");
			brothers.find(".Validform_checktip").removeClass(
					"Validform_wrong Validform_right Validform_loading");
			brothers.find(".Validform_error").removeClass("Validform_error");
			brothers.find("[datatype]").removeData("cked").removeData(
					"dataIgnore").each(function() {
				this.validform_lastval = null;
			});
			brothers.eq(0).find("input:first").focus();
		},
		abort : function() {
			if (this.validform_ajax) {
				this.validform_ajax.abort();
			}
		}
	}
	$.Datatype = Validform.util.dataType;
	Validform.prototype = {
		dataType : Validform.util.dataType,
		eq : function(n) {
			var obj = this;
			if (n >= obj.forms.length) {
				return null;
			}
			if (!(n in obj.objects)) {
				obj.objects[n] = new Validform($(obj.forms[n]).get(),
						obj.settings, true);
			}
			return obj.objects[n];
		},
		resetStatus : function() {
			var obj = this;
			$(obj.forms).each(function() {
				this.validform_status = "normal";
			});
			return this;
		},
		setStatus : function(status) {
			var obj = this;
			$(obj.forms).each(function() {
				this.validform_status = status || "posting";
			});
			return this;
		},
		getStatus : function() {
			var obj = this;
			var status = $(obj.forms)[0].validform_status;
			return status;
		},
		ignore : function(selector) {
			var obj = this;
			var selector = selector || "[datatype]"
			$(obj.forms).find(selector).each(
					function() {
						$(this).data("dataIgnore", "dataIgnore").removeClass(
								"Validform_error");
					});
			return this;
		},
		unignore : function(selector) {
			var obj = this;
			var selector = selector || "[datatype]"
			$(obj.forms).find(selector).each(function() {
				$(this).removeData("dataIgnore");
			});
			return this;
		},
		addRule : function(rule) {
			var obj = this;
			var rule = rule || [];
			for ( var index = 0; index < rule.length; index++) {
				var o = $(obj.forms).find(rule[index].ele);
				for ( var attr in rule[index]) {
					attr !== "ele" && o.attr(attr, rule[index][attr]);
				}
			}
			$(obj.forms).each(
					function() {
						var $this = $(this);
						Validform.util.enhance.call($this,
								obj.settings.tiptype, obj.settings.usePlugin,
								obj.settings.tipSweep, "addRule");
					});
			return this;
		},
		ajaxPost : function(flag, sync, url) {
			var obj = this;
			if (obj.settings.tiptype == 1 || obj.settings.tiptype == 2
					|| obj.settings.tiptype == 3) {
				creatMsgbox();
			}
			Validform.util.submitForm.call($(obj.forms[0]), obj.settings, flag,
					url, "ajaxPost", sync);
			return this;
		},
		submitForm : function(flag, url) {
			var obj = this;
			var subflag = Validform.util.submitForm.call($(obj.forms[0]),
					obj.settings, flag, url);
			subflag === undef && (subflag = true);
			if (subflag === true) {
				obj.forms[0].submit();
			}
			return this;
		},
		resetForm : function() {
			var obj = this;
			Validform.util.resetForm.call($(obj.forms));
			return this;
		},
		abort : function() {
			var obj = this;
			$(obj.forms).each(function() {
				Validform.util.abort.call(this);
			});
			return this;
		},
		check : function(bool, selector) {
			var selector = selector || "[datatype]", obj = this, curform = $(obj.forms), flag = true;
			curform.find(selector).each(
					function() {
						Validform.util.check.call(this, curform, obj, "", bool)
								|| (flag = false);
					});
			return flag;
		},
		config : function(setup) {
			var obj = this;
			setup = setup || {};
			$(obj.forms).each(
					function() {
						this.validform_config = $.extend(true,
								this.validform_config, setup);
					});
			return this;
		}
	}
	$.fn.Validform = function(settings) {
		return new Validform(this, settings);
	};
	function setCenter(obj, time) {
		var left = ($(window).width() - obj.outerWidth()) / 2, top = ($(window)
				.height() - obj.outerHeight()) / 2, top = (document.documentElement.scrollTop ? document.documentElement.scrollTop
				: document.body.scrollTop)
				+ (top > 0 ? top : 0);
		obj.css( {
			left : left
		}).animate( {
			top : top
		}, {
			duration : time,
			queue : false
		});
	}
	function creatMsgbox() {
		if ($("#Validform_msg").length !== 0) {
			return false;
		}
		//update-begin--Author:xuelin  Date:20170512 for：TASK #1947 【样式优化】默认采用validform的提示框信息，美化下-------------------
		msgobj = $(
				'<div id="Validform_msg"><div class="Validform_title">' + tipmsg.tit + '<a class="Validform_close" href="javascript:void(0);">&chi;</a></div><div class="Validform_info"></div><div class="iframe"><iframe frameborder="0" scrolling="no" height="100%" width="100%"></iframe></div><div class="Validform_btn"><a class="Validform_btn_a">确定</a></div></div>')
				.appendTo("body");
		msgobj.find("a.Validform_btn_a").click(function() {
			msgobj.hide();
			msghidden = true;
			if (errorobj) {
				errorobj.focus().addClass("Validform_error");
			}
			return false;
		}).focus(function() {
			this.blur();
		});		
		//update-end--Author:xuelin  Date:20170512 for：TASK #1947 【样式优化】默认采用validform的提示框信息，美化下--------------------
		msgobj.find("a.Validform_close").click(function() {
			msgobj.hide();
			msghidden = true;
			if (errorobj) {
				errorobj.focus().addClass("Validform_error");
			}
			return false;
		}).focus(function() {
			this.blur();
		});
		$(window).bind("scroll resize", function() {
			!msghidden && setCenter(msgobj, 400);
		});
	}
	;
	$.Showmsg = function(msg) {
		creatMsgbox();
		Validform.util.showmsg.call(win, msg, 1, {});
	};
	$.Hidemsg = function() {
		msgobj.hide();
		msghidden = true;
	};
})(jQuery, window)