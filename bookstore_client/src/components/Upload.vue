<template>
    <div class="content">
      <el-upload
        action="http://localhost:8082/batch"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-success="handleSuccess"
        :on-remove="handleRemove">
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Upload",
        data() {
            return {
                dialogImageUrl: '',
                dialogVisible: false
            };
        },
        methods: {
            handleRemove(file, fileList) {
                console.log("文件被移动");
                console.log(file.name);
                console.log(file.response);
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleSuccess(response, file, fileList){
                console.log(response);
            }
        }
    }
</script>

<style scoped>
  .content{
    background-color: #ffffff;
  }
</style>
