<template>

  <el-dialog v-model="centerDialogVisible" title="加入频道" width="70%" align-center>

    <div class="channel-box-panel">

      <div style="font-size: 13px;margin-bottom: 10px;width: 100%;">
        社区频道列表，参与其它人更有意思的角色定义和频道学习
      </div>
      
      <el-scrollbar height="500px">
        <ul style="margin: 0;padding: 0px;list-style: none;">
          <li v-for="(item, index) in chatChannelTemplate" :key="item" class="channel-item-li">
            <div class="channel-item">
              <img class="channel-image" :src="'http://data.linesno.com/icons/sepcialist/dataset_' + (index + 5) + '.png'" />
              <div class="channel-text">
                #{{ item.name }}
              </div>
              <div class="channel-desc">
                {{ item.desc }}
              </div>
              <el-button type="primary" @click="handleSetChannelId(item.id)" style="position: absolute;right: 10px;" text bg>
                <i class="fa-brands fa-google-play icon-btn"></i> 进入
              </el-button>
            </div>
          </li>
        </ul>
      </el-scrollbar>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary">加入频道</el-button>
      </span>
    </template>

  </el-dialog>

</template>

<script setup>

let { proxy } = getCurrentInstance();
import Cookies from 'js-cookie'

import { onMounted } from 'vue';
import { getParam } from '@/utils/ruoyi'

const centerDialogVisible = ref(false)
const chatChannelTemplate = ref([
  { id: '1', name: '公共频道', desc: '这是公共讨论服务频道', icon: '' },
  { id: '2', name: '数据库设计频道', desc: '关于数据库设计的讨论', icon: '' },
  { id: '3', name: '代码模块生成频道', desc: '讨论代码模块生成相关话题', icon: '' },
  { id: '4', name: '数据分析频道', desc: '数据分析技术和方法的讨论', icon: '' },
  { id: '5', name: '考核题目设计频道', desc: '考核题目设计相关讨论', icon: '' },
  { id: '6', name: '文档生成频道', desc: '讨论文档生成工具和最佳实践', icon: '' },
  { id: '7', name: '技术架构设计频道', desc: '关于技术架构设计的讨论', icon: '' },
  { id: '8', name: '解决方案讨论频道', desc: '讨论解决问题的不同方案', icon: '' },
  { id: '9', name: '压力测试频道', desc: '关于系统压力测试的话题', icon: '' },
  { id: '10', name: '前端开发讨论频道', desc: '关于前端开发的讨论', icon: '' },
  { id: '11', name: '后端开发讨论频道', desc: '关于后端开发的讨论', icon: '' },
  { id: '12', name: '人工智能与机器学习频道', desc: '人工智能与机器学习技术讨论', icon: '' },
  { id: '13', name: '物联网技术频道', desc: '讨论物联网技术和发展', icon: '' },
  { id: '14', name: '移动应用开发频道', desc: '关于移动应用开发的话题', icon: '' },
  { id: '15', name: '网络安全讨论频道', desc: '关于网络安全的讨论和建议', icon: '' },
  { id: '16', name: '云计算与服务频道', desc: '讨论云计算和相关服务', icon: '' },
  { id: '17', name: '产品管理讨论频道', desc: '产品管理与策划的话题', icon: '' },
  { id: '18', name: '用户体验设计频道', desc: '关于用户体验设计的讨论', icon: '' },
  { id: '19', name: '区块链技术讨论频道', desc: '讨论区块链技术和应用', icon: '' },
  { id: '20', name: '软件测试与质量控制频道', desc: '软件测试与质量控制的讨论', icon: '' },
  { id: '21', name: '项目管理讨论频道', desc: '项目管理方法和实践的讨论', icon: '' },
  { id: '22', name: '大数据技术讨论频道', desc: '大数据技术和应用的讨论', icon: '' },
  { id: '23', name: '编程语言讨论频道', desc: '各种编程语言的讨论和比较', icon: '' },
  { id: '24', name: '自然语言处理频道', desc: '自然语言处理技术讨论', icon: '' },
  { id: '25', name: '虚拟现实与增强现实频道', desc: '虚拟现实与增强现实技术讨论', icon: '' },
  { id: '26', name: '电子商务技术频道', desc: '电子商务技术和趋势讨论', icon: '' },
  { id: '27', name: '微服务架构频道', desc: '微服务架构设计与实践讨论', icon: '' },
  { id: '28', name: '人机交互设计频道', desc: '人机交互设计的话题讨论', icon: '' },
  { id: '29', name: '敏捷开发方法论频道', desc: '敏捷开发方法论的讨论', icon: '' }
]);

/** 设置ChannelId */
function handleSetChannelId(channelId){
  console.log('handleSetChannelId channelId = ' + channelId) ;

  window.location.reload();
}

function handleOpenChannel(val){
    centerDialogVisible.value = val;
}

defineExpose({
  handleOpenChannel,
})

</script>

<style lang="scss" scoped>
.channel-box-panel {

  .channel-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    padding: 10px;
    position: relative;
    background: #fafafa;
    border-radius: 5px;
  }

  .channel-item-li{
    width: calc(50% - 20px);
    float: left;
    margin-right: 10px;
    margin-left: 10px;
  }

  .channel-image {
    width: 35px;
    height: 35px;
    border-radius: 50%;
    margin-right: 10px;
  }

  .channel-text {
    font-size: 14px;
    font-weight: bold;
    color: #333;
  }

  .channel-desc {
    margin-left: 10px;
    font-size: 13px;
  }

  .icon-btn{
    margin-right: 10px;
  }

}

</style>