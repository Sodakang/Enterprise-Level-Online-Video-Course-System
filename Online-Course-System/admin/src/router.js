import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login'
import Admin from './views/admin'
import Welcome from './views/admin/welcome'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '*',
        redirect: "/login",
    }, {
        path: '/login',
        component: Login
    }, {
        path: '/admin',
        component: Admin,
        children: [{
            // the configuration of the subroutine cannot begin with '/'
            path: 'welcome',
            component: Welcome
        }]
    }]
})