package com.fhs.publics.controller;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.logger.Logger;
import com.fhs.module.base.controller.ModelSuperController;
import com.fhs.publics.dox.ServiceFileDO;
import com.fhs.publics.service.FileServerBusiness;
import com.fhs.publics.service.ServiceFileService;
import com.fhs.publics.vo.ServiceFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author qiuhang
 * @des 文件上传action
 *
 */
@Controller
@RequestMapping("upload")
public class UploadController extends ModelSuperController<ServiceFileVO, ServiceFileDO> {

    private static final Logger LOG = Logger.getLogger(UploadController.class);

    @Autowired
    private FileServerBusiness fileServerBusiness;

    @Autowired
    private ServiceFileService serviceFileService;

    /**
     * 文件
     * @param Filedata
     * @return
     */
    @RequestMapping(value = "file", method = RequestMethod.POST)
    public void uploadFile(@RequestParam MultipartFile[] Filedata, HttpServletRequest request,
                                            HttpServletResponse response) {
        if (Filedata == null || Filedata.length == 0) {
            super.outToClient(false);
        }
        LOG.infoMsg ( "开始上传文件,当前时间为{}", DateUtils.getCurrentDateStr ( DateUtils.DATETIME_PATTERN) );
        ServiceFileVO file = fileServerBusiness.uploadFileForList (Arrays.asList (Filedata)).get (0);
        LOG.infoMsg ( "结束上传文件,结束时间为{}", DateUtils.getCurrentDateStr ( DateUtils.DATETIME_PATTERN) );

        super.outWriteJson(JsonUtils.bean2json(file));

    }




}
