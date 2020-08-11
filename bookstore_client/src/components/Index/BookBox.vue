<template>
  <div class="content">
    <div class="gallery-book_title">
      <router-link :to="{path: '/search',query:{id:1}}" v-for="(item,index) in bookList" :key="index">
        <span v-if="index==0" @mouseenter="enter(index)" style="margin-right: 20px;margin-left: 30px;font-size: 24px">{{item.sortName}}</span>
        <span v-else @mouseenter="enter(index)" style="margin-right: 20px;">{{item.sortName}}</span>
      </router-link>
<!--      <a style="float: right;margin-right: 30px" href="#">>更多书单</a>-->
      <router-link :to="{path: '/search',query:{id:bookList[0].sortId,name:bookList[0].sortName}}" style="float: right;margin-right: 30px">>更多图书</router-link>

    </div>
    <div class="gallery-book_list" v-for="(book,index) in bookList" v-show="current==index" :key="index">
      <div class="gallery-book_card" v-for="(item) in book.bookList" :key="item.id">
        <router-link :to="{path: '/book',query:{id:item.id}}">
        <el-image
          style="width: 82%; height: 190px;margin:5px 9%"
          :src="item.coverImg"
          fit="fill"></el-image>
        <div style="width: 86%;margin: 0px 7%">
          <a href="#" class="gallery-book_text">
            {{item.bookName}}
          </a>
          <div class="gallery-book_author">
            {{item.author}}
          </div>
          <div class="gallery-book_price">
            <span style="font-size:14px;color: #bf7f5f;">￥</span>
            {{item.price}}
          </div>
        </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
    import GalleryBook from "./GalleryBook";
    //reqGetSortBookList
    import {reqGetSortBookList} from "../../api/book";

    export default {
        name: "BookBox",
        components:{GalleryBook},

        data(){
            return {
                current: 0,//当前显示哪一个卡片
                bookSortList:[],
                imgS: ["static/image/bookdefault.jpg",
                    "static/image/21.jpg",
                    "static/image/22.jpg",
                    "static/image/23.jpg"],
                bookList:[ {
                    sortId: 1,
                    sortName: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                    bookList: []
                }],
            };
        },
        methods: {
            enter(index){
                this.current = index;
                // console.log("鼠标进入了！");
                // console.log("index:"+this.current);
                // for(let i=0;i<this.bookList.length;i++){
                //     console.log(this.bookList[i]);
                // }
            },
            getSortBookList(){
                console.log("发送了获取分类图书的请求");
                reqGetSortBookList(117).then(response=>{
                    if(response.code==200){
                        console.log(response);
                        this.bookList = response.sortBookResList;
                        console.log(this.bookList);
                    }else{
                        this.$message({
                            type: 'warning',
                            message: response.message
                        })
                    }
                }).catch(err=>{
                    this.$message({
                        type: 'warning',
                        message: "获取图书列表数据失败"
                    })
                })
            }
        },
        created() {
          this.getSortBookList();
        }
    }
</script>

<style scoped>
  .content{
    width: 1240px;
  }
  .gallery-book_title{
    padding-top: 20px;
    width: 100%;
    height: 32px;
    margin-bottom: 25px;
    border-bottom: 1px solid #ccc;
    margin-top: 20px;
    padding-bottom: 34px;
    font-size: 22px;
  }

  .gallery-book_list{
    width: 100%;
    position: relative;
  }
  .gallery-book_card{
    position: relative;
    width:12.28%;
    margin: 3px 1%;
    height: 290px;
    border: 1px solid #e4e4e4;
    display: inline-block;
    vertical-align: top;
  }
  .gallery-book_card:hover{
    border: 1px #d9d9d9 solid;
    box-shadow: 0px 0px 6px #9a9a9a;
  }
  .gallery-book_text{
    display: block;
    width: 100%;
    font-size: 12px;
    color: #333;
    word-break: break-all;
    max-height: 40px;
    line-height: 20px;
    overflow: hidden;
    margin: 0 auto;
  }
  .gallery-book_author{
    width: 98px;
    height: 22px;
    overflow: hidden;
    margin: 0;
    color: #999;
    font-size: 12px;
  }
  .gallery-book_price{
    float: left;
    font-style: normal;
    margin-right: 3px;
  }
  .gallery-book{
    width: 100%;
    font-family: 微软雅黑;
  }
  a{
    font-size: 16px;
    text-decoration: none;
    color: #333333;
  }



  /deep/ .el-tabs__item {
    height: 50px;
    line-height: 50px;
    font-size: 16px;
  }
  /deep/ .el-tabs__nav{
    width: 100%;
    margin: 0px auto;
  }

  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }
  .fade-enter{
    opacity: 0;
  }



</style>
