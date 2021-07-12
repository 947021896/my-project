<template>
  <div class='admin_main_card'>
    <div class='admin_order_id'>
      工单 : {{orderItem.orderId}}
    </div>
    <div>
      <div class='admin_order_name'>报修姓名：{{orderItem.name}}</div>
      <div class='order_tel'>联系电话：{{orderItem.tel}}</div>
      <div class='order_stress'>报修地点：{{orderItem.addressName}}-{{orderItem.addressContent}}</div>
      <div class='order_type'>报修类型：{{orderItem.repairCategoryName}}</div>
      <div class='order_title'>报修标题：{{orderItem.title}}</div>
      <div class='order_time'>报修时间：{{orderItem.time}}</div>

      <!--      // TODO 按钮-->
    </div>
    <div class='order_button'  v-if=lookShow @click=goDetail(orderItem.orderId)>
      <van-button plain  type="info" size="mini" >查看</van-button>
    </div>
    <div class='order_button' v-if=sendShow @click=refresh()>
      <van-button plain  type="danger" size="mini" >审核派单</van-button>
    </div>
  </div>
</template>

<script>
  import config from '../../config'
  export default {
    props: {
      orderItem: Object
    },
    data () {
      return {
        commentShow: false,
        lookShow: true,
        sendShow: false
      }
    },
    onLoad () {
      if (this.orderItem.stateTag === 0) {
        this.sendShow = true
      }
      // eslint-disable-next-line no-use-before-define
      let date = new Date(this.orderItem.time)
      // 注意js里面的getMonth是从0开始的
      let FormattedDateTime =
        date.getFullYear() + '-' +
        (date.getMonth() + 1) + '-' +
        date.getDate() + ' ' +
        date.getHours() + ':' +
        date.getMinutes() + ':' +
        date.getSeconds()
      console.log(FormattedDateTime)
      // 2020-9-10 15:36:51
      this.orderItem.time = FormattedDateTime
    },
    methods: {
      goDetail (id) {
        const url = '/pages/specific_order/main?id=' + id
        wx.navigateTo({url: url})
        console.log('详情页面跳转成功')
      },
      goEvaluate (id) {
        const url = '/pages/evaluate/main?id=' + id
        wx.navigateTo({url: url})
      },
      refresh () {
        var _this = this
        wx.request({
          url: config.adSendUrl,
          data: {
            'orderId': _this.orderItem.orderId
          },
          header: {
            'content-type': 'application/json' // 默认值
          },
          success (res) {
            console.log(res.data.data)
            wx.showToast({
              title: '派单成功',
              icon: 'success',
              duration: 1000,
              complete: () => {
                setTimeout(() => {
                  console.log(res.data)
                }, 1000)
                // _this.onLoad()
                _this.sendShow = false
              }
            })
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
    }
  }
</script>

<style lang='scss'>
  .admin_main_card{
    width:90%;
    border-radius: 10px;
    margin:20px auto;
    border: 0.5px solid darkgrey;
    overflow: hidden;
    -moz-box-shadow:2px 2px 10px #D3D3D3;
    -webkit-box-shadow:2px 2px 10px #D3D3D3;
    box-shadow:2px 2px 10px #D3D3D3;
  }
  .admin_order_id{
    margin:10px;
  }
  .admin_order_name,.order_tel,.order_title,
  .order_stress,.order_type,.order_time{
    font-size: 16px;
    margin-left: 5px;
    margin-bottom: 5px;
  }
  /*分割线*/
  .order_id::after{
    content:'';
    height: 1px;
    display: block;
    margin: 0  auto;
    border-bottom: 0.5px solid darkgrey;
  }
  /*按钮*/
  .order_button{
    display: inline-block;
    margin-right: 20px;
    float: right;
    margin-bottom: 13px;
  }
</style>
