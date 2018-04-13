import {
  FETCH_LOADING,
  SHOW_MSG_TIPS,
  HIDE_MSG_TIPS,
} from './types'
export default {
  /**
   * 显示提示弹层
   * @param msg 提示文案 String Or Object
   */
  [SHOW_MSG_TIPS]({commit}, msg){
    commit(SHOW_MSG_TIPS,msg)
  },
  /**
   * 隐藏提示弹层
   * @param msg 提示文案 String Or Object
   */
  [HIDE_MSG_TIPS]({commit}){
    commit(HIDE_MSG_TIPS)
  },
}
