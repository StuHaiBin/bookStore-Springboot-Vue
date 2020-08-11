<!--测试vuex-->
<template>
    <div style="width: 100%;height: 400px;background-color: aqua">
      <h1>这里是info</h1>
      <h1>count的值为：{{count}}</h1>
      <h1>num的值为：{{num}}</h1>
      <button @click="addSync1">+1</button>
      <button @click="addSync2(10)">+n</button>
      <br>

    </div>
</template>

<script>
    import {mapState,mapMutations} from 'vuex'

    export default {
        name: "info",
        computed: {
            ...mapState(['count','num'])
        },
        methods:{
            // addNum(){
            //     this.$store.commit('addNum');
            // },
            // addN(n){
            //     this.$store.commit('addN',n);
            // },
            ...mapMutations(['addNum','addN']),
            // addNum1(){
            //     this.addNum();
            // },
            // addN1(n){
            //     this.addN(n);
            // }
            addSync1(){
                this.$store.dispatch('addSync');
            },
            addSync2(n){
                this.$store.dispatch('addSyncN',n);
            }
        }
    }
</script>

<style scoped>

</style>
