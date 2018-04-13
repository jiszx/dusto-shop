<template>
  <div class="page-cloak-room">
    <div class="divBottom">
      <div class="ggskin_button wish_list">
        <img src="../../assets/images/cloakroom/icon-xinyuand-b.png" alt="" />
        <p>心愿单</p>
      </div>
      <div class="ggskin_button recommend">
        <img src="../../assets/images/cloakroom/icon-tuijian-b.png" alt="" />
        <p>推荐</p>
      </div>
      <div class="ggskin_button details">
        <img src="../../assets/images/cloakroom/icon-xq-b.png" alt="" />
        <p>详情</p>
      </div>
      <div class="ggskin_button classification" @click="moreBtnShow=!moreBtnShow">
        <img src="../../assets/images/cloakroom/icon-fenlei-b.png" alt="" />
        <p>分类</p>
      </div>

      <div class="ggskin_button-more fontsize-28" :class="{active:moreBtnShow}">
        <ul>
          <li v-for="(item,index) in btnList.list">
            <span class="block" :class="{red:btnList.active===item.index}" @click="clickButton(btnList,item.index)">{{item.text}}</span>
            <ul v-show="btnList.active===item.index">
              <li v-for="(item_child, index) in item.list">
                <span class="block" :class="{red:item_child.index===item.active}"  @click="clickButton(item,item_child.index)">{{item_child.text}}</span>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="btn1 bounceant1"></div> <!-- 会员信息 -->
    </div>
    <div class="looks-show-wrapper" :class="{active:showLooks}" @click="showLooksFn">
      <div class="looks-show">
        <img src="../../assets/images/Looks.png" alt="">
      </div>
    </div>
    <div class="page-cloal-room" ref="pageCloalRoom">

      <div id="pano">
        <!-- 正方体的六个面 -->
        <div id="bg_section_0" class="bg_section bg_section_4 scale_test">
          <img class="bg" src="../../assets/images/cloakroom/panorama.right.jpg" alt="">
          <div class="btn3 bounceant1"></div> <!-- 商城入口 -->
        </div>
        <div id="bg_section_1" class="bg_section bg_section_5 scale_test">
          <img class="bg" src="../../assets/images/cloakroom/panorama.left.jpg" alt="">
          <div class="btn1 bounceant1"></div> <!-- 会员信息 -->
          <div class="btn2 bounceant1"></div> <!-- 杂志 -->
          <div class="shoe1"></div> <!-- 鞋1 -->
        </div>
        <div id="bg_section_2" class="bg_section bg_section_2 scale_test">
          <img class="bg" src="../../assets/images/cloakroom/panorama.top.jpg" alt="">
        </div>
        <div id="bg_section_3" class="bg_section bg_section_3 scale_test">
          <img class="bg" src="../../assets/images/cloakroom/panorama.bottom.jpg" alt="">
        </div>
        <div id="bg_section_4" class="bg_section bg_section_1 scale_test">
          <img class="bg" src="../../assets/images/cloakroom/panorama.front.jpg" alt="">
        </div>
        <div id="bg_section_5" class="bg_section bg_section_0 scale_test">
          <img class="bg" src="../../assets/images/cloakroom/panorama.back.jpg" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script scoped>
  export default {
    data(){
      return {
        showLooks:false,
        moreBtnShow:false,
        btnList: {
          active: 2,
          list: [{
            text: '高跟',
            index:0,
            active: 0,
            list: [{
              text: '皮具',
              index: 0
            }, {
              text: '皮具',
              index: 1
            }, {
              text: '皮具',
              index: 2
            }, {
              text: '皮具',
              index: 3
            }],
          }, {
            text: '季节',
            index:1,
            active: 0,
            list: [{
              text: '皮具',
              index: 0
            }, {
              text: '皮具',
              index: 1
            }, {
              text: '皮具',
              index: 2
            }, {
              text: '皮具',
              index: 3
            }],
          }, {
            text: '大类',
            index:2,
            active: 0,
            list: [{
              text: '皮具',
              index: 0
            }, {
              text: '皮具',
              index: 1
            }, {
              text: '皮具',
              index: 2
            }, {
              text: '皮具',
              index: 3
            }],
          }],
        }
      }
    },
    created(){
      if(!window.THREE){//如果当前页面的three.js没有加载完成就返回到首页
        this.$router.push({path:'/'});
        return;
      }
    },
    mounted(){
      var _this = this;
      var pageCloalRoomDom = this.$refs.pageCloalRoom;
      var camera, scene, renderer;
      var geometry, material, mesh;
      var target = new THREE.Vector3();
      var deviceControl;
      var lon = 90, lat = 0;
      var phi = 0, theta = 0;

      var touchX, touchY;

      init();
      requestAnimationFrame(animate);

      function init() {
        /**
         * 添加相机
         * @type {THREE.PerspectiveCamera}
         */
        camera = new THREE.PerspectiveCamera(
          75, // 相机视角的夹角
          window.innerWidth / window.innerHeight,  // 相机画幅比
          1, // 最近焦距
          1000 // 最远焦距
        );

        /**
         * 创建场景
         * @type {THREE.Scene}
         */
        scene = new THREE.Scene();

        /**
         *正方体的6个面的资源及相关（坐标、旋转等）设置
         */
        var flipAngle = Math.PI, // 180度
          rightAngle = flipAngle / 2, // 90度
          tileWidth = 512;
        var sides = [{
          url: "images/panorama.right.jpg", //right
          position: [-tileWidth, 0, 0],
          rotation: [0, rightAngle, 0]
        }, {
          url: "images/panorama.left.jpg", //left
          position: [tileWidth, 0, 0],
          rotation: [0, -rightAngle, 0]
        }, {
          url: "images/panorama.top.jpg", //top
          position: [0, tileWidth, 0],
          rotation: [rightAngle, 0, Math.PI]
        }, {
          url: "images/panorama.bottom.jpg", //bottom
          position: [0, -tileWidth, 0],
          rotation: [-rightAngle, 0, Math.PI]
        }, {
          url: "images/panorama.front.jpg", //front
          position: [0, 0, tileWidth],
          rotation: [0, Math.PI, 0]
        }, {
          url: "images/panorama.back.jpg", //back
          position: [0, 0, -tileWidth],
          rotation: [0, 0, 0]
        }];

        for ( var i = 0; i < sides.length; i ++ ) {
          var side = sides[ i ];
          var element = document.getElementById("bg_section_"+i);
          element.width = 1026;
          element.height = 1026; // 2 pixels extra to close the gap.
          // 添加一个渲染器
          var object = new THREE.CSS3DObject( element );
          object.position.fromArray( side.position );
          object.rotation.fromArray( side.rotation );
          scene.add( object );

        }

        renderer = new THREE.CSS3DRenderer(); // 定义渲染器
        renderer.setSize( window.innerWidth, window.innerHeight ); // 定义尺寸
        pageCloalRoomDom.appendChild( renderer.domElement ); // 将场景到加入页面中

        initDevices();
        initMouseControl();

      }

      // 初始化控制器
      function initMouseControl() {
        // mouseControl = new THREE.OrbitControls(camera);
        pageCloalRoomDom.addEventListener( 'mousedown', onDocumentMouseDown, false );
        pageCloalRoomDom.addEventListener( 'wheel', onDocumentMouseWheel, false );
        pageCloalRoomDom.addEventListener( 'touchstart', onDocumentTouchStart, false );
        pageCloalRoomDom.addEventListener( 'touchmove', onDocumentTouchMove, false );
        pageCloalRoomDom.addEventListener( 'resize', onWindowResize, false );

      }

      /*var controlsBtn= document.getElementById("controlBtn");*/ // 控制陀螺仪开关的按钮
      var isDeviceing = false; // 陀螺仪状态
      /*controlsBtn.addEventListener("touchend", controlDevice, true);*/
      isDeviceing == true ? $("#controlBtn").addClass("controlIconae") : $("#controlBtn").addClass("controlIcon");
      // 初始化陀螺仪
      function initDevices() {
        deviceControl = new THREE.DeviceOrientationControls(camera);
      }
      /* 控制陀螺仪 */
      function controlDevice(event) {
        if (isDeviceing == true) {
          isDeviceing = false;
          //关闭陀螺仪
          $("#controlBtn").removeClass("controlIcon").addClass("controlIconae");
        } else {
          isDeviceing = true;
          //开启陀螺仪
          $("#controlBtn").removeClass("controlIconae").addClass("controlIcon");
        }
      }

      /**
       * 窗体大小改变
       */
      function onWindowResize() {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();
        renderer.setSize( window.innerWidth, window.innerHeight );
      }

      /*
          相机焦点跟着鼠标或手指的操作移动
           */
      function onDocumentMouseDown( event ) {
        event.preventDefault();
        pageCloalRoomDom.addEventListener( 'mousemove', onDocumentMouseMove, false );
        pageCloalRoomDom.addEventListener( 'mouseup', onDocumentMouseUp, false );
        animate()

      }

      function onDocumentMouseMove( event ) {
        var movementX = event.movementX || event.mozMovementX || event.webkitMovementX || 0;
        var movementY = event.movementY || event.mozMovementY || event.webkitMovementY || 0;
        lon -= movementX * 0.1;
        lat += movementY * 0.1;
        animate()
      }

      function onDocumentMouseUp( event ) {
        pageCloalRoomDom.removeEventListener( 'mousemove', onDocumentMouseMove );
        pageCloalRoomDom.removeEventListener( 'mouseup', onDocumentMouseUp );
        animate()
      }

      /**
       * 鼠标滚轮改变相机焦距
       */
      function onDocumentMouseWheel( event ) {
        camera.fov += event.deltaY * 0.05;
        camera.updateProjectionMatrix();
        animate()
      }

      function onDocumentTouchStart( event ) {
        event.preventDefault();
        var touch = event.touches[ 0 ];
        touchX = touch.screenX;
        touchY = touch.screenY;
        animate()

      }
      //滑动速度的操作
      function onDocumentTouchMove( event ) {
        event.preventDefault();
        var touch = event.touches[ 0 ];
        lon -= ( touch.screenX - touchX ) * 0.2;
        lat += ( touch.screenY - touchY ) * 0.2;
        touchX = touch.screenX;
        touchY = touch.screenY;
        animate()
      }

      /**
       * 实时渲染函数
       */
      function animate() {
        //requestAnimationFrame(animate);
        // lon = Math.max(-180, Math.min(180, lon));//限制固定角度内旋转
        // lon += 0.1;//自动旋转
        lat = Math.max(-85, Math.min(85, lat)); //限制固定角度内旋转
        phi = THREE.Math.degToRad(85 - lat);
        theta = THREE.Math.degToRad(lon+180);
        target.x = Math.sin(phi) * Math.cos(theta);
        target.y = Math.cos(phi);
        target.z = Math.sin(phi) * Math.sin(theta);
        camera.lookAt( target );
        camera.updateProjectionMatrix();
        isDeviceing == false ? initMouseControl() : deviceControl.update();
        renderer.render(scene, camera);
      }

      $('.btn1').on('touchstart',function(){
        _this.$router.push({name:'MineCenter'});
      });
      $('.btn2').on('touchstart',function(){
        _this.$router.push({name:'Message'});
      });
      $('.btn3').on('touchstart',function(){
        _this.$router.push({name:'Home'});
      });
      $('.shoe1').on('touchstart',function(){
        _this.showLooksFn();
      });
    },
    methods:{

      /**
       * 链接跳转到
       * @param routerName
       */
      linkTo(routerName){
        this.$router.push({name:routerName})
      },
      /**
       *
       */
      clickButton(itemObj,index){
        itemObj.active = index;
      },
      /**
       *
       */
      showLooksFn(){
        this.showLooks = !this.showLooks;
        //todo 显示穿搭模特
        console.log('todo 显示穿搭模特')
      },
    },
  }
