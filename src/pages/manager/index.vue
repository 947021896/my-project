<template>
  <div>
    <div class="manager_home_main" v-show=" isHome">
      <div class="manager_home_first">
        <div class="manager_home_first_title">工单数量统计</div>
        <div class="manager_home_first_item">
          <span class="manager_pending">{{pending}}</span>
          <div class="manager_home_first_item_word">未处理</div>
        </div>
        <div class="manager_home_first_item">
          <span class="manager_processing">{{processing}}</span>
          <div class="manager_home_first_item_word">已处理</div>
        </div>
        <div class="manager_home_first_item">
          <span class="manager_solved">{{solved}}</span>
          <div class="manager_home_first_item_word">已完成</div>
        </div>

      </div>
      <div v-for="(item,index) in orderList" :key="index">
        <ad-card :orderItem="item">
        </ad-card>
      </div>
      <div class="manager_home_empty"></div>

    </div>
    <div v-show="isRepairer">
      <div class="manager_home_first">
        <div class="manager_home_first_title">维修工统计</div>
        <div class="manager_repairer_first_item">
          <span class="manager_pending">{{manCount}}</span>
          <div class="manager_repairer_first_item_word">总人数</div>
        </div>
        <div class="manager_repairer_first_item">
          <span class="manager_pending">{{orderCount}}</span>
          <div class="manager_repairer_first_item_word">总单数</div>
        </div>
      </div>
      <van-collapse  accordion :value=" activeNames " @change="onChange">
        <div v-for="(item,index) in repairerList" :key="index">
          <van-collapse-item :name="index">
            <view slot="title">{{index+1}}. {{ item.name}}</view>
            <van-cell title="姓名：" :value="item.name" />
            <van-cell title="员工编号：" :value="item.repairerId" />
            <van-cell title="联系电话" :value="item.tel" />
            <!--            <van-cell title="负责订单数：" :value="item.orderCount" />-->
          </van-collapse-item>
        </div>
      </van-collapse>

    </div>
    <div v-show="isMaterial">
      <div class="material_first">
        <van-search class="search"
                    shape="round"
                    :value="value"
                    use-action-slot
                    @change="updateValue">
          <view slot="action" @click="search">搜索</view>
        </van-search>
        <button class="material_button">物料申请</button>
      </div>
      <div class="materialList" v-for="(item,index) in materialList" :key="index">
        <div >
          <div class="materialName">{{index+1}}. {{item.name}}</div>
          <div class="materialNumber">{{item.number}}</div>
          <div class="materialUnit">{{item.unitOfMeasurement}}</div>
          <div class="materialUpdate"><van-icon name="edit"  size="26px"  color="#6495ED"@click="updateMaterial"/></div>
        </div>
        <van-divider />
      </div>
        <van-panel class="panel" title="物料修改" v-if="panelShow">
          <view>
            <input class="panel_item" type="text" :value="name"  disabled="disabled" ></input>
            <input class="panel_item" type="text" :value="number">
            <input class="panel_item" type="text" :value="unit"  disabled="disabled">
          </view>
          <view slot="footer">
            <van-button size="small">确定</van-button>
            <van-button size="small" type="danger">取消</van-button>
          </view>
        </van-panel>



    </div>
    <div class="manager_home_bar">
      <managerBar @isShow="isActive"></managerBar>
    </div>
  </div>


</template>

