package com.alinesno.infra.base.im.adapter;

import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.im.dto.BrainTaskDto;
import com.alinesno.infra.base.im.dto.TaskContentDto;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调用接口
 */
@Slf4j
@Service
public class SmartBrainConsumer {

    private static String javaCode = "```java\n" +
            "public enum AccountType {\n" +
            "    PLATFORM(0, \"平台账号\"),\n" +
            "    AGENT(1, \"Agent账号\"),\n" +
            "    OTHER(9, \"其它类型\");\n" +
            "\n" +
            "    private int value;\n" +
            "    private String label;\n" +
            "\n" +
            "    AccountType(int value, String label) {\n" +
            "        this.value = value;\n" +
            "        this.label = label;\n" +
            "    }\n" +
            "\n" +
            "    public int getValue() {\n" +
            "        return value;\n" +
            "    }\n" +
            "\n" +
            "    public String getLabel() {\n" +
            "        return label;\n" +
            "    }\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "使用示例：\n" +
            "```java\n" +
            "public class Main {\n" +
            "    public static void main(String[] args) {\n" +
            "        AccountType accountType = AccountType.PLATFORM;\n" +
            "        System.out.println(accountType.getValue()); // Output: 0\n" +
            "        System.out.println(accountType.getLabel()); // Output: 平台账号\n" +
            "    }\n" +
            "}\n" +
            "```\n" +
            "\n" ;

    /**
     * 发送离线任务
     * @param dto
     * @return
     */
    @Post(url="/api/llm/chatTask")
    public AjaxResult chatTask(@JSONBody BrainTaskDto dto){
        return AjaxResult.success() ;
    }

    /**
     * 查询离线任务
     * @param businessId
     * @return
     */
    @Post(url="/api/llm/chatContent")
    public AjaxResult chatContent(@Query("businessId") String businessId) {

        TaskContentDto dto = new TaskContentDto();
        TaskContentDto.CodeContent c = new TaskContentDto.CodeContent("java" , javaCode) ;

        dto.setGenContent("你的任务已经完成，我已经在回复了，你可以多了解。\n" + javaCode) ;
        dto.setBusinessId(businessId);
        dto.setCodeContent(List.of(c));

        return AjaxResult.success("查询成功." , JSONObject.toJSON(dto)) ;
    }

    /**
     * 更新任务生成内容
     * @param dto
     * @return
     */
    @Post(url="/api/llm/modifyContent")
    public AjaxResult modifyContent(@JSONBody TaskContentDto dto){
        log.debug("dto = {}" , dto);
        return AjaxResult.success() ;
    }
}
