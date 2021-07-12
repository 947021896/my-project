<template>
  <div>
    <van-tabs id="tabs" sticky color="cornflowerblue"   v-model="active">
      <van-tab  title="全部">
        <div v-for="(item,index) in orderList3" :key="index">
          <card :orderItem="item">
          </card>
        </div>
      </van-tab>
      <van-tab title="待确认" >
        <div v-for="(item,index) in orderList0" :key="index">
        <card :orderItem="item"></card>
      </div>
      </van-tab>
      <van-tab title="维修中" >
        <div v-for="(item,index) in orderList1" :key="index">
          <card :orderItem="item"></card>
        </div>
      </van-tab>
      <van-tab title="已完成">
        <div v-for="(item,index) in orderList2" :key="index">
          <card :orderItem="item"></card>
        </div>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
  import config from '../../config'
  import card from '../../components/card'
  export default {
    components: {
      card
    },
    data () {
      return {
        userId: '',
        active: 3,
        orderList0: [
          {
            id: '202105060001',
            orderId: '202105060001',
            name: '李京阳',
            tel: '17659654123',
            title: '宿舍灯泡不亮了',
            addressId: '翡翠湖校区-1号楼南楼327',
            time: '2021-05-06 17:49',
            repairCategoryId: '电类',
            stateTag: '0'
          },
          {
            id: '11',
            orderId: '202105060002',
            name: '李亚欣',
            tel: '15623584411',
            addressId: '屯溪路校区-主教201',
            time: '2021-05-06 17:49',
            title: '教室第三排椅子坏了',
            repairCategoryId: '器械类',
            stateTag: '4'
          }
        ],
        orderList1: [],
        orderList2: [],
        orderList3: []
      }
    },
    methods: {
    },
    onLoad (option) {
      console.log(option)
      this.active = option.id
    },
    mounted () {
      console.log(this.active)
      wx.showLoading({
        title: '加载中'
      })
      if (wx.getStorageSync('userinfo')) {
        this.userId = wx.getStorageSync('userinfo').openId
      }
      const url0 = config.userOrderUrl + '?userId=' + this.userId + '&state=' + 0
      const _this = this
      console.log(url0)
      wx.request({
        url: url0,
        success (res) {
          console.log(res.data.data)
          _this.orderList0 = res.data.data
        }
      })
      const url1 = config.userOrderUrl + '?userId=' + this.userId + '&state=' + 1
      wx.request({
        url: url1,
        success (res) {
          console.log(res.data.data)
          _this.orderList1 = res.data.data
        }
      })
      const url2 = config.userOrderUrl + '?userId=' + this.userId + '&state=' + 2
      wx.request({
        url: url2,
        success (res) {
          console.log(res.data.data)
          _this.orderList2 = res.data.data
        }
      })
      const url3 = config.userOrderUrl + '?userId=' + this.userId + '&state=' + 3
      wx.request({
        url: url3,
        success (res) {
          console.log(res.data.data)
          _this.orderList3 = res.data.data
        },
        complete (res) {
          wx.hideLoading()
        }
      })
    }
  }
</script>

<style lang="scss">

</style>