<script>
  import config from '../../config'
  import managerBar from '../../components/managerBar/index'
  import adCard from '../../components/adCard/index'
  export default {
    components: {
      managerBar,
      adCard
    },
    data () {
      return {
        panelShow: false,
        name: '灯泡',
        number: 20,
        unit: '个',
        isUpdateShow: false,
        isHome: true,
        isRepairer: false,
        isMaterial: false,
        active: 0,
        pending: 3, // 待（未）处理
        processing: 1, // 处理中
        solved: 1, // 已完成
        orderList: [
          {
            id: '202105060001',
            orderId: '202105060001',
            name: '李京阳',
            tel: '17659654123',
            title: '宿舍灯泡不亮了',
            address: '翡翠湖校区-1号楼南楼327',
            time: '2021-05-06 17:49',
            type: '电类',
            stateTag: ''
          },
          {
            id: '11',
            orderId: '202105060002',
            name: '李亚欣',
            tel: '15623584411',
            address: '屯溪路校区-主教201',
            time: '2021-05-06 17:49',
            title: '教室第三排椅子坏了',
            type: '器械类',
            stateTag: ''
          },
          {
            id: '11',
            orderId: '11111',
            name: '张三',
            tel: '1320******',
            address: '',
            time: '',
            stateTag: ''
          },
          {
            id: '11',
            orderId: '11111',
            name: '张三',
            tel: '1320******',
            address: '',
            time: '',
            stateTag: ''
          }
        ],
        repairerList: [
          {
            repairerId: '2131',
            name: '陈师傅',
            tel: '176412458962',
            orderCount: 1
          },
          {
            repairerId: '44431',
            name: '李师傅',
            tel: '18756923485',
            orderCount: 0
          },
          {
            repairerId: '2131',
            name: '杨师傅',
            tel: '176412458962',
            orderCount: 0
          }
        ],
        activeNames: '1',
        manCount: 3,
        orderCount: 1,
        value: '',
        materialList: [
          {
            id: 1,
            name: '灯泡',
            number: 100,
            unitOfMeasurement: '个'
          },
          {
            id: 2,
            name: '水龙头',
            number: 50,
            unitOfMeasurement: '个'
          },
          {
            id: 3,
            name: '插座',
            number: 20,
            unitOfMeasurement: '个'
          },
          {
            id: 4,
            name: '地漏',
            number: 50,
            unitOfMeasurement: '个'
          },
          {
            id: 5,
            name: 'LED灯管',
            number: 70,
            unitOfMeasurement: '个'
          }
        ]
      }
    },
    mounted () {
      wx.showLoading({
        title: '加载中'
      })
      var _this = this
      wx.request({
        url: config.adOrderUrl,
        success (res) {
          console.log(res.data.data)
          _this.orderList = res.data.data.orders
          _this.pending = res.data.data.pending
          _this.solved = res.data.data.solved
          _this.processing = res.data.data.processing
        },
        complete (res) {
          wx.hideLoading()
        }
      })
      wx.request({
        url: config.adRepairersUrl,
        success (res) {
          console.log(res.data.data)
          _this.manCount = res.data.data.manCounts
          _this.orderCount = res.data.data.OrderCount
          _this.repairerList = res.data.data.repairers
        },
        complete (res) {
          wx.hideLoading()
        }
      })
    },
    methods: {
      isActive (data) {
        this.active = data
        if (data === 0) {
          this.isHome = true
          this.isRepairer = false
          this.isMaterial = false
        } else if (data === 1) {
          this.isHome = false
          this.isRepairer = true
          this.isMaterial = false
        } else if (data === 2) {
          this.isHome = false
          this.isRepairer = false
          this.isMaterial = true
        }
      },
      onChange (e) {
        this.activeNames = e.mp.detail
      },
      updateValue (e) {
        this.value = e.mp.detail
      },
      search () {
        console.log('this.value:', this.value)
      },
      updateMaterial () {
        this.panelShow = true
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
    width:33%;
    margin:0  auto;
    text-align: center;
  }
  .manager_pending,.manager_processing,.manager_solved{
    color:#ED9F50;
    font-size: 17px;
  }
  .manager_home_first_item_word,.manager_repairer_first_item_word{
    font-size :15px;
  }
  .manager_home_empty{
    height: 60px;
  }
  .manager_repairer_first_item{
    width:50%;
  }
  .materialName{
    display: inline-block;
  }
  .materialNumber{
    display: inline-block;
    margin-left: 2px;
  }
  .materialUnit{
    display: inline-block;
    margin-left: 2px;
  }
  .search {
    margin-top: 5px;
    width: 75%;
    display: inline-block;
  }
  .van-search {
    padding-right: 0;
    width: 70%;
    border-radius: 5px;
    margin: 10px;
  }
  .materialList {
    width: 90%;
    margin-left: 20px;
    margin-top: 10px;
  }
  .materialUpdate {
    display: inline-block;
    float: right
  }
  .panel {
    margin: 100px auto;
    border-radius: 10px;
  }
  .panel_item {
    align-content: center;
  }
  .van-search--withaction {
    width: 100%;
  }
  .material_button {
    display: inline-block;
    margin-right: 7.5px;
    float: right;
    margin-top: 28px;
    font-size: 13px;
    border-radius: 5px;
    line-height: 30px;
    background-color: #779de0;
    color: white;
    padding: 1px 5px;
  }
</style>
