<!--测试如何设置页面的高度为浏览器的高度-->
<template>
<div class="content" v-bind:style="{ color: activeColor, height: height + 'px' }">
    <p>哈哈哈哈哈</p>
</div>
</template>

<script>
    export default {
        name: "VuexTest",
        data() {
            return {
                activeColor: 'red',
                height: 0
            };
        },
        created: function () {
            this.height = window.innerHeight;
            console.log("hhh");
        },
        computed: {
            classObject: function () {
                this.hh = window.innerHeight;
                console.log("h="+this.hh);
                return {
                    'height': this.hh+"px",
                }
            }
        }

    }
</script>

<style scoped>
.content{
  background-color: #64ee5a;
}
  *{
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
  }
</style>
