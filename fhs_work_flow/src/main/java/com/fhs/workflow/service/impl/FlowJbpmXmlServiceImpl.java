package com.fhs.workflow.service.impl;

import com.fhs.common.utils.Base64Util;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamChecker;
import com.fhs.workflow.bean.FlowJbpmXml;
import com.fhs.workflow.bean.WorkFlowJbpmXml;
import com.fhs.workflow.dao.FlowJbpmXmlDao;
import com.fhs.workflow.dao.WorkFlowJbpmXmlDAO;
import com.fhs.workflow.service.FlowJbpmXmlService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 流程列表-xml(FlowJbpmXml)表服务实现类
 *
 * @author sb生成的代码
 * @since 2019-11-11 14:29:04
 */
@Service("flowJbpmXmlService")
public class FlowJbpmXmlServiceImpl extends BaseServiceImpl<FlowJbpmXml>   implements FlowJbpmXmlService {

    private static final Logger LOG = Logger.getLogger(WorkFlowJbpmXmlServiceImpl.class);


    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void releaseWorkFlow(String xmlId) {
        try {
            FlowJbpmXml workFlowJbpmXml = super.selectById(xmlId);
            ParamChecker.isNotNullOrEmpty(workFlowJbpmXml,"id无效");
            String xmlFileName = StringUtil.getUUID() + ".bpmn";
            String pngName = StringUtil.getUUID() + ".png";
            FileUtils.write(new File(EConfig.getPathPropertiesValue("jbpmFilePath") + xmlFileName), workFlowJbpmXml.getXml(),"utf-8");
            FileUtils.writeByteArrayToFile(new File(EConfig.getPathPropertiesValue("jbpmFilePath") + pngName), Base64Util.getByteFromBase64(workFlowJbpmXml.getImg()));
            // 创建发布配置对象
            DeploymentBuilder builder = repositoryService.createDeployment();
           // pngName = catImg(EConfig.getPathPropertiesValue("jbpmFilePath") + xmlFileName,EConfig.getPathPropertiesValue("jbpmFilePath") + pngName);
            // 设置发布信息
            // 添加部署规则的显示别名
            // 添加规则文件
            // 添加规则图片  不添加会自动产生一个图片不推荐
            builder
                    .name(workFlowJbpmXml.getName())
                    .addClasspathResource("jbpm/" + xmlFileName);
                    //.addClasspathResource("jbpm/" + pngName);
            // 完成发布
            builder.deploy();
            super.updateSelectiveById(FlowJbpmXml.builder().id(xmlId).status(FlowJbpmXmlService.STATUS_HAS_DEPLOY).build());
        } catch (Exception e) {
            LOG.error("流程部署出错");
            LOG.error(this, e);
        }
    }

    /**
     * 截取需要的 部分
     * @param xmlPath xml路径
     * @param pngPath png图片路径
     * @return 最终的png图片路径
     * @throws IOException
     */
    public static String catImg(String xmlPath,String pngPath) throws IOException
    {
        String xml = FileUtils.readFileToString(new File(xmlPath));
        Pattern p = Pattern.compile("y=\"(\\d+)\"");
        Matcher m = p.matcher(xml);
        Integer maxY = 100;
        int temp = 0;
        while (m.find()) {
            temp = ConverterUtils.toInt(m.group(1));
            if(temp > maxY)
            {
                maxY = temp;
            }
        }
        maxY+=100;
        p = Pattern.compile("x=\"(\\d+)\"");
        m = p.matcher(xml);
        Integer maxX = 100;
        while (m.find()) {
            temp = ConverterUtils.toInt(m.group(1));
            if(temp > maxX)
            {
                maxX = temp;
            }
        }
        maxX+=100;
        String pngName =  StringUtil.getUUID() + ".png";
        String outputFile = EConfig.getPathPropertiesValue("jbpmFilePath") + pngName;
        OutputStream os = new FileOutputStream(new File(outputFile));
        cutPNG(new FileInputStream(new File(pngPath)), os,0,
                0, maxX, maxY);
        try
        {
            os.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            os.close();
        }
        return pngName;
    }

    public static void cutPNG(InputStream input, OutputStream out, int x,
                              int y, int width, int height) throws IOException {
        ImageInputStream imageStream = null;
        try {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
            ImageReader reader = readers.next();
            imageStream = ImageIO.createImageInputStream(input);
            reader.setInput(imageStream, true);
            ImageReadParam param = reader.getDefaultReadParam();


            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, "png", out);
        } finally {
            imageStream.close();
        }
    }


}