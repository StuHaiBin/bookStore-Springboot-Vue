<template>
  <div>
    <el-switch v-model="showPublish"
               :active-value="1"
               :inactive-value="0"
               @change="changeStatus($event)"
    ></el-switch>
    <el-switch v-model="show"
               @change="change($event)"
    ></el-switch>
  </div>
</template>

<script>
    export default {
        name: "SwitchTest",
        data(){
            return{
                showPublish: 1,
                show: true
            }
        },
        methods:{
            changeStatus(e){
                console.log("哈哈哈哈")
            },
            change(e){
                console.log("改变了")
            }
        }
    }
</script>

<style scoped>

</style>
