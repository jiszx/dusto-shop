import Message from '@/pages/message/MessageIndex'
import MsgList from '@/pages/message/MsgList'
import MsgDetail from '@/pages/message/MessagaDetail'
import MsgCXHD from '@/pages/message/MessageCXHD'
import Tickets from '@/pages/message/MsgTickets'
import CXHDDetail from '@/pages/message/CXHDDetail'

import MsgCXHDList from  'pages/message/children/CXHD-List'

export default [
  {
    path: "/message",
    name: "Message",
    component: Message,
    meta: {
      title: "资讯"
    }
  },
  {
    path: "/message/list",
    name: "msgList",
    component: MsgList,
    meta: {
      title: "列表"
    }
  },
  {
    path: "/message/detail",
    name: "MsgDetail",
    component: MsgDetail,
    meta: {
      title: "详情"
    }
  },
  {
    path: "/message/cxhd",
    name: "MsgCXHD",
    component: MsgCXHD,
    meta: {
      title: "点我有优惠"
    },
    redirect: { name: 'MsgCXHDList' },
    children:[{
      path: "list",
      name: "MsgCXHDList",
      component: MsgCXHDList,
      meta: {
        title: "点我有优惠"
      },
    },{
      path: "tickets",
      name: "MsgCXHDTickets",
      component: Tickets,
      meta: {
        title: "点我有优惠"
      },
    }],
  },
  {
    path: "/message/tickets",
    name: "Tickets",
    component: Tickets,
    meta: {
      title: "点我有优惠"
    }
  },
  {
    path: "/message/cxhdDetail",
    name: "CXHDDetail",
    component: CXHDDetail,
    meta: {
      title: "促销活动"
    }
  },
]
