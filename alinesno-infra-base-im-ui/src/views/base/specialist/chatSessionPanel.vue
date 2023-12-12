<template>
  <div class="chatChannel-session-list-box">

    <!-- 创建频道 -->
    <div class="chatChannel-builder">
      <el-button type="primary" text bg icon="Link" @click="addChannel">加入频道</el-button>
      <el-button type="danger" text bg icon="ChatRound" @click="createChannel">创建频道</el-button>
    </div>

    <el-scrollbar style="float:left;height:calc(100vh - 200px)">
      <div class="chatChannel-session" 
          @mouseover="showTools(item)"
          @mouseleave="hideTools(item)"
          :class="item.id == currentChatChannel ? 'select-chatChannel' : ''" v-for="item in chatChannel" :key="item">
        <el-row>
          <el-col :span="5" @click="handleSelectChatChannel(item)" >
            <div class="chatChannel-icon">
              <img :src="item.icon" style="width:40px;height:40px;border-radius: 5px;" />
            </div>
          </el-col>
          <el-col :span="19">
            <div class="chatChannel-title" @click="handleSelectChatChannel(item)" >
              #{{ item.channelName }}
            </div>
            <div class="chatChannel-desc">
              {{ item.channelDesc }}
            </div>
            <el-button type="danger" 
                :class="item.showTools && item.channelType !== '1' ?'show-tools':'hide-tools'"  
                link
                style="position: absolute;top: 10px;right: 10px;" 
                icon="Close" 
                @click="handelDeleteChannel(item)">删除</el-button>
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
  allMyChannel , 
  delChannel , 
  removeChannel
} from '@/api/base/im/channel'

import Cookies from 'js-cookie'
import { getParam } from '@/utils/ruoyi'
import ChannelGroup from "./channelGroup";
import ChannelGroupDiscover from "./channelGroupDiscover";

const router = useRouter();
const createChildComp = ref(null);
const addChildComp = ref(null);

const chatChannel = ref([
  { id: '1', channelName: '公共频道', channelDesc: '这是公共讨论服务频道', icon: '' },
]);
const currentChatChannel = ref('0');

// 若要动态添加频道并使其 ID 递增，你可以使用类似下面的方法：
// let nextId = chatChannel.value.length + 1;

// function addChannel(name, desc) {
//   chatChannel.value.push({ id: String(nextId), channelName:name, channelDesc:desc, icon: '' });
//   nextId++;
// }

const emit = defineEmits(['onSendParams'])

/** 选择当前频道 */
function handleSelectChatChannel(item) {

  const channelId = item.id ;
  router.push({
      path: '/index',
      query: { 'channel': channelId }
  })

  // window.location.reload();
}

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

/** 删除路径 */
function handelDeleteChannel(item){
  removeChannel(item.id).then(response => {
    handleAllMyChannel();
  })
}

/** 查询所所有我在参与的频道 */
function handleAllMyChannel() {

  const channelId = getParam("channel");
  currentChatChannel.value = channelId ; 

  allMyChannel().then(response => {
    chatChannel.value = response.data;
  })
}

function showTools(item) {
    this.chatChannel.forEach((i) => {
      i.showTools = i === item; // 只有当前项的 showTools 被设置为 true
    });
}

function hideTools(item) {
  item.showTools = false; // 鼠标移出时隐藏 tools
}

handleAllMyChannel();

/** 监听路由变化 */
watch(() =>  router.currentRoute.value.path,
    (toPath) => {
    //要执行的方法
    // const channelId = router.currentRoute.value.channelId;

    handleAllMyChannel();
      
    },{immediate: true,deep: true}
)
  

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

.show-tools{
  visibility: visible;
}

.hide-tools{
  visibility: hidden;
}
}
</style>