<template>
  <BookTopicDetail :is-edit="true"></BookTopicDetail>
</template>

<script>
  import BookTopicDetail from "./component/BookTopicDetail";
    export default {
        name: "UpdateBookTopic",
        components: {BookTopicDetail}
    }
</script>

<style scoped>

</style>
