<template>
  <div class="page-cxhd-list">
    <div class="cxhd-wrapper">
      <router-link :to="{ name: 'CXHDDetail'}">
        <img src="../../../assets/images/cxhd.png"/>
      </router-link>
    </div>

    <div class="cxhd-wrapper">
      <router-link :to="{ name: 'CXHDDetail'}">
        <img src="../../../assets/images/cxhd.png"/>
      </router-link>
    </div>

    <div class="cxhd-wrapper">
      <router-link :to="{ name: 'CXHDDetail'}">
        <img src="../../../assets/images/cxhd.png"/>
      </router-link>
    </div>
  </div>
</template>

<script>
  export default {
    methods: {
    }
  };
</script>

<style scoped>
  .cxhd-wrapper {
    padding: 5px 12px;
  }

  .cxhd-wrapper img {
    width: 100%;
  }

</style>
