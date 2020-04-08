package com.fhs.flow.service.impl;

import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamException;
import com.fhs.core.valid.checker.ParamChecker;
import com.fhs.flow.dox.FlowJbpmXmlDO;
import com.fhs.flow.service.FlowJbpmXmlService;
import com.fhs.flow.vo.FlowJbpmXmlVO;
import com.fhs.logger.Logger;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 流程列表-xml(FlowJbpmXml)表服务实现类
 *
 * @author jackwong
 * @since 2019-11-11 14:29:04
 */
@Service("flowJbpmXmlService")
public class FlowJbpmXmlServiceImpl extends BaseServiceImpl<FlowJbpmXmlVO, FlowJbpmXmlDO> implements FlowJbpmXmlService {

    private static final Logger LOG = Logger.getLogger(WorkFlowJbpmXmlServiceImpl.class);


    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void releaseWorkFlow(String xmlId) {
        try {
            FlowJbpmXmlVO workFlowJbpmXml = super.selectById(xmlId);
            ParamChecker.isNotNullOrEmpty(workFlowJbpmXml, "id无效");
            String xmlFileName = StringUtil.getUUID() + ".bpmn";
            //FileUtils.writeByteArrayToFile(new File(EConfig.getPathPropertiesValue("jbpmFilePath") + pngName), Base64Util.getByteFromBase64(workFlowJbpmXml.getImg()));
            // 创建发布配置对象
            DeploymentBuilder builder = repositoryService.createDeployment();
            String preId = workFlowJbpmXml.getProcessKey() + workFlowJbpmXml.getVersion();
            //xml 版本号升级 默认版本是0 发布的时候版本号变成1
            String xml = null;
            if( workFlowJbpmXml.getXml().contains(preId)){
                xml = workFlowJbpmXml.getXml().replace(preId,
                        workFlowJbpmXml.getProcessKey() + (workFlowJbpmXml.getVersion() + 1));
            }
            else{
                xml = workFlowJbpmXml.getXml().replace(workFlowJbpmXml.getProcessKey(),
                        workFlowJbpmXml.getProcessKey() + (workFlowJbpmXml.getVersion() + 1));
            }
            //xml 版本号升级 默认版本是0 发布的时候版本号变成1

            FileUtils.write(new File(EConfig.getPathPropertiesValue("jbpmFilePath") + xmlFileName), xml, "utf-8");
            // pngName = catImg(EConfig.getPathPropertiesValue("jbpmFilePath") + xmlFileName,EConfig.getPathPropertiesValue("jbpmFilePath") + pngName);
            // 设置发布信息
            // 添加部署规则的显示别名
            // 添加规则文件
            // 添加规则图片  不添加会自动产生一个图片不推荐
            builder
                    .name(workFlowJbpmXml.getName())
                    .addInputStream(xmlFileName, new FileInputStream(new File(EConfig.getPathPropertiesValue("jbpmFilePath") + xmlFileName)));
            //.addClasspathResource("jbpm/" + pngName);
            // 完成发布
            builder.deploy();
            super.updateSelectiveById(FlowJbpmXmlDO.builder().id(xmlId).xml(xml).version(workFlowJbpmXml.getVersion() + 1).status(FlowJbpmXmlService.STATUS_HAS_DEPLOY).build());
        } catch (Exception e) {
            LOG.error("流程部署出错");
            LOG.error(this, e);
            throw new ParamException("部署流程出错");
        }
    }

    /**
     * 截取需要的 部分
     *
     * @param xmlPath xml路径
     * @param pngPath png图片路径
     * @return 最终的png图片路径
     * @throws IOException
     */
    public static String catImg(String xmlPath, String pngPath) throws IOException {
        String xml = FileUtils.readFileToString(new File(xmlPath));
        Pattern p = Pattern.compile("y=\"(\\d+)\"");
        Matcher m = p.matcher(xml);
        Integer maxY = 100;
        int temp = 0;
        while (m.find()) {
            temp = ConverterUtils.toInt(m.group(1));
            if (temp > maxY) {
                maxY = temp;
            }
        }
        maxY += 100;
        p = Pattern.compile("x=\"(\\d+)\"");
        m = p.matcher(xml);
        Integer maxX = 100;
        while (m.find()) {
            temp = ConverterUtils.toInt(m.group(1));
            if (temp > maxX) {
                maxX = temp;
            }
        }
        maxX += 100;
        String pngName = StringUtil.getUUID() + ".png";
        String outputFile = EConfig.getPathPropertiesValue("jbpmFilePath") + pngName;
        OutputStream os = new FileOutputStream(new File(outputFile));
        cutPNG(new FileInputStream(new File(pngPath)), os, 0,
                0, maxX, maxY);
        try {
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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