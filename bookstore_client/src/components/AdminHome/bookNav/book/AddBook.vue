<template>
    <BookDetail :is-edit="false"></BookDetail>
</template>

<script>
  import BookDetail from "./components/BookDetail";
    export default {
        name: "AddBook",
        components: {BookDetail}
    }
</script>

<style scoped>

</style>
