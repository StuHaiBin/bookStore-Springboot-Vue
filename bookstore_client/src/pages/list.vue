<template>
  <div>
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="grey_content">
      <div class="content_main">

      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
    import Nav from "../../components/Common/Nav";
    import HeadNav from "../../components/Common/HeadNav";
    import Footer from "../../components/Common/Footer";
    export default {
        name: "list",
        components:{Nav,HeadNav,Footer},
        data(){
            return{

            }
        }
    }
</script>

<style scoped>
  .grey_content{
    width: 100%;
    background-color: #f7f7f6;
  }
  .white_content{
    width: 100%;
    background-color: #ffffff;
  }
  .content_main{
    margin: 0px auto;
    width: 1240px;
    height: 950px;
    background-color: #f7f7f6;
  }
  a{
    text-decoration: none;
    color: #333333;
  }
  a:hover{
    color: #8acfd1;
  }


</style>
