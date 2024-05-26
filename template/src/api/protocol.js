import request from '@/utils/request'

// 查询产品列表
export function listProtocol(query) {
  return request({
    url: '/protocol/list',
    method: 'get',
    params: query
  })
}

// 查询产品详细
export function getProtocol(protocolId) {
  return request({
    url: '/protocol/' + protocolId,
    method: 'get'
  })
}

// 新增产品
export function addProtocol(data) {
  return request({
    url: '/protocol',
    method: 'post',
    data: data
  })
}

// 修改产品
export function updateProtocol(data) {
  return request({
    url: '/protocol',
    method: 'put',
    data: data
  })
}

// 删除产品
export function delProtocol(protocolId) {
  return request({
    url: '/protocol/' + protocolId,
    method: 'delete'
  })
}
