<template>
  <div>
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="first_content">
      <div class="content_main">
        <div style="width: 100%">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="首页" name="first">
                <div class="content_main_left">
                  <div class="tab">
                    <div v-if="single==0">
                      <div v-for="(item,index) in options" :key="index" class="tab_list" @mouseenter="enter(index)" @mouseleave="out(index)">
                        <div>
                          <router-link :to="{path: '/search',query:{id:item[0].value,name:item[0].label}}"><span style="color: black">{{item[0].label}}</span></router-link>
                            <span> | </span>
                          <router-link :to="{path: '/search',query:{id:item[1].value,name:item[1].label}}"><span style="color: black">{{item[1].label}}</span></router-link>
                          <transition name="fade">
                          <div class="tab_page" v-show="seen&&index==current" >
                            <div v-for="book in item">
                              <h3>
                                <router-link :to="{path: '/search',query:{id:book.value,name:book.label}}">>{{book.label}}</router-link>
                              </h3>
                              <div class="tab_page_list">
                                <el-breadcrumb separator="|">
                                  <!--                            <el-breadcrumb-item :to="{ path: '/' }" exact>首页</el-breadcrumb-item>-->
                                  <el-breadcrumb-item v-for="it in book.children" :to="{path: '/search',query:{id:it.value,name:getName(book.label,it.label)}}" :key="it.value">
                                    {{it.label}}
                                  </el-breadcrumb-item>
                                  <el-breadcrumb-item>
                                  </el-breadcrumb-item>
                                </el-breadcrumb>
                              </div>
                            </div>
                          </div>
                          </transition>
                        </div >
                      </div>
                    </div>
                    <div v-if="single==1">
                      <div v-for="(item,index) in options" class="tab_list" @mouseenter="enter(index)" @mouseleave="out(index)">
                        <div v-if="item.length<2">
