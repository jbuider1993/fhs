package com.fhs.workflow.service.impl;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.fhs.common.utils.*;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.config.EConfig;
import com.fhs.workflow.bean.WorkFlowJbpmXml;
import com.fhs.workflow.service.WorkFlowJbpmXmlService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 流程xml管理
 * 
 * @author  wanglei
 * @version  [版本号, 2017/07/25 11:04:23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("workFlowJbpmXmlServiceImpl")
public class WorkFlowJbpmXmlServiceImpl extends BaseServiceImpl<WorkFlowJbpmXml> implements WorkFlowJbpmXmlService
{
    private static final String jbpmFilePath = EConfig.getPathPropertiesValue("jbpmFilePath");
    
    private static final Logger LOG = Logger.getLogger(WorkFlowJbpmXmlServiceImpl.class);
   

	@Autowired
	private DBPubService<WorkFlowJbpmXml> dbpubService;

	@Autowired
	private RepositoryService repositoryService;
    @Override
    public void releaseWorkFlow(WorkFlowJbpmXml workFlowJbpmXml) {
        try {
            workFlowJbpmXml = dbpubService.get(workFlowJbpmXml);
            String xmlFileName = StringUtil.getUUID() + ".bpmn";
            String pngName = StringUtil.getUUID() + ".png";
            FileUtils.write(new File(jbpmFilePath + xmlFileName), workFlowJbpmXml.getProcessDescriptor(),"utf-8");
            FileUtils.writeByteArrayToFile(new File(jbpmFilePath + pngName),Base64Util.getByteFromBase64(workFlowJbpmXml.getXmlImgBase64()));
           // 创建发布配置对象
            DeploymentBuilder builder = repositoryService.createDeployment();
            pngName = catImg(jbpmFilePath + xmlFileName,jbpmFilePath + pngName);
            // 设置发布信息
            builder
            .name(workFlowJbpmXml.getProcessName())// 添加部署规则的显示别名
            .addClasspathResource("jbpm/" + xmlFileName)// 添加规则文件
            .addClasspathResource("jbpm/" + pngName);// 添加规则图片  不添加会自动产生一个图片不推荐
            // 完成发布
            builder.deploy();
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
        String outputFile = jbpmFilePath + pngName;
        OutputStream os = new  FileOutputStream(new File(outputFile));
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
              
            System.out.println(reader.getWidth(0));  
            System.out.println(reader.getHeight(0));  
              
            Rectangle rect = new Rectangle(x, y, width, height);  
            param.setSourceRegion(rect);  
            BufferedImage bi = reader.read(0, param);  
            ImageIO.write(bi, "png", out);  
        } finally {  
            imageStream.close();  
        }  
    }
    
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String getWorkFlowTreeJson(DbParam param) {
        //几乎就查询所有的了
        param.setSize(1000000);
        List<Map<String,Object>> resultList = new ArrayList<>();
        try {
            List<WorkFlowJbpmXml> workFlowList =  dbpubService.getListByEntity(WorkFlowJbpmXml.class, param);
            Map<Integer,Map<String,Object>> workFlowTypeMap = new HashMap<>();
            Integer workFlowTypeId = 0;
            Map<String,Object> workFlowType = null;
            Map<String,Object>  workFlowMap = null;
            List<Map<String,Object>> childrenList = null;
            for(WorkFlowJbpmXml tempWrokFlow : workFlowList)
            {
                workFlowTypeId = tempWrokFlow.getTypeId();
                if(workFlowTypeMap.containsKey(workFlowTypeId))
                {
                    workFlowType = workFlowTypeMap.get(workFlowTypeId);
                    workFlowMap = MapUtils.bean2Map(tempWrokFlow);
                    workFlowMap.put("name",workFlowMap.get("processName"));
                    ((List)workFlowType.get("children")).add(workFlowMap);
                }
                else
                {
                    workFlowType = new HashMap<>();
                    workFlowType.put("name", tempWrokFlow.getTransMap().get("typeIdName"));
                    childrenList = new ArrayList<>();
                    workFlowMap = MapUtils.bean2Map(tempWrokFlow);
                    workFlowMap.put("name",workFlowMap.get("processName"));
                    childrenList.add(workFlowMap);
                    workFlowType.put("children", childrenList);
                    workFlowTypeMap.put(workFlowTypeId, workFlowType);
                }
            }
            Set<Integer> keys = workFlowTypeMap.keySet();
            for(Integer typeId : keys)
            {
                resultList.add(workFlowTypeMap.get(typeId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.list2json(resultList);
    }
    
   
}