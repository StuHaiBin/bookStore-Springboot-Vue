<template>
  <div class="content">
    <el-carousel :interval="5000" arrow="always" height="300px">
      <el-carousel-item v-for="item in imgList" :key="item.id">
        <router-link :to="{path: '/bookTopic',query:{id:item.id}}">
        <img v-bind:src="item.cover" alt="图片">
        </router-link>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
    export default {
        name: "Carousel",
        props:{
            imgList: {
                type: Array,
                default: null
            }
        },
        data(){
            return{
                imgS: ["static/image/20.jpg",
                    "static/image/21.jpg",
                    "static/image/22.jpg",
                    "static/image/23.jpg"]
            }
        },
        created() {
            // console.log("轮播图中this.imgList:"+this.imgList)
        }
    }
</script>

<style scoped>
  .content{
    width: 100%;
  }
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
  img{
    width: 100%;
    height: 100%;
    /*height: inherit;*/
  }
</style>
