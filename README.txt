Version 0.2:

Added by Ricardo J. Mendez (ricardo@arges-systems.com)

- Tagging with an user identifier: book.addTag("java", 'user1')
- Getting for all tags:  book.getCountForAllTags()
- Getting count of all tags for an user:  book.getCountForAllTags('user1')
- Getting count a tag for an user:  book.getCountForTag('java', 'user1')
- Listing objects of the current class that have a tag:  book.listByTagName('java'), book.listByTagName('java', 'user1')

While these methods have been declared static for some reason I'm able to access them only via domain instances.  See http://www.nabble.com/Metaclass-and-domain-classes-to18189095.html for more details.  Do let me know if you have a solution.
