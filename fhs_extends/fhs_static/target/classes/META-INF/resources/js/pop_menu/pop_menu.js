"use strict";!function(n,t){"undefined"!=typeof module&&module.exports?module.exports=t():"function"==typeof define&&define.amd?define(t):window[n]=t()}("basicContext",function(){var n=null,t="item",e="separator",i=function(){var n=arguments.length<=0||void 0===arguments[0]?"":arguments[0];return document.querySelector(".basicContext "+n)},l=function(){var n=arguments.length<=0||void 0===arguments[0]?{}:arguments[0],i=0===Object.keys(n).length?!0:!1;return i===!0&&(n.type=e),null==n.type&&(n.type=t),null==n["class"]&&(n["class"]=""),n.visible!==!1&&(n.visible=!0),null==n.icon&&(n.icon=null),null==n.title&&(n.title="Undefined"),n.disabled!==!0&&(n.disabled=!1),n.disabled===!0&&(n["class"]+=" basicContext__item--disabled"),null==n.fn&&n.type!==e&&n.disabled===!1?(console.warn("Missing fn for item '"+n.title+"'"),!1):!0},o=function(n,i){var o="",r="";return l(n)===!1?"":n.visible===!1?"":(n.num=i,null!==n.icon&&(r="<span class='basicContext__icon "+n.icon+"'></span>"),n.type===t?o="\n		       <tr class='basicContext__item "+n["class"]+"'>\n		           <td onclick='handleCheckBox(this);' clickFun='"+n.clickFun+"' class='basicContext__data' data-num='"+n.num+"'>"+"<input type='checkbox' class='cloumnCheckbox' value='"+n.val+"'>"+r+n.title+"</td>\n		       </tr>\n		       ":n.type===e&&(o="\n		       <tr class='basicContext__item basicContext__item--separator'></tr>\n		       "),o)},r=function(n){var t="";return t+="\n	        <div class='basicContextContainer'>\n	            <div class='basicContext'>\n	                <table>\n	                    <tbody>\n	        ",n.forEach(function(n,e){return t+=o(n,e)}),t+="<tr class='basicContext__item '>	<td  class='basicContext__data' onclick='closePlugin()' ><center>关闭</center></td> <tr class='basicContext__item basicContext__item--separator'></tr>";t+="\n	                    </tbody>\n	                </table>\n	            </div>\n	        </div>\n	        "},a=function(){var n=arguments.length<=0||void 0===arguments[0]?{}:arguments[0],t={x:n.clientX,y:n.clientY};if("touchend"===n.type&&(null==t.x||null==t.y)){var e=n.changedTouches;null!=e&&e.length>0&&(t.x=e[0].clientX,t.y=e[0].clientY)}return(null==t.x||t.x<0)&&(t.x=0),(null==t.y||t.y<0)&&(t.y=0),t},s=function(n,t){var e=a(n),i=e.x,l=e.y,o={width:window.innerWidth,height:window.innerHeight},r={width:t.offsetWidth,height:t.offsetHeight};i+r.width>o.width&&(i-=i+r.width-o.width),l+r.height>o.height&&(l-=l+r.height-o.height),r.height>o.height&&(l=0,t.classList.add("basicContext--scrollable"));var s=e.x-i,u=e.y-l;return{x:i,y:l,rx:s,ry:u}},u=function(){var n=arguments.length<=0||void 0===arguments[0]?{}:arguments[0];n.fn=function(){};return n.visible===!1},c=function(t,e,l,o){var a=r(t);document.body.insertAdjacentHTML("beforeend",a),null==n&&(n=document.body.style.overflow,document.body.style.overflow="hidden");var c=i(),d=s(e,c);return c.style.left=d.x+"px",c.style.top=d.y+"px",c.style.transformOrigin=d.rx+"px "+d.ry+"px",c.style.opacity=1,null==l&&(l=f),c.parentElement.oncontextmenu=l,t.forEach(u),"function"==typeof e.preventDefault&&e.preventDefault(),"function"==typeof e.stopPropagation&&e.stopPropagation(),"function"==typeof o&&o(),!0},d=function(){var n=i();return null==n||0===n.length?!1:!0},f=function(){if(d()===!1){return !1}var t=document.querySelector(".basicContextContainer");return t.parentElement.removeChild(t),null!=n&&(document.body.style.overflow=n,n=null),!0};return{ITEM:t,SEPARATOR:e,show:c,visible:d,close:f}});function handleCheckBox(_obj){var _temp=$(_obj).find(".cloumnCheckbox");_temp.prop("checked",(!_temp.prop("checked")));if($(_obj).attr("clickFun")!="undefined"){eval($(_obj).attr("clickFun")+"('"+_temp.val()+"',"+_temp.prop("checked")+")")}}function closePlugin(){var t=document.querySelector(".basicContextContainer");return t.parentElement.removeChild(t)};