<template>
    <div>
        <h1>Hello, Welcome to the content management system.</h1>
    </div>
</template>

<script>
    export default {
        name: "welcome",
        mounted: function() {
            this.$parent.activeSidebar("welcome-sidebar");
        },
        methods: {
        }
    }
</script>