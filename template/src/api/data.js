import request from '@/utils/request'

// 查询属性数据列表
export function listPropData(deviceSn) {
  return request({
    url: '/propData/list/' + deviceSn,
    method: 'get',
  })
}

// 查询历史数据列表
export function getHistoryData(data) {
  return request({
    url: '/propData/history/',
    method: 'post',
    data: data
  })
}

export function putChange(deviceSn, propertyKey, propertyValue) {
  const data = {
    deviceSn,
    propertyKey,
    propertyValue
  }
  return request({
    url: '/propData/put/',
    method: 'put',
    data: data
  })
}