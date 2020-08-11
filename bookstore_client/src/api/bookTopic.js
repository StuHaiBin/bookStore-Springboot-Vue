import ajax from "./ajax";

const BASE_URL = 'http://localhost:8082/topic'



export const reqGetTopicList = (page,pageSize)=>ajax(BASE_URL+'/getTopicList',{page, pageSize})

export const reqGetTopic = (id) => ajax(BASE_URL+"/getTopic",{id})

export const reqModifyTopic = (bookTopic) => ajax(BASE_URL+'/modifyTopic', {
  id: bookTopic.id,
  topicName: bookTopic.topicName,
  subTitle: bookTopic.subTitle,
  rank: bookTopic.rank,
  put: bookTopic.put
}, 'POST')

export const reqModifyRank = (id,rank) => ajax(BASE_URL+"/modifyTopicRank",{id,rank})
export const reqModifyPut = (id,put) => ajax(BASE_URL+"/modifyTopicPut",{id,put})

export const reqDelTopic = (id) => ajax(BASE_URL+"/delTopic",{id})
export const reqDelTopicImg = (id,url)=>ajax(BASE_URL+'/delTopicImg',{id,url})

//得到所有未添加到书单的图书
export const reqGetNoAddBookList = (topicId,page,pageSize)=>ajax(BASE_URL+'/getNoAddBookList',{topicId,page, pageSize})



export const reqAddSubTopic = (id,bookId)=>ajax(BASE_URL+'/addSubTopic',{id,bookId})
export const reqDelSubTopic = (id,bookId)=>ajax(BASE_URL+'/delSubTopic',{id,bookId})
export const reqModifySubTopic = (id,bookId,recReason)=>ajax(BASE_URL+'/modifySubTopic',{id,bookId,recReason})
export const reqGetSubTopicList = (id,page,pageSize)=>ajax(BASE_URL+'/getSubTopicList',{id,page, pageSize})
export const reqGetReason = (topicId,bookId)=>ajax(BASE_URL+'/getReason',{topicId,bookId})
export const reqGetTopicBookList = (id)=>ajax(BASE_URL+'/getTopicBookList',{id})

//getReason
