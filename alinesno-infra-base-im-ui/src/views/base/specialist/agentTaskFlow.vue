<template>
    <div style="margin: -10px;margin-top: -20px;">
        <!-- <div>
            频道运行任务列表
        </div> -->
        <el-scrollbar height="500" ref="scrollbarRef" >
            <div ref="innerRef">

                <div class="task-item-message" v-for="(item , index) in tableData" :key="index">
                    <div>
                        <div class="task-status-job-result">
                            <span class="task-status-name task-status-icon">
                                <i v-if="item.taskType == 1" class="fa-solid fa-truck-fast"></i> 
                                <i v-if="item.taskType == 4" class="fa-solid fa-list-ol"></i>
                                <i v-if="item.taskType == 5" class="fa-solid fa-file-waveform"></i>
                            </span>
                            <span class="task-status-name">
                                {{ item.taskName }} 
                            </span>
                            {{ item.assistantContent }}
                            <span class="task-status-job-usageTime">
                                {{ item.businessId }} - {{ item.usageTime }}
                            </span>
                        </div>
                    </div>
                </div>

                <!-- 
                <el-table :data="tableData" stripe style="width: 100%">
                    <el-table-column prop="taskStatus" label="状态" width="80" align="center">
                        <template #default="scope">
                            asdfsdf
                        </template>
                    </el-table-column>
                    <el-table-column prop="businessId" label="业务ID" width="80" align="center" />
                    <el-table-column prop="taskName" label="任务名称" width="180" />
                    <el-table-column prop="assistantContent" label="任务内容" :show-overflow-tooltip="true" />
                    <el-table-column prop="usageTime" label="用时" width="80" align="center" />
                </el-table> 
                -->
            </div>
        </el-scrollbar>
    </div>
</template>

<script setup>

import { nextTick } from 'vue'

// 滚动条的处理_starter
const innerRef = ref(null);
const scrollbarRef = ref(null);

// 任务类型taskType: 1消息结果|2思考任务|3排除状态|4循环中|5状态通知
const tableData = ref([
  {
    taskStatus: 'completed',
    taskType:'1',
    businessId: 'B001',
    taskName: '财务报告数据分析',
    assistantContent: '我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈业务拓展战略规划遇到了一些意料之外的挑战，需要全面重新评估市场情况、资源配置和风险缓解策略。尽管遇到了挫折，但我们学到了宝贵的经验教训。',
    usageTime: '2小时',
  },
  {
    taskStatus: 'failed',
    taskType:'5',
    businessId: 'B003',
    taskName: '正在思考中',
    assistantContent: '任务正在生成内容中',
    usageTime: '3小时',
  },
  {
    taskStatus: 'failed',
    taskType:'think',
    businessId: 'B003',
    taskType:'1',
    taskName: '业务拓展战略规划',
    assistantContent: '业务拓展战略规划遇到了一些意料之外的挑战，需要全面重新评估市场情况、资源配置和风险缓解策略。尽管遇到了挫折，但我们学到了宝贵的经验教训。',
    usageTime: '3小时',
  },
  {
    taskStatus: 'failed',
    taskType:'5',
    businessId: 'B003',
    taskName: '添加新任务',
    assistantContent: '业务拓展战略规划',
    usageTime: '3小时',
  },
  {
    taskStatus: 'failed',
    businessId: 'B003',
    taskType:'1',
    taskName: '业务拓展战略规划',
    assistantContent: '业务拓展战略规划遇到了一些意料之外的挑战，需要全面重新评估市场情况、资源配置和风险缓解策略。尽管遇到了挫折，但我们学到了宝贵的经验教训。',
    usageTime: '3小时',
  },
  {
    taskStatus: 'failed',
    taskType:'4',
    businessId: 'B003',
    taskName: '正在查询结果7次',
    assistantContent: '业务拓展战略规划',
    usageTime: '3小时',
  },
  {
    taskStatus: 'completed',
    businessId: 'B001',
    taskType:'1',
    taskName: '财务报告数据分析',
    assistantContent: '在分析财务数据时，我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈现。',
    usageTime: '2小时',
  },
  {
    taskStatus: 'failed',
    businessId: 'B003',
    taskType:'1',
    taskName: '业务拓展战略规划',
    assistantContent: '我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈业务拓展战略规划遇到了一些意料之外的挑战，需要全面重新评估市场情况、资源配置和风险缓解策略。尽管遇到了挫折，但我们学到了宝贵的经验教训。',
    usageTime: '3小时',
  },
])

function initChatBoxScroll() {

  nextTick(() => {
    const element = innerRef.value;  // 获取滚动元素
    const scrollHeight = element.scrollHeight;

    scrollbarRef.value.setScrollTop(scrollHeight) ; 
  })

}

// 定义定时任务方法
function addTestDataRandomly() {

  const chineseContent = [
    '这是随机生成的测试数据内容，用于模拟新的任务信息。详细的分析包括各种指标和数据的可视化呈现。我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈,我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈',
    '业务拓展战略规划遇到了一些意料之外的挑战，详细的分析包括各种指标和数据的可视化呈',
    '在分析财务数据时，我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈,我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈',
    '业务拓展战略规划遇到了一些意料之外的挑战，我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈呈需要全面重新评估市场情况、资源配置和风险缓解策略。',
    '在分析财务数据时，我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈,我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈我们发现了一些有价值的趋势和见解，这将对决策过程产生重要影响。详细的分析包括各种指标和数据的可视化呈现。'
  ];

  const statusOptions = ['pending', 'completed', 'failed'];
  const taskType = ['1', '4', '5'];
  const taskNameOptions = ['财务报告数据分析', '业务拓展战略规划', '产品研发进度跟踪', '市场营销活动策划', '人力资源招聘计划'];

  setInterval(() => {
    const randomIndex = Math.floor(Math.random() * tableData.value.length);
    tableData.value.splice(tableData.value.length, 0, {
      taskStatus: statusOptions[Math.floor(Math.random() * statusOptions.length)],
      businessId: 'B00' + (randomIndex + 1),
      taskType: taskType[Math.floor(Math.random() * taskType.length)],
      taskName: taskNameOptions[Math.floor(Math.random() * taskNameOptions.length)],
      assistantContent: chineseContent[Math.floor(Math.random() * chineseContent.length)],
      usageTime: Math.floor(Math.random() * 10 + 1) + '秒',
    });

    // 保留最后100条数据，删除多余数据
    if (tableData.value.length > 20) {
        tableData.value.splice(0, tableData.value.length - 20);
    }

    initChatBoxScroll();

  }, Math.floor(Math.random() * 900) + 100); // 1000ms 到 10000ms 之间的随机时间间隔
}

addTestDataRandomly();
initChatBoxScroll();

</script>

<style lang="scss" scoped>

.task-item-message{
    background: #f5f5f5;
    padding: 10px;
    line-height: 1.5rem;
    border-radius: 5px;
    margin-bottom: 5px;

    span.task-status-job-usageTime {
        font-size: 13px;
        color: #F56C6C;
        font-weight: bold;
    }
    span.task-status-icon{
        color: #007bff !important;
    }

    span.task-status-name{
        color: #F56C6C ;
        font-weight: bold;
        margin-left: 2px;
        margin-right: 2px;
        font-size: 14px;
    }
}

</style>