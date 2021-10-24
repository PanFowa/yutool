package cn.yuan.yutool;


import cn.yuan.yutool.module.AgentInfo;
import cn.yuan.yutool.module.ReturnChargeInfo;
import cn.yuan.yutool.poi.excel.ExcelReader;
import cn.yuan.yutool.poi.excel.util.ExcelUtil;
import org.junit.Test;

import java.util.List;

/**
 * Excel读取单元测试
 *
 * @author PanFowa
 * @email 2782169906@qq.com
 */
public class ExcelReadTest {
    @Test
    public void excelReadToBeanListHeadAlias() throws Exception {
        String file = this.getClass().getClassLoader().getResource("data/excel/COMMINFO_Test.xlsx").getFile();
        ExcelReader reader = ExcelUtil.getReader(file);
        List<AgentInfo> all = reader.readAll(AgentInfo.class);
        for (AgentInfo agentInfo : all) {
            System.out.println(agentInfo);
        }
    }

    @Test
    public void excelReadToBeanListAllHeadAlias() throws Exception {
        String file = this.getClass().getClassLoader().getResource("data/excel/RETURN_202110.xlsx").getFile();
        ExcelReader reader = ExcelUtil.getReader(file);
        List<ReturnChargeInfo> all = reader.readAll(ReturnChargeInfo.class);
        for (ReturnChargeInfo chargeInfo : all) {
            System.out.println(chargeInfo);
        }
    }

}
