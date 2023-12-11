<template>
  <div class="chatChannel-session-list-box">

    <!-- 创建频道 -->
    <div class="chatChannel-builder">
      <el-button type="primary" text bg icon="Link" @click="addChannel">加入频道</el-button>
      <el-button type="danger" text bg icon="ChatRound" @click="createChannel">创建频道</el-button>
    </div>

    <el-scrollbar style="float:left;height:calc(100vh - 200px)">
      <div class="chatChannel-session" :class="item.id == currentChatChannel ? 'select-chatChannel' : ''"
        @click="handleSelectChatChannel(item)" v-for="item in chatChannel" :key="item">
        <el-row>
          <el-col :span="5">
            <div class="chatChannel-icon">
              <img :src="item.icon" style="width:40px;height:40px;border-radius: 5px;" />
            </div>
          </el-col>
          <el-col :span="19">
            <div class="chatChannel-title">
              #{{ item.channelName }}
            </div>
            <div class="chatChannel-desc">
              {{ item.channelDesc }}
            </div>
          </el-col>
        </el-row>
      </div>
    </el-scrollbar>

    <!-- 频道弹窗 -->
    <ChannelGroup ref="createChildComp" />

    <!-- 频道弹窗 -->
    <ChannelGroupDiscover ref="addChildComp" />

  </div>
</template>

<script setup name="Role">

import {
  chatMessage
} from '@/api/base/im/robot'

import {
  allMyChannel
} from '@/api/base/im/channel'

import Cookies from 'js-cookie'
import { getParam } from '@/utils/ruoyi'
import ChannelGroup from "./channelGroup";
import ChannelGroupDiscover from "./channelGroupDiscover";

const createChildComp = ref(null);
const addChildComp = ref(null);

const chatChannel = ref([
  { id: '1', channelName: '公共频道', channelDesc: '这是公共讨论服务频道', icon: '' },
  { id: '2', channelName: '数据库设计频道', channelDesc: '关于数据库设计的讨论', icon: '' },
  { id: '3', channelName: '代码模块生成频道', channelDesc: '讨论代码模块生成相关话题', icon: '' },
  { id: '4', channelName: '数据分析频道', channelDesc: '数据分析技术和方法的讨论', icon: '' },
  { id: '5', channelName: '考核题目设计频道', channelDesc: '考核题目设计相关讨论', icon: '' },
  { id: '6', channelName: '文档生成频道', channelDesc: '讨论文档生成工具和最佳实践', icon: '' },
  { id: '7', channelName: '技术架构设计频道', channelDesc: '关于技术架构设计的讨论', icon: '' },
  { id: '8', channelName: '解决方案讨论频道', channelDesc: '讨论解决问题的不同方案', icon: '' },
  { id: '9', channelName: '压力测试频道', channelDesc: '关于系统压力测试的话题', icon: '' },
]);
const currentChatChannel = ref('0');

// 若要动态添加频道并使其 ID 递增，你可以使用类似下面的方法：
let nextId = chatChannel.value.length + 1;

// function addChannel(name, desc) {
//   chatChannel.value.push({ id: String(nextId), channelName:name, channelDesc:desc, icon: '' });
//   nextId++;
// }

const emit = defineEmits(['onSendParams'])

/** 选择当前频道 */
function handleSelectChatChannel(item) {
  Cookies.set('currentChannelId', item.id);

  window.location.reload();
}


// 检查更新后的 chatChannel
// console.log(chatChannel.value);

/** 创建频道 */
function createChannel() {
  createChildComp.value.handleOpenChannel(true);
}

// 例如，添加新的频道并提供描述信息：
// addChannel('新频道名称', '这是新频道的描述');
/** 加入频道 */
function addChannel() {
  addChildComp.value.handleOpenChannel(true);
}

/** 查询所所有我在参与的频道 */
function handleAllMyChannel() {

  currentChatChannel.value = Cookies.get('currentChannelId');
  allMyChannel().then(response => {
    chatChannel.value = response.data;
  })
}

handleAllMyChannel();

</script>

<style lang="scss" scoped>
.chatChannel-builder {
  float: left;
  width: 100%;
  padding: 10px;
  text-align: center;
  margin-bottom: 10px;
}

.chatChannel-session-list-box {

  .chatChannel-session:hover {
    color: #409EFF;
    background: #fafafa;
  }

  .select-chatChannel {
    color: #409EFF;
    background: #fafafa;
  }

  .chatChannel-session {
    margin-bottom: 10px;
    border-radius: 5px;
    cursor: pointer;
    float: left;
    width: 100%;
    height: 60px;

    .chatChannel-title {
      font-size: 14px;
      font-weight: bold;
      padding-top: 10px;
      width: calc(100% - 0px);
      float: left;
      line-height: 1rem;
      padding-bottom: 5px;
    }

    .chatChannel-desc {
      font-size: 13px;
      float: left;
      width: calc(100% - 0px);
      color: #a5a5a5;
      line-height: 16px;
    }

    .chatChannel-icon {
      // position: absolute;
      margin: 8px;
      text-align: center;
    }
  }
}
</style>