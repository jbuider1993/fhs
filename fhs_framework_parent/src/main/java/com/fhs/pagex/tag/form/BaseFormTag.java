package com.fhs.pagex.tag.form;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.pagex.tag.PagexBaseTag;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * form tag的基类
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.tag.form
 * @ClassName: BaseFormTag
 * @Author: JackWang
 * @CreateDate: 2018/12/3 0003 12:50
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/3 0003 12:50
 * @Version: 1.0
 */
public abstract class BaseFormTag extends PagexBaseTag implements InitializingBean {

    /**
     * 格式化dataType务必使用格式化后的给最终输出的html赋值
     */
    protected  String formartDataType(){
        String dataType = ConverterUtils.toString(super.tagSett.get("dataType"));
        // 如果为空，则看看是否允许为空
        if(CheckUtils.isNullOrEmpty(dataType))
        {
            dataType = ConverterUtils.toBoolean(tagSett.get("required")) ? "*" : "empty|*";
        }
        else if(!ConverterUtils.toBoolean(tagSett.get("required")))// 如果不为空，则看下 required 设置，如果required 为false则设置一个|empty代表可以非必填
        {
            dataType = dataType + "|empty";
        }
        String lengErrorTips = "";
        //如果最大最小都给了 那么拼接s${min}-${max}
        if(!CheckUtils.isNullOrEmpty(tagSett.get("max")) && !CheckUtils.isNullOrEmpty(tagSett.get("min")))
        {
            lengErrorTips = "或者长度错误：长度必须在" + tagSett.get("min")  + "和" + tagSett.get("max") + "之间";
            dataType = dataType + "&s" + tagSett.get("min") + "-" + tagSett.get("max");
        }
        // 如果只写最大的 判断是否能为空来判断最少 是0还是1
        else if(!CheckUtils.isNullOrEmpty(tagSett.get("max")) && CheckUtils.isNullOrEmpty(tagSett.get("min")))
        {
            String min = "0";
            // 如果必填那么最少要填写一个字符
            if(dataType.contains("*"))
            {
                min = "1";
            }
            lengErrorTips = "或者长度错误：长度必须在" + min  + "和" + tagSett.get("max") + "之间";
            dataType = dataType + "&s" + min + "-" + tagSett.get("max");
        }
        // 如果只给了min不给最大不管他。
        return  " dataType='" + dataType + "' nullmsg='" + super.tagSett.get("title") + "不能为空'  errormsg='"
                + super.tagSett.get("title") +"输入了错误的格式" + lengErrorTips + "' " ;
    }

    /**
     * 格式化id和name 的html
     */
    protected String formartIdNameHtml(){
        return " id='" + tagSett.get("name") + "' name='"+ tagSett.get("name") + "' ";
    }

    /**
     * 格式化自动提示
     */
    protected String formartPlaceholderHtml(){
        return " placeholder='请填写" + super.tagSett.get("title") + "' prompt='请选择" + super.tagSett.get("title") + "' ";
    }

    /**
     * 必填小红点html
     */
    protected String formartRequiredHtml(){
        return ConverterUtils.toBoolean(tagSett.get("required")) ?
                " <span class='form-field-required'>*</span> "  : "";
    }

    /**
     * 格式化end html
     */
    protected String formartEndHtml(){
        return "<div>";
    }

    /**
     * 直接看代码吧
     * @return
     */
    protected String getTitleHtml(){
        String rowClass =  isNewRow() ?  "bigLabelDiv" : "fitemDiv";
        if(!isNewRow())
        {
            return "<div class='fitemDiv'><label>" + super.tagSett.get("title") + ":</label>";
        }
        else
        {
            return "<div class='bigLabelDiv'><label>" + super.tagSett.get("title") + ":</label></div><div class='bigContent'>";
        }
    }





    /**
     * 此标签是否是新启一行 如果设置为true 系统会自动拼接一个</div>后拼接此标签的html
     * return true 是新启一行 false  可以和上一个标签在同一个行
     */
    public abstract  boolean isNewRow();

    /**
     * document ready的时候执行的js
     */
    public abstract  String readyJs();

    /**
     * 页面表单渲染成功时候的js
     *  接收参数请用 info
     *  比如你要写一段 $('#title').val(info.title);
     */
    public abstract  String loadSuccessJs();

    /**
     * 在保存的时候调用的js
     * 如果您需要在保存的时候判断一个字段是否为空可以这么写
     * if($('#titile').val()==''){ElaertE('标题不能为空');return false;}
     * @return
     */
    public abstract  String saveJs();

    /**
     * 全局js  这里写的js 不会被套到document ready 而是直接输出到<script>标签下面，所以千万不要写错了
     */
    public abstract  String overallJs();

     /*
        Beetl 支持 start
     */

    @Autowired
    private BeetlGroupUtilConfiguration beetlGroupUtilConfiguration;

    @Override
    public void render() {
        // 1先把参数给设置一下
        Map<String,Object> tagSett  = (Map<String,Object>) args[1];
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        setTagSett(tagSett,servletRequestAttributes.getRequest(),servletRequestAttributes.getResponse());
        //2 输出输出
        try {
            //输出全局的js
            this.ctx.byteWriter.writeString(getScript(overallJs()));
            this.ctx.byteWriter.writeString(getContentHtml());
            this.ctx.byteWriter.writeString(getScript(" $(function() {" + this.readyJs() +  "})"));
            this.ctx.byteWriter.writeString(getScript("loadSuccessFuns.push(function(info){  " + this.loadSuccessJs() +  "})"));
            this.ctx.byteWriter.writeString(getScript("onSaveFuns.push(function(info){  " + this.saveJs() +  "})"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把script 套上2个script 开始和结尾的字符串
     * @param  script script
     * @return script
     */
    protected  String getScript(String script)
    {
        return "<script>" + script + "</script>";
    }

    /**
     * 获取字段渲染的html
     *
     */
    public abstract String getContentHtml();

    @Override
    public void afterPropertiesSet() throws Exception {
        // 写给小白，this.getClass获取的是子类的class
        beetlGroupUtilConfiguration.getGroupTemplate().registerTag(this.getClass().getSimpleName(),this.getClass());
    }

    /**
     * 获取beetls模板使用的参数map
     * map种会包含tagSett
     * @return 参数map
     */
    public Map<String,Object> getBeetlParamMap(){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("tagSett",this.tagSett);
        return paramMap;
    }

    /*
      Beetl 支持 end
     */
}
