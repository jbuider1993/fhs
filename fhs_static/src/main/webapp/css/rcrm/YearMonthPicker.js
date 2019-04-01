/*重写时间显示模板*/
if (Ext.DatePicker) {
    Ext.apply(Ext.DatePicker.prototype, {
        createMonthPicker: function () {
            if (!this.monthPicker.dom.firstChild) {
                var buf = ['<table border="0" cellspacing="0">'];
                for (var i = 0; i < 6; i++) {
                    buf.push(
                        '<tr>',
                        i === 0 ?
                        '<td class="x-date-mp-ybtn" align="center"><a class="x-date-mp-prev"></a></td><td class="x-date-mp-ybtn x-date-mp-sep" align="center"><a class="x-date-mp-next"></a></td>' :
                        '<td class="x-date-mp-year"><a href="#"></a></td><td class="x-date-mp-year x-date-mp-sep"><a href="#"></a></td>',
                        '<td class="x-date-mp-month"><a href="#">', Date.getShortMonthName(i), '</a></td>',
                        '<td class="x-date-mp-month"><a href="#">', Date.getShortMonthName(i + 6), '</a></td>',
                        '</tr>'
                    );
                }
                buf.push(
                    '<tr class="x-date-mp-btns"><td colspan="4"><button type="button" class="x-date-mp-ok">',
                        this.okText,
                        '</button><button type="button" class="x-date-mp-cancel">',
                        this.cancelText,
                        '</button></td></tr>',
                    '</table>'
                );
                this.monthPicker.update(buf.join(''));
                this.mon(this.monthPicker, 'click', this.onMonthClick, this);
                this.mon(this.monthPicker, 'dblclick', this.onMonthDblClick, this);
                this.mpMonths = this.monthPicker.select('td.x-date-mp-month');
                this.mpYears = this.monthPicker.select('td.x-date-mp-year');
                this.mpMonths.each(function (m, a, i) {
                    i += 1;
                    if ((i % 2) === 0) {
                        m.dom.xmonth = 5 + Math.round(i * 0.5);
                    } else {
                        m.dom.xmonth = Math.round((i - 1) * 0.5);
                    }
                });
            }
        },
		onMonthClick : function(e, t){
			e.stopEvent();
			var el = new Ext.Element(t), pn;
			if(el.is('button.x-date-mp-cancel')){
				delete this.cancelFocus;
				this.fireEvent('select', this, this.value);
			}
			else if(el.is('button.x-date-mp-ok')){
				var d = new Date(this.mpSelYear, this.mpSelMonth, (this.activeDate || this.value).getDate());
				if(d.getMonth() != this.mpSelMonth){
					d = new Date(this.mpSelYear, this.mpSelMonth, 1).getLastDateOfMonth();
				}
				this.update(d);
				//年月赋值
				e.stopEvent();
				this.setValue(new Date(d));
				delete this.cancelFocus;
				this.fireEvent('select', this, this.value);
			}
			else if((pn = el.up('td.x-date-mp-month', 2))){
				this.mpMonths.removeClass('x-date-mp-sel');
				pn.addClass('x-date-mp-sel');
				this.mpSelMonth = pn.dom.xmonth;
			}
			else if((pn = el.up('td.x-date-mp-year', 2))){
				this.mpYears.removeClass('x-date-mp-sel');
				pn.addClass('x-date-mp-sel');
				this.mpSelYear = pn.dom.xyear;
			}
			else if(el.is('a.x-date-mp-prev')){
				this.updateMPYear(this.mpyear-10);
			}
			else if(el.is('a.x-date-mp-next')){
				this.updateMPYear(this.mpyear+10);
			}
		},
		onMonthDblClick : function(e, t){
			e.stopEvent();
			var el = new Ext.Element(t), pn;
			if((pn = el.up('td.x-date-mp-month', 2)) || (pn = el.up('td.x-date-mp-year', 2))){
				var d = new Date(this.mpSelYear, this.mpSelMonth, (this.activeDate || this.value).getDate());
				if(d.getMonth() != this.mpSelMonth){
					d = new Date(this.mpSelYear, this.mpSelMonth, 1).getLastDateOfMonth();
				}
				this.update(d);
				//年月赋值
				e.stopEvent();
				this.setValue(new Date(d));
				delete this.cancelFocus;
				this.fireEvent('select', this, this.value);
			}
		}
    });
}
/*点击日历图标，直接显示年月选择项*/
if(Ext.form.DateField){
	Ext.apply(Ext.form.DateField.prototype, {
		onTriggerClick:function(){
			if(this.disabled){
				return;
			}
			if(this.menu == null){
				if(Ext.version == '3.0'){
					this.menu = new Ext.menu.DateMenu({
						hideOnClick: false
					});
				}
				if(Ext.version == '3.3.1'){
					this.menu = new Ext.menu.DateMenu({
						hideOnClick: false,
						focusOnSelect: false
					});
				}
			}
			this.onFocus();
			if(Ext.version == '3.0'){
				Ext.apply(this.menu.picker,  {
					minDate : this.minValue,
					maxDate : this.maxValue,
					disabledDatesRE : this.disabledDatesRE,
					disabledDatesText : this.disabledDatesText,
					disabledDays : this.disabledDays,
					disabledDaysText : this.disabledDaysText,
					format : this.format,
					showToday : this.showToday,
					minText : String.format(this.minText, this.formatDate(this.minValue)),
					maxText : String.format(this.maxText, this.formatDate(this.maxValue))
				});
			}
			if(Ext.version == '3.3.1'){
				Ext.apply(this.menu.picker,  {
					minDate : this.minValue,
					maxDate : this.maxValue,
					disabledDatesRE : this.disabledDatesRE,
					disabledDatesText : this.disabledDatesText,
					disabledDays : this.disabledDays,
					disabledDaysText : this.disabledDaysText,
					format : this.format,
					showToday : this.showToday,
					startDay: this.startDay,
					minText : String.format(this.minText, this.formatDate(this.minValue)),
					maxText : String.format(this.maxText, this.formatDate(this.maxValue))
				});
			}
			this.menu.picker.setValue(this.getValue() || new Date());
			this.menu.show(this.el, "tl-bl?");
			this.menuEvents('on');
			//触发显示年月选项
			this.menu.picker.showMonthPicker();
		}
	});
}
/*解决日历控件在IE9中的兼容问题*/
if(Ext.menu.Menu){
	Ext.menu.Menu.prototype.showAt = function(xy, parentMenu){
        if(this.fireEvent('beforeshow', this) !== false){
            this.parentMenu = parentMenu;
            if(!this.el){
                this.render();
            }
            if(this.enableScrolling){
                this.el.setXY(xy);
                xy[1] = this.constrainScroll(xy[1]);
                xy = [this.el.adjustForConstraints(xy)[0], xy[1]];
            }else{
                
                xy = this.el.adjustForConstraints(xy);
            }
            this.el.setXY(xy);
            this.el.show();
            Ext.menu.Menu.superclass.onShow.call(this);
            if(Ext.isIE){
                this.fireEvent('autosize', this);
                if(!(Ext.isIE8)){
                    this.el.repaint();
                }
				var ua = navigator.userAgent.toLowerCase();  
				var isOpera = ua.indexOf("opera")>-1;// 是Opera      
				Ext.isIE9 = !isOpera&&ua.indexOf("msie 9")>-1; //IE9 
				if(Ext.isIE9){
                    this.el.dom.style.width = '';
                }
            }
            this.hidden = false;
            this.focus();
            this.fireEvent('show', this);
        }
    }
}
/*解决IE9的Script438:对象不支持“createContextualFragment”属性或方法*/
if ((typeof Range !== "undefined") && !Range.prototype.createContextualFragment){
	Range.prototype.createContextualFragment = function(html)   
	{  
		var frag = document.createDocumentFragment(),     
		div = document.createElement("div");   
		frag.appendChild(div);   
		div.outerHTML = html;   
		return frag;   
	}
}  