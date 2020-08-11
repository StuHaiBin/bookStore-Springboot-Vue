<!--书籍详情页面-->
<template>
  <div class="content">
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="box book_box">
      <div class="book_img">
        <CarouselBtn :imgList="book.imgSrc"></CarouselBtn>
      </div>
      <div class="book_buy">
        <div class="book_name">{{book.bookName}}</div>
        <div class="book_content book_buy_content">
          <div class="book_list_content">作者: 	{{book.author}}</div>
          <div class="book_list_content">ISBN: 	{{book.isbn}}</div>
          <div class="book_list_content">出版社: 	{{book.publish}}</div>
          <div class="book_list_content">出版时间: 	{{book.birthday}}</div>
        </div>
        <div class="book_content book_buy_price">
          <div class="book_buy_info">
            <span class="labelSpan">售价</span>
            <span class="labelPrice">￥{{book.price}}</span>
          </div>
          <div class="book_buy_info">
            <span class="labelSpan">市场价</span>
            <s>￥{{book.marketPrice}}</s>
          </div>
        </div>
        <div class="book_content">
          <span class="labelSpan">运费</span>
          ￥6.00
        </div>
        <div class="book_content">
          <span class="labelSpan">上架时间</span>
          2020-03-26
        </div>
        <div class="book_content">
          <span class="labelSpan">库存</span>
          {{book.stock}}件
        </div>
        <div class="book_content">
          <el-button class="plainBtn" plain @click="goBuyPage(book.id)">立即购买</el-button>
          <el-button type="primary" icon="el-icon-shopping-cart-2" class="cartBtn" @click="addBookToCart">加入购物车</el-button>
        </div>
      </div>
    </div>
    <div class="box">
      <div class="book_sort">
        <div class="tab">
          <div class="tab_head">分类</div>
          <div v-for="sort in sortList" class="tab_list">
            <router-link :to="{path: '/search',query:{id:sort.upperSort.id,name:sort.upperSort.sortName}}"><div style="color: black;width: 100%">{{sort.upperSort.sortName}}</div></router-link>
          </div>
        </div>
      </div>
      <div class="book_info">
        <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
          <el-tab-pane label="商品详情" name="first">
            <div class="product">
              <div class="markdown-body" v-html="book.description"></div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="店铺评价" name="second">
            <div class="evaluation"></div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import HeadNav from "../../components/Common/HeadNav";
    import Footer from "../../components/Common/Footer";
    import CarouselBtn from "../../components/Index/CarouselBtn";
    import {reqGetBook} from "../../api/book";
    import {reqGetSortList} from "../../api/sort";
    import {reqAddCart} from "../../api/cart";
    import 'github-markdown-css'
    export default {
        name: "Book",
        components:{CarouselBtn, Nav,HeadNav,Footer,CarouselBtn},
        data(){
            return{
                result: "",
                book: {
                    id: null,
                    bookName: '',
                    author: '',
                    isbn: '',
                    publish: '',
                    birthday: '',
                    marketPrice: '',
                    price: '',
                    stock: '',
                    description: '',
                    imgSrc: [],
                    put: true,
                    coverImg: '',
                    rank: '',
                    newProduct: true,
                    recommend: true,
                    bookSort: [],//图书分类的绑定值
                },
                fileList:null,
                bookId: null,
                sortList:[
                      {
                          upperSort: {
                              sortName: null,
                          },
                          children:null
                      }
                    ],
                activeName: 'first'
            }
        },
        methods: {
            handleClick(tab, event) {
                // console.log(tab, event);
            },

            getSortList() {
                reqGetSortList().then(response => {
                    if(response.code==200){
                        this.sortList = response.sortResponseList;
                    }
                    // console.log(this.sortList);
                    // console.log("response.sortResponseList"+response.sortResponseList);
                });
            },

            getBook(bookId){
                reqGetBook(bookId).then(response=>{
                    // console.log(response.book);
                    this.book = response.book;
                    // console.log("this.book.imgSrc:"+response.book.imgSrc);
                    let MarkdownIt = require("markdown-it");
                    let md = new MarkdownIt();
                    let result = md.render(this.book.description);
                    this.book.description = result;
                }).catch(err=>{
                    console.log(err);
                })
            },

            goBuyPage(id){
                let arr = [];
                arr.push(id);
                arr.push(0);
                // arr.push(2);
                let ids = JSON.stringify(arr);
                this.$router.push({
                    path: "/buyPage",
                    query: {
                        ids: ids
                    }
                })
            },

            //添加图书到购物车
            addBookToCart(){
                // console.log("=======this.book.id:===="+this.book.id+"=================")
                reqAddCart(this.$store.getters.getUser.account,this.book.id,1).then(response=>{
                    if(response.code==200){
                        this.$message({
                            message: response.message,
                            type: "success",
                            duration: 1000
                        });
                    }else{
                        this.$message({
                            message: response.message,
                            type: "warning",
                            duration: 1000
                        })
                    }
                }).catch(err=>{
                    console.log(err)
                })
            }
        },
        created() {
            this.bookId = this.$route.query.id;
            // console.log("this.bookId"+this.bookId);
            this.getBook(this.bookId);
            this.getSortList();
            // let account = this.$store.getters.getUser.account;
            // console.log("====account==="+account+"==============")
        }
    }
