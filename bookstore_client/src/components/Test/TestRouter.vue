<template>
  <div>
    <p>传过来的数据是：</p>{{id}}
  </div>
</template>

<script>
    export default {
        name: "TestRouter",
        data(){
            return{
                id: null
            }
        },
        created() {
            this.id=this.$route.params.id;
        }
    }
</script>

<style scoped>

</style>
