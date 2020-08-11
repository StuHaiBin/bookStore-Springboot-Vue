import ajax from "./ajax";

const BASE_URL = 'http://localhost:8082'

//添加图书到购物车
export const reqAddCart = (account,id,num) => ajax(BASE_URL+'/addCart', {account,id,num})

//从购物车中删除指定商品
export const reqDelCart = (account,id) => ajax(BASE_URL+'/delCart', {account,id})

//批量删除购物车中的商品
export const reqBatchDelCart = (account,ids) => ajax(BASE_URL+'/batchDelCart', {account,ids})

//修改购物车中指定图书的数量
export const reqModCart = (account,id,num) => ajax(BASE_URL+'/modifyCart', {account,id,num})

//得到购物车中图书列表
export const reqGetCartList = (account,page,pageSize) => ajax(BASE_URL+'/getCartList', {account,page,pageSize})