<!--                          {{item[0].label}}-->
                          <router-link :to="{path: '/search',query:{id:item[0].value,name:item[0].label}}"><span style="color: black">{{item[0].label}}</span></router-link>
                          <transition name="fade">
                          <div class="tab_page" v-show="seen&&index==current">
                            <div v-for="book in item">
                              <h3>
                                <router-link :to="{path: '/search',query:{id:book.value,name:book.label}}">>{{book.label}}</router-link>
                              </h3>
                              <div class="tab_page_list">
                                <el-breadcrumb separator="|">
                                  <!--                            <el-breadcrumb-item :to="{ path: '/' }" exact>首页</el-breadcrumb-item>-->
                                  <el-breadcrumb-item v-for="it in book.children" :to="{path: '/search',query:{id:it.value,name:getName(book.label,it.label)}}" :key="it.value">
                                    {{it.label}}
                                  </el-breadcrumb-item>
                                  <el-breadcrumb-item>
                                  </el-breadcrumb-item>
                                </el-breadcrumb>
                              </div>
                            </div>
                          </div>
                          </transition>
                        </div>

                        <div v-if="item.length==2">
                          {{item[0].label}}<span> | </span>{{item[1].label}}
                          <transition name="fade">
                          <div class="tab_page" v-show="seen&&index==current">
                            <div v-for="book in item">
                              <h3>
                                <router-link :to="{path: '/search',query:{id:book.value,name:book.label}}">>{{book.label}}</router-link>
                              </h3>
                              <div class="tab_page_list">
                                <el-breadcrumb separator="|">
                                  <!--                            <el-breadcrumb-item :to="{ path: '/' }" exact>首页</el-breadcrumb-item>-->
                                  <el-breadcrumb-item v-for="it in book.children" :to="{path: '/search',query:{id:it.value,name:getName(book.label,it.label)}}" :key="it.value">
                                    {{it.label}}
                                  </el-breadcrumb-item>
                                  <el-breadcrumb-item>
                                  </el-breadcrumb-item>
                                </el-breadcrumb>
                              </div>
                            </div>
                          </div>
                          </transition>
                        </div>

                      </div>
                    </div>
                  </div>
                  <div class="communicate">
                    <i class="el-icon-phone"></i><span>书店客服中心</span><br>
                    <span>预约电话 010-8430 857</span>
                  </div>
                </div>
                <div class="content_main_right">
                  <Carousel :imgList="bookTopicList"></Carousel>
                  <div class="gallery-book_title">
                    <span>最新出版</span>
                  </div>
                  <GalleryBook></GalleryBook>
                  <SpikeBox></SpikeBox>
                </div>
            </el-tab-pane>
            <el-tab-pane label="新书区" name="second">新书区</el-tab-pane>
            <el-tab-pane label="书单区" name="third">书单区</el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
    <div class="second_content">
      <div class="book_box">
        <RecBookBox :list-sort="recommend"></RecBookBox>
      </div>
    </div>
    <div class="first_content">
      <div class="book_box">
        <RecBookBox :list-sort="newProduct"></RecBookBox>
      </div>
    </div>
    <div class="second_content">
      <div class="book_box">
        <BookBox></BookBox>
      </div>
    </div>
    <Footer></Footer>

  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import Footer from "../../components/Common/Footer";
    import Carousel from "../../components/Index/Carousel";
    import HeadNav from "../../components/Common/HeadNav";
    import GalleryBook from "../../components/Index/GalleryBook";
    import SpikeBox from "../../components/Index/SpikeBox";
    import BookBox from "../../components/Index/BookBox";
    import RecBookBox from "../../components/Index/RecBookBox";
    import {reqGetSortList} from "../../api/sort";
    import {reqGetTopicList} from "../../api/bookTopic";

    export default {
        name: "index",
        components: {Nav,Footer,Carousel,HeadNav,GalleryBook,SpikeBox,BookBox,RecBookBox},
        data() {
            return {
                recommend:"recommend",
                newProduct:"newProduct",
                bookTopicList: [],

                single: 0,
                current: 0,//复制成功提示显示或者隐藏
                seen: false, //显示和隐藏

                activeName: 'first',
                imgS: ["static/image/bookdefault.jpg",
                    "static/image/21.jpg",
                    "static/image/22.jpg",
                    "static/image/23.jpg"],
                bookSortList:['小说','文学','地理','教科书','生物','科学','地理','教科书','生物','教科书'],
                options: [],
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },

            enter(index){
                // console.log(this.seen);
                this.seen = true;
                this.current = index;
                // console.log("鼠标进入了！");
                // console.log("index:"+index)
            },
            out(index){
                this.seen = false;
                this.current = null;
            },

            //得到书单列表
            GetTopic(page,pageSize){
                this.loading=false;
                reqGetTopicList(page,pageSize).then(response=>{
                    if(response.code==200){
                        this.bookTopicList = [];
                        let list = response.bookTopicList;
                        for(let i=0;i<list.length;i++){
                            this.bookTopicList.push({cover:list[i].cover,id:list[i].id});
                        }
                    }
                }).catch(err=>{
                    console.log(err);
                })
            },

            getName(upperName,childName){
                return upperName+"/"+childName;
            },

            //得到并设置图书分类的联级选择器
            getSortList() {
                reqGetSortList().then(response => {
                    if(response.code==200){
                        console.log("list:"+response);
                        let list = response.sortResponseList;
                        this.options = [];
                        if(list.length%2==0){
                            for (let i = 0; i < list.length; i=i+2) {
                                let children = [];
                                if (list[i].children != null && list[i].children.length > 0) {
                                    for (let j = 0; j < list[i].children.length; j++) {
                                        children.push({label: list[i].children[j].sortName, value: list[i].children[j].id});
                                    }
                                }
                                // console.log(list[i]);

                                let children1 = [];
                                if (list[i+1].children != null && list[i+1].children.length > 0) {
                                    for (let j = 0; j < list[i+1].children.length; j++) {
                                        children1.push({label: list[i+1].children[j].sortName, value: list[i+1].children[j].id});
                                    }
                                }
                                // console.log(list[i+1]);
                                this.single=0
                                this.options.push([{label: list[i].upperSort.sortName, value: list[i].upperSort.id, seen: false, children: children},{label: list[i+1].upperSort.sortName, value: list[i+1].upperSort.id, seen: false, children: children1}])
                            }
                        }
                        else {
                            for (let i = 0; i < list.length-1; i=i+2) {
                                let children = [];
                                if (list[i].children != null && list[i].children.length > 0) {
                                    for (let j = 0; j < list[i].children.length; j++) {
                                        children.push({label: list[i].children[j].sortName, value: list[i].children[j].id});
                                    }
                                }
                                // console.log(list[i]);

                                let children1 = [];
                                if (list[i+1].children != null && list[i+1].children.length > 0) {
                                    for (let j = 0; j < list[i+1].children.length; j++) {
                                        children1.push({label: list[i+1].children[j].sortName, value: list[i+1].children[j].id});
                                    }
                                }
                                // console.log(list[i+1]);

                                this.options.push([{label: list[i].upperSort.sortName, value: list[i].upperSort.id, seen: false, children: children},{label: list[i+1].upperSort.sortName, value: list[i+1].upperSort.id, seen: false, children: children1}])
                            }
                            let children2 = [];
                            if (list[list.length-1].children != null && list[list.length-1].children.length > 0) {
                                for (let j = 0; j < list[list.length-1].children.length; j++) {
                                    children2.push({label: list[list.length-1].children[j].sortName, value: list[list.length-1].children[j].id});
                                }
                            }
                            this.options.push([{label: list[list.length-1].upperSort.sortName, value: list[list.length-1].upperSort.id, seen: false, children: children2}])
                            this.single=1;
                        }
                    }
                });
            },

        },
        computed:{
            optionsList(){
                const optionsList  = [];
                const a = this.options;
                for(let i=0;i<a.length;i=i+2){
                  let j=i+1;
                  optionsList.push([a[i],a[j]]);
                }
                return optionsList;
            }
        },
        mounted(){
            // this.getSortList();
            this.getSortList();
        },
        created() {
            this.GetTopic(1,5);
            // this.getSortList();
        },
    }
