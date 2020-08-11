<template>
  <div class="headNav">
    <div class="content">
      <div style="float: left">
        <div style="width: 232px; height: 40px;vertical-align: middle;font-family: 宋体;font-size: 38px;font-style: italic">
          浮游书店
        </div>
<!--        <el-image-->
<!--          style="width: 232px; height: 40px;vertical-align: middle;"-->
<!--          :src="imgS"-->
<!--          fit="fill"></el-image>-->
      </div>
      <div style="float: right">
        <el-input placeholder="书名、作者、出版社、ISBN" v-model="input" class="input-with-select" style="width: 500px;height: 40px;">
          <el-select v-model="select" slot="prepend" placeholder="请选择">
            <el-option label="书名" value="1"></el-option>
            <el-option label="作者" value="2"></el-option>
            <el-option label="出版社" value="3"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "HeadNav",
        data() {
            return {
                input: "",
                select: '',
                //第一种设置图片的方法
                // imgS: "static/image/logo-250.png"
                //第二种设置图片的方法
                imgS: require('../../assets/image/logo-250.png')
            }
        },
        computed: {
            //第三种方法
            getImgS(){
                let imgUrl = "logo-250.png";
                let imgSrc = require('../../assets/image/'+imgUrl);
                return imgSrc;
            }
        }
    }
</script>

<style scoped>

  .headNav{
    width: 100%;
    background-color: #f2f1ea;
    line-height: 100px;
    clear: both;
  }
  .content{
    margin: 0px auto;
    width: 1240px;
    height: 100px;
    background-color: #f2f1ea;
  }
  .el-select {
    width: 110px;
  }
</style>
