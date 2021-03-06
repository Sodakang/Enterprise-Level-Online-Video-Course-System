import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'
import filter from './filter/filter'

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

/**
 * axios interceptor.
 */
axios.interceptors.request.use(function (config) {
  console.log("Request: ", config);
  return config;
}, error => {});
axios.interceptors.response.use(function (response) {
  console.log("Return results: ", response);
  return response;
}, error => {});

// Global filter.
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key]);
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

console.log("Environment: ", process.env.NODE_ENV);