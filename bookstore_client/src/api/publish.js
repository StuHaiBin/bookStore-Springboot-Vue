import ajax from "./ajax";

const BASE_URL = 'http://localhost:8082'



//添加出版社
export const reqAddPublish = (publish) => ajax(BASE_URL+'/addPublish', {
  name: publish.name,
  showPublish: publish.showPublish,
  rank: publish.rank,
}, 'POST')

export const reqGetPublishList = (page,pageSize)=>ajax(BASE_URL+'/getPublishList',{page, pageSize})

export const reqGetPublishNames = ()=>ajax(BASE_URL+'/getPublishNames')

export const reqGetPublish = (id) => ajax(BASE_URL+"/getEditPublish",{id})

export const reqModifyPublish = (publish) => ajax(BASE_URL+"/modifyPublish",{
  id:publish.id,
  name: publish.name,
  showPublish: publish.showPublish,
  rank: publish.rank,
},'POST')

export const reqModifyShow = (id) => ajax(BASE_URL+"/modifyShowPublish",{id})
export const reqDelPublish = (id) => ajax(BASE_URL+"/delPublish",{id})
