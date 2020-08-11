<template>
  <SortDetail :is-edit="true" :isFirst="true"></SortDetail>
</template>

<script>
    import SortDetail from "./components/SortDetail";
    export default {
        name: "UpdateFirstSort",
        components: {SortDetail}
    }
</script>

<style scoped>

</style>
