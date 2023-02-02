// vue-bus数据总线机制，兄弟组件间通信

import Vue from 'vue'
// 使用 Event Bus，创建全局空的Vue实例：bus
const bus = new Vue()

export default bus