class SiteUserTests extends GroovyTestCase {

	void testFindForUser() {
		
	}

	void testGetCount() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java", 'user1')
		book.addTag("groovy", 'user1')
		book.addTag("grails", 'user1')

		def book2 = new Book(name: "bb")
		assert book2.save()
		book2.addTag("grails", 'user1')
		book2.addTag("grails", 'user2')
		book2.addTag("hasB", 'user2')
		book2.addTag("javascript", 'user1')

		def book3 = new Book(name: "cc")
		assert book3.save()
		book3.addTag("java", 'user1')
		book3.addTag("groovy", 'user1')

		def list = book3.listByTagName("grails")
		assertEquals list.size(), 3 // There are three tagging items with 'grails' on it
		assert book2 in list
		assert book  in list

		list = book3.getCountForTag('grails', 'user1')
		assertEquals list['grails'], 2

		list = book3.getCountForAllTags('user1')
		assertEquals list.size(), 4
		assertEquals list['java'], 2
		assertEquals list['groovy'], 2
		assertEquals list['grails'], 2
		assertEquals list['javascript'], 1

		list = book3.getCountForAllTags('user2')
		assertEquals list.size(), 2
		assertEquals list['grails'], 1
		assertEquals list['hasB'], 1


		list = book3.getCountForAllTags()
		assertEquals list.size(), 5
		assertEquals list['java'], 2
		assertEquals list['groovy'], 2
		assertEquals list['grails'], 3
		assertEquals list['hasB'], 1
		assertEquals list['javascript'], 1
	}

	void testListForUser() {
		Book book = new Book(name: "aa");
		book.save()

		book.addTag("java", 'user1')
		book.addTag("groovy", 'user1')
		book.addTag("grails", 'user1')

		def book2 = new Book(name: "bb")
		assert book2.save()
		book2.addTag("grails", 'user1')
		book2.addTag("grails", 'user2')
		book2.addTag("hasB", 'user2')
		book2.addTag("javascript", 'user1')

		def book3 = new Book(name: "cc")
		assert book3.save()
		book3.addTag("java", 'user1')
		book3.addTag("groovy", 'user1')

		def list = book3.listByTagName("hasB")

		assertEquals list.size(), 1
		assert book2 in list

		list = book3.listByTagName('java', 'user1')
		assertEquals list.size(), 2
		assert book in list
		assert book3 in list
		assert !(book2 in list)
	}

}
