package com.fhs.fileService.action;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.base.action.BaseAction;
import com.fhs.fileService.bean.ServiceFile;
import com.fhs.fileService.business.FileServerBusiness;
import com.fhs.fileService.service.ServiceFileService;
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
public class UploadAction extends BaseAction<ServiceFile> {

    private static final Logger LOG = Logger.getLogger(DownLoadAction.class);

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
            super.outToClient(false, response);
        }
        LOG.infoMsg ( "开始上传文件,当前时间为{}", DateUtils.getCurrentDateStr ( DateUtils.DATETIME_PATTERN) );
        ServiceFile file = fileServerBusiness.uploadFileForList (Arrays.asList (Filedata)).get (0);
        LOG.infoMsg ( "结束上传文件,结束时间为{}", DateUtils.getCurrentDateStr ( DateUtils.DATETIME_PATTERN) );

        super.outWriteJson(JsonUtils.bean2json(file), response);

    }




}
