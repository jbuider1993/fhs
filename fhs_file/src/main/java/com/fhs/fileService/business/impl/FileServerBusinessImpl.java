package com.fhs.fileService.business.impl;

import com.fhs.core.config.EConfig;
import com.fhs.fileService.bean.ServiceFile;
import com.fhs.fileService.business.FileServerBusiness;
import com.fhs.fileService.service.ServiceFileService;
import com.fhs.common.utils.*;
import com.fhs.fileStorage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public ServiceFile uploadFile(MultipartFile fileData) {
        ServiceFile sf = new ServiceFile();
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
    public List<ServiceFile> uploadFileForList(List<MultipartFile> allFileData) {
        List<ServiceFile> rvList = new ArrayList<ServiceFile>();

        allFileData.forEach(fileData -> {
            rvList.add(this.uploadFile(fileData));
        });

        return rvList;
    }

    /**
     * @param sf
     * @return
     */
    private boolean insertDataToDB(ServiceFile sf) {
        return (fileService.insert(sf) > 0);
    }
}
