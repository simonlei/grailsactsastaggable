/**
 * User: SimonLei
 * Date: 2007-12-4
 * Time: 5:45:32
 */

class TaggableMixin {
	static mixin() {
		println "=======================I am mixing======================="
		println Taggable.metaClass.methods

		/*
		Taggable.metaClass.addTag = { String tagName ->
			return delegate.addTag(tagName, '')
		}

		Taggable.metaClass.addTag << {String tagName, String userId ->
			// Tag.find
			Tag tag = Tag.findByName(tagName)
			tag = tag ?: new Tag(name: tagName).save()
			// find tagging, if not save, save
			Tagging tagging = Tagging.findWhere(tag: tag, taggableId: delegate.id, taggableType: delegate.class.toString(), userId: userId)
			if (!tagging) tagging = new Tagging(tag: tag, taggableId: delegate.id, taggableType: delegate.class.toString(), dateCreated: new Date(), userId: userId).save()
			println "Sought:  ${tag} ${delegate.id} ${delegate.class.toString()}"
			println "Created: ${tagging.tag} ${tagging.taggableId} ${tagging.taggableType} ${tagging.id}"

			return tagging
		}
		*/
		Taggable.metaClass.addTag = { String tagName ->
			return delegate.addTag(tagName, '')
		}

		Taggable.metaClass.addTag << {String tagName, String userId ->
			// Tag.find
			Tag tag = Tag.findByName(tagName)
			tag = tag ?: new Tag(name: tagName).save()
			// find tagging, if not save, save
			Tagging tagging = Tagging.findWhere(tag: tag, taggableId: delegate.id, taggableType: delegate.class.toString(), userId: userId)
			tagging = tagging ?: new Tagging(tag: tag, taggableId: delegate.id, taggableType: delegate.class.toString(), dateCreated: new Date(), userId: userId).save()
		}



		Taggable.metaClass.getTags = {->
			def taggings = Tagging.findAllWhere(taggableId: delegate.id, taggableType: delegate.class.toString())
			taggings.join(" ")
		}

		Taggable.metaClass.setTags = {String tagsStr ->
			// should remove all tags first
			Tagging.executeUpdate("delete Tagging tagging where tagging.taggableId = ? and tagging.taggableType = ?", [delegate.id, delegate.class.toString()])
			tagsStr.split(" ").each {tagName ->
				addTag(tagName)
			}
		}

		Taggable.metaClass.removeTag = {String tagName ->
			// remove tagging
			Tag tag = Tag.findByName(tagName)
			// no such tag
			if (!tag) return
			Tagging tagging = Tagging.findWhere(tag: tag, taggableId: delegate.id, taggableType: delegate.class.toString())
			// no such tagging
			if (!tagging) return
			// remove tag
			tagging.delete()
		}

		Taggable.metaClass.'static'.listByTagName = { String tagName ->
			return delegate.listByTagName(tagName, '')
		}

		Taggable.metaClass.'static'.listByTagName = { String tagName, String userId ->
			Tag tag = Tag.findByName(tagName)
			// no such tag
			if (!tag) return []

			def taggingList = userId ? Tagging.findAllWhere(tag: tag, taggableType: delegate.class.toString(), userId: userId)  : Tagging.findAllWhere(tag: tag, taggableType: delegate.class.toString())

			// no such tagging
			if (!taggingList) return []
			// Initialize the objects
			def objList = taggingList.collect {
				String name = it.taggableType - "class "
				def objClass = Thread.currentThread().contextClassLoader.loadClass(name)
				objClass.get(it.taggableId)
			}
			return objList
		}

		Taggable.metaClass.'static'.getCountForAllTags = { ->
			return delegate.getCountForAllTags('')
		}

		Taggable.metaClass.'static'.getCountForAllTags = { userId ->
			return delegate.getCountForTag(null, userId)
		}

		Taggable.metaClass.'static'.getCountForTag = { String tagName, userId ->
			String query = "select count(t.tag), t.tag.name from Tagging t where t.taggableType = ?"
			def params = [delegate.class.toString()]
			if (userId) {
				query += " and t.userId = ?"
				params << userId
			}
			if (tagName) {
				query += " and t.tag.name = ?"
				params << tagName
			}
			query += "group by t.tag.name"

			def result = delegate.class.executeQuery(query, params)

			def map = [:]
			result.each {
				map[it[1]] = it[0]
			}
			return map
		}

		println "=======================I am mixed======================="
		println Taggable.metaClass.methods
	}
}
