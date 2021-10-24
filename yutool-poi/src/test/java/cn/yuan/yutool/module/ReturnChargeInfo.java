package cn.yuan.yutool.module;

import cn.yuan.yutool.poi.annaction.HeadAlias;

import java.util.Date;

/**
 * 翼商贷--回佣信息表
 *
 * @author pan
 * @email pan@tangdi.com
 * @date 2021-10-20 14:56:12
 */

@HeadAlias(rels = "agentName,areaCode,areaName:渠道代理商名称,归属地编码,归属地名称")
public class ReturnChargeInfo extends ReturnChargeInfoKey {
    private static final long serialVersionUID = 1L;


    /**
     * 代理商名称
     */
    private String agentName;
    /**
     * 归属地编码
     */
    private String areaCode;
    /**
     * 归属地名称
     */
    private String areaName;
    /**
     * 回佣数据所属年月
     */
    private String retMon;
    /**
     * 记录入库日期
     */

    private Date insDate;
    /**
     * 记录入库时间
     */
    private Date insTime;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRetMon() {
        return retMon;
    }

    public void setRetMon(String retMon) {
        this.retMon = retMon;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public Date getInsTime() {
        return insTime;
    }

    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }
}
