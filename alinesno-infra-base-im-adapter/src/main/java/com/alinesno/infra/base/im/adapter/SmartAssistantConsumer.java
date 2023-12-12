package com.alinesno.infra.base.im.adapter;

import com.alibaba.fastjson.JSONArray;
import com.alinesno.infra.base.im.dto.IndustryRoleDto;
import com.alinesno.infra.base.im.dto.RobotMessageDto;
import com.alinesno.infra.base.im.dto.TaskContentDto;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartAssistantConsumer {

    public AjaxResult runChainAgent(@JSONBody TaskContentDto ta, @Query("roleId") String roleId){
        return AjaxResult.success() ;
    }

    public AjaxResult sendMessageWebhook(@JSONBody RobotMessageDto dto, @JSONBody Object o){
        return AjaxResult.success() ;
    }

    public List<IndustryRoleDto> getAgentList() {

        String str = "[\n" +
                "        {\n" +
                "            \"id\": \"1728168099531755522\",\n" +
                "            \"fieldProp\": null,\n" +
                "            \"addTime\": \"2023-11-24 21:44:44\",\n" +
                "            \"deleteTime\": null,\n" +
                "            \"hasStatus\": 0,\n" +
                "            \"updateTime\": null,\n" +
                "            \"operatorId\": null,\n" +
                "            \"lastUpdateOperatorId\": null,\n" +
                "            \"hasDelete\": 0,\n" +
                "            \"deleteManager\": null,\n" +
                "            \"applicationId\": null,\n" +
                "            \"applicationName\": null,\n" +
                "            \"tenantId\": \"0\",\n" +
                "            \"fieldId\": null,\n" +
                "            \"departmentId\": null,\n" +
                "            \"roleName\": \"运维工程师\",\n" +
                "            \"industry\": null,\n" +
                "            \"responsibilities\": \"负责维护软件系统的稳定性和运行效率，实施自动化操作。\",\n" +
                "            \"skills\": null,\n" +
                "            \"experience\": null,\n" +
                "            \"roleLevel\": \"资深工程师\",\n" +
                "            \"salaryRange\": null,\n" +
                "            \"education\": null,\n" +
                "            \"otherAttributes\": null,\n" +
                "            \"chainId\": null,\n" +
                "            \"promptId\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"1728141329814339585\",\n" +
                "            \"fieldProp\": null,\n" +
                "            \"addTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"deleteTime\": null,\n" +
                "            \"hasStatus\": 0,\n" +
                "            \"updateTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"operatorId\": null,\n" +
                "            \"lastUpdateOperatorId\": null,\n" +
                "            \"hasDelete\": 0,\n" +
                "            \"deleteManager\": null,\n" +
                "            \"applicationId\": null,\n" +
                "            \"applicationName\": null,\n" +
                "            \"tenantId\": \"0\",\n" +
                "            \"fieldId\": null,\n" +
                "            \"departmentId\": null,\n" +
                "            \"roleName\": \"开发技术总监\",\n" +
                "            \"industry\": null,\n" +
                "            \"responsibilities\": \"负责执行和管理技术战略，领导技术团队以实现公司的技术目标和愿景。\",\n" +
                "            \"skills\": null,\n" +
                "            \"experience\": null,\n" +
                "            \"roleLevel\": \"高级工程师\",\n" +
                "            \"salaryRange\": null,\n" +
                "            \"education\": null,\n" +
                "            \"otherAttributes\": null,\n" +
                "            \"chainId\": \"1730959946587942914\",\n" +
                "            \"promptId\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"1728142262153592834\",\n" +
                "            \"fieldProp\": null,\n" +
                "            \"addTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"deleteTime\": null,\n" +
                "            \"hasStatus\": 0,\n" +
                "            \"updateTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"operatorId\": null,\n" +
                "            \"lastUpdateOperatorId\": null,\n" +
                "            \"hasDelete\": 0,\n" +
                "            \"deleteManager\": null,\n" +
                "            \"applicationId\": null,\n" +
                "            \"applicationName\": null,\n" +
                "            \"tenantId\": \"0\",\n" +
                "            \"fieldId\": null,\n" +
                "            \"departmentId\": null,\n" +
                "            \"roleName\": \"解决方案架构师\",\n" +
                "            \"industry\": null,\n" +
                "            \"responsibilities\": \"负责设计全面的解决方案架构，确保系统满足业务需求和技术要求。\",\n" +
                "            \"skills\": null,\n" +
                "            \"experience\": null,\n" +
                "            \"roleLevel\": \"资深架构师\",\n" +
                "            \"salaryRange\": null,\n" +
                "            \"education\": null,\n" +
                "            \"otherAttributes\": null,\n" +
                "            \"chainId\": \"1733447757043589121\",\n" +
                "            \"promptId\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"1728142488922832897\",\n" +
                "            \"fieldProp\": null,\n" +
                "            \"addTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"deleteTime\": null,\n" +
                "            \"hasStatus\": 0,\n" +
                "            \"updateTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"operatorId\": null,\n" +
                "            \"lastUpdateOperatorId\": null,\n" +
                "            \"hasDelete\": 0,\n" +
                "            \"deleteManager\": null,\n" +
                "            \"applicationId\": null,\n" +
                "            \"applicationName\": null,\n" +
                "            \"tenantId\": \"0\",\n" +
                "            \"fieldId\": null,\n" +
                "            \"departmentId\": null,\n" +
                "            \"roleName\": \"产品经理\",\n" +
                "            \"industry\": null,\n" +
                "            \"responsibilities\": \"负责规划和执行产品策略，管理产品生命周期，满足市场需求。\",\n" +
                "            \"skills\": null,\n" +
                "            \"experience\": null,\n" +
                "            \"roleLevel\": \"高级工程师\",\n" +
                "            \"salaryRange\": null,\n" +
                "            \"education\": null,\n" +
                "            \"otherAttributes\": null,\n" +
                "            \"chainId\": null,\n" +
                "            \"promptId\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"1728146276987518977\",\n" +
                "            \"fieldProp\": null,\n" +
                "            \"addTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"deleteTime\": null,\n" +
                "            \"hasStatus\": 0,\n" +
                "            \"updateTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"operatorId\": null,\n" +
                "            \"lastUpdateOperatorId\": null,\n" +
                "            \"hasDelete\": 0,\n" +
                "            \"deleteManager\": null,\n" +
                "            \"applicationId\": null,\n" +
                "            \"applicationName\": null,\n" +
                "            \"tenantId\": \"0\",\n" +
                "            \"fieldId\": null,\n" +
                "            \"departmentId\": null,\n" +
                "            \"roleName\": \"技术文档编写人员\",\n" +
                "            \"industry\": null,\n" +
                "            \"responsibilities\": \"负责编写技术文档和指南，使用户理解和使用软件。\",\n" +
                "            \"skills\": null,\n" +
                "            \"experience\": null,\n" +
                "            \"roleLevel\": \"资深现场项目经理\",\n" +
                "            \"salaryRange\": null,\n" +
                "            \"education\": null,\n" +
                "            \"otherAttributes\": null,\n" +
                "            \"chainId\": null,\n" +
                "            \"promptId\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"1728157115467513857\",\n" +
                "            \"fieldProp\": null,\n" +
                "            \"addTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"deleteTime\": null,\n" +
                "            \"hasStatus\": 0,\n" +
                "            \"updateTime\": \"2023-11-24 19:57:26\",\n" +
                "            \"operatorId\": null,\n" +
                "            \"lastUpdateOperatorId\": null,\n" +
                "            \"hasDelete\": 0,\n" +
                "            \"deleteManager\": null,\n" +
                "            \"applicationId\": null,\n" +
                "            \"applicationName\": null,\n" +
                "            \"tenantId\": \"0\",\n" +
                "            \"fieldId\": null,\n" +
                "            \"departmentId\": null,\n" +
                "            \"roleName\": \"数据分析师\",\n" +
                "            \"industry\": null,\n" +
                "            \"responsibilities\": \"负责收集、分析和解释数据，提供关于产品和用户行为的见解。\",\n" +
                "            \"skills\": null,\n" +
                "            \"experience\": null,\n" +
                "            \"roleLevel\": \"高级工程师\",\n" +
                "            \"salaryRange\": null,\n" +
                "            \"education\": null,\n" +
                "            \"otherAttributes\": null,\n" +
                "            \"chainId\": null,\n" +
                "            \"promptId\": null\n" +
                "        }\n" +
                "    ]" ;

        List<IndustryRoleDto> list = JSONArray.parseArray(str , IndustryRoleDto.class) ;

        return list ;
    }
}
