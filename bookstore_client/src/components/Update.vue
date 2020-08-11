<template>
<div style="width: 100%;height: 300px;background-color: white">
  <h2>这是是更新</h2>
  <h1>count的值为：{{count}}</h1>
  <h1>num的值为：{{num}}</h1>
</div>
</template>

<script>
    import {mapState,mapMutations} from 'vuex'
    export default {
        name: "update",
        computed:{
            ...mapState(["count",'num']),
        },
        methods:{

        }

    }
</script>

<style scoped>

</style>
