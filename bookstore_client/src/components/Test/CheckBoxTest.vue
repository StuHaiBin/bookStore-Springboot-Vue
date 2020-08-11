<template>
  <div>
    <el-checkbox v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
    <el-checkbox v-for="(city,i) in cities" :label="city.name" :key="i" v-model="checkedCities" @change="handleCheckedCitiesChange">
      {{city.name}}
    </el-checkbox>
  </div>
</template>

<script>
    export default {
        name: "CheckBoxTest",
        data() {
            return {
                //全选
                checkAll: false,
                cities: [
                    {
                        "name":"高一",
                        "value":"928"
                    },
                    {
                        "name":"高二",
                        "value":"929"
                    },
                    {
                        "name":"高三",
                        "value":"930"
                    }
                ], //数据源
                checkedCities:[], //绑定默认选中
                isIndeterminate:false, //设置 indeterminate 状态，只负责样式控制
            }
        },
        methods: {
            handleCheckAllChange(val) {
                if(this.checkAll){
                    this.cities.forEach(item=>{
                        this.checkedCities.push(item.name)
                    })
                }else {
                    this.checkedCities = []
                }

            },
            handleCheckedCitiesChange(value) {
                console.log(this.checkedCities);
                if(this.checkedCities.length == this.cities.length){
                    this.checkAll=true
                }else{
                    this.checkAll=false
                }
            }
        }
    }
</script>

<style scoped>

</style>
