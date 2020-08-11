<!--书单页面-->
<template>
  <div class="content">
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="bg" v-bind:style="{backgroundImage:'url(' + bookTopic.cover + ')'}"></div>
    <div class="box">
      <p>{{bookTopic.subTitle}}</p>
    </div>
    <div class="box_book">
      <div class="book_item" v-for="item in TopicBookList" :key="item.id">
        <router-link :to="{path: '/book',query:{id:item.id}}">
        <div class="book_img">
          <el-image
            style="width: 115px; height: 160px;vertical-align: middle;"
            :src="item.coverImg"
            fit="fill"></el-image>
        </div>
        <div class="info_box">
          <div class="book_name">{{item.bookName}}</div>
          <div class="book_author">{{item.author}}/{{item.publish}}/{{item.birthday}}/</div>
          <div class="book_recommend">{{item.recommendReason}}</div>
        </div>
        </router-link>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import HeadNav from "../../components/Common/HeadNav";
    import Footer from "../../components/Common/Footer";
    import {reqGetTopicBookList} from "../../api/bookTopic";

    export default {
        name: "BookTopic",
        components:{Nav,HeadNav,Footer},
        data(){
            return{
                topicId: null,
                TopicBookList:[],
                bookTopic:{
                    cover: null,
                    subtitle: null,
                    id: null,
                }
            }
        },
        created() {
            this.topicId = this.$route.query.id;
            // console.log("this.topicId:"+this.topicId);
            this.GetTopicBookList(this.topicId);
        },
        methods: {
            //得到出版社列表
            GetTopicBookList() {
                reqGetTopicBookList(this.topicId).then(response => {
                    if (response.code == 200) {
                        this.TopicBookList = response.TopicBookList;
                        this.bookTopic = response.bookTopic;
                    }
                    // console.log(response);
                }).catch(err => {
                    console.log(err);
                })
            },

        }

    }
</script>

<style scoped>
  .content{
    background-color: #f8f5e7;
    min-width: 1240px;
  }
  .bg{
    margin: 0px auto;
    width: 100%;
    height: 500px;
    background-color: #333333;
    /*background-image: url("../../assets/image/21.jpg");*/
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
  }
  .box{
    margin: 20px auto;
    line-height: 70px;
    text-align: center;
    font-size: 25px;
  }
  .box_book{
    margin: 20px auto;
    width: 1000px;
  }
  .book_item{
    margin: 10px 1%;
    width: 48%;
    height: 180px;
    display: inline-block;
    border: 1px #ffffff solid;
    background-color: white;
  }
  .book_item:hover{
    border: 1px #d9d9d9 solid;
  }

  .book_img{
    margin: 10px;
    float: left;
  }
  .info_box{
    margin: 10px;
    padding: 5px;
    float: right;
    width: 320px;
    height: 160px;
  }
  .book_name{
    width: 100%;
    font-size: 15px;
    color: #333;
    word-break: break-all;
    max-height: 40px;
    line-height: 20px;
    overflow: hidden;
    margin: 0 auto;
  }
  .book_author{
    width: 100%;
    line-height: 16px;
    word-break: break-all;
    overflow: hidden;
    max-height: 32px;
    margin: 3px 0px;
    color: #999;
    font-size: 14px;
  }
  .book_recommend{
    width: 100%;
    font-size: 14px;
    color: #333;
    word-break: break-all;
    max-height: 80px;
    line-height: 19px;
    overflow: hidden;
    margin: 0 auto;
    color: #ff9f00;
  }

  .a{
    text-decoration: none;
    color: #333333;
  }
  .a:hover{
    color: #8acfd1;
  }
</style>
