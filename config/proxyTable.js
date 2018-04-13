module.exports = {
  '/interf-rf': {
    target: 'http://122.228.147.82:8083',//设置你调用的接口域名和端口号 别忘了加http
    changeOrigin: true,               // needed for virtual hosted sites
    ws: true,
    pathRewrite: {
      // '^/memberRs': '/interf-rf/memberRs'
    }
  },
};
