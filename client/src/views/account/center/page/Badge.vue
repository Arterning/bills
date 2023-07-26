<template>
  <div class="app-list">
    <a-list
      :grid="{ gutter: 36, column: 3 }"
      :dataSource="dataSource">
      <a-list-item slot="renderItem" slot-scope="item">
        <a-card hoverable size="small" >
          <img :src="item.badgeUrl"/>
          <a-card-meta :title="item.title" :description="item.description"/>
        </a-card>
      </a-list-item>
    </a-list>

  </div>
</template>

<script>
const dataSource = []
for (let i = 0; i < 3; i++) {
  dataSource.push({
    title: 'github超级开发者',
    badgeUrl: '/badges/badge1.svg',
    description: 'github超级开发者'
  })
}

export default {
  name: 'Badge',
  components: {},
  data () {
    return {
      dataSource
    }
  }
}
</script>
