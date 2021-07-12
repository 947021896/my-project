<template>
    <div class="all">
      <div class="userInfo">
        <div class="avatarUrl">
          <img :src="userInfo.avatarUrl" alt="">
        </div>
        <div class="nickname">
          <label>{{userInfo.nickName}}</label>
        </div>
      </div>
      <!--user_order-->
      <div class="order">
        <span class="title">我的订单</span>
        <div class="all_order_button">
          <van-button plain round type="info" size="mini" @click="toOrder(3)">全部报修</van-button>
        </div>
        <ul class="order_main">
          <li class="order_status">
              <div class="status_icon" @click="toOrder(0)">
                <van-icon name="todo-list-o" size="26px" color="#E8EEEB" class="van_status_icon"/>
              </div>
              待确认
          </li>
          <li class="order_status">
          <div class="status_icon" @click="toOrder(1)">
              <van-icon name="records" size="26px" color="#E8EEEB" class="van_status_icon" />
            </div>维修中
          </li>
          <li class="order_status">
            <div class="status_icon" @click="toOrder(2)">
              <van-icon name="completed" size="26px" color="#E8EEEB" class="van_status_icon"/>
            </div>已完成
          </li>
<!--          <li class="order_status">-->
<!--            <div class="status_icon" @click="toOrder(4)">-->
<!--              <van-icon name="failure" size="26px" color="#E8EEEB" class="van_status_icon"/>-->
<!--            </div>已取消-->
<!--          </li>-->
        </ul>
      </div>
      <!-- user_center_card  -->
      <div class="user_center_card" >
        <van-cell-group>
          <van-cell class="user_center_card_list" size="small"
            is-link
            title="意见反馈"
            link-type="navigateTo"
            url="/pages/orderStatus/main"
          />
          <van-cell class="user_center_card_list" size="small"
                    is-link
                    title="关于我们"
                    link-type="navigateTo"
                    url="/pages/orderStatus/main"
                    :border=" false "
          />
        </van-cell-group>
      </div>
<!--      <van-button type="danger" size="large" class="logout">退出登录</van-button>-->
    </div>
</template>

<script>
   export default {
     components: {
     },
     data () {
       return {
         userInfo: {
           nickName: '',
           avatarUrl: ''
         }
       }
     },
     mounted () {
       if (wx.getStorageSync('userinfo')) {
         this.userInfo = wx.getStorageSync('userinfo')
         console.log('进入用户中心')
       } else {
         console.log('异常登录进入')
       }
     },
     methods: {
       logout () {
         wx.removeStorage({
           key: 'userinfo',
           success (res) {
             console.log(res)
             wx.hideTabBar()
             wx.showToast({
               title: '退出登录',
               icon: 'success',
               duration: 2000
             })
           }
         })
       },
       // 界面跳转
       toOrder (id) {
         const url = '/pages/user_order/main?id=' + id
         wx.navigateTo({url: url})
       }

     }
   }
</script>

<style lang="scss">
  .userInfo {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    background: cornflowerblue;
    width: 100%;
    height: 150px
  }
  .nickname label{
    color: #fff;
    font-weight:bold;

  }
  .avatarUrl img{
    border-radius: 50%;
    width:70px;
    height:70px;
    margin: 15px;
  }
  .all{
    background-color: lightgrey;
    height: 100%;
    width: 100%;
    position: absolute;
  }
  /*myOrder*/
  .order{
    width:92%;
    height: 100px;
    background: white;
    margin: -20px auto;
    position: relative;
    border-radius: 10px;
    font-size: 17px;
  }
  .title{
    text-align: left;
    margin:10px;
    color:black;
  }
  .order_main{
    color:black;
    margin:10px auto
  }
  .all_order_button{
    display: inline-block;
    margin-right: 20px;
    float: right;
    margin-top: 2px;
  }
  .ul{
    list-style: none;
    margin:0;
    padding:0;
  }
  .order_status{
    font-size: 15px;
    width:33%;
    margin: 5px auto;
    display: inline-block;
    text-align: center;
  }
  .status_icon{
    border-radius: 50%;
    width:35px;
    height:35px;
    margin:5px auto;
    background-color: cornflowerblue;
    text-align: center;
  }
  .van_status_icon{
    margin: 1px 0 0 0
  }
  a{
    text-decoration: none;
  }
  .logout{
    width:98%
  }
  /*订单下的部分*/
  .user_center_card{
    width: 93%;
    background-color: white;
    color: black;
    position: relative;
    margin:30px auto;
    font-size: 17px;
  }
  .user_center_card_list {
    width: 93%;
    height: 50px;
    margin: 5px 0;
    line-height: 50px;
  }
</style>
