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
			notes: [
				{
					title: 'qui est esse',
					body: 'est rerum tempore vitae<br>nsequi sint nihil reprehenderit dolor beatae ea dolores neque <br>fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis<br>qui aperiam non debitis possimus qui neque nisi nulla'
				},
				{
					title: 'qui est esse',
					body: 'est rerum tempore vitae<br>nsequi sint nihil reprehenderit dolor beatae ea dolores neque <br>fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis<br>qui aperiam non debitis possimus qui neque nisi nulla'
				},
			]
		}
	},
	methods:{
		addNewNote(){
			this.notes.unshift({title:'',body:''});
		},
		deleteNote(note){
			this.notes.splice(this.notes.indexOf(note),1);
		},
		noteUpdate(changes){
			let new_note = this.notes[changes["index"]];
			new_note[changes["key"]]=changes["newValue"];
			this.notes[changes["index"]]= new_note;
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
