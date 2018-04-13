import Login from '@/pages/loginRegister/Login'
import ForgetPwd from '@/pages/loginRegister/ForgetPwd'
import ModifyPwd from '@/pages/loginRegister/ModifyPwd'
import Register from '@/pages/loginRegister/Register'
import BindingPhone from '@/pages/loginRegister/BindingPhone'

export default [
    {//登录
      path: "/loginRegister/Login",
      name: 'Login',
      component: Login
    },
    {//忘记密码
      path: "/loginRegister/ForgetPwd",
      name: 'ForgetPwd',
      component: ForgetPwd,
      meta: {
          title: '忘记密码'
      }
    },
    {//修改密码
      path: "/loginRegister/ModifyPwd",
      name: 'ModifyPwd',
      component: ModifyPwd,
      meta: {
          title: '修改密码'
      }
    },
    {//注册VIP会员
      path: "/loginRegister/Register",
      name: 'Register',
      component: Register,
      meta: {
          title: '注册VIP会员'
      }
    },
    {//绑定手机号
      path: "/loginRegister/BindingPhone",
      name: 'BindingPhone',
      component: BindingPhone,
      meta: {
          title: '绑定手机号'
      }
    }
  ]
