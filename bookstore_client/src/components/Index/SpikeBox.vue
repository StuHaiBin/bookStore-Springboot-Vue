<template>
  <div class="gallery-book">
    <div class="gallery-book_title">
      <span>今日秒杀</span><a href="#/spike">/进入秒杀会场</a>
    </div>
    <div class="gallery-book_list">
      <div class="gallery-book_card" v-for="item in books" :key="item.id">
        <el-image
          style="width: 82%; height: 190px;margin:5px 9%"
          :src="imgS[0]"
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
            <s>{{item.price}}</s>
            <span style="font-size:14px;color: #bf7f5f;">￥</span>
            {{item.price}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "SpikeBox",
        data(){
            return {
                imgS: ["static/image/light.jpg",
                    "static/image/21.jpg",
                    "static/image/22.jpg",
                    "static/image/23.jpg"],
                books: [
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
                    }
                ]
            };
        }
    }
</script>

<style scoped>
  .gallery-book_title{
    width: 100%;
    height: 22px;
    margin-bottom: 25px;
    border-bottom: 1px solid #ccc;
    margin-top: 20px;
    padding-bottom: 34px;
    font-size: 24px;
  }
  .gallery-book_list{
    width: 100%;
  }
  .gallery-book_card{
    width:21%;
    margin: 3px 2%;
    height: 290px;
    display: inline-block;
    /*border: 1px #788394 solid;*/
    border: 1px solid #e4e4e4;
    background-color: white;
  }
  .gallery-book_card:hover{
    border: 1px #d9d9d9 solid;
    box-shadow: 0px 0px 4px #9a9a9a;
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
  }
</style>
