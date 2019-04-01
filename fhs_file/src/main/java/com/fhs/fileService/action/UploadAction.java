package com.fhs.fileService.action;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.exception.ParamException;
import com.fhs.fileService.bean.ServiceFile;
import com.fhs.fileService.business.FileServerBusiness;
import com.fhs.fileService.service.ServiceFileService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * @author qiuhang
 * @des 文件上传action
 *
 */
@Controller
@RequestMapping("upload")
public class UploadAction extends BaseAction<ServiceFile> {

    private static final Logger LOG = Logger.getLogger(DownLoadAction.class);

    /**
     * 哪些文件类型需要返回长度
     */
    private static Set<String>  MEDIA_FILE_SUFFIX = new HashSet<>();

    static
    {
        MEDIA_FILE_SUFFIX.add(".mp4");
        MEDIA_FILE_SUFFIX.add(".MP4");
        MEDIA_FILE_SUFFIX.add(".mp3");
        MEDIA_FILE_SUFFIX.add(".MP3");
        MEDIA_FILE_SUFFIX.add(".wav");
        MEDIA_FILE_SUFFIX.add(".WAV");
    }

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
        if(MEDIA_FILE_SUFFIX.contains(file.getFileSuffix()))
        {
            file.setTimeLength( readVideoTime(new File(serviceFileService.getAllPath(file))) );
        }
        super.outWriteJson(JsonUtils.bean2json(file), response);

    }

    /**
     * 获取媒体文件的时间长度
     * @param source 文件对象
     * @return 长度
     */
    private static long readVideoTime(File source) {
        Encoder encoder = new Encoder();
        String length = "";
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration()/1000;
            return ls;
        } catch (Exception e) {
            LOG.error(null,e);
            throw new ParamException("您上传了文件但是我们无法获取他的长度");
        }
    }


}
