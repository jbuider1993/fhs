package com.fhs.pub.result;

import com.fhs.pub.dox.PubFileDO;

public class DownLoadResult {

    private Integer rvCode = 0;

    private String rvMsg = "ok!";

    private PubFileDO serviceFile;

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

    public PubFileDO getServiceFile() {
        return serviceFile;
    }

    public void setServiceFile(PubFileDO serviceFile) {
        this.serviceFile = serviceFile;
    }
}
