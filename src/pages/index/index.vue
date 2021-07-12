<template>
  <div class="home_main">
    <div v-if="notLogin">
      <login @loginsuccess="loginSuccess"></login>
    </div>
    <div v-if="show">
      <div class="home_repair_logo" >
        <div class="home_repair_logo_div" @click="toRepair">
          <img src='/static/images/repair.png'
               class="home_repair_logo">
          <div class="home_repair_word">一键报修</div>
        </div>
      </div>
      <div>
        <div  class="repair_user_login"  @click="toUserLogin(1)">
          <van-icon name="manager-o" size="50px" color="#6495ED"/>
          <div class="repair_user_word">维修员</div>
        </div>
        <div class="manager_user_login"  @click="toUserLogin(2)">
          <van-icon name="manager-o" size="50px" color="#6495ED" />
          <div class="manager_user_word">管理员</div>
        </div>

      </div>
    </div>
  </div>

</template>

<script>
  import login from '../../components/login'
  export default {
    components: {
      login
    },
    data () {
      return {
        notLogin: true,
        // type=1, '维修员'
        // type=2, '管理员'
        type: 1,
        show: false
      }
    },
    mounted () {
      if (wx.getStorageSync('userinfo')) {
        this.notLogin = false
        this.show = true
        // console.log(wx.getStorageSync('userinfo'))
      } else {
        wx.hideTabBar()
      }
    },
    methods: {
      // 登录成功
      loginSuccess () {
        this.notLogin = false
        this.show = true
        wx.showTabBar()
      },
      // 界面跳转一键报修
      toRepair () {
        const url = '/pages/user_repair/main'
        wx.navigateTo({url: url})
        console.log('一键报修')
      },
      // 界面跳转管理员和维修员登录
      toUserLogin (type) {
        const url = '/pages/user_login/main?id=' + type
        wx.navigateTo({url: url})
        console.log('成功跳转登录页面,此时type:' + type + 'url:' + url)
      },
      // 界面跳转管理员和维修员登录
      toUserLogin1 () {
        const url = '/pages/user_login/main'
        wx.navigateTo({url: url})
      }
    }
  }
</script>

<style lang="scss">
  .home_repair_logo_div{
    height: 125px;
    width: 125px;
    border-radius: 50%;
    background-color: cornflowerblue;
    margin: 100px 125px;
    text-align: center;
  }
  .home_repair_logo{
    height: 125px;
    width: 125px;
  }
  .home_repair_word{
    margin-bottom: 100px;
  }
  .repair_user_login,.manager_user_login
  {
    display: inline-block;
    width: 50%;
    margin-top: 100px;
    text-align: center;
  }
  .repair_user_word,.manager_user_word{

  }
</style>
