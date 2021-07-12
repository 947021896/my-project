<template>
  <div>
    <div class="one_order_status">{{statusArray[index]}}</div>
    <div>
      <van-cell-group>
        <van-cell title="报修姓名：" :value="order.name" />
        <van-cell title="报修校区：" :value="order.addressName"/>
        <van-cell title="具体地点：" :value="order.addressContent"/>
        <van-cell title="联系电话：" :value="order.tel"/>
        <van-cell title="报修类型：" :value="order.repairCategoryName"/>
        <van-cell title="报修标题：" :value="order.title"/>
        <van-cell title="报修内容：" :value="order.content"/>
        <van-cell title="报修时间：" :value="order.time" :border="false"/>
      </van-cell-group>
  </div>
  </div>
</template>

<script>
  import config from '../../config'
  import * as dateUtils from '../../utils/date.js'
  export default {
    data () {
      return {
        statusArray: ['待审核', '处理中', '已受理'],
        index: 0,
        orderId: '',
        order: {
          id: '202105060001',
          orderId: '202105060001',
          name: '李京阳',
          tel: '17659654123',
          title: '宿舍灯泡不亮了',
          content: '宿舍卫生间灯泡坏了',
          address: '翡翠湖校区-1号楼南楼327',
          time: '2021-05-06 17:49',
          type: '电类',
          stateTag: ''
        }
      }
    },
    onLoad (option) {
      console.log(option)
      this.orderId = option.id
      console.log('orderId:' + this.orderId)
      wx.showLoading({
        title: '加载中'
      })
      if (this.order.stateTag === 0) {
        this.index = 0
      } else if (this.order.stateTag === 4 || this.order.stateTag === 5) {
        this.index = 2
      } else {
        this.index = 1
      }
      this.order.time = dateUtils.formatDate(this.order.time)
      var _this = this
      const url = config.userOneOrderUrl + '?orderId=' + this.orderId
      wx.request({
        url: url,
        success (res) {
          console.log(res.data.data)
          _this.order = res.data.data
        },
        complete (res) {
          wx.hideLoading()
        }
      })
    },
    mounted () {
      if (this.order.stateTag === 0) {
        this.index = 0
      } else if (this.order.stateTag === 4 || this.order.stateTag === 5) {
        this.index = 2
      } else {
        this.index = 1
      }
      this.order.time = dateUtils.formatDate(this.order.time)
      console.log(this.order.time)
    }
  }
</script>

<style lang="scss">
  .one_order_status{
    font-size: 25px;
    text-align: center;
    height: 55px;
    margin: 40px auto;
    color: green;
    margin-bottom: 0;
  }

  /*分割线*/
  .one_order_status::after{
    content:'';
    height: 1px;
    display: block;
    margin: 10px  auto;
    border-bottom: 0.5px solid darkgrey;
  }
  .one_order_name,.one_order_tel,.one_order_title,
  .one_order_stress,.one_order_type,.one_order_time{
    font-size: 18px;
    margin-left: 5px;
    margin-bottom: 3px;

  }
  .van-cell__value {
     min-width: 70%;
     span {
       display: inline-block;
       text-align: left;
       word-break: break-all;
     }
   }
</style>
