<template>

  <el-dialog v-model="centerDialogVisible" title="创建频道" width="30%" align-center>

    <div class="channel-box-panel" :class="showCreateBox?'show-box':'hidden-box'">

      <ul style="margin: 0;padding: 0px;list-style: none;">
        <li>
          <div class="channel-item">
            <img class="channel-image" src="http://data.linesno.com/icons/sepcialist/dataset_55.png" />
            <div class="channel-text">
              创建自己的频道
            </div>
            <el-button type="danger" style="position: absolute;right: 10px;" text bg @click="showCreateBox = false">
              <i class="fa-solid fa-feather icon-btn"></i> 创建 
            </el-button>
          </div>
        </li>
      </ul>

      <div style="font-size: 13px;margin-bottom: 10px;width: 100%;">
        从模板开始进入
      </div>
      
      <el-scrollbar height="400px">
        <ul style="margin: 0;padding: 0px;list-style: none;">
          <li v-for="(item, index) in chatChannelTemplate" :key="item">
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

    <div :class="showCreateBox?'hidden-box':'show-box'" style="height:481px">
        <el-page-header @back="goBack" title="返回" style="margin-bottom: 30px;">
          <template #content>
            <span class="text-large font-600 mr-3"> 填写频道信息</span>
          </template>
        </el-page-header>
        <el-form :model="form" :rules="rules" label-position="top" ref="userRef" label-width="80px">
          <el-row>
              <el-col :span="24">
                <el-form-item label="频道图标" prop="icon">
                  <el-avatar :size="size" src="http://data.linesno.com/icons/sepcialist/dataset_55.png" />
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="24">
                <el-form-item label="频道名称" prop="channelName">
                    <el-input size="large" v-model="form.channelName" placeholder="请输入频道名称" maxlength="30" />
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
              <el-col :span="24">
                <el-form-item label="备注" prop="channelDesc">
                    <el-input size="large" v-model="form.channelDesc" placeholder="请输入备注"></el-input>
                </el-form-item>
              </el-col>
          </el-row>
        </el-form> 
        <div class="dialog-footer">
            <el-button type="primary" size="large" bg text @click="submitChannelForm" style="float:right;margin-top:20px" icon="Link">创建频道</el-button>
        </div>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <!-- <el-button type="primary">加入频道</el-button> -->
      </span>
    </template>

  </el-dialog>

</template>

<script setup>

let { proxy } = getCurrentInstance();
import Cookies from 'js-cookie'

import {
  createChannel
} from '@/api/base/im/channel'

import { reactive, ref , onMounted } from 'vue'
import { getParam } from '@/utils/ruoyi'
import { ElLoading } from 'element-plus'

const showCreateBox = ref(true) ; 
const centerDialogVisible = ref(false)

const data = reactive({
  form: {},
  rules: {
    channelName: [{ required: true, message: "频道名称不能为空", trigger: "blur" }],
    channelDesc: [{ required: true, message: "频道描述不能为空", trigger: "blur" }],
  }
});

const { form, rules } = toRefs(data);

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
]);

const goBack = () => {
  console.log('go back')
  showCreateBox.value = true ;
}

/** 设置ChannelId */
function handleSetChannelId(channelId){
  console.log('handleSetChannelId channelId = ' + channelId) ;
  Cookies.set('currentChannelId', channelId) ;

  window.location.reload();
}

/** 创建频道 */
function submitChannelForm(){
  proxy.$refs["userRef"].validate(valid => {
    if (valid) {
      const loading = ElLoading.service({
        lock: true,
        text: 'Loading',
      })
      createChannel(form.value).then(response => {
        proxy.$modal.msgSuccess("新增成功");
        // loading.close();
        // centerDialogVisible.value = false ;
        handleSetChannelId(response.data)
      });
    }
  });
}

/** 初始化 */
onMounted(() => {

  const channelId = Cookies.get('currentChannelId') ;
  console.log('channelId = ' + channelId + '--' + (channelId == undefined)) ;

  if(channelId == undefined) {
    centerDialogVisible.value = true;
  }

})

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

.hidden-box {
  display: none;
}

.show-box {
  display: block;
}

</style>