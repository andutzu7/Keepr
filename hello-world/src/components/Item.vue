<template>
	<div class = "item_container">
		<div class="tc-note" >
			<div class="tc-note-header">
				<span @click="deleteNote" class="tc-note-close">
					X
				</span>
			</div>
			<div class="tc-note-title" contenteditable="" @blur="titleChange">
				{{note.title}}
			</div>
			<div class="tc-note-body" contenteditable="" @blur="bodyChange">
				{{note.body}}
			</div>
		</div>
		<div>

		</div>
	</div>

</template>

<script>
export default{
	name:"NoteItem",
	props:{
		note:{
			type:Object,
			required:true
		},
		index:{
			required:true
		}
	},
	methods:{
		deleteNote(){
			this.$emit('deleteNote',this.note)
		},
		titleChange($event){
			let change = {"index":this.index,
				"key":"title",
				"newValue":$event.target.innerText}
			this.$emit('noteUpdate',change)
		},
		bodyChange($event){
			let change = {"index":this.index,
				"key":"body",
				"newValue":$event.target.innerText}
			this.$emit('noteUpdate',change)
		},

	},
	data:function(){
		return {
			fields:{},
			edit:false
		};
	}
}
</script>

<style lang="scss" scoped>

.tc-note {
	background-color: #f0c806;
	border-radius: 8px;
	width: 280px;
	margin: 0 10px 20px;
	box-shadow: 1px 3px 5px rgba(0, 0, 0, 0.2);
	transition: all 0.5s;
	cursor: pointer;
	font-family: 'Caveat', cursive;

	.tc-note-header {
		padding: 10px 16px 0;

		.tc-note-close {
			display: inline-block;
			width: 24px;
			height: 24px;
			border-radius: 50%;
			line-height: 24px;
			text-align: center;
			transition: all 0.3s;

			&:hover {
				background-color: rgba(0, 0, 0, 0.2);
			}

			&:focus {
				box-shadow: inset 2px 3px 0px rgba(0, 0, 0, 0.8);
			}
		}

		.tc-note-close {
			float: right;
		}
	}

	.tc-note-title,
	.tc-note-body {
		outline: 0;
	}

	.tc-note-title {
		font-size: 24px;
		padding: 10px 16px;
		font-weight: bold;
	}

	.tc-note-body {
		font-size: 20px;
		padding: 10px 16px 16px;
	}

	&:hover {
		box-shadow: 2px 4px 10px rgba(0, 0, 0, 0.3);
	}
}

</style>
