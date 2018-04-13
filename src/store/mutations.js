import {
  FETCH_LOADING,
  SHOW_MSG_TIPS,
  HIDE_MSG_TIPS,
} from './types'

export default {
  /**
   * 修改请求数据时loading状态标识字段
   * @param state
   * @param res Boolean
   */
  [FETCH_LOADING] (state, res) {
    state.fetchLoading = res;
  },
  /**
   * 显示提示弹层
   * @param state
   * @param msg 提示文案 String Or Object
   */
  [SHOW_MSG_TIPS](state, msg) {
    let vantToastType = ['success','fail'];
    if((typeof msg).toLocaleLowerCase() === 'string'){
      state.messageTips.text = msg;
    }else{
      state.messageTips.text = msg.text;
      state.messageTips.type = vantToastType.includes(msg.type) ? msg.type : 'text';
    }
    state.messageTips.isShow = true;
  },
  /**
   * 隐藏提示弹层
   * @param state
   */
  [HIDE_MSG_TIPS](state) {
    state.messageTips.isShow = false;
    state.messageTips.text = '';
  },
}
