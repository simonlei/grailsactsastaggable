            
class BookController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max) params.max = 10
        [ bookList: Book.list( params ) ]
    }

    def show = {
        [ book : Book.get( params.id ) ]
    }

    def delete = {
        def book = Book.get( params.id )
        if(book) {
            book.delete()
            flash.message = "Book ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Book not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def book = Book.get( params.id )

        if(!book) {
            flash.message = "Book not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ book : book ]
        }
    }

    def update = {
        def book = Book.get( params.id )
        if(book) {
						println "Book's meta properties : ${Book.metaClass.properties}"
						println "The params is: ${params}"
						println "Book's properties : ${book.properties}"
            book.properties = params
            if(!book.hasErrors() && book.save()) {
                flash.message = "Book ${params.id} updated"
                redirect(action:show,id:book.id)
            }
            else {
                render(view:'edit',model:[book:book])
            }
        }
        else {
            flash.message = "Book not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def book = new Book()
        book.properties = params
        return ['book':book]
    }

    def save = {
				println "The params is: ${params}"
        def book = new Book(params)
				println "Book's properties : ${book.properties}"
        if(!book.hasErrors() && book.save()) {
						book.tags = params["tags"]
						println "Book's properties : ${book.properties}"
            flash.message = "Book ${book.id} created"
            redirect(action:show,id:book.id)
        }
        else {
            render(view:'create',model:[book:book])
        }
    }
}
