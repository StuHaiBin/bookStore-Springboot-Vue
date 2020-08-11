import ajax from "./ajax";

const BASE_URL = 'http://localhost:8082/sort'

//添加图书分类
export const reqAddBookSort = (bookSort) => ajax(BASE_URL+'/addBookSort', {
  sortName: bookSort.sortName,
  upperName: bookSort.upperName,
  rank: bookSort.rank,
  level: bookSort.level
}, 'POST')

export const reqGetUpperName = ()=>ajax(BASE_URL+'/getUpperNames')

export const reqGetBookSort = (upperName,sortName) => ajax(BASE_URL+"/getBookSort",{upperName,sortName})

export const reqModifyBookSort = (bookSort) => ajax(BASE_URL+"/modifyBookSort",{
  id: bookSort.id,
  sortName: bookSort.sortName,
  upperName: bookSort.upperName,
  rank: bookSort.rank,
  level: bookSort.level
},'POST')

export const reqDelFirstSort = (sortName)=>ajax(BASE_URL+'/delFirstSort',{sortName})
export const reqDelSecondSort = (upperName,sortName)=>ajax(BASE_URL+'/delSecondSort',{upperName,sortName})

export const reqGetFirstSortList = (page,pageSize) => ajax(BASE_URL+"/getFirstSortList",{page,pageSize})
export const reqGetSecondSortList = (upperName,page,pageSize) => ajax(BASE_URL+"/getSecondSortList",{upperName,page,pageSize})
export const reqGetSortList = () => ajax(BASE_URL+"/getBookSortList")
