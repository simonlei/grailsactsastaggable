class BookTests extends GroovyTestCase {

    void testAddOneTag() {
			Book book = new Book();
			book.save()

			book.addTag( "java")
			assertEquals( "java", book.tags)
    }

		void testAddMoreTags() {
			Book book = new Book();
			book.save()
			
			book.addTag( "java")
			book.addTag( "groovy")
			book.addTag( "grails")

			assertEquals( "java groovy grails", book.tags)
		}

		void testAddTwoSameTags() {
			Book book = new Book();
			book.save()
			
			book.addTag( "java")
			book.addTag( "java")

			assertEquals( "java", book.tags)
		}

		void testRemoveNotExistTag() {
			Book book = new Book();
			book.save()
			
			book.addTag( "java")
			book.removeTag( "notexist")
			assertEquals( "java", book.tags)
		}

		void testRemoveOneTag() {
			Book book = new Book();
			book.save()
			
			book.addTag( "java")
			book.addTag( "groovy")
			book.addTag( "grails")

			assertEquals( "java groovy grails", book.tags)

			book.removeTag( "groovy")
			assertEquals( "java grails", book.tags)
		}
	
		void testSetTags() {
			Book book = new Book();
			book.save()

			book.tags = "java groovy grails"
			
			book.removeTag( "groovy")
			assertEquals( "java grails", book.tags)
		}
}
