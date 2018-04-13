<template>
  <div id="app" class="MyProfile">
    <div class="myProfile">
      <div class="userImg" @click="showActionAS"></div>
      <ActionSheet :showAs="show" @hideAs="hideAs">
          <div class="cameraModel">
              <p class="photograph">拍照</p>
              <p class="albumSelection">从手机相册选取<uploader class="albumSelection-upload" :after-read="onRead"></uploader></p>
              <p class="partingLine"></p>
              <p class="cancel" @click="hideAs">取消</p>
          </div>
      </ActionSheet>

      <div class="weui-cell">
          <div class="weui-cell__hd"><label class="weui-label"><span class="star">*</span>名字</label></div>
          <div class="weui-cell__bd">
              <input class="weui-input" type="text" v-model="formData.memberName" placeholder="请输入真实名字">
          </div>
      </div>

      <div class="weui-cell">
          <div class="weui-cell__hd"><label class="weui-label"><span class="star">*</span>性别</label></div>
          <div class="weui-cell__bd">
              <input class="weui-input" type="text" v-model="formData.sex" placeholder="女">
          </div>
          <div class="weui-cell__hd"><label class="weui-label"><span class="star">*</span>出生年月</label></div>
          <div class="weui-cell__bd">
              <input class="weui-input" v-model="formData.birthday" @click="bornTimeFun" type="text" placeholder="请选择出生年月">
          </div>
      </div>

      <div class="weui-cell" @click="showSelectArea">
          <div class="weui-cell__hd">
              <label class="weui-label">所在地区</label>
          </div>
          <div class="inline-block fontsize-24">
            <span class=" gray" v-if="!formData.area">请选择所在区域</span>
            <span class="fontsize-28" v-else>{{formData.area}}</span>
          </div>
      </div>

      <div class="weui-cell">
        <div class="weui-cell__hd">
          <label class="weui-label">详细地址:</label>
        </div>
        <div class="weui-cell__bd">
          <input v-model="formData.areaDetail" type="text" placeholder="具体接到或门牌号等" class="weui-input">
        </div>
      </div>

      <div class="weui-cell">
          <div class="weui-cell__hd">
            <label class="weui-label myShoeCode">我的鞋码<span class="notice">可多选</span></label>
          </div>
      </div>

      <div class="weui-media-box_appmsg">
        <div class="weui-media-box__bd">
          <div class="promotion-sku clear">
            <ul>
              <li v-for="(item,index) in footSizeList" :class="{active:formData.size.includes(item.size)}" @click="chooseSize(item)"><a href="javascript:;">{{item.size}}</a></li>
            </ul>
          </div>
        </div>
      </div>

      <div class="weui-cell">
          <div class="weui-cell__hd">
            <label class="weui-label myShoeCode">我的脚维<span class="notice">（单位：cm）</span></label>
          </div>
      </div>
      <div class="weui-media-box_appmsg">
        <div class="weui-media-box__bd">
          <div class="promotion-sku clear myHem">
            <ul>
              <li>
                <field v-model="formData.footSize" label="脚长" type="number"/>
              </li>
              <li>
                <field v-model="formData.hbw" label="脚宽" type="number"/>
              </li>
              <li>
                <field v-model="formData.qzw" label="前脚维" type="number"/>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="weui-cell">
          <div class="weui-cell__hd">
            <label class="weui-label myShoeCode"> <span class="star">*</span>您的月收入范围<span class="notice">（单位：元）</span></label>
          </div>
      </div>
      <div class="weui-media-box_appmsg">
        <div class="weui-media-box__bd">
          <div class="promotion-sku clear myIncome">
            <ul>
              <li v-for="(item,index) in inconmeList" :class="{active:formData.inconme==item.inconme}" @click="chooseInconme(item)"><a href="javascript:;">{{item.inconme}}</a></li>
            </ul>
          </div>
        </div>
      </div>

      <div class="weui-cell">
          <div class="weui-cell__hd">
            <label class="weui-label myShoeCode"> 注：<span class="star">*</span>为必填项</label>
          </div>
      </div>

      <div class="login-form">
        <button class="weui-btn login-btn common-div" @click="goMineCenter">完善信息领取优惠券</button>
      </div>

    </div>
    <ActionSheet :showAs="showAs" @hideAs="hideAs">
	    <datetime-picker
			  v-model="currentDate"
			  type="date"
			  :min-hour="minHour"
			  :max-hour="maxHour"
			  :min-date="minDate"
			  @confirm="timeConfirm"
			  @cancel = "timeCancel">
			</datetime-picker>
		</ActionSheet>

    <Popup v-model="showAreaSelect" position="bottom">
      <Area
        :area-list="areaList"
        @confirm="selectAreaConfirm"
        @cancel="showSelectArea"/>
    </Popup>

    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import { Icon, Field, Uploader, Button, DatetimePicker } from 'vant';
  import ActionSheet from "components/ActionSheet";
  import areaData from 'assets/js/area.js'
  import { Area, Popup } from 'vant';
  import QuickBar from "components/QuickBar";
  import { mapState } from "vuex";
  import { getUpdateMemberData } from 'api/member'
  export default {
    data() {
      return {
        headData: '我的信息',
        show: false,
        showAs: false,
        currentDate:　new Date(),
        minHour:　0,
        maxHour: 23,
        minDate: new Date(1990, 1, 1),
        areaList:areaData,
        showAreaSelect:false,
        formData:{
          memberNo:'25000105079',
          phone:'18200000000',

          area:"",
          areaDetail:"",

          memberId:"3850",
          memberName:"wqc",
          nickName:"wqc",
          sex:"1",
          birthday:"1993-12-11",
          size:[43],
          qzw:"1",
          hbw:"1",
          shopAddress:"1",
          footSize:"1",
          inconme:"5000-10000"
        },
        updateMemberData:{
          response_msg:"",
        },
        footSizeList:[
          {size:34},
          {size:35},
          {size:36},
          {size:37},
          {size:38},
          {size:39},
          {size:40},
          {size:41},
          {size:42},
          {size:43},
          {size:44},
        ],
        qparam:"前掌围",
        bparam:"脚背围",
        cparam:"脚长",
        inconmeList:[
          {inconme:"0-2000"},
          {inconme:"2000-5000"},
          {inconme:"5000-10000"},
          {inconme:"10000以上"},
        ]
      }
    },
    computed:{
      ...mapState({
        memberMsg:state => state.auth.memberMsg,
      })
    },
    created() {
      console.log(this.memberMsg);
      this.$store.dispatch('getMyMessage',this.formData);
    },
    methods:{/**
       * 检验表单是否填写完整
       */
      validateForm(){
        if(!this.formData.memberName){
          this.$store.dispatch('showMsgTips','请输入用户名')
          return false;
        }
        if(!this.formData.sex){
          this.$store.dispatch('showMsgTips','请输入性别')
          return false;
        }
        if(!this.formData.birthday){
          this.$store.dispatch('showMsgTips','请选择出生年月')
          return false;
        }
        if(!this.formData.inconme){
          this.$store.dispatch('showMsgTips','请选择收入范围')
          return false;
        }
        return true;
      },
      onRead(file) {
        console.log(file)
      },
      goMineCenter:function(){
        if(!this.validateForm()){//如果表单验证不通过，则返回
          return;
        }
        //this.$store.dispatch("login", true)
        this.getData();
      },
      showActionAS(){
        this.show = true
      },
      hideAs(){
        this.show = false
        this.showAs  = false;
      },
      // 点击选择日期
      bornTimeFun(){
      	this.showAs = true;
      },
      // 点击日期控件 确定事件
      timeConfirm(val){
      	var year = val.getFullYear()+"-",
      			month = val.getMonth()+1+ "-",
      			day = val.getDate();
      	this.formData.birthday = year + month + day;
      	this.showAs = false;
      },
      // 点击日期控件 取消事件
      timeCancel(){
      	this.showAs = false;
      },
      getData(){
        getUpdateMemberData(this.formData).then(res=>{
          if(res.response_code=="S000000"){
              this.$store.commit('showMsgTips',res.response_msg)
              this.$router.push({name:'Mine'})
            }
          }).catch(err=>{
            this.$store.commit('showMsgTips','请求失败，请重试！')
            this.$router.push({name:'Mine'})
          })


      },
      /**
       * 显示选择地址
       */
      showSelectArea(){
        this.showAreaSelect=!this.showAreaSelect;
      },
      selectAreaConfirm(val){
        let data = '';
        val.forEach(item=>{
          if(item.code!=-1){
            data+=(item.name+' ');
          }
        });
        if(data)
          this.profile.area = data;
          this.showSelectArea()
      },
      /**
       * 选择尺码
       * @param item
       */
      chooseSize(item){
          if(this.formData.size.includes(item.size)){
            this.formData.size.splice(this.formData.size.indexOf(item.size),1);
          }else{
            this.formData.size.push(item.size);
          }
          console.log(this.formData.size)
      },
      /**
       * 选择收入范围
       * @param item
       */
      chooseInconme(item){
          this.formData.inconme=item.inconme;
      },
    },
    components: {
      Icon,
      Field,
      Uploader,
      Button,
      ActionSheet,
      QuickBar, // 快速导航
      DatetimePicker,
      ActionSheet,
      Area,
      Popup
    }
  };
</script>

<style>
  .MyProfile,.cameraModel {
    background: #fff;
    font-size:0.24rem;
    color:#000;
  }

  .myProfile .userImg {
    width: 1.8rem;
    height: 1.8rem;
    margin: 0.3rem auto;
    background-image:url("../../assets/images/mine/img-mrtx.png");
    background-size: 100%;
    border-radius: 0.1rem;
  }
  .albumSelection-upload{
    width:100%;
    height:1rem;
    position:absolute;
    left:0;
  }
</style>
