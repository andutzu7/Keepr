<template>
	<div class="home">
		<note-items />
	</div>
</template>

<script>
// @ is an alias to /src
import NoteItems from "../components/Items.vue"
export default {
	name: 'HomeView',
	components: {
		NoteItems
	}
}
</script>
