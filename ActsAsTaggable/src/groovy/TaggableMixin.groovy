/**
 * User: SimonLei
 * Date: 2007-12-4
 * Time: 5:45:32
 */

class TaggableMixin {
    static mixin() {
        println "=======================I am mixing======================="
				println Taggable.metaClass.methods
/*        Taggable.metaClass.onLoad = { ->
            loadTagsForObject( delegate)
            callMethodOfItSelf( delegate, 'onLoad')
        }
*/
        Taggable.metaClass.addTag = { String tagName ->
						// Tag.find
						Tag tag = Tag.findByName( tagName)
						tag = tag ?: new Tag( name:tagName).save()
						// find tagging, if not save, save
						Tagging tagging = Tagging.findWhere( tag:tag, taggableId: delegate.id, taggableType: delegate.class.toString())
						tagging = tagging ?: new Tagging( tag:tag, taggableId: delegate.id, taggableType: delegate.class.toString(), dateCreated:new Date()).save()
            // delegate.tags << tag
        }
				Taggable.metaClass.getTags = { ->
					def taggings = Tagging.findAllWhere( taggableId: delegate.id, taggableType: delegate.class.toString())
					taggings.join(" ")
				}
				Taggable.metaClass.setTags = { String tags ->
					tags.split(" ").each { tagName ->
						addTag( tagName)
					}
				}
				Taggable.metaClass.removeTag = { String tagName ->
						// remove tagging
						Tag tag = Tag.findByName( tagName)
						// no such tag
						if ( !tag) return
						Tagging tagging = Tagging.findWhere( tag:tag, taggableId: delegate.id, taggableType: delegate.class.toString())
						// no such tagging
						if ( !tagging) return
						// remove tag
						tagging.delete()
				}
        println "=======================I am mixed======================="
				println Taggable.metaClass.methods
    }
}
