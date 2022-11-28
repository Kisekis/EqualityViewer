const { defineConfig } = require('@vue/cli-service')
module.exports = {
  transpileDependencies: true
,devServer: {
    allowedHosts: "all",
    proxy: {
      '/api' : {
        target: 'http://localhost:9090',
        changeOrigin: true,
        pathRewrite: {
          'api': ''
        }
      }
    }
  }};
