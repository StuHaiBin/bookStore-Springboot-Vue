<template>
  <div class="content">
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="box">
      <p>秒杀会场</p>
    </div>
    <div class="labelBox">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane  v-for="(spike,index) in spikeList" :key="index" :label="spike.spikeName+'/'+spike.startTime" :name="index">
          <div class="box_book">
            <div class="book_item" v-for="book in spike.bookList" :key="book.id">
              <div class="book_img">
                <el-image
                  style="width: 115px; height: 160px;vertical-align: middle;"
                  :src="imgS"
                  fit="fill"></el-image>
              </div>
              <div class="info_box">
                <div class="book_name">{{book.name}}</div>
                <div class="book_author">{{book.author}}</div>
                <div class="book_recommend">￥{{book.price}}</div>
              </div>
            </div>
          </div>
        </el-tab-pane>
<!--        <el-tab-pane label="22:00即将开始" name="second">22:00即将开始</el-tab-pane>-->
<!--        <el-tab-pane label="22:00即将开始" name="third">22:00即将开始</el-tab-pane>-->
<!--        <el-tab-pane label="22:00即将开始" name="fourth">22:00即将开始</el-tab-pane>-->
      </el-tabs>
    </div>

    <Footer></Footer>
  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import HeadNav from "../../components/Common/HeadNav";
    import Footer from "../../components/Common/Footer";
    export default {
        name: "Spike",
        components:{Nav,HeadNav,Footer},
        data(){
            return{
                activeName: 0,
                imgS: require('../../assets/image/bookdefault.jpg'),
                bgImg: 'static/image/read.jpg',
                book_name:'福尔摩斯探案全集（上中下）',
                book_author:'[英]阿·柯南道尔  著；丁钟华  译/群众出版社/1981-08/平装',
                book_recommend:'离奇的情节，扣人的悬念，世界上最聪明的侦探，人间最诡秘的案情，福尔摩斯不但让罪犯无处藏身，也让你的脑细胞热情激荡。',
                spikeList: [
                    {
                        spikeName: "秋季秒杀",
                        startTime: "22:00即将开始",
                        bookList: [
                            {
                                id: 1,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 12,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 13,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 14,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 215,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 315,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 2125,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 3135,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 2215,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 3215,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 21225,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 31325,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            }
                        ]
                    },
                    {
                        spikeName: "秋季秒杀",
                        startTime: "22:00即将开始",
                        bookList: [
                            {
                                id: 1,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 12,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 13,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 14,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 15,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 19,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 215,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 315,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            }
                        ]
                    },
                    {
                        spikeName: "秋季秒杀",
                        startTime: "22:00即将开始",
                        bookList: [
                            {
                                id: 12,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 13,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 14,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 215,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 315,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 2125,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 3135,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            }
                        ]
                    },
                    {
                        spikeName: "秋季秒杀",
                        startTime: "22:00即将开始",
                        bookList: [
                            {
                                id: 1,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 12,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 13,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 14,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 15,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 16,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 17,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 215,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 315,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 2125,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            },
                            {
                                id: 3135,
                                name: "灰阑中的叙述（增订本）灰阑中的叙述（增订本）灰阑中的叙述（增订本）",
                                author: "黄子平 著",
                                price: 123.23
                            }
                        ]
                    }

                ]
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }

    }
</script>

<style scoped>
  .content{
    background-color: #f7f7f6;
  }
  .labelBox{
    margin: 20px auto;
    width: 1240px;
  }

  /deep/ .el-tabs__item {
    height: 60px;
    width: 25%;
    line-height: 60px;
    font-size: 22px;
    color: white;
    text-align: center;
  }

  /deep/ .is-active{
    background-color: #ff2e26;
  }

  /deep/ .el-tabs__nav{
    background-color: #414141;
    width: 100%;
    margin: 0px auto;
  }





  .box{
    margin: 20px auto;
    line-height: 70px;
    text-align: center;
    font-size: 30px;
    color: #9d232c;
  }
  .box_book{
    margin: 20px auto;
    width: 1240px;
  }
  .book_item{
    margin: 10px 1%;
    width: 31%;
    height: 180px;
    display: inline-block;
    border: 1px #ffffff solid;
    background-color: white;
  }
  .book_item:hover{
    border: 1px #d9d9d9 solid;
    box-shadow: 0px 0px 6px #9a9a9a;
  }

  .book_img{
    margin: 10px;
    float: left;
  }
  .info_box{
    margin: 10px;
    padding: 5px;
    float: right;
    width: 220px;
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
    max-height: 76px;
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
