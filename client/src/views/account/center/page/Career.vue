<template>
  <div>
    <a-timeline pending="Keep moving..." :reverse="reverse">
      <a-timeline-item>学习后端开发 2017-05-20</a-timeline-item>
      <a-timeline-item>学习spark开发 2019-07-03</a-timeline-item>
      <a-timeline-item>学习前端开发 2022-11-15</a-timeline-item>
      <a-timeline-item>学习区块链开发 2023-01-01</a-timeline-item>
    </a-timeline>
    <a-button type="primary" style="margin-top: 16px" @click="handleClick">
      反转时间
    </a-button>
  </div>
</template>

<script>
import { Timeline } from 'ant-design-vue'

export default {
  name: 'Career',
  components: {
    ATimeline: Timeline,
    ATimelineItem: Timeline.Item
  },
  data () {
    return {
      reverse: false
    }
  },
  mounted () {
  },
  methods: {
    handleClick () {
      this.reverse = !this.reverse
      this.$message.warn('我是时间轴啦~')
    }
  }
}
</script>

<style lang="less" scoped>
  .ant-pro-pages-account-projects-cardList {
    margin-top: 24px;

    /deep/ .ant-card-meta-title {
      margin-bottom: 4px;
    }

    /deep/ .ant-card-meta-description {
      height: 44px;
      overflow: hidden;
      line-height: 22px;
    }

    .cardItemContent {
      display: flex;
      height: 20px;
      margin-top: 16px;
      margin-bottom: -4px;
      line-height: 20px;

      > span {
        flex: 1 1;
        color: rgba(0,0,0,.45);
        font-size: 12px;
      }

      /deep/ .ant-pro-avatar-list {
        flex: 0 1 auto;
      }
    }
  }
</style>
