import httpClient from "./http.service";

const notesService = {
	create(note) {
		return httpClient.post('notes', note)
	},
	get() {
		return httpClient.get('items')
	},
	update(note) {
		return httpClient.put(`notes/${note.id}`, note)
	},
	delete(noteId) {
		return httpClient.delete(`notes/${noteId}`)
	}
};

export default notesService;
