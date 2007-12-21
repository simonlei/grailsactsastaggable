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
						Tag tag = new Tag( name:tagName)
						Tag.find
						// find it, if not save, save
            Tagging tagging = new Tagging( tag:tag, taggableId: delegate.id, taggableType: delegate.class.toString(), dateCreated:new Date()).save()
            // delegate.tags << tag
        }
				Taggable.metaClass.tags = { ->
				}
				Taggable.metaClass.removeTag = { tag ->
						// remove tagging
						// remove tag?

				}
        Taggable.metaClass.aaa = { ->
            "aaa"
        }
        println "=======================I am mixed======================="
				println Taggable.metaClass.methods
    }

    static loadTagsForObject( delegate) {
        def tags = Tagging.findByTaggableTypeAndTaggableId( delegate.class.toString(), delegate.id)
        tags.each() { tag ->
            println( tag)            
        }
    }

    static callMethodOfItSelf( delegate, name, args) {
        def metaMethod = Taggable.metaClass.getMetaMethod( name, args)
        if ( metaMethod) metaMethod.invoke( delegate, args)
    }

    def aaa() {
        Taggable taggable = (Taggable)this
        println "This   ............... + ${taggable}"
        "aaa"
    }
}
