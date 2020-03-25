package com.fhs.publics.result;

import com.fhs.publics.dox.ServiceFileDO;

public class DownLoadResult {

    private Integer rvCode = 0;

    private String rvMsg = "ok!";

    private ServiceFileDO serviceFile;

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

    public ServiceFileDO getServiceFile() {
        return serviceFile;
    }

    public void setServiceFile(ServiceFileDO serviceFile) {
        this.serviceFile = serviceFile;
    }
}
