<template>
    <div>
      <p>单文件上传</p>
      <form action="http://localhost:8082/upload" method="POST" enctype="multipart/form-data">
        文件：<input type="file" name="file"/>
        <input type="text" name="ISBN">
        <input type="submit"/>
      </form>
      <hr/>
      <p>文件下载</p>
      <a href="http://localhost:8082/download">下载文件</a>
      <hr/>
      <p>多文件上传</p>
      <form method="POST" enctype="multipart/form-data" action="http://localhost:8082/batch">
        <p>文件1：<input type="file" name="file"/></p>
        <p>文件2：<input type="file" name="file"/></p>
        <p><input type="submit" value="上传"/></p>
      </form>
    </div>
</template>

<script>
    export default {
        name: "Upload3"
    }
</script>

<style scoped>

</style>
