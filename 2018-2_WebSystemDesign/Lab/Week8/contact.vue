<template>
    <div id="contact">
        <h1> {{name}} </h1>
        <h2> {{tel}}  </h2>
    </div>
</template>

<script>
    export default {
        name: "contact",
        props: ['name', 'tel']
    }
</script>

<style scoped>

</style>