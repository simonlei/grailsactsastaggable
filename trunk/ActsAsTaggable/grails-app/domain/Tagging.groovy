class Tagging {
	Tag tag
	long taggableId
	String taggableType
	Date dateCreated
	// ID for the user who tagged the object
	String userId = ''


	String toString() {
		return tag.name
	}

	static constraints = {
		userId(blank:true)
	}
}
