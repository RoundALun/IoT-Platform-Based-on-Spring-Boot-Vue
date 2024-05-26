import request from '@/utils/request'

// 查询角色列表
export function listRole(query) {
  return request({
    url: '/role/list',
    method: 'get',
    params: query
  })
}
// 查询角色列表
export function getAllRole(){
  return request({
      url: '/role/all',
      method: 'get'
  });
}
// 查询角色详细
export function getRole(userId) {
  return request({
    url: '/role/' + userId,
    method: 'get'
  })
}

// 新增角色
export function addRole(data) {
  return request({
    url: '/role',
    method: 'post',
    data: data
  })
}

// 修改角色
export function updateRole(data) {
  return request({
    url: '/role',
    method: 'put',
    data: data
  })
}

// 删除角色
export function delRole(userId) {
  return request({
    url: '/role/' + userId,
    method: 'delete'
  })
}
