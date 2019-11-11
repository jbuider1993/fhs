$.data(this,"combotree").tree;
tree.tree("loadData",data);
});
},reload:function(jq,url){
return jq.each(function(){
var opts=$.data(this,"combotree").options;
var tree=$.data(this,"combotree").tree;
if(url){
opts.url=url;
}
tree.tree({url:opts.url});
});
},setValues:function(jq,_76a){
return jq.each(function(){
_763(this,_76a);
});
},setValue:function(jq,_76b){
return jq.each(function(){
_763(this,[_76b]);
});
},clear:function(jq){
return jq.each(function(){
var tree=$.data(this,"combotree").tree;
tree.find("div.tree-node-selected").removeClass("tree-node-selected");
var cc=tree.tree("getChecked");
for(var i=0;i<cc.length;i++){
tree.tree("uncheck",cc[i].target);
}
$(this).combo("clear");
});
}};
$.fn.combotree.parseOptions=function(_76c){
return $.extend({},$.fn.combo.parseOptions(_76c),$.fn.tree.parseOptions(_76c));
};
$.fn.combotree.defaults=$.extend({},$.fn.combo.defaults,$.fn.tree.defaults,{editable:false});
})(jQuery);
(function($){
function _76d(_76e){
var opts=$.data(_76e,"combogrid").options;
var grid=$.data(_76e,"combogrid").grid;
$(_76e).addClass("combogrid-f");
$(_76e).combo(opts);
var _76f=$(_76e).combo("panel");
if(!grid){
grid=$("<table></table>").appendTo(_76f);
$.data(_76e,"combogrid").grid=grid;
}
grid.datagrid($.extend({},opts,{border:false,fit:true,singleSelect:(!opts.multiple),onLoadSuccess:function(data){
var _770=$.data(_76e,"combogrid").remainText;
var _771=$(_76e).combo("getValues");
_77d(_76e,_771,_770);
opts.onLoadSuccess.apply(_76e,arguments);
},onClickRow:_772,onSelect:function(_773,row){
_774();
opts.onSelect.call(this,_773,row);
},onUnselect:function(_775,row){
_774();
opts.onUnselect.call(this,_775,row);
},onSelectAll:function(rows){
_774();
opts.onSelectAll.call(this,rows);
},onUnselectAll:function(rows){
if(opts.multiple){
_774();
}
opts.onUnselectAll.call(this,rows);
}}));
function _772(_776,row){
$.data(_76e,"combogrid").remainText=false;
_774();
if(!opts.multiple){
$(_76e).combo("hidePanel");
}
opts.onClickRow.call(this,_776,row);
};
function _774(){
var _777=$.data(_76e,"combogrid").remainText;
var rows=grid.datagrid("getSelections");
var vv=[],ss=[];
for(var i=0;i<rows.length;i++){
vv.push(rows[i][opts.idField]);
ss.push(rows[i][opts.textField]);
}
if(!opts.multiple){
$(_76e).combo("setValues",(vv.length?vv:[""]));
}else{
$(_76e).combo("setValues",vv);
}
if(!_777){
$(_76e).combo("setText",ss.join(opts.separator));
}
};
};
function _778(_779,step){
var opts=$.data(_779,"combogrid").options;
var grid=$.data(_779,"combogrid").grid;
var _77a=grid.datagrid("getRows").length;
$.data(_779,"combogrid").remainText=false;
var _77b;
var _77c=grid.datagrid("getSelections");
if(_77c.length){
_77b=grid.datagrid("getRowIndex",_77c[_77c.length-1][opts.idField]);
_77b+=step;
if(_77b<0){
_77b=0;
}
if(_77b>=_77a){
_77b=_77a-1;
}
}else{
if(step>0){
_77b=0;
}else{
if(step<0){
_77b=_77a-1;
}else{
_77b=-1;
}
}
}
if(_77b>=0){
grid.datagrid("clearSelections");
grid.datagrid("selectRow",_77b);
}
};
function _77d(_77e,_77f,_780){
var opts=$.data(_77e,"combogrid").options;
var grid=$.data(_77e,"combogrid").grid;
var rows=grid.datagrid("getRows");
var ss=[];
for(var i=0;i<_77f.length;i++){
var _781=grid.datagrid("getRowIndex",_77f[i]);
if(_781>=0){
grid.datagrid("selectRow",_781);
ss.push(rows[_781][opts.textField]);
}else{
ss.push(_77f[i]);
}
}
if($(_77e).combo("getValues").join(",")==_77f.join(",")){
return;
}
$(_77e).combo("setValues",_77f);
if(!_780){
$(_77e).combo("setText",ss.join(opts.separator));
}
};
function _782(_783,q){
var opts=$.data(_783,"combogrid").options;
var grid=$.data(_783,"combogrid").grid;
$.data(_783,"combogrid").remainText=true;
if(opts.multiple&&!q){
_77d(_783,[],true);
}else{
_77d(_783,[q],true);
}
if(opts.mode=="remote"){
grid.datagrid("clearSelections");
grid.datagrid("load",$.extend({},opts.queryParams,{q:q}));
}else{
if(!q){
return;
}
var rows=grid.datagrid("getRows");
for(var i=0;i<rows.length;i++){
if(opts.filter.call(_783,q,rows[i])){
grid.datagrid("clearSelections");
grid.datagrid("selectRow",i);
return;
}
}
}
};
$.fn.combogrid=function(_784,_785){
if(typeof _784=="string"){
var _786=$.fn.combogrid.methods[_784];
if(_786){
return _786(this,_785);
}else{
return $.fn.combo.methods[_784](this,_785);
}
}
_784=_784||{};
return this.each(function(){
var _787=$.data(this,"combogrid");
if(_787){
$.extend(_787.options,_784);
}else{
_787=$.data(this,"combogrid",{options:$.extend({},$.fn.combogrid.defaults,$.fn.combogrid.parseOptions(this),_784)});
}
_76d(this);
});
};
$.fn.combogrid.methods={options:function(jq){
return $.data(jq[0],"combogrid").options;
},grid:function(jq){
return $.data(jq[0],"combogrid").grid;
},setValues:function(jq,_788){
return jq.each(function(){
_77d(this,_788);
});
},setValue:function(jq,_789){
return jq.each(function(){
_77d(this,[_789]);
});
},clear:function(jq){
return jq.each(function(){
$(this).combogrid("grid").datagrid("clearSelections");
$(this).combo("clear");
});
}};
$.fn.combogrid.parseOptions=function(_78a){
var t=$(_78a);
return $.extend({},$.fn.combo.parseOptions(_78a),$.fn.datagrid.parseOptions(_78a),$.parser.parseOptions(_78a,["idField","textField","mode"]));
};
$.fn.combogrid.defaults=$.extend({},$.fn.combo.defaults,$.fn.datagrid.defaults,{loadMsg:null,idField:null,textField:null,mode:"local",keyHandler:{up:function(){
_778(this,-1);
},down:function(){
_778(this,1);
},enter:function(){
_778(this,0);
$(this).combo("hidePanel");
},query:function(q){
_782(this,q);
}},filter:function(q,row){
var opts=$(this).combogrid("options");
return row[opts.textField].indexOf(q)==0;
}});
})(jQuery);
(function($){
function _78b(_78c){
var _78d=$.data(_78c,"datebox");
var opts=_78d.options;
$(_78c).addClass("datebox-f");
$(_78c).combo($.extend({},opts,{onShowPanel:function(){
_78d.calendar.calendar("resize");
opts.onShowPanel.call(_78c);
}}));
$(_78c).combo("textbox").parent().addClass("datebox");
if(!_78d.calendar){
_78e();
}
function _78e(){
var _78f=$(_78c).combo("panel");
_78d.calendar=$("<div></div>").appendTo(_78f).wrap("<div class=\"datebox-calendar-inner\"></div>");
_78d.calendar.calendar({fit:true,border:false,onSelect:function(date){
var _790=opts.formatter(date);
_794(_78c,_790);
$(_78c).combo("hidePanel");
opts.onSelect.call(_78c,date);
}});
_794(_78c,opts.value);
var _791=$("<div class=\"datebox-button\"></div>").appendTo(_78f);
$("<a href=\"javascript:void(0)\" class=\"datebox-current\"></a>").html(opts.currentText).appendTo(_791);
$("<a href=\"javascript:void(0)\" class=\"datebox-close\"></a>").html(opts.closeText).appendTo(_791);
_791.find(".datebox-current,.datebox-close").hover(function(){
$(this).addClass("datebox-button-hover");
},function(){
$(this).removeClass("datebox-button-hover");
});
_791.find(".datebox-current").click(function(){
_78d.calendar.calendar({year:new Date().getFullYear(),month:new Date().getMonth()+1,current:new Date()});
});
_791.find(".datebox-close").click(function(){
$(_78c).combo("hidePanel");
});
};
};
function _792(_793,q){
_794(_793,q);
};
function _795(_796){
var opts=$.data(_796,"datebox").options;
var c=$.data(_796,"datebox").calendar;
var _797=opts.formatter(c.calendar("options").current);
_794(_796,_797);
$(_796).combo("hidePanel");
};
function _794(_798,_799){
var _79a=$.data(_798,"datebox");
var opts=_79a.options;
$(_798).combo("setValue",_799).combo("setText",_799);
_79a.calendar.calendar("moveTo",opts.parser(_799));
};
$.fn.datebox=function(_79b,_79c){
if(typeof _79b=="string"){
var _79d=$.fn.datebox.methods[_79b];
if(_79d){
return _79d(this,_79c);
}else{
return this.combo(_79b,_79c);
}
}
_79b=_79b||{};
return this.each(function(){
var _79e=$.data(this,"datebox");
if(_79e){
$.extend(_79e.options,_79b);
}else{
$.data(this,"datebox",{options:$.extend({},$.fn.datebox.defaults,$.fn.datebox.parseOptions(this),_79b)});
}
_78b(this);
});
};
$.fn.datebox.methods={options:function(jq){
return $.data(jq[0],"datebox").options;
},calendar:function(jq){
return $.data(jq[0],"datebox").calendar;
},setValue:function(jq,_79f){
return jq.each(function(){
_794(this,_79f);
});
}};
$.fn.datebox.parseOptions=function(_7a0){
var t=$(_7a0);
return $.extend({},$.fn.combo.parseOptions(_7a0),{});
};
$.fn.datebox.defaults=$.extend({},$.fn.combo.defaults,{panelWidth:180,panelHeight:"auto",keyHandler:{up:function(){
},down:function(){
},enter:function(){
_795(this);
},query:function(q){
_792(this,q);
}},currentText:"Today",closeText:"Close",okText:"Ok",formatter:function(date){
var y=date.getFullYear();
var m=date.getMonth()+1;
var d=date.getDate();
return m+"/"+d+"/"+y;
},parser:function(s){
var t=Date.parse(s);
if(!isNaN(t)){
return new Date(t);
}else{
return new Date();
}
},onSelect:function(date){
}});
})(jQuery);
(function($){
function _7a1(_7a2){
var _7a3=$.data(_7a2,"datetimebox");
var opts=_7a3.options;
$(_7a2).datebox($.extend({},opts,{onShowPanel:function(){
var _7a4=$(_7a2).datetimebox("getValue");
_7a7(_7a2,_7a4,true);
opts.onShowPanel.call(_7a2);
},formatter:$.fn.datebox.defaults.formatter,parser:$.fn.datebox.defaults.parser}));
$(_7a2).removeClass("datebox-f").addClass("datetimebox-f");
$(_7a2).datebox("calendar").calendar({onSelect:function(date){
opts.onSelect.call(_7a2,date);
}});
var _7a5=$(_7a2).datebox("panel");
if(!_7a3.spinner){
var p=$("<div style=\"padding:2px\"><input style=\"width:80px\"></div>").insertAfter(_7a5.children("div.datebox-calendar-inner"));
_7a3.spinner=p.children("input");
var _7a6=_7a5.children("div.datebox-button");
var ok=$("<a href=\"javascript:void(0)\" class=\"datebox-ok\"></a>").html(opts.okText).appendTo(_7a6);
ok.hover(function(){
$(this).addClass("datebox-button-hover");
},function(){
$(this).removeClass("datebox-button-hover");
}).click(function(){
_7ac(_7a2);
});
}
_7a3.spinner.timespinner({showSeconds:opts.showSeconds,separator:opts.timeSeparator}).unbind(".datetimebox").bind("mousedown.datetimebox",function(e){
e.stopPropagation();
});
_7a7(_7a2,opts.value);
};
function _7a8(_7a9){
var c=$(_7a9).datetimebox("calendar");
var t=$(_7a9).datetimebox("spinner");
var date=c.calendar("options").current;
return new Date(date.getFullYear(),date.getMonth(),date.getDate(),t.timespinner("getHours"),t.timespinner("getMinutes"),t.timespinner("getSeconds"));
};
function _7aa(_7ab,q){
_7a7(_7ab,q,true);
};
function _7ac(_7ad){
var opts=$.data(_7ad,"datetimebox").options;
var date=_7a8(_7ad);
_7a7(_7ad,opts.formatter.call(_7ad,date));
$(_7ad).combo("hidePanel");
};
function _7a7(_7ae,_7af,_7b0){
var opts=$.data(_7ae,"datetimebox").options;
$(_7ae).combo("setValue",_7af);
if(!_7b0){
if(_7af){
var date=opts.parser.call(_7ae,_7af);
$(_7ae).combo("setValue",opts.formatter.call(_7ae,date));
$(_7ae).combo("setText",opts.formatter.call(_7ae,date));
}else{
$(_7ae).combo("setText",_7af);
}
}
var date=opts.parser.call(_7ae,_7af);
$(_7ae).datetimebox("calendar").calendar("moveTo",date);
$(_7ae).datetimebox("spinner").timespinner("setValue",_7b1(date));
function _7b1(date){
function _7b2(_7b3){
return (_7b3<10?"0":"")+_7b3;
};
var tt=[_7b2(date.getHours()),_7b2(date.getMinutes())];
if(opts.showSeconds){
tt.push(_7b2(date.getSeconds()));
}
return tt.join($(_7ae).datetimebox("spinner").timespinner("options").separator);
};
};
$.fn.datetimebox=function(_7b4,_7b5){
if(typeof _7b4=="string"){
var _7b6=$.fn.datetimebox.methods[_7b4];
if(_7b6){
return _7b6(this,_7b5);
}else{
return this.datebox(_7b4,_7b5);
}
}
_7b4=_7b4||{};
return this.each(function(){
var _7b7=$.data(this,"datetimebox");
if(_7b7){
$.extend(_7b7.options,_7b4);
}else{
$.data(this,"datetimebox",{options:$.extend({},$.fn.datetimebox.defaults,$.fn.datetimebox.parseOptions(this),_7b4)});
}
_7a1(this);
});
};
$.fn.datetimebox.methods={options:function(jq){
return $.data(jq[0],"datetimebox").options;
},spinner:function(jq){
return $.data(jq[0],"datetimebox").spinner;
},setValue:function(jq,_7b8){
return jq.each(function(){
_7a7(this,_7b8);
});
}};
$.fn.datetimebox.parseOptions=function(_7b9){
var t=$(_7b9);
return $.extend({},$.fn.datebox.parseOptions(_7b9),$.parser.parseOptions(_7b9,["timeSeparator",{showSeconds:"boolean"}]));
};
$.fn.datetimebox.defaults=$.extend({},$.fn.datebox.defaults,{showSeconds:true,timeSeparator:":",keyHandler:{up:function(){
},down:function(){
},enter:function(){
_7ac(this);
},query:function(q){
_7aa(this,q);
}},formatter:function(date){
var h=date.getHours();
var M=date.getMinutes();
var s=date.getSeconds();
function _7ba(_7bb){
return (_7bb<10?"0":"")+_7bb;
};
var _7bc=$(this).datetimebox("spinner").timespinner("options").separator;
var r=$.fn.datebox.defaults.formatter(date)+" "+_7ba(h)+_7bc+_7ba(M);
if($(this).datetimebox("options").showSeconds){
r+=_7bc+_7ba(s);
}
return r;
},parser:function(s){
if($.trim(s)==""){
return new Date();
}
var dt=s.split(" ");
var d=$.fn.datebox.defaults.parser(dt[0]);
if(dt.length<2){
return d;
}
var _7bd=$(this).datetimebox("spinner").timespinner("options").separator;
var tt=dt[1].split(_7bd);
var hour=parseInt(tt[0],10)||0;
var _7be=parseInt(tt[1],10)||0;
var _7bf=parseInt(tt[2],10)||0;
return new Date(d.getFullYear(),d.getMonth(),d.getDate(),hour,_7be,_7bf);
}});
})(jQuery);
(function($){
function init(_7c0){
var _7c1=$("<div class=\"slider\">"+"<div class=\"slider-inner\">"+"<a href=\"javascript:void(0)\" class=\"slider-handle\"></a>"+"<span class=\"slider-tip\"></span>"+"</div>"+"<div class=\"slider-rule\"></div>"+"<div class=\"slider-rulelabel\"></div>"+"<div style=\"clear:both\"></div>"+"<input type=\"hidden\" class=\"slider-value\">"+"</div>").insertAfter(_7c0);
var name=$(_7c0).hide().attr("name");
if(name){
_7c1.find("input.slider-value").attr("name",name);
$(_7c0).removeAttr("name").attr("sliderName",name);
}
return _7c1;
};
function _7c2(_7c3,_7c4){
var opts=$.data(_7c3,"slider").options;
var _7c5=$.data(_7c3,"slider").slider;
if(_7c4){
if(_7c4.width){
opts.width=_7c4.width;
}
if(_7c4.height){
opts.height=_7c4.height;
}
}
if(opts.mode=="h"){
_7c5.css("height","");
_7c5.children("div").css("height","");
if(!isNaN(opts.width)){
_7c5.width(opts.width);
}
}else{
_7c5.css("width","");
_7c5.children("div").css("width","");
if(!isNaN(opts.height)){
_7c5.height(opts.height);
_7c5.find("div.slider-rule").height(opts.height);
_7c5.find("div.slider-rulelabel").height(opts.height);
_7c5.find("div.slider-inner")._outerHeight(opts.height);
}
}
_7c6(_7c3);
};
function _7c7(_7c8){
var opts=$.data(_7c8,"slider").options;
var _7c9=$.data(_7c8,"slider").slider;
if(opts.mode=="h"){
_7ca(opts.rule);
}else{
_7ca(opts.rule.slice(0).reverse());
}
function _7ca(aa){
var rule=_7c9.find("div.slider-rule");
var _7cb=_7c9.find("div.slider-rulelabel");
rule.empty();
_7cb.empty();
for(var i=0;i<aa.length;i++){
var _7cc=i*100/(aa.length-1)+"%";
var span=$("<span></span>").appendTo(rule);
span.css((opts.mode=="h"?"left":"top"),_7cc);
if(aa[i]!="|"){
span=$("<span></span>").appendTo(_7cb);
span.html(aa[i]);
if(opts.mode=="h"){
span.css({left:_7cc,marginLeft:-Math.round(span.outerWidth()/2)});
}else{
span.css({top:_7cc,marginTop:-Math.round(span.outerHeight()/2)});
}
}
}
};
};
function _7cd(_7ce){
var opts=$.data(_7ce,"slider").options;
var _7cf=$.data(_7ce,"slider").slider;
_7cf.removeClass("slider-h slider-v slider-disabled");
_7cf.addClass(opts.mode=="h"?"slider-h":"slider-v");
_7cf.addClass(opts.disabled?"slider-disabled":"");
_7cf.find("a.slider-handle").draggable({axis:opts.mode,cursor:"pointer",disabled:opts.disabled,onDrag:function(e){
var left=e.data.left;
var _7d0=_7cf.width();
if(opts.mode!="h"){
left=e.data.top;
_7d0=_7cf.height();
}
if(left<0||left>_7d0){
return false;
}else{
var _7d1=_7e0(_7ce,left);
_7d2(_7d1);
return false;
}
},onStartDrag:function(){
opts.onSlideStart.call(_7ce,opts.value);
},onStopDrag:function(e){
var _7d3=_7e0(_7ce,(opts.mode=="h"?e.data.left:e.data.top));
_7d2(_7d3);
opts.onSlideEnd.call(_7ce,opts.value);
}});
function _7d2(_7d4){
var s=Math.abs(_7d4%opts.step);
if(s<opts.step/2){
_7d4-=s;
}else{
_7d4=_7d4-s+opts.step;
}
_7d5(_7ce,_7d4);
};
};
function _7d5(_7d6,_7d7){
var opts=$.data(_7d6,"slider").options;
var _7d8=$.data(_7d6,"slider").slider;
var _7d9=opts.value;
if(_7d7<opts.min){
_7d7=opts.min;
}
if(_7d7>opts.max){
_7d7=opts.max;
}
opts.value=_7d7;
$(_7d6).val(_7d7);
_7d8.find("input.slider-value").val(_7d7);
var pos=_7da(_7d6,_7d7);
var tip=_7d8.find(".slider-tip");
if(opts.showTip){
tip.show();
tip.html(opts.tipFormatter.call(_7d6,opts.value));
}else{
tip.hide();
}
if(opts.mode=="h"){
var _7db="left:"+pos+"px;";
_7d8.find(".slider-handle").attr("style",_7db);
tip.attr("style",_7db+"margin-left:"+(-Math.round(tip.outerWidth()/2))+"px");
}else{
var _7db="top:"+pos+"px;";
_7d8.find(".slider-handle").attr("style",_7db);
tip.attr("style",_7db+"margin-left:"+(-Math.round(tip.outerWidth()))+"px");
}
if(_7d9!=_7d7){
opts.onChange.call(_7d6,_7d7,_7d9);
}
};
function _7c6(_7dc){
var opts=$.data(_7dc,"slider").options;
var fn=opts.onChange;
opts.onChange=function(){
};
_7d5(_7dc,opts.value);
opts.onChange=fn;
};
function _7da(_7dd,_7de){
var opts=$.data(_7dd,"slider").options;
var _7df=$.data(_7dd,"slider").slider;
if(opts.mode=="h"){
var pos=(_7de-opts.min)/(opts.max-opts.min)*_7df.width();
}else{
var pos=_7df.height()-(_7de-opts.min)/(opts.max-opts.min)*_7df.height();
}
return pos.toFixed(0);
};
function _7e0(_7e1,pos){
var opts=$.data(_7e1,"slider").options;
var _7e2=$.data(_7e1,"slider").slider;
if(opts.mode=="h"){
var _7e3=opts.min+(opts.max-opts.min)*(pos/_7e2.width());
}else{
var _7e3=opts.min+(opts.max-opts.min)*((_7e2.height()-pos)/_7e2.height());
}
return _7e3.toFixed(0);
};
$.fn.slider=function(_7e4,_7e5){
if(typeof _7e4=="string"){
return $.fn.slider.methods[_7e4](this,_7e5);
}
_7e4=_7e4||{};
return this.each(function(){
var _7e6=$.data(this,"slider");
if(_7e6){
$.extend(_7e6.options,_7e4);
}else{
_7e6=$.data(this,"slider",{options:$.extend({},$.fn.slider.defaults,$.fn.slider.parseOptions(this),_7e4),slider:init(this)});
$(this).removeAttr("disabled");
}
_7cd(this);
_7c7(this);
_7c2(this);
});
};
$.fn.slider.methods={options:function(jq){
return $.data(jq[0],"slider").options;
},destroy:function(jq){
return jq.each(function(){
$.data(this,"slider").slider.remove();
$(this).remove();
});
},resize:function(jq,_7e7){
return jq.each(function(){
_7c2(this,_7e7);
});
},getValue:function(jq){
return jq.slider("options").value;
},setValue:function(jq,_7e8){
return jq.each(function(){
_7d5(this,_7e8);
});
},enable:function(jq){
return jq.each(function(){
$.data(this,"slider").options.disabled=false;
_7cd(this);
});
},disable:function(jq){
return jq.each(function(){
$.data(this,"slider").options.disabled=true;
_7cd(this);
});
}};
$.fn.slider.parseOptions=function(_7e9){
var t=$(_7e9);
return $.extend({},$.parser.parseOptions(_7e9,["width","height","mode",{showTip:"boolean",min:"number",max:"number",step:"number"}]),{value:(t.val()||undefined),disabled:(t.attr("disabled")?true:undefined),rule:(t.attr("rule")?eval(t.attr("rule")):undefined)});
};
$.fn.slider.defaults={width:"auto",height:"auto",mode:"h",showTip:false,disabled:false,value:0,min:0,max:100,step:1,rule:[],tipFormatter:function(_7ea){
return _7ea;
},onChange:function(_7eb,_7ec){
},onSlideStart:function(_7ed){
},onSlideEnd:function(_7ee){
}};
})(jQuery);

