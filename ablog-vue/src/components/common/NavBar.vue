<template>
  <div class="navbar" style="height: 80px;width: auto">
    <div>
<!--      <a href="javascript:;" @click="jumpPage">-->
<!--        <img src="../../../static/image/ablog.png" style="height: 100px;margin-right: 1000px"/>-->
<!--      </a>-->
      <a href="/index">
        <img src="../../../static/image/ablog.png" style="height: 100px;margin-right: 1000px"/>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NavBar',
  methods: {
    jumpPage () {
      this.$router.push({name: 'Index'})
    }
  }
}
</script>

<style scoped>
  .navbar{
    -moz-box-shadow:0px 10px 5px rgba(0,0,0,0.1);
    -webkit-box-shadow:0px 10px 5px rgba(0,0,0,0.1);
    box-shadow:0px 10px 5px rgba(0,0,0,0.1);
    border-radius:10px;
  }
</style>
