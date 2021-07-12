<template>
  <div class="evaluate_main">
    <form @submit="formSubmit">
      <div class="evaluate_item" id="evaluate_title">服务评价:</div>
      <div class="evaluate_item" id="evaluate_rate">
        <van-rate size="25px" :value=" value " @change="onChange" />
      </div>
      <textarea placeholder="请留下你的宝贵意见" class="evaluate_item" id="evaluate_main_word"></textarea>
      <div class="evaluate_item" id="evaluate_submit">
        <van-button  class="submit" type="info" form-type="submit" size="large">
          提交
        </van-button>
      </div>
    </form>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        value: 0
      }
    },
    methods: {
      onChange (event) {
        this.value = event.detail
      },
      formSubmit () {

      }
    }
  }
</script>

<style scoped>
  .evaluate_main{
    width: 90%;
    font-size: 20px;
    margin:0 auto
  }
  .evaluate_item{
    margin: 20px 0
  }
  #evaluate_main_word{
    font-size:18px;
    border: 1px lightgrey solid;
    width: 98%;
    border-radius: 5px;
  }

  #evaluate_title{
    font-size: 23px;
  }

</style>
