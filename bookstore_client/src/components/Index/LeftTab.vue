<template>
  <div class="main">
    <div class="content">
      <div class="tab">
        <div v-for="book in books" class="tab_list">
          {{book}}
          <div class="tab_page">
            {{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}
            {{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}
            {{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}{{book}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import "../../../static/js/jquery-1.8.3"
    export default {
        name: "LeftTab",
        data() {
            return{
                books:['小说','文学','地理','教科书','生物','科学','地理','教科书','生物','教科书','生物']
                // books:['小说']
            }
        },
    }
  $(document).ready(function () {
      $(".tab_list").bind({
          mouseover:function () {
              $(this).children(".tab_page").css({
                  "display": "block"
              });
              $(this).css({"z-index":"9999","background-color": "#8acfd1"});
              // $(this).siblings().css("border-right","1px solid #8f2c21");
              console.log("jquery 起作用了")
          },
          mouseout:function () {
              $(this).css({"background-color": "#ffffff"});
              $(this).children(".tab_page").hide();
          }
      });
  });
</script>

<style scoped>
  .main{
    margin: 0px auto;
    width: 1240px;
    height: 600px;
    background-color: #ffffff;
    position: relative;
  }
  .content{
    margin: 0px auto;
    width: 1240px;
    height: 600px;
    background-color: #ffffff;
  }
  .tab{
    width: 200px;
    height: 500px;
    background-color: #00C1B3;
    float: left;
  }
  .tab_list{
    padding-left: 5px;
    width: 100%;
    height: 40px;
    line-height: 40px;
    background-color: #ffffff;
    margin: 1px 0px;
    /*position: relative;*/
  }
  .tab_page{
    display: none;
    width: 992px;
    position: absolute;
    left: 198px;
    top: 0;
    z-index: 24;
    height: 458px;
    border: 1px solid #e0e0e0;
    border-left: 0;
    background: #fff;
    -webkit-box-shadow: 0 2px 4px rgba(0,0,0,.18);
    box-shadow: 0 2px 4px rgba(0,0,0,.18);
  }
</style>
