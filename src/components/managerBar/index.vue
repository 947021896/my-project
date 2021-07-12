<template>
  <van-tabbar :active=" active " @change="onChange">
    <van-tabbar-item icon="home-o" @click="getActive">维修单</van-tabbar-item>
    <van-tabbar-item icon="contact" @click="getActive">维修员</van-tabbar-item>
    <van-tabbar-item icon="records" @click="getActive">物料</van-tabbar-item>
  </van-tabbar>
</template>

<script>
  export default {
    data () {
      return {
        active: 0
      }
    },
    methods: {
      onChange (event) {
        this.active = event.mp.detail
      },
      getActive () {
        this.$emit('isShow', this.active)
      }
    }
  }
</script>

<style lang="scss">

</style>
