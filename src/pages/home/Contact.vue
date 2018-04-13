<template>
    <div class="page-contact">
      <iframe id="contact-iframe" frameborder=0 name="showHere" scrolling=auto src="http://chat10.live800.com/live800/chatClient/chatbox.jsp?companyID=935667&configID=234522&jid=4701964379"></iframe>
    </div>
</template>

<script>
    export default {
        name: "contact",
        data(){
          return{}
        },
        mounted(){
          const oIframe = document.getElementById('contact-iframe');
          const deviceWidth = document.documentElement.clientWidth;
          const deviceHeight = document.documentElement.clientHeight;
          oIframe.style.width = deviceWidth + 'px';
          oIframe.style.height = deviceHeight + 'px';
        },
    }
</script>

<style scoped>
</style>
