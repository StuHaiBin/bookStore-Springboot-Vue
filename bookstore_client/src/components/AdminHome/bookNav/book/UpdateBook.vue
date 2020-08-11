<template>
  <BookDetail :is-edit="true"></BookDetail>
</template>

<script>
  import BookDetail from "./components/BookDetail";
    export default {
        name: "UpdateBook",
        components: {BookDetail}
    }
</script>

<style scoped>

</style>
