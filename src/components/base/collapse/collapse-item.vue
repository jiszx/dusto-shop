<template>
	<div class="collapse-item"  :class="{'is-active': isActive}">
    <div class="collapse-title-wrapper" @click="handleHeaderClick">
      <div class="">
        <slot  name="title"></slot>
        <template v-if="!$slots.title" class="inline-block">
          {{title}}
        </template>
      </div>

      <Icon name="arrow"  :class="{'is-active': isActive}" class="collapse-item-header-arrow" v-if="showArrow"></Icon>
    </div>

    <transition
      v-on:before-enter="beforeEnter"
      v-on:enter="enter"
      v-on:after-enter="afterEnter"

      v-on:before-leave="beforeLeave"
      v-on:leave="leave"
      v-on:after-leave="afterLeave"
    >
      <div class="collapse-item-inner"  v-show="isActive">
        <div class="collapse-item__content">
          <slot></slot>
        </div>
      </div>
    </transition>
	</div>
</template>

<script>
  import {
    Icon,
  } from 'vant';
	export default {
    props: {
      title: String,
      name: {
        type: [String, Number],
        default() {
          return (new Date()).getTime().toString();
        }
      },
      showArrow:{
        type:Boolean,
        default:true,
      },
    },
		data() {
			return {
        contentWrapStyle: {
          height: 'auto',
          display: 'block'
        },
        contentHeight: 0
      }
		},
    computed: {
      isActive() {
        return this.$parent.activeNames.indexOf(this.name) > -1;
      }
    },
    watch: {
      'isActive'(value) {
      }
    },
    components:{Icon},

    methods: {
      handleHeaderClick() {
        this.$parent.handleItemClick(this);
      },

//      动画
      beforeEnter(el) {
        addClass(el, 'collapse-transition');
        if (!el.dataset) el.dataset = {};

        el.dataset.oldPaddingTop = el.style.paddingTop;
        el.dataset.oldPaddingBottom = el.style.paddingBottom;

        el.style.height = '0';
        el.style.paddingTop = 0;
        el.style.paddingBottom = 0;
      },

      enter(el) {
        el.dataset.oldOverflow = el.style.overflow;
        if (el.scrollHeight !== 0) {
          el.style.height = el.scrollHeight + 'px';
          el.style.paddingTop = el.dataset.oldPaddingTop;
          el.style.paddingBottom = el.dataset.oldPaddingBottom;
        } else {
          el.style.height = '';
          el.style.paddingTop = el.dataset.oldPaddingTop;
          el.style.paddingBottom = el.dataset.oldPaddingBottom;
        }

        el.style.overflow = 'hidden';
      },

      afterEnter(el) {
        // for safari: remove class then reset height is necessary
        removeClass(el, 'collapse-transition');
        el.style.height = '';
        el.style.overflow = el.dataset.oldOverflow;
      },

      beforeLeave(el) {
        if (!el.dataset) el.dataset = {};
        el.dataset.oldPaddingTop = el.style.paddingTop;
        el.dataset.oldPaddingBottom = el.style.paddingBottom;
        el.dataset.oldOverflow = el.style.overflow;

        el.style.height = el.scrollHeight + 'px';
        el.style.overflow = 'hidden';
      },

      leave(el) {
        if (el.scrollHeight !== 0) {
          // for safari: add class after set height, or it will jump to zero height suddenly, weired
          addClass(el, 'collapse-transition');
          el.style.height = 0;
          el.style.paddingTop = 0;
          el.style.paddingBottom = 0;
        }
      },

      afterLeave(el) {
        removeClass(el, 'collapse-transition');
        el.style.height = '';
        el.style.overflow = el.dataset.oldOverflow;
        el.style.paddingTop = el.dataset.oldPaddingTop;
        el.style.paddingBottom = el.dataset.oldPaddingBottom;
      }
    },
	}

  function addClass(el, cls) {
    if (!el) return;
    var curClass = el.className;
    var classes = (cls || '').split(' ');

    for (var i = 0, j = classes.length; i < j; i++) {
      var clsName = classes[i];
      if (!clsName) continue;

      if (el.classList) {
        el.classList.add(clsName);
      } else {
        if (!hasClass(el, clsName)) {
          curClass += ' ' + clsName;
        }
      }
    }
    if (!el.classList) {
      el.className = curClass;
    }
  };


  function removeClass(el, cls) {
    if (!el || !cls) return;
    var classes = cls.split(' ');
    var curClass = ' ' + el.className + ' ';

    for (var i = 0, j = classes.length; i < j; i++) {
      var clsName = classes[i];
      if (!clsName) continue;

      if (el.classList) {
        el.classList.remove(clsName);
      } else {
        if (hasClass(el, clsName)) {
          curClass = curClass.replace(' ' + clsName + ' ', ' ');
        }
      }
    }
    if (!el.classList) {
      el.className = curClass.trim();
    }
  };
</script>

<style scoped>
.collapse-title-wrapper{
  position: relative;
  height: 43px;
  line-height: 43px;
  background-color: #fff;
  color: #333;
  font-size: 16px;
}
  .collapse-item-inner{
    will-change: height;
    background-color: #fbfdff;
    overflow: hidden;
    box-sizing: border-box;
  }
  .collapse-item__content{
    padding: 10px 15px;
    font-size: 14px;
    line-height: 1.769230769230769;
  }
  .collapse-item-header-arrow{
    display: inline-block;
    width: 20px;
    height: 20px;
    line-height: 1;
    position: absolute;
    top:14px;
    color: #8c8c8c;
    right: 10px;
    font-size: 14px;
    font-weight: 600;
    transform: rotate(0deg);
    transition: .3s transform;
  }

</style>
