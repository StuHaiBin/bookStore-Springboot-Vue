<template>
  <BookSort :is-first="true"></BookSort>
</template>

<script>
  import BookSort from "./components/BookSort";
    export default {
        name: "FirstSortList",
        components: {BookSort}
    }
</script>

<style scoped>

</style>
