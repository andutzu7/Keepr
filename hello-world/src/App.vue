<template>
	<img alt="Vue logo" src="./assets/logo.png" />

<!--	<div>
		<input v-model="text" />
		<button v-on:click="handle_post()">Insert item</button>
	</div>
	<div v-for="(item, index) in items" :key="index">
		{{ item.title }}
		<button v-on:click="handle_delete(index)">Delete me!?!</button>
	</div>

	!-->
	<div id="app">
	<notes/>
	</div>
	</template>

<script>
//import axios from "axios"
import Notes from './components/Items.vue';
/*
let default_routes={
	"items":"http://localhost:8080/items",
	"users":'http://localhost:8080/users',
	"attributes":'http://localhost:8080/attributes',
	"attribute_values":'http://localhost:8080/attributeValues',
	"item_attribute_values":'http://localhost:8080/itemAttributeValues'
};*/
export default {
	name: 'App',
	components: {Notes},
	/*
	mounted: function(){
		this.functional();
	}*/
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
