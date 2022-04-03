<template>
	<div class="tc-notes-wrapper">

		<add-new-button @addNewNote="addNewNote"/>
			<div class="tc-notes">
				<note emits="" v-for="(note,index) in notes" :key="index" :index="index" :note="note" @deleteNote="deleteNote" @noteUpdate="noteUpdate"/>
			</div>


	</div>
</template>

<script>
import AddNewButton from "./AddNewItem.vue";
import Note from "./Item.vue";
import httpClient from "../services/httpService.js"
import axios from 'axios';
/*
	functional:function(){
		axios.get(default_routes["items"]).then((result)=>{
			this.items = result.data._embedded.items;
			console.log(result.data)

		})
	},
		handle_delete:function(index){
			let item_link = this.items[index]._links.self.href;
			axios.delete(item_link).then(()=>{
			this.items.splice(index,1)
		})
	},

	handle_post:function(){
		let fields = {"user_id": 1,"title": this.text}
		axios.post('http://localhost:8080/items',fields).then((result)=>{
			let newItem = result.data;
			this.items.push(newItem);
			this.text='';
		})
	}
},
	data: function(){
		return {
			items:[],
			text:'',
		}
	},*/
export default{
	name:"NoteItems",
	components:{Note,AddNewButton},
	data(){
		return {
			notes: []
		}
	},
	methods:{
		async addNewNote(){
			const {status,data} = await httpClient.post(('items'),{});
			if (status==200){
				this.notes.unshift(data);
			}

		},
		async deleteNote(note){
			// Fix because spring boot hides id's by default
			if(note.id)
			{
				const request = await httpClient.delete((`items/${note.id}`));
				if (request.status==200){
					// Really ugly Fix
					const {status,data} = await httpClient.get('items');

					if (status==200){
						this.notes = data._embedded.items;
						this.notes.reverse();
					}
				}
			}
			else{
				let index = this.notes.indexOf(note);
				let item_link = this.notes[index]._links.self.href;
				const response = await axios.delete(item_link);
				if(response.status===200){
					this.notes.splice(index,1)
				}
			}

		},
		noteUpdate(changes){
			let new_note = this.notes[changes["index"]];
			new_note[changes["key"]]=changes["newValue"];
			this.notes[changes["index"]]= new_note;
		}
	},
	async mounted(){

		const {status,data} = await httpClient.get('items');
		if (status==200){
			this.notes = data._embedded.items;
			this.notes.reverse();
		}
	}
}
</script>

<style lang="scss" scoped>


.tc-notes-wrapper {

	.tc-notes {
		display: flex;
		justify-content: center;
		flex-wrap: wrap;
		margin: 0 auto;
	}
}

</style>
