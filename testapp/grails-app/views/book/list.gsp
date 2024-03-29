  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Book List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Book</g:link></span>
        </div>
        <div class="body">
            <h1>Book List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        <g:sortableColumn property="name" title="Name" />
                   	        <g:sortableColumn property="tags" title="Tags" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bookList}" status="i" var="book">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${book.id}">${book.id?.encodeAsHTML()}</g:link></td>
														<td>${book.name?.encodeAsHTML()}</td>
														<td>${book.tags?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Book.count()}" />
            </div>
        </div>
    </body>
</html>
