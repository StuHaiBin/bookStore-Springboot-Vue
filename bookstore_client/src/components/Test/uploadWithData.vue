<template>
  <el-form-item label="附件上传" prop="fysjtDesc">
    <el-upload
      class="upload-demo"
      ref="upload"
      action=""
      :data="getfileData()"
      list-type="picture-card"
      :auto-upload="false">
    <el-button slot="trigger" size="small" type="primary">选取附件</el-button>
    <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
    </el-upload>
  </el-form-item>
</template>
<script>
    export default {
        name: "uploadWithData",
        data() {
            return {
                fileData: {
                    fileId: '',
                    fileType: '',
                    fileDesc: '',
                    filePath: '',
                }
            }
        },
        methods: {
            getfileData() {
                return this.fileData;
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            }
        }
    }
</script>
<style lang="scss" scoped>
</style>

