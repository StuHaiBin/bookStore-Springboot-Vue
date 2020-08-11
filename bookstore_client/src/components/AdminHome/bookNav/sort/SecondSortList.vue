<template>
  <BookSort :is-first="false"></BookSort>
</template>

<script>
  import BookSort from "./components/BookSort";
    export default {
        name: "SecondSortList",
        components: {BookSort}
    }
</script>

<style scoped>

</style>
