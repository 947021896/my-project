<template>
  <form @submit="formSubmit">
  <div class="repair">
    <div class="repair_main" >
        <div class="repair_list" id="area" >
          <span class="area_title">区域/楼宇</span>
          <div class="area_main">
            <div class="area_list" id="area_list_one">
              <span class="picker_title" >区域：</span>
              <picker mode="selector" id="area_select"  class="picker" @change="bindAreaChange" :value="index" :range="array">
                {{array[index]}}
              </picker>
            </div>
          </div>
        </div>
        <div  class="repair_list">
          <span class="area_title">维修地点</span>
          <input id="area_detail" class="area_main" type="text" placeholder="必填"
                 focus="true" :value="addressContent" @change="changeAddress"></input>
        </div>
        <div  class="repair_list">
          <span class="area_title">维修类型</span>
          <div class="area_main">
            <picker mode="selector"  @change="bindChange" :value="index2" :range="array2">
              {{array2[index2]}}
            </picker>
          </div>
        </div>
      <div  class="repair_list">
        <span class="area_title">报修人姓名</span>
        <input id="user_name" class="area_main" type="text" placeholder="必填"
               focus="true" :value="name" @change="changeName"></input>
      </div>
      <div  class="repair_list">
        <span class="area_title">联系电话</span>
        <input id="user_tel" class="area_main" type="text" placeholder="必填"
               focus="true" :value="tel" @change="changeTel"></input>
      </div>
        <div  class="repair_list">
          <span class="area_title">标题</span>
            <input id="catalog" class="area_main"  type = "text" placeholder="必填,20字以内"
                   focus="true" :value="title" @change="changeTitle"></input>
        </div>
        <div  class="repair_list">
          <span class="area_title">内容</span>
            <textarea  id="content" class="area_main"  placeholder="必填,50字以内"
                       focus="true" :value="content" @change="changeContent"></textarea>
        </div>
        <div class="repair_list">
          <div class="area_title">现场照片</div>
          <div class="area_main">
            <van-uploader :file-list=" photo " @after-read="afterRead" />
          </div>
        </div>

        <div class="repair_submit">
          <van-button  class="submit" type="info" form-type="submit" size="large">提交</van-button>
        </div>


    </div>
  </div>
  </form>
</template>

<script>
  import config from '../../config'
  export default {
    components: {

    },
    data () {
      return {
        array: ['请选择校区', '美国', '中国', '巴西', '日本'],
        objectArray: [
          {
            id: 0,
            name: '请选择校区'
          },
          {
            id: 1,
            name: '美国'
          },
          {
            id: 2,
            name: '中国'
          },
          {
            id: 3,
            name: '巴西'
          },
          {
            id: 4,
            name: '日本'
          }
        ],
        index: 0,
        array2: ['请选择类型', '电类', '水类', '其他'],
        Array2: [
          {
            categoryId: 0,
            name: '请选择类型'
          },
          {
            categoryId: 1,
            name: '电类'
          },
          {
            categoryId: 2,
            name: '水类'
          },
          {
            categoryId: 3,
            name: '其他'
          }
        ],
        index2: 0,
        photo: [],
        addressContent: '',
        title: '',
        content: '',
        userInfo: '',
        name: '',
        tel: ''
      }
    },

    mounted () {
      if (wx.getStorageSync('userinfo')) {
        this.userInfo = wx.getStorageSync('userinfo')
        console.log('进行报修')
      } else {
        console.log('异常登录进入')
      }
    },
    methods: {
      returnFirst () {
        this.index = 0
        this.index2 = 0
        this.addressContent = ''
        this.title = ''
        this.content = ''
        this.name = ''
        this.tel = ''
        this.photo = []
      },
      bindAreaChange (e) { this.index = e.target.value },
      bindChange (e) { this.index2 = e.target.value },
      changeAddress (e) { this.addressContent = e.target.value },
      changeTitle (e) { this.title = e.target.value },
      changeContent (e) { this.content = e.target.value },
      changeName (e) { this.name = e.target.value },
      changeTel (e) { this.tel = e.target.value },
      formSubmit (e) {
        var _this = this
        const repairUrl = config.repairUrl
        console.log('以点击')
        if (this.index2 === 0 || this.index === 0) {
          wx.showToast({
            title: '内容填写不完整',
            icon: 'error',
            duration: 1000
          }
          )
        } else {
          wx.request({
            url: repairUrl,
            data: {
              'addressId': this.index,
              'addressContent': this.addressContent,
              'repairCategoryId': this.index2,
              'title': this.title,
              'content': this.content,
              'userId': this.userInfo.openId,
              'name': this.name,
              'tel': this.tel
            },
            method: 'POST',
            header: {
              'content-type': 'application/json',
              'Cookie': 'SESSION=' + wx.getStorageSync('session')
            }, // 设置请求的 header
            success (res) {
              console.log('提交任务成功')
              console.log(res)
              wx.showToast({
                title: '提交成功',
                icon: 'success',
                duration: 2000,
                complete: () => {
                  setTimeout(() => {
                    _this.returnFirst()
                  }, 2000)
                }
              })
            },
            fail (res) {
              console.log('数据提交失败')
            }
          })
        }
      },
      afterRead (event) {
        const { file } = event.detail
        // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
        wx.uploadFile({
          url: 'https://img.yzcdn.cn/vant/leaf.jpg', // 仅为示例，非真实的接口地址
          filePath: file.url,
          name: 'file',
          formData: { user: 'test' },
          success (res) {
            // 上传完成需要更新 fileList
            const { file = [] } = this.data
            file.push({ ...file, url: res.data })
            this.setData({file})
          }
        })
      }

    },
    onLoad () {
      wx.showLoading({
        title: '加载中'
      })
      setTimeout(function () { wx.hideLoading() }, 2000)
      var _this = this
      const url = config.getAddAndCateUrl
      wx.request({
        url: url,
        data: {
        },
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success (res) {
          console.log(res.data.data)
          const myData = res.data.data
          _this.array = myData.list3
          _this.objectArray = myData.list1
          _this.array2 = myData.list4
          _this.Array2 = myData.list2
        },
        fail (res) {
          console.log('数据请求失败')
        }
      })
    }
  }
</script>

<style lang="scss">
  .repair{
    width: 100%;
    height: 100%;
    background-color: white;
    color: black;
    font-size:17px;
    position: absolute;
  }
  .repair_main{
    width:94%;
    margin: 0 auto;
    /*边框阴影*/
    -moz-box-shadow:2px 2px 10px #D3D3D3;
    -webkit-box-shadow:2px 2px 10px #D3D3D3;
    box-shadow:2px 2px 10px #D3D3D3;
  }
  .repair_list {
    width: 98%;
    margin: 2px auto;
    background-color: white;
    padding: 5px;
  }
  .area_main {
    border: 0.5px solid grey;
    border-radius: 5px;
    width: 90%;
    margin: 0 auto;
    padding: 5px;
  }
    .picker {
      display: inline-block;
    }

    .area_title {
      font-size: 18px;
      margin-left: 13px;
    }

    .area_list {
      margin: 5px auto;
    }

    #content{
      height: 80px;
    }
  .repair_submit{
    width: 90%;
    margin: 30px auto;
  }

</style>
