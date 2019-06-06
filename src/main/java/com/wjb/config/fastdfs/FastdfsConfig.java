package com.wjb.config.fastdfs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastdfsConfig {

    public String fastdfsAddress;

    public String fastdfsConfigAddress;

    public String filePreviewAddress;

    public String filePreviewOpen;

    public String filePreviewFastdfsStr;

    /**
     * rest的文件 预览地址+fasdst服务名
     */
    public String restFilePreviewAddress;

    public String restFilePreviewFastdfsStr;

    @Value("${file_preview_fastdfs_str}")
    public void setFilePreviewFastdfsStr(String filePreviewFastdfsStr) {
        this.filePreviewFastdfsStr = filePreviewFastdfsStr;
    }

    public String getFastdfsAddress() {
        return fastdfsAddress;
    }

    @Value("${fastdfs.address}")
    public void setFastdfsAddress(String fastdfsAddress) {
        this.fastdfsAddress = fastdfsAddress;
    }

    public String getFastdfsConfigAddress() {
        return fastdfsConfigAddress;
    }

    @Value("${fastdfs_config_address}")
    public void setFastdfsConfigAddress(String fastdfsConfigAddress) {
        this.fastdfsConfigAddress = fastdfsConfigAddress;
    }

    public String getFilePreviewAddress() {
        return filePreviewAddress;
    }

    @Value("${file_preview_address}")
    public void setFilePreviewAddress(String filePreviewAddress) {
        this.filePreviewAddress = filePreviewAddress;
    }

    public String getFilePreviewOpen() {
        return filePreviewOpen;
    }

    @Value("${file_preview_open}")
    public void setFilePreviewOpen(String filePreviewOpen) {
        this.filePreviewOpen = filePreviewOpen;
    }

    public String getFilePreviewFastdfsStr() {
        return filePreviewFastdfsStr;
    }

    public String getRestFilePreviewAddress() {
        return restFilePreviewAddress;
    }

    @Value("${rest_file_preview_address}")
    public void setRestFilePreviewAddress(String restFilePreviewAddress) {
        this.restFilePreviewAddress = restFilePreviewAddress;
    }

    public String getRestFilePreviewFastdfsStr() {
        return restFilePreviewFastdfsStr;
    }

    @Value("${rest_file_preview_fastdfs_str}")
    public void setRestFilePreviewFastdfsStr(String restFilePreviewFastdfsStr) {
        this.restFilePreviewFastdfsStr = restFilePreviewFastdfsStr;
    }
}