</script>

<style scoped>
  .first_content{
     width: 100%;
     background-color: #f7f7f6;
   }
  .content_main{
    margin: 0px auto;
    width: 1240px;
    height: 1450px;
    background-color: #f7f7f6;
  }
  .content_main_left{
    width: 17%;
    height: 100%;
    float: left;
  }
  .content_main_right{
    width: 82%;
    height: 100%;
    float: right;
  }
  .gallery-book_title{
    padding-top: 10px;
    width: 100%;
    height: 32px;
    margin-bottom: 25px;
    border-bottom: 1px solid #ccc;
    margin-top: 20px;
    padding-bottom: 34px;
    font-size: 22px;
  }
  .second_content{
    width: 100%;
    background-color: #ffffff;
  }
  .book_box{
    width: 1240px;
    height: 700px;
    margin: 0px auto;
  }
  .tab_page a{
    font-size: 19px;
    color: #000000;
    text-decoration: none;
  }

  /*/deep/ .el-breadcrumb__inner a, .el-breadcrumb__inner.is-link{*/
  /*  !*font-weight: 700;*!*/
  /*  !*text-decoration: none;*!*/
  /*  !*-webkit-transition: color .2s cubic-bezier(.645,.045,.355,1);*!*/
  /*  !*transition: color .2s cubic-bezier(.645,.045,.355,1);*!*/
  /*  color: #be1f89 !important;*/
  /*}*/

  /deep/ .el-breadcrumb__item .el-breadcrumb__inner{
    /*color: #393a42 !important;*/
    font-weight: 300;
  }

  .router-link-active {
    text-decoration: none;
  }
  .el-breadcrumb__item{
    line-height: 30px;
  }
  .tab{
    width: 100%;
    height: 420px;
  }
  .tab_list{
    padding-left: 5px;
    width: 100%;
    height: 40px;
    line-height: 40px;
    background-color: #ffffff;
    margin: 1px 0px;
    border: none !important;
  }
  .tab_list:hover{
    background-color: #f2f1ea;
  }
  .tab_page{
    /*display: none;*/
    padding: 25px 30px;
    width: 832px;
    position: absolute;
    left: 209px;
    top: 0;
    z-index: 24;
    height: 410px;
    border: 1px solid #e0e0e0;
    border-left: 0;
    background: #fff;
    -webkit-box-shadow: 0 2px 4px rgba(0,0,0,.18);
    box-shadow: 0 2px 4px rgba(0,0,0,.18);
  }
  .tab_page_list{
    margin-left: 15px;
    margin-right: 15px;
    margin-bottom: 20px;
    position: relative;
  }


  .communicate{
    clear: both;
    position: relative;
    width: 100%;
    height: 100px;
    border: 1px #acb4bc solid;
    padding: 2px 5px;
    line-height: 30px;
  }
  /deep/ .el-tabs__item {
    height: 60px;
    line-height: 60px;
    font-size: 20px;
  }


</style>
