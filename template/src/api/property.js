import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/myiot";

// 查询属性列表
export function listProperty(query) {
  return request({
    url: '/property/list',
    method: 'get',
    params: query
  })
}

// 查询属性详细
export function getProperty(propertyId) {
  return request({
    url: '/property/' + parseStrEmpty(propertyId),
    method: 'get'
  })
}

// 新增属性
export function addProperty(data) {
  return request({
    url: '/property',
    method: 'post',
    data: data
  })
}

// 修改属性
export function updateProperty(data) {
  return request({
    url: '/property',
    method: 'put',
    data: data
  })
}

// 删除属性
export function delProperty(propertyId) {
  return request({
    url: '/property/' + propertyId,
    method: 'delete'
  })
}
