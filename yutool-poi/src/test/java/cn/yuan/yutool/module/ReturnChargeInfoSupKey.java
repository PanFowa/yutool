package cn.yuan.yutool.module;



import cn.yuan.yutool.poi.annaction.HeadAlias;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 翼商贷--回佣信息表
 *
 * @author PanFowa
 * @email 2782169906@qq.com
 * @date 2021-10-24 23:56:12
 */

@HeadAlias(rels = "monChargeTotal1,agentCode1:月结佣金总金额1,渠道代理商编码1")
public class ReturnChargeInfoSupKey implements Serializable {
    private static final long serialVersionUID = 1L;


    private BigDecimal monChargeTotal1;

    private String agentCode1;

    public BigDecimal getMonChargeTotal1() {
        return monChargeTotal1;
    }

    public void setMonChargeTotal1(BigDecimal monChargeTotal1) {
        this.monChargeTotal1 = monChargeTotal1;
    }

    public String getAgentCode1() {
        return agentCode1;
    }

    public void setAgentCode1(String agentCode1) {
        this.agentCode1 = agentCode1;
    }

}
