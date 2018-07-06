/*
 * UploadDisplayer.java
 *
 * Created on Oct 11, 2008, 2008, 1:35:31 PM
 */
package com.tool;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 描述:文件上传
 */
public class UploadDisplayer extends TagSupport {

    private String fileId;
    private String fileName;
    private String fileType;
    private String fileValue;
    private String fileCss;
    private String btnCss;
    private String base;
    private String readOnly;
    private String savePath;

    public int doStartTag() throws JspException {
        try {
            String show = "<a href=\""+ base + fileValue + "\" target=\"_blank\"><img width=\"20\" height=\"20\" src=\"" + base + "/images/download.png\" /></a>";
            String outstr = "";
            if (readOnly != null && readOnly.equals("true")) {
                outstr = show;
            } else {
                outstr += "<input type=\"text\"  readonly=\"readonly\" class=\"" + fileCss + "\" value=\"" + (fileValue == null ? "" : fileValue) + "\" name=\"" + fileName + "\" id=\"" + fileId + "\" />";
                outstr += "<input type=\"button\" value=\"上传文件\" class=\"" + btnCss + "\" onclick=\"fileupload('" + fileId + "','" + fileType + "','" + savePath+ "')\" />";
                if (fileValue != null && !fileValue.equals("")) {
                    outstr += show;
                }
            }
            pageContext.getOut().println(outstr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public String getBtnCss() {
        return btnCss;
    }

    public void setBtnCss(String btnCss) {
        this.btnCss = btnCss;
    }

    public String getFileCss() {
        return fileCss;
    }

    public void setFileCss(String fileCss) {
        this.fileCss = fileCss;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
    
}
