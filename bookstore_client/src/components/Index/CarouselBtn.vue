<template>
  <div class="content">
    <el-carousel :interval="5000" arrow="always" height="430px" indicator-position="outside">
      <el-carousel-item v-for="item in imgList" :key="item">
        <div style="padding: 5px 75px;height: 100%;width: 100%">
          <img v-bind:src="item" alt="图片">
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
    export default {
        name: "CarouselBtn",
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
            console.log("this.imgList:"+this.imgList);
        }
    }
</script>

<style scoped>
  .content{
    width: 100%;
  }

  img{
    width: 100%;
    height: inherit;
  }
</style>
