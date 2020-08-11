<template>
  <BookTopicDetail :is-edit="false"></BookTopicDetail>
</template>

<script>
  import BookTopicDetail from "./component/BookTopicDetail";
    export default {
        name: "AddBookTopic",
        components: {BookTopicDetail}
    }
</script>

<style scoped>

</style>
