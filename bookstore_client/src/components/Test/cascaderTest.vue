<template>
    <div>
      <div class="block">
        <span class="demonstration">默认 click 触发子菜单</span>
        <el-cascader
          v-model="value"
          :options="options"
          @change="handleChange"></el-cascader>
      </div>

    </div>
</template>

<script>
    export default {
        name: "cascaderTest",
        data() {
            return {
                value: [],
                options: [{
                    value: 'zhinan',
                    label: '指南',
                    children: [{
                        value: 'shejiyuanze',
                        label: '设计原则',
                    }, {
                        value: 'daohang',
                        label: '导航',
                    }]
                }, {
                    value: 'zujian',
                    label: '组件',
                    children: [{
                        value: 'basic',
                        label: 'Basic',
                    }, {
                        value: 'form',
                        label: 'Form',
                    }, {
                        value: 'data',
                        label: 'Data',
                    }, {
                        value: 'notice',
                        label: 'Notice',
                    }, {
                        value: 'navigation',
                        label: 'Navigation',
                    }, {
                        value: 'others',
                        label: 'Others',
                    }]
                }]
            };
        },
        methods: {
            handleChange(value) {
                console.log(value);
            }
        }
    }
</script>

<style scoped>

</style>
