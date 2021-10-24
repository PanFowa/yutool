package cn.yuan.yutool.module;

import cn.yuan.yutool.poi.annaction.HeadAlias;

@HeadAlias(rels = "agentCode,socCreditCode:渠道代理商编码,统一信用代码证")
public class AgentInfo {
    private String agentCode;
    private String socCreditCode;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getSocCreditCode() {
        return socCreditCode;
    }

    public void setSocCreditCode(String socCreditCode) {
        this.socCreditCode = socCreditCode;
    }
}
