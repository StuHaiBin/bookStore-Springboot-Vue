<template>
    <div>
      <router-link :to="{name: 'TestRouter',params:{id:123}}">点击前往testRouter页面</router-link>
      <button @click="goTo">通过函数点击前往</button>
    </div>
</template>

<script>
    export default {
        name: "RouterTest",
        methods:{
            goTo(){
                this.$router.push({
                    name: 'TestRouter',
                    params: {
                        id: 321
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
