package com.fhs.publics.service.impl;

import com.fhs.common.utils.DateUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.logger.Logger;
import com.fhs.publics.dox.ServiceFileDO;
import com.fhs.publics.service.FileServerBusiness;
import com.fhs.publics.service.FileStorage;
import com.fhs.publics.service.ServiceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileServerBusinessImpl implements FileServerBusiness {

    private static final Logger LOG = Logger.getLogger(FileServerBusinessImpl.class);

    @Autowired
    private ServiceFileService fileService;

    @Autowired
    private FileStorage fileStorage;

    @Override
    public ServiceFileDO uploadFile(MultipartFile fileData) {
        ServiceFileDO sf = new ServiceFileDO();
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
    public List<ServiceFileDO> uploadFileForList(List<MultipartFile> allFileData) {
        List<ServiceFileDO> rvList = new ArrayList<ServiceFileDO>();

        allFileData.forEach(fileData -> {
            rvList.add(this.uploadFile(fileData));
        });

        return rvList;
    }

    /**
     * @param sf
     * @return
     */
    private boolean insertDataToDB(ServiceFileDO sf) {
        return (fileService.insert(sf) > 0);
    }
}
