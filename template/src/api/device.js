import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/myiot";

// 查询接入列表
export function listDevice(query) {
  return request({
    url: '/device/list',
    method: 'get',
    params: query
  })
}

// 查询接入详细
export function getDevice(deviceId) {
  return request({
    url: '/device/' + parseStrEmpty(deviceId),
    method: 'get'
  })
}

// 新增接入
export function addDevice(data) {
  return request({
    url: '/device',
    method: 'post',
    data: data
  })
}

// 修改接入
export function updateDevice(data) {
  return request({
    url: '/device',
    method: 'put',
    data: data
  })
}

// 删除接入
export function delDevice(deviceSn) {
  return request({
    url: '/device/' + deviceSn,
    method: 'delete'
  })
}