</script>

<style scoped>
  .content{
    background-color: #ffffff;
  }
  .box{
    margin: 10px auto;
    width: 1240px;
  }
  .book_box{
    height: 500px;
  }
  .book_img{
    margin: 10px;
    width: 450px;
    height: 480px;
    float: left;
  }
  .book_buy{
    margin: 10px;
    width: 750px;
    height: 480px;
    float: right;
    padding: 1px;
  }
  .book_buy_content{
    border: 1px #f3f0e9 solid;
    padding: 5px;
  }
  .book_content{
    margin: 10px auto;
    width: 740px;
    max-height: 120px;
    line-height: 35px;
  }

  .book_name{
    margin: 10px auto;
    width: 740px;
    line-height: 35px;
    font-size: 18px;
    overflow: hidden;
  }

  .book_list_content{
    width: 32%;
    display: inline-block;
    height: 20px;
    overflow: hidden;
    line-height: 20px;
    font-size: 15px;
  }
  .book_buy_info{
    width: 100%;
    line-height: 40px;
    font-size: 15px;
  }
  .book_buy_price{
    background-color: #f3f0e9;
    padding: 5px;
  }
  .labelSpan{
    color: #999;
  }
  .labelPrice{
    color: #9d232c;
    font-size: 17px;
  }
  .plainBtn{
    width: 150px;
    color: #9d232c;
    background-color: #f3f0e9;
    border: 1px #f3f0e9 solid;
  }
  .plainBtn:hover{
    background-color: white;
    color: #9d232c;
    border: 1px #b2bac2 solid;
  }
  .cartBtn{
    width: 150px;
    background-color: #9d232c;
    border: 1px #9d232c solid;
  }
  .cartBtn:hover{
    background-color: #f52b21;
  }

  .book_sort{
    margin: 10px 10px;
    width: 200px;
    /*height: 780px;*/
    float: left;
    border-right: 1px #f3f0e9 solid;
  }

  .book_info{
    margin: 10px 10px;
    width: 1000px;
    /*height: 780px;*/
    float: right;
    background-color: #f7f7f6;
  }
  .tab{
    width: 100%;
    /*height: 420px;*/
  }
  .tab_head{
    padding-left: 5px;
    width: 100%;
    height: 40px;
    line-height: 40px;
    background-color: #f3f0e9;
    margin: 1px 0px;
  }
  .tab_list{
    padding-left: 5px;
    width: 100%;
    height: 40px;
    line-height: 40px;
    background-color: #ffffff;
    margin: 1px 0px;
  }
  .tab_list:hover{
    background-color: #a0a9b1;
  }
  .product{
    width: 100%;
    padding: 0px 15px;
    height: 800px;
  }
  .evaluation{
    width:100%;
    height: 2500px;
  }
</style>
