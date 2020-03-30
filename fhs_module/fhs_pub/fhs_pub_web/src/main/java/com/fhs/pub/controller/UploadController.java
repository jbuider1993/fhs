package com.fhs.pub.controller;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.logger.Logger;
import com.fhs.module.base.controller.ModelSuperController;
import com.fhs.pub.dox.PubFileDO;
import com.fhs.pub.service.FileServerBusiness;
import com.fhs.pub.service.PubFileService;
import com.fhs.pub.vo.PubFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author qiuhang
 * @des 文件上传action
 *
 */
@RestController
@RequestMapping("upload")
public class UploadController extends ModelSuperController<PubFileVO, PubFileDO> {

    private static final Logger LOG = Logger.getLogger(DownLoadController.class);

    @Autowired
    private FileServerBusiness fileServerBusiness;

    @Autowired
    private PubFileService fileService;

    /**
     * 文件
     * @param Filedata
     * @return
     */
    @RequestMapping(value = "file", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(@RequestParam MultipartFile[] Filedata, HttpServletRequest request,
                           HttpServletResponse response) {
        if (Filedata == null || Filedata.length == 0) {
            super.outToClient(false);
        }
        LOG.infoMsg ( "开始上传文件,当前时间为{}", DateUtils.getCurrentDateStr ( DateUtils.DATETIME_PATTERN) );
        PubFileVO file = fileServerBusiness.uploadFileForList (Arrays.asList (Filedata)).get (0);
        LOG.infoMsg ( "结束上传文件,结束时间为{}", DateUtils.getCurrentDateStr ( DateUtils.DATETIME_PATTERN) );

        super.outWriteJson(JsonUtils.bean2json(file));

    }




}
