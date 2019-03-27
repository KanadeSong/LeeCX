package com.ljj.web.tag;

import com.ljj.service.DataDictService;
import com.ljj.web.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @CLassName DataDictValueTag
 * @Description 定义数据字典标签，根据typeCode和ddkey获取ddvalue
 * @Author LeeJack
 * @Date 2019/3/15/015 7:56
 * @Version 1.0
 */
public class DataDictValueTag extends SimpleTagSupport {

    private String typeCode;

    private String ddKey;

    StringWriter sw = new StringWriter();

    public void doTag() throws JspException, IOException {
        if (StringUtils.isNotEmpty(typeCode) && StringUtils.isNotEmpty(ddKey)) {

            DataDictService ddService = (DataDictService) SpringContextUtil.getBean(DataDictService.class);
            String ddValue = ddService.queryDataDictValueByCodeKey(typeCode, ddKey);

            JspWriter out = getJspContext().getOut();
            out.println(ddValue);
        } else {
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDdKey() {
        return ddKey;
    }

    public void setDdKey(String ddKey) {
        this.ddKey = ddKey;
    }

}
