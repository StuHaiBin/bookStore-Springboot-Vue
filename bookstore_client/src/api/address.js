import ajax from "./ajax";

const BASE_URL = 'http://localhost:8082'

//添加地址
export const reqAddAddress = (address) => ajax(BASE_URL+'/addUserAddress', {
  account: address.account,
  name: address.name,
  phone: address.phone,
  addr: address.addr,
  label: address.label
}, 'POST')

//修改地址
export const reqModAddress = (address) => ajax(BASE_URL+"/modifyUserAddress",{
  id: address.id,
  account: address.account,
  name: address.name,
  phone: address.phone,
  addr: address.addr,
  label: address.label
},'POST')

//删除地址
export const reqDelAddress = (id) => ajax(BASE_URL+"/delUserAddress",{id})

//得到某个用户的地址列表
export const reqGetAddressList = (account) => ajax(BASE_URL+"/getUserAddress",{account})
