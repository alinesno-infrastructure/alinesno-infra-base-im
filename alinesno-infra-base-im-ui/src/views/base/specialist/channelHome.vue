<template>
  <el-scrollbar height="100vh">
    <div class="acp-dashboard" style="max-width:1240px;width:80%;margin:auto;margin-bottom: 80px; padding: 0px 10px !important;background: #fff;">
      <div class="channel-panel-header">
          自动化Agent列表
          <span style="font-size: 13px;color: #a5a5a5;">这里包含所有需要运营的Agent服务列表，你可以理解成一个Agent就是一个员工</span>
      </div>
      <div class="channel-panel-body">
        <el-row>
          <el-col class="channel-item-box" :span="4" v-for="(item,index) in 28" :key="item">
            <div class="img-bannel">
              <img :src="'http://data.linesno.com/icons/sepcialist/dataset_' + (1 + (index+1) * 1)+ '.png'" />
            </div>
            <div class="channel-info-box">
              <img :src="'http://data.linesno.com/icons/sepcialist/dataset_' + (10 + (index+1) * 1)+ '.png'" style="width: 30px;height: 30px;border-radius: 50%;margin-left: -40px;position: absolute;margin-top: 5px;" />
              <div class="title">需求讨论分析频道</div>
              <div class="title-desc">需求讨论分析服务</div>
            </div>
          </el-col>
        </el-row> 
      </div>
    </div>
  </el-scrollbar>
</template>

<style lang="scss" scoped>

.channel-panel-header{
  padding: 10px;
}

.channel-panel-body{

  .channel-info-box {
    margin: 10px;
    line-height: 1.5rem;
    padding-left: 45px;
  }

  .channel-item-box{
    margin-bottom: 10px;
  }

  .title {
    font-size: 15px;
    font-weight: bold;
  }

  .title-desc {
    font-size: 13px;
    color: #a5a5a5;
  }

  .img-bannel {
    padding: 10px;
    height: 130px;
    overflow: hidden;

    img {
      width:100%;
      border-radius: 5px;
    }
  }
}
</style>