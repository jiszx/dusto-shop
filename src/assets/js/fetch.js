import axios from 'axios'

import store from 'store/index'

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
//线上环境配置axios.defaults.baseURL，生产环境则用proxy
if(process.env.API_ROOT==='production'){
  axios.defaults.baseURL = 'http://122.228.147.82:8083';
}
axios.interceptors.request.use(config => {
  // loading
  store.commit('fetchLoading', true);//开始请求接口loading
  return config
}, error => {
  store.commit('fetchLoading', false);
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  store.commit('fetchLoading', false);
  return response
}, error => {
  store.commit('fetchLoading', false);
  return Promise.resolve(error.response)
});

export default {
  post (url, params) {
    return new Promise((resolve, reject) => {
      axios.post(url, params)
        .then(response => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        })
    })
  },
  get (url, params) {
    return new Promise((resolve, reject) => {
      axios.get(url, {params: params})
        .then(response => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        })
    })
  },
  put (url, params) {
    return new Promise((resolve, reject) => {
      axios.put(url, params)
        .then(response => {
          resolve(response.data);
        })
        .catch((error) => {
          reject(error);
        })
    })
  },
}
