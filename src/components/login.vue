<template>
  <div class="content">
    		<img src="/static/images/wx.png" class="weixin-logo">
    <div class="info">
      <div class="oauth-info1">申请获取以下权限</div>
      <div class="oauth-info2">获取你的公开信息(昵称、头像等)</div>
    </div>
    <div>
      <button class="oauth-button" open-type="getUserInfo" @getuserinfo="getUserInfo">授权登录</button>
    </div>
  </div>

</template>

<script>
  import config from '../config'
  export default {
    methods: {
      getUserInfo (e) {
        var _this = this
        console.log(e.target.userInfo)
        let currentUser = e.target.userInfo
        const loginUrl = config.loginUrl
        wx.login({
          success (res) {
            console.log(res)
            if (res.code) {
              // 发起网络请求
              wx.request({
                url: loginUrl,
                data: {
                  code: res.code
                },
                success (loginRes) {
                  console.log('loginRes', loginRes)
                  currentUser['openId'] = JSON.parse(loginRes.data.data)['openid']
                  wx.setStorageSync('session', JSON.parse(loginRes.data.data)['session_key'])
                  wx.setStorage({
                    key: 'userinfo',
                    data: currentUser
                  })
                  // console.log(JSON.parse(loginRes.data.message))
                  _this.$emit('loginsuccess')
                }
              })
            } else {
              console.log('登录失败！' + res.errMsg)
            }
          }
        })
        wx.showModal({
          title: '微信授权',
          content: '申请获取你的昵称、头像',
          cancelText: '拒绝',
          confirmText: '允许',
          confirmColor: '#39B54A',
          cancelColor: '#555555',
          success (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            } else if (res.cancel) {
              console.log('用户点击取消')
            }
          }
        })
      }
    }
  }
</script>

<style lang="scss">
  .content{
    background: #FFFFFF;
    position: fixed;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
  }
  .weixin-logo{
    height: 80px;
    width: 80px;
    margin-left: 40%;
    margin-top: 60px;
    margin-bottom: 30px;
  }
  .info{
    height: 100px;
    width: 80%;
    margin: 5px auto;
    border-top: 3px solid #EEEEEE;
  }
  .oauth-info1{
    margin-top: 40px;
    font-size: 18px;
  }
  .oauth-info2{
    margin-top: 15px;
    font-size: 18px;
    color: #777777;
  }
  .oauth-button{
    margin-top: 50px;
    width: 300px;
    background: #39B54A;
    color: #FFFFFF;
    border-radius: 30px;
  }
</style>
