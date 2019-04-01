package com.fhs.fileService.result;

import com.fhs.fileService.bean.ServiceFile;

public class DownLoadResult {

    private Integer rvCode = 0;

    private String rvMsg = "ok!";

    private ServiceFile serviceFile;

    public Integer getRvCode() {
        return rvCode;
    }

    public void setRvCode(Integer rvCode) {
        this.rvCode = rvCode;
    }

    public String getRvMsg() {
        return rvMsg;
    }

    public void setRvMsg(String rvMsg) {
        this.rvMsg = rvMsg;
    }

    public ServiceFile getServiceFile() {
        return serviceFile;
    }

    public void setServiceFile(ServiceFile serviceFile) {
        this.serviceFile = serviceFile;
    }
}
