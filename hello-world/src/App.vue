<template>
  <img alt="Vue logo" src="./assets/logo.png" />

  <div>
    <input v-model="text" />
    <button v-on:click="handle_post()">Insert item</button>
  </div>
  <div v-for="(item, index) in items" :key="index">
    {{ item.title }}

    <button v-on:click="handle_delete(index)">Delete me!?!</button>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: 'App',
  components: {
  },
  methods:{
	functional:function(){
		axios.get('http://localhost:8080/items').then((result)=>{
		this.items = result.data._embedded.items;
		console.log(result.data)

	})
	},
	handle_delete:function(index){
		let item_link = this.items[index]._links.self.href;
		axios.delete(item_link).then((result)=>{
			alert(JSON.stringify(result))
		})
	},

	handle_post:function(){
		alert(this.text)
	}
	},
  data: function(){
	return {
		items:[],
		text:''
		}
	},
  mounted: function(){
	this.functional();
}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
