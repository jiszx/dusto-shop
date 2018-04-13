/**
 * 存储localStorage
 */
export const setLocalStorage = (name, content) => {
  if (!name) return;
  if (typeof content !== 'string') {
    content = JSON.stringify(content);
  }
  window.localStorage.setItem(name, content);
}

/**
 * 获取localStorage
 */
export const getLocalStorage = name => {
  if (!name) return;
  return window.localStorage.getItem(name);
}

/**
 * 删除localStorage
 */
export const removeLocalStorage = name => {
  if (!name) return;
  window.localStorage.removeItem(name);
}


export const Cookie = {
  /**
   * getCookie
   * @param name
   * @returns {*}
   */
  get(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
      var arr = arrCookie[i].split("=");
      if (arr[0] == name)return arr[1];
    }
    return "";
  },

/**
 * 添加cookie
 * @param name
 * @param value
 * @param expiresHours
 * @param domain
 */
  set(name, value, expiresDays,domain) {
    var cookieString = name + "=" + (!!value?value:'');
    var date = new Date();

    if(domain!=undefined)
      domain=";domain="+domain;
    else
      domain='';

    date.setTime(date.getTime() + expiresDays * 24 * 3600 * 1000);
    cookieString = cookieString + domain+"; path=/; expires=" + date.toGMTString();

    document.cookie = cookieString;
  },

/**
 * 删除cookie
 * @param name
 */
  remove(name){
    this.set(name,'',-1);
  }
};

// 时间相关
/**
* 转换标准时间为时间戳：例如将2016/1/20 19:59:30转换成时间戳形式1484913570000
* @param dateStr
* @returns {number}
*/
export function getDateTimeStamp(dateStr){
  return Date.parse(dateStr.replace(/-/gi,"/"));
}
/**
 * 时间格式方法
 *
 * @param {any} timeStamp  时间戳，秒级/毫秒级
 * @param {any} type 格式化时间类型，默认  Y-M-D H:I:S
 * @returns {string} formatTime 格式化后的时间 例如： 2017-05-05 12:09:22
 */
export function formatDate(nS,str) {
  if(!nS){
    return "";
  }
  if(parseInt(nS)===NaN){
    return nS;
  }
  var date=new Date(nS);
  var year=date.getFullYear();
  var mon = date.getMonth()+1;
  var day = date.getDate();
  var hours = date.getHours();
  var minu = date.getMinutes();
  var sec = date.getSeconds();

  if(str=='yyyy-MM-dd'){
    return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day);
  }else if(str=='hh-mm-ss'){
    return (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
  }else{
    return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
  }

}
/**
 * 获取当前的日期 格式“yyyyMMdd”
 * @returns {string}
 */
export function getcurrentDate(nS) {
  var date=nS?new Date(nS):new Date();
  var year=date.getFullYear();
  var mon = date.getMonth()+1;
  mon=mon>9?mon:'0'+mon;
  var day = date.getDate();
  day=day>9?day:'0'+day;
  return ''+year+mon+day;

}
/**
 * 获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
 * @returns {string}
 */
export function getNowFormatDate() {
  var date = new Date();
  var seperator1 = "-";
  var seperator2 = ":";
  var month = date.getMonth() + 1;
  var strDate = date.getDate();
  if (month >= 1 && month <= 9) {
    month = "0" + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
  }
  var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
    + " " + date.getHours() + seperator2 + date.getMinutes()
    + seperator2 + date.getSeconds();
  return currentdate;
}

/**
 * 字符串格式验证
 */
//checkType('165226226326','phone')
//false
//大家可以根据需要扩展
export function checkType (str, type) {
  str=str?str.toString():'';
  switch (type) {
    case 'email':
      return /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str);
    case 'phone':
      return /^1[3|4|5|7|8][0-9]{9}$/.test(str);
    case 'tel':
      return /^(0\d{2,3}-\d{7,8})(-\d{1,4})?$/.test(str);
    case 'number':
      return /^\+?[1-9][0-9]*$/.test(str);
    case 'english':
      return /^[a-zA-Z]+$/.test(str);
    case 'chinese':
      return /^[\u4E00-\u9FA5]+$/.test(str);
    case 'lower':
      return /^[a-z]+$/.test(str);
    case 'upper':
      return /^[A-Z]+$/.test(str);
    default :
      return true;
  }
}

/**
 * 检测是否是微信
 */
export function isWeixin() {
  const ua = window.navigator.userAgent.toLowerCase();
  if(ua.match(/MicroMessenger/i) == 'micromessenger'){
    return true;
  } else {
    return false;
  }
}


/**
 *  dom操作
 */
export function addClass(el, cls) {
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


export function removeClass(el, cls) {
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
