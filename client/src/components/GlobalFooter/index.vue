<template>
  <global-footer class="footer custom-render">
    <template v-slot:links>
      <a href="https://github.com" target="_blank">Github</a>
    </template>
    <template v-slot:copyright>
      <a href="http://www.jackbin.cn" target="_blank">熊猫账本@2019~2021</a>
    </template>
  </global-footer>
</template>

<script>
import { GlobalFooter } from '@ant-design-vue/pro-layout'

export default {
  name: 'ProGlobalFooter',
  components: {
    GlobalFooter
  }
}
</script>
