package com.fhs.file.action;

import com.fhs.common.utils.*;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamException;
import com.fhs.file.bean.ServiceFile;
import com.fhs.file.service.ServiceFileService;
import com.fhs.file.utils.ThumbnailatorUtils;
import com.fhs.file.storage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiuhang
 * @des 文件下载action
 */
@Controller
@RequestMapping("downLoad")
public class DownLoadAction extends BaseAction<ServiceFile> {

    private static final Logger LOG = Logger.getLogger(DownLoadAction.class);

    @Autowired
    ServiceFileService serviceFileService;

    @Autowired
    private FileStorage fileStorage;



    /**
     * 根据文件id下载文件
     *
     * @return
     */
    @RequestMapping(value = "file", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileId = request.getParameter("fileId");
            // 文件下载路径
            ServiceFile serviceFile = serviceFileService.selectById(fileId);
            fileStorage.downloadFile(serviceFile,response);
        } catch (Exception e) {
            LOG.error(this, e);
            throw new ParamException("下载文件异常,可能是文件不存在");
        }
    }


    /**
     * 根据文件名称下载文件
     *
     * @return
     */
    @RequestMapping(value = "fileByName", method = RequestMethod.GET)
    public void downloadForName(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = request.getParameter("fileName");
            // 文件下载路径
            String fileId = fileName.substring(0, fileName.indexOf("."));
            ServiceFile serviceFile = serviceFileService.selectById(fileId);
            fileStorage.downloadFile(serviceFile,response);
        } catch (Exception e) {
            LOG.error(this, e);
            throw new RuntimeException("下载文件异常:" + e.getMessage());
        }
    }

    /**
     * 文件列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "listData", method = RequestMethod.GET)
    public void listData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("fileIds", StringUtil.getStrToIn(request.getParameter("fileIds")));
        List<ServiceFile> list = serviceFileService.findForListFromMap(param);
        String json = JsonUtils.list2json(list);
        this.outJsonp(json, response, request);
    }


    /**
     * 根据文件id获取文件相关信息
     *
     * @return
     */
    @RequestMapping(value = "getFileById", method = RequestMethod.GET)
    @ResponseBody
    public ServiceFile obtainFileNameById(HttpServletRequest request) {
        String fileId = request.getParameter("fileId");
        ServiceFile serviceFile = serviceFileService.selectById(fileId);
        return serviceFile;
    }

    /**
     * 根据文件id下载压缩文件
     *
     * @param request
     * @param response
     */
    @RequestMapping("downImgMin")
    public void downImgMin(HttpServletRequest request, HttpServletResponse response) {
        String fileId = ConverterUtils.toString(request.getParameter("fileId"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fileId", fileId);
        ServiceFile serviceFile = serviceFileService.findBeanFromMap(map);
        if (serviceFile == null) {
            return;
        }

        int maxWidth = CheckUtils.isNullOrEmpty(request.getParameter("imgFileWidth"))
                ? ConverterUtils.toInt(EConfig.getOtherConfigPropertiesValue("imgFileWidth"))
                : ConverterUtils.toInt(request.getParameter("imgFileWidth"));
        int maxHeight = CheckUtils.isNullOrEmpty(request.getParameter("imgFileHeight"))
                ? ConverterUtils.toInt(EConfig.getOtherConfigPropertiesValue("imgFileWidth"))
                : ConverterUtils.toInt(request.getParameter("imgFileHeight"));

        // 文件后缀
        String suffix = serviceFile.getFileSuffix();
        // 文件下载路径
        String downFilePath = EConfig.getPathPropertiesValue("downFilePath");
        // 文件上传时间
        String uploadDate = serviceFile.getUploadDate();
        // fileId
        fileId = serviceFile.getFileId();
        // 图片规格
        String fileIdWH = fileId + "_" + maxWidth + "_" + maxHeight + suffix;
        String filePath =  uploadDate + File.separator + suffix.replace(".", "") + File.separator;
        //兼容旧数据

        // 文件名
        String showFileName = serviceFile.getFileName();

        String minPath = downFilePath + filePath + fileIdWH;

        File file = new File(minPath);
        if (fileStorage.checkFileIsExist(minPath,serviceFile)) {
            fileStorage.downloadFileByToken(minPath,serviceFile,response);
        } else {
            byte[] fileByte;
            try (InputStream is = fileStorage.getFileInputStream(serviceFile)){
                fileByte = ThumbnailatorUtils
                        .zoom2Bytes(is, maxWidth, maxHeight);
                file.createNewFile();
                fileStorage.uploadFileByToken(fileByte,minPath,serviceFile);
                FileUtils.download(file, response, showFileName);
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error(this, e);
            }
        }
    }



    /**
     * 根据文件id下载文件
     *
     * @return
     */
    @RequestMapping(value = "fileFofFileId", method = RequestMethod.GET)
    public void downloadFofFileId(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileId = request.getParameter("fileId");
            // 文件下载路径
            if (fileId.indexOf(".") > 0) {
                fileId = fileId.substring(0, fileId.indexOf("."));
            }
            ServiceFile serviceFile = serviceFileService.selectById(fileId);
            fileStorage.downloadFile(serviceFile,response);
        } catch (Exception e) {
            throw new RuntimeException("下载文件异常:" + e.getMessage());
        }
    }


}