</script>

<style scoped>
  .page-cloak-room{
    overflow: hidden;
    position: relative;
    width: 100%;
    height: 100%;
  }
  .divBottom{
    position: absolute;
    right: 20px;
    bottom: 0.15rem;
    width: 277px;
    height: 1rem;
    transform-origin: 50% 50% 0px;
    visibility: inherit;
    transition: none;
  }
  .ggskin_button{
    position: absolute;
    left: 0px;
    top: 0px;
    width: 36px;
    height: 50px;
    visibility: inherit;
    cursor: pointer;
    z-index: 999;
    text-align: center;
  }
  .ggskin_button img{
    display: inline-block;
    width: 28px;
    height: 28px;
  }
  .ggskin_button p{
    color: white;
    font-size: 0.24rem;
  }
  .wish_list{
    left: 80px;
  }
  .recommend{
    left: 135px;
  }
  .details{
    left: 185px;
  }
  .classification{
    left: 235px;
  }

  .page-cloal-room { position: relative;background-color: #000000; margin: 0; cursor: move; overflow: hidden;width: 100%;height: 100%; }
  .bg_section { width: 1026px; height: 1026px; background-size: cover; position: absolute; }
  .bg_section .bg { position: absolute; width: 1026px; height: 1026px; }
  .btn1{position: absolute;width: 200px;height: 200px;top:43.5%;left:31%;-webkit-transform: perspective(600px);background: url(../../assets/images/cloakroom/user.png);background-size: 100% 100%;}
  .btn2{position: absolute;width: 200px;height: 200px;top:67%;left:47%;-webkit-transform: perspective(600px);background: url(../../assets/images/cloakroom/magazine.png);background-size: 100% 100%;}
  .btn3{position: absolute;width: 230px;height: 230px;top:33%;left:3%;-webkit-transform: perspective(600px);background: url(../../assets/images/cloakroom/Entrance.png);background-size: 100% 100%;}

  .shoe1{position: absolute;width: 60px;height: 60px;top:34%;left:58.5%;-webkit-transform: perspective(600px);background: url(../../assets/images/cloakroom/shoes1.jpg);background-size: 100% 100%;}
  .btn4{position: absolute;width: 200px;height: 200px;top:40%;left:10%;-webkit-transform: perspective(600px);background: url(../../assets/images/cloakroom/custom_label.png);background-size: 100% 100%;}
  .btn5{position: absolute;width: 200px;height: 200px;top:45%;left:45%;-webkit-transform: perspective(600px);background: url(../../assets/images/cloakroom/after_sale_maintenance.png);background-size: 100% 100%;}
  .btn6{position: absolute;width: 200px;height: 200px;top:20%;left:40%;-webkit-transform: perspective(600px);background: url(../../assets/images/cloakroom/shoppingmall.png);background-size: 100% 100%;}
  .controlBtn{ width: 160px; height: 160px; position: fixed; z-index: 99999; left: 8%; top: 5%; }
  /*.controlIcon{ background: url(../../assets/images/cloakroom/controlIcon.png); background-size: 100% 100%; }*/
  /*.controlIconae{ background: url(../images/controlIconae.png); background-size: 100% 100%; }*/
  .controlIconae{ background: url(../../assets/images/cloakroom/wish.png); background-size: 100% 100%; }
  @-webkit-keyframes bounceant1{
    0%{-webkit-transform:translateY(0)}
    50%{-webkit-transform:translateY(-12px)}
    100%{-webkit-transform:translateY(0px)}
  }
  @keyframes bounceant1{
    0%{transform:translateY(0)}
    50%{transform:translateY(-12px)}
    100%{transform:translateY(0px)}
  }
  .bounceant1{-webkit-animation:bounceant1 3s infinite;animation:bounceant1 3s infinite}

  .ggskin_button-more{
    display: none;
    position: absolute;
    bottom: 1.45rem;
    right: 0px;
    height: 0.76rem;
    z-index: 999;
    background: rgba(0,0,0,.6);
    opacity: 0;
    transition: all 0.28s;
  }
  .ggskin_button-more.active{
    display: block;
    opacity: 1;
  }
  .ggskin_button-more:after{
    content:" ";
    box-sizing: content-box;
    display: inline-block;
    width: 0;
    height: 0;
    border-: 8px;

  }
  .ggskin_button-more>ul{
    position: relative;
    display: inline-block;
    box-sizing: border-box;
    height: 0.76rem;
    text-align: center;
  }
  .ggskin_button-more>ul>li{
    float: left;
    width: 1.68rem;
    height: 0.76rem;
    line-height: 0.76rem;
    color:#fff;
    position: relative;
  }
  .ggskin_button-more>ul>li>ul{
    position: absolute;
    bottom: 0.76rem;
    left: 0;
    width: 1.68rem;
    background: rgba(0,0,0,.5);
  }
  .looks-show-wrapper{
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 9999;
    text-align: center;
  }
  .looks-show{
    position: absolute;
    bottom: 1.45rem;
    left: 50%;
    height: 60%;
    width: 104px;
    margin-left: -52px;
    text-align: center;
  }
  .looks-show img{
    display: inline-block;
    max-width: 100%;
    max-height: 100%;
  }


  /*动画*/
  .looks-show-wrapper{
    bottom: -100%;
    transition: 0s all;
  }
  .looks-show-wrapper.active{
    bottom: 0;
  }
  .looks-show-wrapper.active .looks-show{
    opacity: 1;
  }
  .looks-show-wrapper .looks-show{
    opacity: 0;
    bottom: -1rem;
    transition: .3s all;
  }
  .looks-show-wrapper.active .looks-show{
    opacity: 1;
    bottom:  1.6rem;
  }
</style>
