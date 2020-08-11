<template>
  <div class="content">
    <div class="gallery-book_title">
      <span style="margin-left: 30px">{{this.sortTitle}}</span>
<!--      <router-link :to="{path: '/search',query:{id:1}}" style="float: right;margin-right: 30px">>更多图书</router-link>-->
    </div>
    <div class="gallery-book_list">
      <div class="gallery-book_card" v-for="(item) in bookList" :key="item.id">
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
    import {reqGetRecBookList} from "../../api/book";
    export default {
        props:{
            listSort: {
                default: "recommend"
            }
        },
        name: "RecBookBox",
        data(){
            return {
                sortTitle:"",
                bookSortList:[],
                imgS: ["static/image/bookdefault.jpg",
                    "static/image/21.jpg",
                    "static/image/22.jpg",
                    "static/image/23.jpg"],
                bookList: [],
            }
        },
        methods: {
            getBookList(){
                switch (this.listSort) {
                    case "recommend":
                        this.sortTitle = "精品推荐";
                        reqGetRecBookList("recommend").then(response=>{
                            if(response.code==200){
                                this.bookList = response.bookList;
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
                        break;
                    case "newProduct":
                        this.sortTitle = "新品推荐";
                        reqGetRecBookList("newProduct").then(response=>{
                            if(response.code==200){
                                this.bookList = response.bookList;
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
                        break;
                    default:
                        break;
                }
            }
        },
        created() {
            // console.log("this.listSort:"+this.listSort);
            this.getBookList();
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
    font-size: 24px;
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
