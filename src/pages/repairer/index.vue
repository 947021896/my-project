<template>
  <div>
    <div v-show="isHome">
      <div class="manager_home_first">
        <div class="manager_home_first_title">工单数量统计</div>
        <div class="manager_home_first_item">
          <span class="manager_pending">{{pending}}</span>
          <div class="manager_home_first_item_word">未处理</div>
        </div>
        <div class="manager_home_first_item">
          <span class="manager_solved">{{solved}}</span>
          <div class="manager_home_first_item_word">已完成</div>
        </div>
    </div>
      <div v-for="(item,index) in orderList" :key="index">
        <card :orderItem="item">
        </card>
      </div>
    </div>
    <form  v-show="isMaterial" @submit="formSubmit">
      <div class="repair_main" >
        <div class="repair_main_first">
          <div  class="repair_list">
            <span class="area_title">物料名称</span>
            <input id="user_name" class="area_main" type="text"
                   focus="true"  ></input>
          </div>
          <div  class="repair_list">
            <span class="area_title">数量</span>
            <input id="user_count" class="area_main" type="text"
                   focus="true" ></input>
          </div>
          <div  class="repair_list">
            <span class="area_title">详情</span>
            <input id="user_content" class="area_main" type="text" placeholder="情况说明"
                   focus="true" ></input>
          </div>
        </div>
        <div class="repair_main_button">
          <van-button type="primary" block @click="formSubmit">提交</van-button>
        </div>

      </div>
    </form>
    <div class="manager_home_bar">
      <repairerBar @isShow="isActive"></repairerBar>
    </div>
  </div>
</template>

<script>
  import repairerBar from '../../components/repairerBar/index'
  import card from '../../components/card'
  export default {
    components: {
      repairerBar,
      card
    },
    data () {
      return {
        isHome: true,
        isMaterial: false,
        active: 0,
        pending: 1, // 待（未）处理
        solved: 0, // 已完成
        orderList: [
          {
            id: '202105060001',
            orderId: '202105060001',
            name: '李京阳',
            tel: '17659654123',
            title: '宿舍灯泡不亮了',
            addressName: '翡翠湖校区',
            addressContent: '1号楼南楼327',
            time: '2021-05-06 17:49',
            repairCategoryName: '电类',
            stateTag: 2
          }
        ]
      }
    },
    methods: {
      isActive (data) {
        this.active = data
        if (data === 0) {
          this.isHome = true
          this.isMaterial = false
        } else if (data === 1) {
          this.isHome = false
          this.isMaterial = true
        }
      },
      formSubmit () {
        wx.showToast({
          title: '提交成功',
          icon: 'success',
          duration: 1000
        })
      }
    }
  }
</script>

<style lang="scss">
  .manager_home_main{
    color: black;
  }
  .manager_home_first{
    width: 90%;
    height: 90px;
    border-radius: 10px;
    -moz-box-shadow:2px 2px 10px #D3D3D3;
    -webkit-box-shadow:2px 2px 10px #D3D3D3;
    box-shadow:2px 2px 10px #D3D3D3;
    margin: 20px auto;
  }
  .manager_home_first_title{
    margin-left: 20px;
    font-size: 18px;
    margin-bottom: 10px;
  }
  .manager_home_first_item,.manager_repairer_first_item{
    display: inline-block;
    width:50%;
    margin:0  auto;
    text-align: center;
  }
  .manager_pending,.manager_solved{
    color:#ED9F50;
    font-size: 17px;
  }
  .manager_home_first_item_word,.manager_repairer_first_item_word{
    font-size :15px;
  }
  .manager_home_empty{
    height: 60px;
  }
  .repair_list {
    width: 98%;
    margin: 2px auto;
    background-color: white;
    padding: 5px;
  }
  .area_title {
    font-size: 18px;
    margin-left: 13px;
  }
  .repair_main{
    width:94%;
    margin: 20px auto;
    /*边框阴影*/
    -moz-box-shadow:2px 2px 10px #D3D3D3;
    -webkit-box-shadow:2px 2px 10px #D3D3D3;
    box-shadow:2px 2px 10px #D3D3D3;
  }
  .area_main {
    border: 0.5px solid grey;
    border-radius: 5px;
    width: 90%;
    margin: 10px auto;
    padding: 5px;
    font-size: 15px;
  }
  #user_content{
    height: 50px;
  }
  .repair_main_first{
    margin-bottom: 10px;
  }
  .repair_main_button{
    margin-bottom: 20px;
  }
</style>
