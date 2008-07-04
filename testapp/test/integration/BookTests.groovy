class BookTests extends GroovyTestCase {

	void testAddOneTag() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java")
		assertEquals("java", book.tags)
	}

	void testAddMoreTags() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java")
		book.addTag("groovy")
		book.addTag("grails")

		assertEquals("java groovy grails", book.tags)
	}

	void testListTags() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java")
		book.addTag("groovy")
		book.addTag("grails")

		assertEquals("java groovy grails", book.tags)
	}


	void testAddTwoSameTags() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java")
		book.addTag("java")

		println book.tags
		Tagging.list().each {
			println it
			println it.properties
		}

		assertEquals("java", book.tags)
	}

	void testRemoveNotExistTag() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java")
		book.removeTag("notexist")
		assertEquals("java", book.tags)
	}

	void testRemoveOneTag() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java")
		book.addTag("groovy")
		book.addTag("grails")

		assertEquals("java groovy grails", book.tags)

		book.removeTag("groovy")
		assertEquals("java grails", book.tags)
	}

	void testSetTags() {
		Book book = new Book(name: "aa");
		book.save()

		book.tags = "java groovy grails"

		book.removeTag("groovy")
		assertEquals("java grails", book.tags)

		book.tags = "java java"
		assertEquals("java", book.tags)
	}

	void testListByTag() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java")
		book.addTag("groovy")
		book.addTag("grails")

		def book2 = new Book(name: "bb")
		assert book2.save()
		book2.addTag("grails")

		def book3 = new Book(name: "cc")
		assert book3.save()
		book3.addTag("java")
		book3.addTag("groovy")

		def list = book3.listByTagName("java")

		assertEquals list.size(), 2
		assert book3 in list
		assert book  in list

		list = book3.listByTagName("grails")
		assertEquals list.size(), 2
		assert book2 in list
		assert book  in list

		// Will likely fail because of http://jira.codehaus.org/browse/GRAILS-3070
		// No idea why this isn't working statically
		// list = Book.listByTagName("java")
	}
	
}
