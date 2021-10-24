package cn.yuan.yutool.module;



import cn.yuan.yutool.poi.annaction.HeadAlias;

import java.math.BigDecimal;

/**
 * 翼商贷--回佣信息表
 *
 * @author pan
 * @email pan@tangdi.com
 * @date 2021-10-20 14:56:12
 */

@HeadAlias(rels = "monChargeTotal,agentCode:月结佣金总金额,渠道代理商编码")
public class ReturnChargeInfoKey extends ReturnChargeInfoSupKey {
    private static final long serialVersionUID = 1L;


    private BigDecimal monChargeTotal;

    private String agentCode;

    public BigDecimal getMonChargeTotal() {
        return monChargeTotal;
    }

    public void setMonChargeTotal(BigDecimal monChargeTotal) {
        this.monChargeTotal = monChargeTotal;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

}
