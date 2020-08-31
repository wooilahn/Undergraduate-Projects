<template>
  <div id="app">

    <h1> CONTACT </h1>

    <label> SEARCH: <input type="text" v-model="search" /> </label>

    <form v-on:submit.prevent="onSubmit">
        <label>NAME: <input type="text" name="name" id="name" v-model="name"/> </label>
        <label>PHONE NUMBER: <input type="text" name="phoneNum" id="phoneNum" v-model="phoneNum"/> </label>
        <input type="submit" value="ADD">
    </form>

      <div v-for="contact in filtered_contacts()" :key="contact.id">
        <contact v-bind:name="contact.name" v-bind:tel="contact.tel"></contact>
    </div>

  </div>
</template>

<script>
import contact from './components/contact.vue'

var contact_id = 0;
const contacts = [];

export default {

  name: 'app',

  components: {
    contact
  },

  data() {
    return {
            search : '',
            contacts: contacts,
            id: contact_id
    }
  },

  methods: {
      onSubmit: function () {
          const Info = {
              id: contact_id++,
              name: this.name,
              tel: this.phoneNum,
          };

          contacts.push(Info);

          this.name = "";
          this.phoneNum = "";
      },

      filtered_contacts: function () {

          let filteredList = this.contacts.filter(contact => {
              return (contact.name.toUpperCase().includes(this.search.toUpperCase())
                  ||   contact.tel.toUpperCase().includes(this.search.toUpperCase()))
          });

          return filteredList;
      }
  }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1 {
    margin-bottom : 30px;
}

input {
  margin-bottom : 10px;
  margin-right : 10px;
}

</style>
