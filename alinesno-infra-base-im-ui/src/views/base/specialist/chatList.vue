<template>
  <el-scrollbar class="scroll-panel" ref="scrollbarRef" loading always wrap-style="padding:10px">

    <div ref="innerRef">

      <div class="robot-chat-ai-say-box" v-for="(item, index) in messageList" 
          @mouseover="showTools(item)"
          @mouseleave="hideTools(item)"
          :key="index">

        <div class="chat-ai-header" :class="item.roleType == 'person' ? 'say-right-window' : ''">
          <div class="header-images">
            <img :src="item.icon" />
          </div>
        </div>

        <div class="chat-ai-say-body" :class="item.roleType == 'person' ? 'say-right-window' : ''" style="max-width:90%">
          <div class="say-message-info" v-if="item.roleType == 'person'"> 
            <span style="margin-left:10px" :class="item.showTools?'show-tools':'hide-tools'"> {{ item.dateTime }}</span> {{ item.name }} 
          </div>
          <div class="say-message-info" v-else> 
            {{ item.name }}  <span style="margin-left:10px" :class="item.showTools?'show-tools':'hide-tools'"> {{ item.dateTime }} </span>
          </div>

          <div class="say-message-body markdown-body" v-if="item.readerType === 'html'" v-html="item.chatText"></div>
          <div class="say-message-body markdown-body" v-else v-html="readerHtml(item.chatText)"></div>

            <div class="chat-ai-say-tools" style="margin-top: 3px;width: 100%;text-align: right;" :class="item.showTools?'show-tools':'hide-tools'">
                <el-button type="danger" link icon="Promotion" size="small" @click="handleBusinessIdToMessageBox(item)">执行</el-button>
                <el-button type="primary" link icon="EditPen" size="small" @click="handleEditGenContent(item)">编辑</el-button>
            </div>

        </div>
      </div>

    </div>

  </el-scrollbar>
</template>

<script setup>

import {
  chatMessage
} from '@/api/base/im/robot'

import { getParam } from '@/utils/ruoyi'

import { computed, ref , onMounted,  defineEmits} from 'vue';
import MarkdownIt from 'markdown-it';
import mdKatex from '@traptitech/markdown-it-katex';
import hljs from 'highlight.js';

const messageList = ref([])
const loading = ref(false)

// 滚动条的处理_starter
const innerRef = ref(null);
const scrollbarRef = ref(null);

// 定义派发事件
const emit = defineEmits(['sendMessageToChatBox' , 'handleEditorContent'])

// 改变组件的中的方法
const pushMessageList = (mess) => {
  console.log('mess = ' + mess);
  console.log('mess = ' + JSON.stringify(mess));

  let chatText = '';
  for (let i = 0; i < mess.length; i++) {
    let content = mess[i];
    if (content.type === 'text') {
      chatText += ('<span class="mention-text">' + (content.text) + '</span>');
    } else if (content.type === 'mention') {
      chatText += ('<span class="mention">' + ('@' + content.username) + '</span>');
    } else if (content.type === 'business') {
      chatText += ('<span class="mention-business">' + ('#' + content.businessId) + '</span>');
    }
  }

  console.log('chatText = ' + chatText);
  messageList.value.push({ roleType: 'person', businessId: '1733452663532019712' ,  dateTime: '2023-12-11 16:32:10' ,  readerType:'html', icon:'https://foruda.gitee.com/avatar/1676897721015308137/41655_landonniao_1656075872.png', name: '软件工程师罗小东', date: '12-10 13:58:21', chatText: chatText });

  initChatBoxScroll();
};

// 推送消息到当前面板
const pushResponseMessageList = (message) => {
  messageList.value.push(message) ; 
  initChatBoxScroll();
}

function initChatBoxScroll() {

  const element = innerRef.value;  // 获取滚动元素
  const scrollHeight = element.scrollHeight;

  // TODO 待处理滚动条没有到底部的问题
  console.log('scrollHeight = ' + scrollHeight);
  scrollbarRef.value.setScrollTop(scrollHeight) ; //scrollHeight);
}

const mdi = new MarkdownIt({
  html: false,
  linkify: true,
  highlight(code, language) {
    const validLang = !!(language && hljs.getLanguage(language));
    if (validLang) {
      const lang = language || '';
      return highlightBlock(hljs.highlight(code, { language: lang }).value, lang);
    }
    return highlightBlock(hljs.highlightAuto(code).value, '');
  },
});

// mdi.use(mila, { attrs: { target: '_blank', rel: 'noopener' } });
mdi.use(mdKatex, { blockClass: 'katexmath-block rounded-md p-[10px]', errorColor: ' #cc0000' });

function highlightBlock(str, lang) {
  return `<pre class="code-block-wrapper"><code class="hljs code-block-body ${lang}">${str}</code></pre>`;
}


function readerHtml(chatText) {
  return mdi.render(chatText);
}

/** 获取到会话信息 */
function handleChatMessage() {

  const businessId = getParam('businessId') == null ? '1733452663532019712' : getParam('businessId');
  console.log('businessId = ' + businessId);

  chatMessage(businessId).then(response => {
    messageList.value = response.data;
    loading.value = false;
    initChatBoxScroll();
  })

}

/** 发送业务代码到消息框中 */
function handleBusinessIdToMessageBox(item){
  const businessIdMessage = '#' + item.businessId + ' ' ;
  emit('sendMessageToChatBox' , businessIdMessage) ; 
}

/** 编辑生成内容和更新生成内容 */
function handleEditGenContent(item){
  const businessId = item.businessId + ' ' ;
  emit('handleEditorContent' , businessId) ; 
}

function showTools(item) {
    this.messageList.forEach((i) => {
      i.showTools = i === item; // 只有当前项的 showTools 被设置为 true
    });
}

function hideTools(item) {
  item.showTools = false; // 鼠标移出时隐藏 tools
}

onMounted(() => {
  handleChatMessage();
})

// 将这个方法暴露出去,这样父组件就可以使用了哈
defineExpose({
  pushMessageList,
  pushResponseMessageList
});

</script>

<style lang="scss" scoped>
.scroll-panel {
  padding-bottom: 10px;
  float: left;
  width: 100%;
  height: calc(100% - 55px);
  overflow: hidden;
}

.show-tools{
  visibility: visible;
}

.hide-tools{
  visibility: hidden;
}

.robot-chat-ai-say-box {
  float: left;
  width: 100%;

  .say-right-window {
    float: right !important;

    .say-message-info {
      text-align: right !important;
    }
  }

  .chat-ai-header {
    float: left;
    width: 50px;
    margin: 10px;

    .header-images {
      padding: 5px;

      img {
        width: 100%;
        border-radius: 50%;
      }

    }

  }

  .chat-ai-say-body {
    float: left;
    margin-top: 15px;
    font-size: 14px;

    .say-message-info {
      font-size: 13px;
      margin-bottom: 5px;
      color: #999;
    }

    .say-message-body {
      padding: 10px;
      line-height: 1.4rem;
      background: #fafafa;
      border-radius: 3px;
    }

  }

  .message-list {
    margin-top: 20px;
  }

  .message {
    margin-bottom: 8px;
  }

}

</style>