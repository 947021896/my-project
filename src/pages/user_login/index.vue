<template>
  <div class="user_login_main">
    <van-cell-group>
      <van-field
        :value=" username "
        label="用户名"
        placeholder="请输入用户名"
        required
        :border=" false "
        @change="onUserChange"
      />
    <van-field
      :value=" password "
      type="password"
      label="密码"
      placeholder="请输入密码"
      required
      :border=" false "
      @change="onPwdChange"
    />
    </van-cell-group>
    <van-button type="primary" block @click="toLogin">登录</van-button>
  </div>
</template>

<script>
  import config from '../../config'
  export default {
    data () {
      return {
        type: '',
        username: '',
        password: ''
      }
    },
    mounted () {
      // console.log(this.root.mp.query)
    },
    methods: {
      toLogin () {
        const loginUrl = config.adLoginUrl
        const loginUrl1 = config.reLoginUrl
        const url = '/pages/manager/main?id=' + this.type
        const url1 = '/pages/repairer/main?id=' + this.type
        console.log('this.type===1?', this.type === '1')
        console.log(this.username, this.password)
        if (this.username === null || this.password === null) {
          wx.showToast({
            title: '用户名或密码不能为空',
            icon: 'error',
            duration: 1000
          })
        }
        if (this.type === '2') {
          wx.request({
            url: loginUrl,
            data: {
              'name': this.username,
              'password': this.password
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success (res) {
              console.log(res.data.status)
              if (res.data.status !== 200) {
                wx.showToast({
                  title: '用户名或密码错误',
                  icon: 'error',
                  duration: 1000,
                  complete: () => {
                    setTimeout(() => {
                      console.log(res.data)
                    }, 1000)
                  }
                })
              } else {
                wx.showToast({
                  title: '登录成功',
                  icon: 'success',
                  duration: 1000,
                  complete: () => {
                    setTimeout(() => {
                      console.log(res.data)
                      wx.navigateTo({url: url})
                    }, 1000)
                  }
                })
              }
            },
            fail (res) {
              wx.showToast({
                title: '后端异常',
                icon: 'error',
                duration: 2000
              })
            }
          })
        }
        if (this.type === '1') {
          wx.request({
            url: loginUrl1,
            data: {
              'name': this.username,
              'password': this.password
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success (res) {
              console.log(res.data.status)
              if (res.data.status !== 200) {
                wx.showToast({
                  title: '用户名或密码错误',
                  icon: 'error',
                  duration: 1000,
                  complete: () => {
                    setTimeout(() => {
                      console.log(res.data)
                    }, 1000)
                  }
                })
              } else {
                wx.showToast({
                  title: '登录成功',
                  icon: 'success',
                  duration: 1000,
                  complete: () => {
                    setTimeout(() => {
                      console.log(res.data)
                      wx.navigateTo({url: url1})
                    }, 1000)
                  }
                })
              }
            },
            fail (res) {
              wx.showToast({
                title: '后端异常',
                icon: 'error',
                duration: 2000
              })
            }
          })
        }
      },
      onUserChange (event) {
        this.username = event.mp.detail
      },
      onPwdChange (event) {
        this.password = event.mp.detail
      }
    },
    onLoad (option) {
      console.log(option)
      this.type = option.id
      console.log('type:' + this.type)
    },
    onShow () {
      this.username = ''
      this.password = ''
    }
  }
</script>

<style lang="scss">
  .user_login_main{
    width:90%;
    margin:0 auto
  }

</style>
