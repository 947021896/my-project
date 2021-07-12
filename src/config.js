const server = 'http://localhost:8510'

const config = {
  server,
  /* 普通用户 */
  loginUrl: server + '/user/login',
  repairUrl: server + '/user/repair',
  getAddAndCateUrl: server + '/getAddAndCate',
  userOrderUrl: server + '/user/getOrder',
  userOneOrderUrl: server + '/user/getOneOrder',
  /* 管理员 */
  adLoginUrl: server + '/admin/login',
  adOrderUrl: server + '/admin/orders',
  adUpdateOrderUrl: server + '/admin/updateOrder',
  adOneOrderUrl: server + '/admin/getOneOrder',
  adRepairersUrl: server + '/admin/getRepairers',
  adMaterialsUrl: server + '/admin/materials',
  adAddMaterialUrl: server + '/admin/material/add',
  adReduceMaterialUrl: server + '/admin/material/reduce',
  adSendUrl: server + '/admin/sendOrder',
  /* 维修员 */
  reLoginUrl: server + '/repairer/login',
  reOrderUrl: server + '/repairer/getOrders'
}

export default config
