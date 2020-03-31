package com.fhs.pub.service.impl;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.logger.Logger;
import com.fhs.pub.dox.PubFileDO;
import com.fhs.pub.service.FileServerBusiness;
import com.fhs.pub.service.FileStorage;
import com.fhs.pub.service.PubFileService;
import com.fhs.pub.vo.PubFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class FileServerBusinessImpl implements FileServerBusiness {

    private static final Logger LOG = Logger.getLogger(FileServerBusinessImpl.class);

    @Autowired
    private PubFileService fileService;

    @Autowired
    private FileStorage fileStorage;

    @Override
    public PubFileVO uploadFile(MultipartFile fileData) {
        PubFileVO sf = new PubFileVO();
        String fileName = fileData.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String fileId = StringUtil.getUUID();
        String currentDate = DateUtils.getCurrentDateStr("yyyy-MM-dd");
        sf.setFileId(fileId);
        sf.setFileName(fileName);
        sf.setFileSuffix(suffix);
        sf.setUploadDate(currentDate);
        fileStorage.uploadFile(sf, fileData);
        this.insertDataToDB(sf);
        return sf;
    }

    @Override
    public List<PubFileVO> uploadFileForList(List<MultipartFile> allFileData) {
        List<PubFileVO> rvList = new ArrayList<PubFileVO>();

        for (MultipartFile fileData : allFileData) {
            rvList.add(this.uploadFile(fileData));
        }

        return rvList;
    }

    /**
     * @param sf
     * @return
     */
    private boolean insertDataToDB(PubFileDO sf) {
        return (fileService.insert(sf) > 0);
    }
}
